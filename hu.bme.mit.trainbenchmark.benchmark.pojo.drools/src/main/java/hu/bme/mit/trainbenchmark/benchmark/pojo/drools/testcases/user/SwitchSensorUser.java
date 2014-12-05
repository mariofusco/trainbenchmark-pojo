package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.user;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.SwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelSwitchSensor;

public class SwitchSensorUser extends SwitchSensor implements TestCaseWithModify {

    @Override
    public void modify() {
        modifyModelSwitchSensor(graph, bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr));
    }
}
