package com.sancromeu.utils.profiler;

import static org.junit.Assert.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;


/**
 * Created by sancromeu on 6/28/14.
 */
public class TestProfilerLog4j extends TestBaseProfilerXLogger {

    Logger logger;
    private HashMap<Class<?>,Object> startCustomArguments;

    @Before
    public void setup() {
        logger = Mockito.spy(Logger.getLogger(""));
        startCustomArguments = new HashMap<Class<?>, Object>();
        startCustomArguments.put(Logger.class, logger);
        startCustomArguments.put(Level.class, Level.INFO);
        startCustomArguments.put(String.class, "Method xyz");
        startCustomArguments.put(TimeTracker.Type.class, TimeTracker.Type.NANOS);
        startCustomArguments.put(MessageBuilder.class, MessageBuilders.Time_Units_Plain);
    }

    @Test
    public void test_tracking_is_disabled_for_lower_logging_level() {
        logger.setLevel(Level.INFO);
        startCustomArguments.put(Level.class, Level.DEBUG);
        assertNil(startCustomArguments, ProfilerLog4j.class);
    }

    @Test
    public void test_tracking_enabled_for_proper_logging_level() {
        logger.setLevel(Level.DEBUG);
        startCustomArguments.put(Level.class, Level.DEBUG);
        assertProfilingBase(startCustomArguments, ProfilerLog4j.class);
    }
}
