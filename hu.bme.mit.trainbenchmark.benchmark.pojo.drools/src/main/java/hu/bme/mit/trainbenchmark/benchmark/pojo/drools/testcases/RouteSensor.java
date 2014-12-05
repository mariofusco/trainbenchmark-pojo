package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.ResultListener;
import hu.bme.mit.trainbenchmark.pojo.Sensor;

import java.util.ArrayList;
import java.util.List;

public class RouteSensor extends DroolsTestCase {

    protected ResultListener<Sensor> listener;
    protected List<Sensor> invalids;

    @Override
    public String getName() {
        return "RouteSensor";
    }

    @Override
    protected int doCCheck() {
        if (query == null) {
            listener = new ResultListener<Sensor>("sensor");
            query = ksession.openLiveQuery("RouteSensor check", new Object[] {}, listener);
        } else {
            // activate lazy PHREAK evaluation
            ksession.fireAllRules();
        }
        invalids = new ArrayList<Sensor>(listener.getMatching());
        return invalids.size();
    }
}
