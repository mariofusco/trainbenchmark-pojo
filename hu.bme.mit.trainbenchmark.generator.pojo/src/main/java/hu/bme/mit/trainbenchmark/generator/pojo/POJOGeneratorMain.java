package hu.bme.mit.trainbenchmark.generator.pojo;

import hu.bme.mit.trainbenchmark.generator.Generator;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class POJOGeneratorMain {

	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
		Generator generator = new POJOGenerator(args);
		generator.generateModels();
	}

}
