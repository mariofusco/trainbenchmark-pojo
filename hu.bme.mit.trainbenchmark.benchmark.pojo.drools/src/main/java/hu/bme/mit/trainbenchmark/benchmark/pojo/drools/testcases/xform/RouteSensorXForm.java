package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelRouteSensorRepair;

public class RouteSensorXForm extends RouteSensor implements TestCaseWithModify {

    @Override
    public void modify() {
        modifyModelRouteSensorRepair(bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr), invalids);
    }
}