package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.user;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelSignalNeighbor;

public class SignalNeighborUser extends SignalNeighbor implements TestCaseWithModify {

    @Override
    public void modify() {
        modifyModelSignalNeighbor(graph, bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr));
    }
}
