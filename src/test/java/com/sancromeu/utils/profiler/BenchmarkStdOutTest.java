package com.sancromeu.utils.profiler;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Created by sancromeu on 6/28/14.
 */
public class BenchmarkStdOutTest {

    private static int times = 10000;

    @Test
    public void benchmark_stdout_millis() {
        PrintStream original = System.out;

        long usingStdOut = withstdOut_millis();
        long usingProfilerStdOut = withProfilerStdOut_millis();

        System.setOut(original);
        System.out.println("StdOut MILLIS: " + usingStdOut);
        System.out.println("Profile MILLIS: " + usingProfilerStdOut);
    }

    @Test
    public void benchmark_stdout_nanos() {
        PrintStream original = System.out;

        long usingStdOut = withstdOut_nanos();
        long usingProfilerStdOut = withProfilerStdOut_nanos();

        System.setOut(original);
        System.out.println("StdOut NANOS: " + usingStdOut);
        System.out.println("Profile NANOS: " + usingProfilerStdOut);
    }

    private long withstdOut_millis() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        {
            long overall_start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                mockUserMethodUsingStdOut_millis();
            }
            return System.currentTimeMillis() - overall_start;
        }
    }
    private void mockUserMethodUsingStdOut_millis() {
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        //code goes here
        System.out.println("Checkpoint: " + (System.currentTimeMillis()-start));
        //more code here
        long took = System.currentTimeMillis() - start;
        System.out.println("Took:" + took);
    }
    private long withstdOut_nanos() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        {
            long overall_start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                mockUserMethodUsingStdOut_nanos();
            }
            return System.currentTimeMillis() - overall_start;
        }
    }
    private void mockUserMethodUsingStdOut_nanos() {
        System.out.println("Starting...");
        long start = System.nanoTime();
        //code goes here
        System.out.println("Checkpoint: " + (System.nanoTime()-start));
        //more code here
        long took = System.nanoTime() - start;
        System.out.println("Took:" + took);
    }

    private long withProfilerStdOut_millis() {
        return withProfilerStdOut(TimeTracker.Type.MILLIS);
    }
    private long withProfilerStdOut_nanos() {
        return withProfilerStdOut(TimeTracker.Type.NANOS);
    }
    private long withProfilerStdOut(TimeTracker.Type type) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        {
            ProfilingConfig.setTimeTrackerType(type);
            long overall_start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                mockUserMethodUsingProfilerStdOut();
            }
            return System.currentTimeMillis() - overall_start;
        }
    }

    private void mockUserMethodUsingProfilerStdOut() {
        TimeProfiler profiler = ProfilerStdOut.start();
        //code goes here
        profiler.checkpoint();
        //more code here
        profiler.end();
    }
}