package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.ResultListener;
import hu.bme.mit.trainbenchmark.pojo.Route;

import java.util.ArrayList;
import java.util.List;

public class SignalNeighbor extends DroolsTestCase {

    protected ResultListener<Route> listener;
    protected List<Route> invalids;

    @Override
    public String getName() {
        return "SignalNeighbor";
    }

    @Override
    protected int doCCheck() {
        if (query == null) {
            listener = new ResultListener<Route>("route");
            query = ksession.openLiveQuery("SignalNeighbor check", new Object[] {}, listener);
        } else {
            // activate lazy PHREAK evaluation
            ksession.fireAllRules();
        }
        invalids = new ArrayList<Route>(listener.getMatching());
        return invalids.size();
    }
}
