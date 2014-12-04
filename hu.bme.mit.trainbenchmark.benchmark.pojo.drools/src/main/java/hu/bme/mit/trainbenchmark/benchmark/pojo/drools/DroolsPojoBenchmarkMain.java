package hu.bme.mit.trainbenchmark.benchmark.pojo.drools;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class DroolsPojoBenchmarkMain {

    public static void main(String[] args) throws IOException, ParseException {
        DroolsPojoBenchmarkLogic benchmarkLogic = new DroolsPojoBenchmarkLogic(args);
        benchmarkLogic.runBenchmark();
    }
}
