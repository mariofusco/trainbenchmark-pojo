package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.user;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelPosLength;

public class PosLengthUser extends PosLength implements TestCaseWithModify {

    @Override
    public void modify() {
        modifyModelPosLength(graph, bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr));
    }

}

