package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.ResultListener;
import hu.bme.mit.trainbenchmark.pojo.Segment;

import java.util.ArrayList;
import java.util.List;

public class PosLength extends DroolsTestCase {

    protected ResultListener<Segment> listener;
    protected List<Segment> invalids;

    @Override
    public String getName() {
        return "PosLength";
    }

    @Override
    protected int doCCheck() {
        if (query == null) {
            listener = new ResultListener<Segment>("segment");
            query = ksession.openLiveQuery("PosLength check", new Object[] {}, listener);

        } else {
            // activate lazy PHREAK evaluation
            ksession.fireAllRules();
        }
        invalids = new ArrayList<Segment>(listener.getMatching());
        return invalids.size();
    }
}
