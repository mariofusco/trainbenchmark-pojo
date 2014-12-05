package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelSignalNeighborRepair;

public class SignalNeighborXForm extends SignalNeighbor implements TestCaseWithModify {

    @Override
    public void modify() {
        modifyModelSignalNeighborRepair(bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr), invalids);
    }
}
