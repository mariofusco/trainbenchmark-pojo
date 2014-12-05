package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelSwitchSensorRepair;

public class SwitchSensorXForm extends SwitchSensor implements TestCaseWithModify {

    @Override
    public void modify() {
        modifyModelSwitchSensorRepair(graph, bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr), invalids);
    }
}
