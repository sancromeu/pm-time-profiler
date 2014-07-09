/*
 * Copyright 2014 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sancromeu.utils.profiler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Get a Log4j-based time profiler.
 *
 * @author sancromeu
 */
public final class ProfilerLog4j {

    /**
     * Create Log4j-based profiler using current configuration
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level) {
        return start(logger, level, TimeProfilingBase.NO_LABEL);
    }

    /**
     * Create Log4j-based profiler using current configuration. Prepend message with given label.
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level, String trackingLabel) {
        //only return active profiler if logging level is met
        if (logger.isEnabledFor(level)) {
            return new TimeProfilingBase(new EventLogger(logger, level), trackingLabel);
        }
        return ProfilingConfig.nil;
    }

    /**
     * Create Log4j-based profiler using current configuration but use given tracker this once.
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level, TimeTracker.Type trackerType) {
        return start(logger, level, TimeProfilingBase.NO_LABEL, trackerType);
    }

    /**
     * Create Log4j-based profiler using current configuration but use given tracker this once.
     * Prepend message with given label.
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level, String trackingLabel, TimeTracker.Type trackerType) {
        //only return active profiler if logging level is met
        if (logger.isEnabledFor(level)) {
            return new TimeProfilingBase(new EventLogger(logger, level), trackingLabel, trackerType);
        }
        return ProfilingConfig.nil;
    }

    /**
     * Create Log4j-based profiler using current configuration but use given message builder this once.
     * Prepend message with given label.
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level, MessageBuilder messageBuilder) {
        return start(logger, level, TimeProfilingBase.NO_LABEL, messageBuilder);
    }

    /**
     * Create Log4j-based profiler using current configuration but use given message builder this once.
     * Prepend message with given label.
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level, String trackingLabel, MessageBuilder messageBuilder) {
        //only return active profiler if logging level is met
        if (logger.isEnabledFor(level)) {
            return new TimeProfilingBase(new EventLogger(logger, level), trackingLabel, messageBuilder);
        }
        return ProfilingConfig.nil;
    }

    /**
     * Create Log4j-based profiler using current configuration but use given tracker and message builder this once.
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level, TimeTracker.Type trackerType, MessageBuilder messageBuilder) {
        return start(logger, level, TimeProfilingBase.NO_LABEL, trackerType, messageBuilder);
    }

    /**
     * Create Log4j-based profiler using current configuration but use given tracker and message builder this once.
     * Prepend message with given label.
     * @param logger logger to log time events
     * @param level minimum logging level to track and log time
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static TimeProfiler start(Logger logger, Level level, String trackingLabel, TimeTracker.Type trackerType, MessageBuilder messageBuilder) {
        //only return active profiler if logging level is met
        if (logger.isEnabledFor(level)) {
            return new TimeProfilingBase(new EventLogger(logger, level), trackingLabel, trackerType, messageBuilder);
        }
        return ProfilingConfig.nil;
    }


    private static class EventLogger implements TrackEventLogger {
        private Logger logger;
        private Level level;

        private EventLogger(Logger logger, Level level) {
            this.logger = logger;
            this.level = level;
        }

        @Override
        public final void logEvent(String message) {
            logger.log(level, message);
        }
    }
}
