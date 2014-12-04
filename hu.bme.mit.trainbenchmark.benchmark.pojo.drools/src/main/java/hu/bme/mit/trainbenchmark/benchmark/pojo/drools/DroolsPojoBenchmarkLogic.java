package hu.bme.mit.trainbenchmark.benchmark.pojo.drools;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import org.apache.commons.cli.ParseException;

public class DroolsPojoBenchmarkLogic extends GenericBenchmarkLogic {

    public DroolsPojoBenchmarkLogic(final String[] args) throws ParseException {
        super(args);
        bc = new BenchmarkConfig(args);
    }

    @Override
    protected String getPackageName() {
        return "pojo.drools";
    }

}
