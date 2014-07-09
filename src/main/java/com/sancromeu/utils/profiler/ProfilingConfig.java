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
 * PM Time Profiler global configurations. Individual time profilers support per-tracking
 * values too.
 * All values are initialized with defaults:
 * <ol>
 * <li>Message builder - {@link com.sancromeu.utils.profiler.MessageBuilders#Time_Units}</li>
 * <li>Time tracker type - {@link com.sancromeu.utils.profiler.TimeTracker.Type#MILLIS}</li>
 * <li>Log message upon start - true</li>
 * </ol>
 *
 * @author sancromeu
 */
public class ProfilingConfig {

    static TimeProfiler nil;
    static MessageBuilder messageBuilder;
    static TimeTracker.Type tracker;
    static boolean logStart;

    //defaults
    static {
        messageBuilder = MessageBuilders.Time_Units;
        tracker = TimeTracker.Type.MILLIS;
        nil = new ProfilerNil();
        logStart = true;
    }

    /**
     * Indicate if you want message logged when tracking starts. Default is true.
     * @param logStart default is true
     */
    public static void setLogStartMessage(boolean logStart) {
        ProfilingConfig.logStart = logStart;
    }

    /**
     * Set builder to use for time events. Use {@link com.sancromeu.utils.profiler.MessageBuilders}
     * or provide your own implementation. Make sure your implementation is constant (enum, singletong, etc)
     * to minimize tracking overhead.
     * @param messageBuilder dedault is {@link com.sancromeu.utils.profiler.MessageBuilders#Time_Units}
     */
    public static void setProfileMessageBuilder(MessageBuilder messageBuilder) {
        ProfilingConfig.messageBuilder = messageBuilder;
    }

    /**
     * Provide time tracker to use. Default is MILLIS.
     * @param type default is {@link com.sancromeu.utils.profiler.TimeTracker.Type#MILLIS}
     */
    public static void setTimeTrackerType(TimeTracker.Type type) {
        tracker = type;
    }
}