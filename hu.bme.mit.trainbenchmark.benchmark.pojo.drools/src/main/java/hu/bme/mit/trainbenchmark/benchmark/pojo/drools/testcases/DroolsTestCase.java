package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.testcases;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.testcase.TestCase;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.pojo.Graph;
import hu.bme.mit.trainbenchmark.generator.pojo.PojoMarshaller;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.LiveQuery;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public abstract class DroolsTestCase implements TestCase {

    protected BenchmarkResult bmr;
    protected BenchmarkConfig bc;
    protected KieSession ksession;
    protected LiveQuery query;

    @Override
    public String getTool() {
        return "Drools";
    }

    protected void readKnowledgeBase() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kfs = kieServices.newKieFileSystem();
        String queryFile = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.pojo.drools/src/main/resources/queries/" + getName() + ".drl";
        kfs.write( "src/main/resources/KBase1/oneQuery.drl", kieServices.getResources().newFileSystemResource( queryFile ) );

        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
        kieBuilder.buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
        }

        KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        ksession = kContainer.newKieSession();
    }

    @Override
    public void init(BenchmarkConfig bc) throws IOException {
        bmr = new BenchmarkResult(getTool(), getName());
        bmr.setBenchmarkConfig(bc);
        bmr.setBenchmarkArtifact(Paths.get(bc.getBenchmarkArtifact()).getFileName().toString());

        this.bc = bc;
        if (bc.isBenchmarkMode()) {
            Util.freeCache(bc);
        }
    }

    @Override
    public void load() throws IOException {
        bmr.startStopper();

        query = null;
        readKnowledgeBase();

        File file = new File(bc.getBenchmarkArtifact());
        Graph graph = new PojoMarshaller().fromXML(file);

        for (Object object : graph) {
            ksession.insert(object);
        }

        graph.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // TODO
            }
        });

        bmr.setReadTime();
    }

    @Override
    public void measureMemory() {
        Util.runGC();
        bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }

    @Override
    public void destroy() {
        query.close();
    }

    @Override
    public BenchmarkResult getBMR() {
        return bmr;
    }

    @Override
    public void check() {
        bmr.startStopper();
        bmr.addInvalid(doCCheck());
        bmr.addCheckTime();
    }

    protected abstract int doCCheck();
}
