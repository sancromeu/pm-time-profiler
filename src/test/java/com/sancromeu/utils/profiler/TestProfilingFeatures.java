package com.sancromeu.utils.profiler;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;


/**
 * Created by sancromeu on 6/28/14.
 */
public class TestProfilingFeatures {

    @Test
    public void test_log_start_message_on_off() {
        //test default = on
        {
            MessageBuilder mockMS = mock(MessageBuilder.class);
            ProfilingConfig.setProfileMessageBuilder(mockMS);
            TrackEventLogger eLogger = mock(TrackEventLogger.class);

            TimeProfilingBase base = new TimeProfilingBase(eLogger, "label", TimeTracker.Type.MILLIS, mockMS);
            Mockito.verify(mockMS, times(1)).getStartMessage(anyString(), any(TimeTracker.Type.class));
        }
        //turn off
        {
            MessageBuilder mockMS = mock(MessageBuilder.class);
            ProfilingConfig.setLogStartMessage(false);
            TrackEventLogger eLogger = mock(TrackEventLogger.class);

            TimeProfilingBase base = new TimeProfilingBase(eLogger, "label", TimeTracker.Type.MILLIS, mockMS);
            Mockito.verify(mockMS, never()).getStartMessage(anyString(), any(TimeTracker.Type.class));
        }

        //reset for other tests
        ProfilingConfig.setLogStartMessage(true);
    }

}
