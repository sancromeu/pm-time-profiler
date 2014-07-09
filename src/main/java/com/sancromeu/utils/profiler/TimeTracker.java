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
 * Basic time tracking contract. A TimeTracker instance is the actual object that calculates elapsed time.
 * Implementations of ElapsedTimeTracker will define how this is done (granularity, caching, etc).
 *
 * @author sancromeu
 */
public interface TimeTracker {

    /**
     * Tell tracker to start timer
     */
    void startTracking();

    /**
     * Get time elapsed since tracker was started (#startTracking)
     * @return elapsed time
     */
    long getElapsedTime();

    /**
     * Get type
     * @return tracker type
     */
    Type type();


    /**
     * Type of time trackers available
     */
    public enum Type {
        /**
         * Millisecond time tracking (System.currentTimeMillis)
         */
        MILLIS ("ms") {
            @Override
            TimeTracker get() {
                return new TimeTracker() {
                    private long start;
                    @Override
                    public void startTracking() {
                        start = System.currentTimeMillis();
                    }
                    @Override
                    public long getElapsedTime() {
                        return System.currentTimeMillis() - start;
                    }
                    @Override
                    public Type type() {
                        return MILLIS;
                    }
                };
            }
        },
        /**
         * Nanosecond time tracking (System.nanoTime)
         */
        NANOS ("ns") {
            @Override
            TimeTracker get() {
                return new TimeTracker() {
                    private long start;
                    @Override
                    public void startTracking() {
                        start = System.nanoTime();
                    }
                    @Override
                    public long getElapsedTime() {
                        return System.nanoTime() - start;
                    }
                    @Override
                    public Type type() {
                        return NANOS;
                    }
                };
            }
        };

        private String units;
        Type(String units) {
            this.units = units;
        }

        /**
         * Provide time units based on tracker ('ms', 'ns')
         * @return time units string
         */
        public String getUnits() {
            return units;
        }

        abstract TimeTracker get();
    }
}
