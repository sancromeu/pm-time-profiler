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
 * A class implementing MessageBuilder will build readable messages using the elapsed time provided.
 * Implement this interface in a way that can be reusable (single instance, thread-safe).
 *
 * @author sancromeu
 */
public interface MessageBuilder {

    /**
     * Generate message to log when the tracking starts
     * @return assembled message
     */
    String getStartMessage(String trackingLabel, TimeTracker.Type type);

    /**
     * Generate message to log for the {@link com.sancromeu.utils.profiler.TimeProfiler#checkpoint} event
     * @return assembled message
     */
    String getCheckpointMessage(String trackingLabel, long elapsed, TimeTracker.Type type);

    /**
     * Generate message to log for the {@link com.sancromeu.utils.profiler.TimeProfiler#end} event
     * @return assembled message
     */
    String getEndMessage(String trackingLabel, long elapsed, TimeTracker.Type type);
}
