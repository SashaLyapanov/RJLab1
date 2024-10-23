package org.example;

import org.example.generators.CompetitionGenerator;
import org.openjdk.jmh.annotations.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class StartWork {
    private Action actionFork;
    private Action actionSequence;
    private Action actionParallel;

    private Action allTest;
    @Setup
    public void setup(){
//        actionFork = new Action(CompetitionGenerator.generate(80000, 500));
//        actionSequence = new Action(CompetitionGenerator.generate(35000, 500));
//        actionParallel = new Action(CompetitionGenerator.generate(40000, 500));
        allTest = new Action(CompetitionGenerator.generate(400000, 500));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void ForkPool() {
        Map forkPool = allTest.forkJoinPoolStreamLoop();
    }

//    @Benchmark
//    @OutputTimeUnit(TimeUnit.SECONDS)
//    public void ParallelStream() {
//        Map res2parallel = allTest.streamLoopParallel();
//    }
//
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void SequenceStream() {
        Map res2posled = allTest.streamLoopSequence();
    }
//    public StartWork(){}
}
