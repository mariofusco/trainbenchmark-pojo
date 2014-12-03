package hu.bme.mit.trainbenchmark.generator.pojo;

import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.pojo.Route;
import hu.bme.mit.trainbenchmark.pojo.Segment;
import hu.bme.mit.trainbenchmark.pojo.Sensor;
import hu.bme.mit.trainbenchmark.pojo.Signal;
import hu.bme.mit.trainbenchmark.pojo.Switch;
import hu.bme.mit.trainbenchmark.pojo.SwitchPosition;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.cli.ParseException;

public class POJOGenerator extends Generator {

	private String modelPkg;
	private Map<String, Class<?>> modelClasses = new HashMap<>();
	
	private Graph graph = new Graph();
	
	public POJOGenerator(final String args[]) throws ParseException {
		super();
		generatorConfig = new GeneratorConfig(args);
	}

	@Override
	protected String syntax() {
		return "POJO";
	}

	protected GeneratorConfig rdfGeneratorConfig;
	
	private Object createModelInstance(String type) {
		try {
			return getModelClass(type).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Class<?> getModelClass(String type) {
		Class<?> modelClass = modelClasses.get(type);
		if (modelClass == null) {
			try {
				modelClass = Class.forName(modelPkg + "." + type);
				modelClasses.put(type, modelClass);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return modelClass;
	}

	@Override
	public void initModel() throws IOException {
		modelPkg = Signal.class.getPackage().getName();
	}

	@Override
	public void persistModel() throws IOException {
		//checkXml();
		String fileName = generatorConfig.getInstanceModelPath() + "/railway" + generatorConfig.getVariant() + generatorConfig.getSize() + ".xml";
		PojoMarshaller marshaller = new PojoMarshaller();
		Writer writer = new FileWriter(fileName); 
		try {
			marshaller.toXML(writer, graph);
			writer.flush();
		} finally {
			writer.close();
		}
	}
	
	private void checkXml() {
		PojoMarshaller marshaller = new PojoMarshaller();
		String xml = marshaller.toXML(graph);
		Graph graphFromXml = marshaller.fromXML(xml);
		if (!graph.toString().equals(graphFromXml.toString())) {
			throw new RuntimeException("Wrong unmarshalling: different graphs!");
		}
	}

	@Override
	protected Object createNode(final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {
		Object node = createModelInstance(type);

		for (final Entry<String, Object> attribute : attributes.entrySet()) {
			setAttribute(type, node, attribute.getKey(), attribute.getValue());
		}
		
		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			createEdge(outgoingEdge.getKey(), node, outgoingEdge.getValue());
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			createEdge(incomingEdge.getKey(), incomingEdge.getValue(), node);
		}

		graph.add(node);
		return node;
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		if (to instanceof Long) {
			return;
		}
		
		setAttribute(null, from, label, to);
	}

	@Override
	protected void setAttribute(String type, Object node, String key, Object value) throws IOException {
		Method method = findMethod(node.getClass(), toSetter(key));
		if (method == null || Collection.class.isAssignableFrom(method.getParameterTypes()[0])) {
			method = findMethod(node.getClass(), toAdder(key));
		}
		if (method == null) {
			throw new RuntimeException("Unknown attribute " + key + " on class " + node.getClass().getName());
		}
		invokeMethod(method, node, value);
	}
	
	private void invokeMethod(Method method, Object node, Object value) {
		try {
			method.invoke(node, normalizeValue(value));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Error invoking " + method + " on " + node + " with " + value, e);
		}
	}

	private Object normalizeValue(Object value) {
		Class<?> valueClass = value.getClass();
		if (valueClass.isEnum()) {
			Class enumClass = getModelClass(valueClass.getSimpleName());
			return Enum.valueOf(enumClass, ((Enum)value).name());
		}
		return value;
	}
	
	private String toSetter(String key) {
		return toInvoker("set", key);
	}
	
	private String toAdder(String key) {
		return toInvoker("add", key);
	}
	
	private String toInvoker(String prefix, String key) {
		int underscore = key.indexOf('_');
		if (underscore >= 0) {
			key = key.substring(underscore+1);
		}
		return prefix + Character.toUpperCase(key.charAt(0)) + key.substring(1);
	}
	
	private Method findMethod(Class<?> clazz, String name) {
		for (Method m : clazz.getMethods()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
	
	public static class Graph {
		private Set<Segment> segments = new HashSet<>();
		private Set<Switch> switchs = new HashSet<>();
		private Set<SwitchPosition> switchPositions = new HashSet<>();
		private Set<Signal> signals = new HashSet<>();
		private Set<Route> routes = new HashSet<>();
		private Set<Sensor> sensors = new HashSet<>();
		
		public void add(Object object) {
			if (object instanceof Segment) {
				segments.add((Segment)object);
			} else if (object instanceof Switch) {
				switchs.add((Switch)object);
			} else if (object instanceof SwitchPosition) {
				switchPositions.add((SwitchPosition)object);
			} else if (object instanceof Signal) {
				signals.add((Signal)object);
			} else if (object instanceof Route) {
				routes.add((Route)object);
			} else if (object instanceof Sensor) {
				sensors.add((Sensor)object);
			} else {
				throw new RuntimeException("Unknown object type: " + object.getClass());
			}
		}
		
		public String toString() {
			return "segments: " + segments.size() +
					"; switchs: " + switchs.size() +
					"; switchPositions: " + switchPositions.size() +
					"; signals: " + signals.size() +
					"; routes: " + routes.size() +
					"; sensors: " + sensors.size();
		}
	}
}
