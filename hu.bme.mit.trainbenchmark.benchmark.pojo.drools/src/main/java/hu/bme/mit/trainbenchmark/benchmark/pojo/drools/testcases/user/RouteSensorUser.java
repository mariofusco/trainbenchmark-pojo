package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.user;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelRouteSensor;

public class RouteSensorUser extends RouteSensor implements TestCaseWithModify {

    @Override
    public void modify() {
        modifyModelRouteSensor(graph, bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr));
    }

}