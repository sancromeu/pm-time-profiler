package com.sancromeu.utils.profiler;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;


/**
 * Created by sancromeu on 6/28/14.
 */
public class BenchmarkJLoggerTest {

    private static int times = 10000;

    Logger logger;

    @Before
    public void setup() {
        logger = Logger.getLogger("myLog");
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
    }

    @Test
    public void benchmark() {
        long jLogger_active = profileUsingJLogger_properLevel();
        long jLogger_partial = profileUsingJLogger_partiallyDisabledViaLevel();
        long jLogger_disabled = profileUsingJLogger_fullyDisabledViaLevel();

        long profiler_active = profileUsingProfilerJLogger_properLevel();
        long profiler_disabled = profileUsingProfilerJLogger_fullyDisabledViaLevel();

        System.out.println("JLogger (active/partial/disabled): " + jLogger_active+"/"+jLogger_partial+"/"+jLogger_disabled);
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
        if (logger.isLoggable(Level.FINE)) {
            logger.info("Checkpoint: " + (System.currentTimeMillis() - start));
        }
        //more code here
        if (logger.isLoggable(Level.FINE)) {
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
        if (logger.isLoggable(Level.FINE)) {
            logger.info("Starting...");
            start = System.currentTimeMillis();
        }
        //code goes here
        if (logger.isLoggable(Level.FINE)) {
            logger.info("Checkpoint: " + (System.currentTimeMillis() - start));
        }
        //more code here
        if (logger.isLoggable(Level.FINE)) {
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
        TimeProfiler profiler = ProfilerJLogger.start(logger, Level.INFO);
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
        TimeProfiler profiler = ProfilerJLogger.start(logger, Level.FINE);
        //code goes here
        profiler.checkpoint();
        //more code here
        profiler.end();
    }
}