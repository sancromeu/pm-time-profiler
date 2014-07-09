package com.sancromeu.utils.profiler;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;


/**
 * Created by sancromeu on 6/28/14.
 */
public abstract class TestBaseProfilerXLogger {

    void assertNil(HashMap<Class<?>,Object> startCustomArguments, Class<?> profilerXLoggerClass) {
        for (Method m : getStartMethods(profilerXLoggerClass)) {
            try {
                assertNil((TimeProfiler) m.invoke(null, getArgs(startCustomArguments, m)));
            } catch (Exception e) {
                fail("Method not properly tested: " + m);
            }
        }
    }

    void assertProfilingBase(HashMap<Class<?>,Object> startCustomArguments, Class<?> profilerXLoggerClass) {
        for (Method m : getStartMethods(profilerXLoggerClass)) {
            try {
                assertTimeProfilingBase((TimeProfiler) m.invoke(null, getArgs(startCustomArguments, m)));
            } catch (Exception e) {
                fail("Method not properly tested: " + m);
            }
        }
    }

    //get all variations of start methods
    private List<Method> getStartMethods(Class<?> c) {
        List<Method> methods = new ArrayList<Method>();
        for (Method m : c.getMethods()) {
            if (m.getName().equals("start")) {
                methods.add(m);
            }
        }
        return methods;
    }

    private Object[] getArgs(HashMap<Class<?>,Object> startCustomArguments, Method m) {
        List<Object> args = new ArrayList<Object>();
        for (Class<?> param : m.getParameterTypes()) {
            args.add(startCustomArguments.get(param));
        }
        return args.toArray(new Object[args.size()]);
    }

    private void assertNil(TimeProfiler p) {
        assertTrue(p instanceof ProfilerNil);
        assertEquals(p, ProfilingConfig.nil);
    }

    private void assertTimeProfilingBase(TimeProfiler p) {
        assertTrue(p instanceof TimeProfilingBase);
    }
}
