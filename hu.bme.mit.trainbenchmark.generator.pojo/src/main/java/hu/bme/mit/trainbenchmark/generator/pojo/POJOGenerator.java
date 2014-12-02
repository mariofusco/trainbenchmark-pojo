package hu.bme.mit.trainbenchmark.generator.pojo;

import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.pojo.Signal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.cli.ParseException;

public class POJOGenerator extends Generator {

	private String modelPkg;
	private Map<String, Class<?>> modelClasses = new HashMap<String, Class<?>>();
	
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
		final String fileName = generatorConfig.getInstanceModelPath() + "/railway" + generatorConfig.getVariant() + generatorConfig.getSize() + ".concept";
	}

	@Override
	public void persistModel() throws IOException {
		throw new UnsupportedOperationException();
	}

	protected Resource addIndividual(final String name, final String type) throws IOException {
		throw new UnsupportedOperationException();
	}

	protected void addRelation(final String label, final Object source, final Object target) throws IOException {
		throw new UnsupportedOperationException();
	}

	protected void addDataRelation(final Object source, final String relationName, final Integer value)
			throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object createNode(final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {
		Object node = createModelInstance(type);

		for (final Entry<String, Object> attribute : attributes.entrySet()) {
			setAttribute(type, node, attribute.getKey(), attribute.getValue());
		}
		
		throw new UnsupportedOperationException();
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		addRelation(label, from, to);

		throw new UnsupportedOperationException();
	}

	@Override
	protected void setAttribute(String type, Object node, String key, Object value) throws IOException {
		try {
			findMethod(node.getClass(), toSetter(key)).invoke(node, normalizeValue(value));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
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
		int underscore = key.indexOf('_');
		if (underscore >= 0) {
			key = key.substring(underscore+1);
		}
		return "set" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
	}
	
	private Method findMethod(Class<?> clazz, String name) {
		for (Method m : clazz.getMethods()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		throw new RuntimeException("Unknown method " + name + " on class " + clazz);
	}

}
