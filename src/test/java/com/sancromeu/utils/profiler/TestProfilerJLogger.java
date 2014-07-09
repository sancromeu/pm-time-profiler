package com.sancromeu.utils.profiler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Created by sancromeu on 6/28/14.
 */
public class TestProfilerJLogger extends TestBaseProfilerXLogger {

    Logger logger;
    private HashMap<Class<?>,Object> startCustomArguments;

    @Before
    public void setup() {
        logger = Logger.getLogger("");
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
        startCustomArguments.put(Level.class, Level.FINE);
        assertNil(startCustomArguments, ProfilerJLogger.class);
    }

    @Test
    public void test_tracking_enabled_for_proper_logging_level() {
        logger.setLevel(Level.FINE);
        startCustomArguments.put(Level.class, Level.FINE);
        Level customLevel = Level.FINE;
        assertProfilingBase(startCustomArguments, ProfilerJLogger.class);
    }
}
