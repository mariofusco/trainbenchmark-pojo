package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.ResultListener;
import hu.bme.mit.trainbenchmark.pojo.Switch;

import java.util.ArrayList;
import java.util.List;

public class SwitchSensor extends DroolsTestCase {

    protected ResultListener<Switch> listener;
    protected List<Switch> invalids;

    @Override
    public String getName() {
        return "SwitchSensor";
    }

    @Override
    protected int doCCheck() {
        if (query == null) {
            listener = new ResultListener<Switch>("switch");
            query = ksession.openLiveQuery("SwitchSensor check", new Object[] {}, listener);
        } else {
            // activate lazy PHREAK evaluation
            ksession.fireAllRules();
        }

        invalids = new ArrayList<Switch>(listener.getMatching());
        return invalids.size();
    }
}
