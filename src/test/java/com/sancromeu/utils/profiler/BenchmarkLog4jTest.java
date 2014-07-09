package com.sancromeu.utils.profiler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by sancromeu on 6/28/14.
 */
public class BenchmarkLog4jTest {

    private static int times = 10000;

    Logger logger;

    @Before
    public void setup() {
        logger = Logger.getLogger("myLog");
        logger.setLevel(Level.INFO);
    }

    @Test
    public void benchmark() {
        long jLogger_active = profileUsingJLogger_properLevel();
        long jLogger_partial = profileUsingJLogger_partiallyDisabledViaLevel();
        long jLogger_disabled = profileUsingJLogger_fullyDisabledViaLevel();

        long profiler_active = profileUsingProfilerJLogger_properLevel();
        long profiler_disabled = profileUsingProfilerJLogger_fullyDisabledViaLevel();

        System.out.println("Log4j (active/partial/disabled): " + jLogger_active+"/"+jLogger_partial+"/"+jLogger_disabled);
        System.out.println("Profiler (active/disabled): " + profiler_active+"/"+profiler_disabled);
    }


    private long profileUsingJLogger_properLevel() {
        long overall_start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            aProfiledMethodUsingJLogger_properLevel();
        }
        return System.currentTimeMillis() - overall_start;
    }
    private void aProfiledMethodUsingJLogger_properLevel() {
        logger.info("Starting...");
        long start = System.currentTimeMillis();
        //code goes here
        logger.info("Checkpoint: " + (System.currentTimeMillis()-start));
        //more code here
        long took = System.currentTimeMillis() - start;
        logger.info("Took:" + took);
    }

    private long profileUsingJLogger_partiallyDisabledViaLevel() {
        long overall_start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            aProfiledMethodUsingJLogger_partiallyDisabledViaLevel();
        }
        return System.currentTimeMillis() - overall_start;
    }
    private void aProfiledMethodUsingJLogger_partiallyDisabledViaLevel() {
        logger.info("Starting...");
        long start = System.currentTimeMillis();
        //code goes here
        if (logger.isEnabledFor(Level.DEBUG)) {
            logger.info("Checkpoint: " + (System.currentTimeMillis() - start));
        }
        //more code here
        if (logger.isEnabledFor(Level.DEBUG)) {
            long took = System.currentTimeMillis() - start;
            logger.info("Took:" + took);
        }
    }

    private long profileUsingJLogger_fullyDisabledViaLevel() {
        long overall_start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            aProfiledMethodUsingJLogger_fullyDisabledViaLevel();
        }
        return System.currentTimeMillis() - overall_start;
    }
    private void aProfiledMethodUsingJLogger_fullyDisabledViaLevel() {
        long start = 0; //need to declare regardless
        if (logger.isEnabledFor(Level.DEBUG)) {
            logger.info("Starting...");
            start = System.currentTimeMillis();
        }
        //code goes here
        if (logger.isEnabledFor(Level.DEBUG)) {
            logger.info("Checkpoint: " + (System.currentTimeMillis() - start));
        }
        //more code here
        if (logger.isEnabledFor(Level.DEBUG)) {
            long took = System.currentTimeMillis() - start;
            logger.info("Took:" + took);
        }
    }

    private long profileUsingProfilerJLogger_properLevel() {
        long overall_start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            aProfiledMethodUsingProfileJLogger_properLevel();
        }
        return System.currentTimeMillis() - overall_start;
    }
    private void aProfiledMethodUsingProfileJLogger_properLevel() {
        TimeProfiler profiler = ProfilerLog4j.start(logger, Level.INFO);
        //code goes here
        profiler.checkpoint();
        //more code here
        profiler.end();
    }

    private long profileUsingProfilerJLogger_fullyDisabledViaLevel() {
        long overall_start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            aProfiledMethodUsingProfileJLogger_fullyDisabledViaLevel();
        }
        return System.currentTimeMillis() - overall_start;
    }
    private void aProfiledMethodUsingProfileJLogger_fullyDisabledViaLevel() {
        TimeProfiler profiler = ProfilerLog4j.start(logger, Level.DEBUG);
        //code goes here
        profiler.checkpoint();
        //more code here
        profiler.end();
    }
}