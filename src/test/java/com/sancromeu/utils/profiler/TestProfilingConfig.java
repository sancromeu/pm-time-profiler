package com.sancromeu.utils.profiler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


/**
 * Created by sancromeu on 6/28/14.
 */
public class TestProfilingConfig {

    @Test
    public void test_defaults_init() {
        TimeProfiler p = ProfilingConfig.nil;
        assertTrue(p instanceof ProfilerNil);
        assertTrue(ProfilingConfig.tracker.equals(TimeTracker.Type.MILLIS));
        assertTrue(ProfilingConfig.messageBuilder.equals(MessageBuilders.Time_Units));
        assertTrue(ProfilingConfig.logStart);
    }

    @Test
    public void test_change_log_start() {
        ProfilingConfig.setLogStartMessage(false);
        assertFalse(ProfilingConfig.logStart);
        ProfilingConfig.setLogStartMessage(true);
        assertTrue(ProfilingConfig.logStart);
    }

    @Test
    public void test_change_tracker_type() {
        ProfilingConfig.setTimeTrackerType(TimeTracker.Type.NANOS);
        assertEquals(TimeTracker.Type.NANOS, ProfilingConfig.tracker);
        ProfilingConfig.setTimeTrackerType(TimeTracker.Type.MILLIS);
        assertEquals(TimeTracker.Type.MILLIS, ProfilingConfig.tracker);
    }

    @Test
    public void test_change_message_builder() {
        ProfilingConfig.setProfileMessageBuilder(MessageBuilders.Time_Plain);
        assertEquals(MessageBuilders.Time_Plain, ProfilingConfig.messageBuilder);
        MessageBuilder mockMS = mock(MessageBuilder.class);
        ProfilingConfig.setProfileMessageBuilder(mockMS);
        assertEquals(mockMS, ProfilingConfig.messageBuilder);
    }

}
