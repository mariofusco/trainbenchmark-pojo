package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCaseWithModify;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;

import static hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases.Modifications.modifyModelPosLengthRepair;

public class PosLengthXForm extends PosLength implements TestCaseWithModify {

    @Override
    public void modify() throws IOException {
        modifyModelPosLengthRepair(bmr, Util.calcModify(bc, bc.getModificationConstant(), bmr), invalids);
    }
}
