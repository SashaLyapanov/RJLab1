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
    @Setup
    public void setup(){
        actionFork = new Action(CompetitionGenerator.generate(60000, 500));
        actionSequence = new Action(CompetitionGenerator.generate(35000, 500));
        actionParallel = new Action(CompetitionGenerator.generate(40000, 500));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void ForkPool() {
        Map forkPool = actionFork.forkJoinPoolStreamLoop();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void parallelStream() {
        Map res2parallel = actionParallel.streamLoopParallel();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void sequenceStream() {
        Map res2posled = actionSequence.streamLoopSequence();
    }

    public StartWork(){}
}
