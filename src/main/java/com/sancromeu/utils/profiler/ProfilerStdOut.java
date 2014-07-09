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

/**
 * Get a System.out-based time profiler. Note that this profiler is never disabled by PM Time Profiler
 * as logger-based profilers are (key feature). Thus, while this profiler will get you going
 * quickly if you don't yet have logging set up, it is not recommended for production code. In fact, this
 * profiler is much slower that using System.out directly on your code (per benchmarks in tests).
 *
 * @author sancromeu
*/
public final class ProfilerStdOut {

    //reuse
    private static final TrackEventLogger stdOutLogger = new TrackEventLogger() {
        @Override
        public final void logEvent(String message) {
            System.out.println(message);
        }
    };

    /**
     * Create StdOut-based profiler using current configuration
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start() {
        return start(TimeProfilingBase.NO_LABEL);
    }

    /**
     * Create StdOut-based profiler using current configuration. Prepend message with given label.
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start(String trackingLabel) {
        return new TimeProfilingBase(stdOutLogger, trackingLabel);
    }

    /**
     * Create StdOut-based profiler using current configuration but use given tracker this once.
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start(TimeTracker.Type trackerType) {
        return start(TimeProfilingBase.NO_LABEL, trackerType);
    }

    /**
     * Create StdOut-based profiler using current configuration but use given tracker this once.
     * Prepend message with given label.
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start(String trackingLabel, TimeTracker.Type trackerType) {
        return new TimeProfilingBase(stdOutLogger, trackingLabel, trackerType);
    }

    /**
     * Create StdOut-based profiler using current configuration but use given message builder this once.
     * Prepend message with given label.
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start(MessageBuilder messageBuilder) {
        return start(TimeProfilingBase.NO_LABEL, messageBuilder);
    }

    /**
     * Create StdOut-based profiler using current configuration but use given message builder this once.
     * Prepend message with given label.
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start(String trackingLabel, MessageBuilder messageBuilder) {
        return new TimeProfilingBase(stdOutLogger, trackingLabel, messageBuilder);
    }

    /**
     * Create StdOut-based profiler using current configuration but use given tracker and message builder this once.
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start(TimeTracker.Type trackerType, MessageBuilder messageBuilder) {
        return new TimeProfilingBase(stdOutLogger, trackerType, messageBuilder);
    }

    /**
     * Create StdOut-based profiler using current configuration but use given tracker and message builder this once.
     * Prepend message with given label.
     * @param trackingLabel label to prepend to logged event ("processRequest: Took 123ms")
     * @param trackerType use this tracker instead of the globally configured one just this once
     * @param messageBuilder use this message builder this once (it is more efficient to configure a
     *                       {@link com.sancromeu.utils.profiler.MessageBuilder} globally, it is reused!)
     * @return Started {@link com.sancromeu.utils.profiler.TimeProfiler}
     */
    public static final TimeProfiler start(String trackingLabel, TimeTracker.Type trackerType, MessageBuilder messageBuilder) {
        return new TimeProfilingBase(stdOutLogger, trackingLabel, trackerType, messageBuilder);
    }
}
