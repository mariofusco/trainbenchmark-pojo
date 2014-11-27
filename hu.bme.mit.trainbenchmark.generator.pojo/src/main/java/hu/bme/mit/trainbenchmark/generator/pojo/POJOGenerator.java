package hu.bme.mit.trainbenchmark.generator.pojo;

import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.cli.ParseException;

public class POJOGenerator extends Generator {

	public POJOGenerator(final String args[]) throws ParseException {
		super();
		generatorConfig = new GeneratorConfig(args);
	}

	@Override
	protected String syntax() {
		return "POJO";
	}

	protected GeneratorConfig rdfGeneratorConfig;

	@Override
	public void initModel() throws IOException {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		addRelation(label, from, to);

		throw new UnsupportedOperationException();
	}

	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value)
			throws IOException {

		throw new UnsupportedOperationException();
	}

}
