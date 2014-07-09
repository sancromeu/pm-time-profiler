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
 * Collection of some MessageBuilder implementations
 *
 * @author sancromeu
 */
public enum MessageBuilders implements MessageBuilder {
    /**
     * Predefined message formats.
     * <ul>
     *     <li>start = "[myTrackingLabel] Tracking time"</li>
     *     <li>checkpoint = "[myTrackingLabel] So far: 1234ms"</li>
     *     <li>end = "[myTrackingLabel] Took: 2345ms"</li>
     * </ul>
     */
    SoFar_Took {
        @Override
        public String getStartMessage(String trackingLabel, TimeTracker.Type type) {
            return label(trackingLabel) + "Tracking time";
        }
        @Override
        public String getCheckpointMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + "So far: " + elapsed + type.getUnits();
        }
        @Override
        public String getEndMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + "Took: " + elapsed + type.getUnits();
        }
    },
    /**
     * Predefined message formats.
     * <ul>
     *     <li>start = "[myTrackingLabel] Tracking time",
     * </li><li>checkpoint = "[myTrackingLabel]  (1234ms)",
     * </li><li>end = "[myTrackingLabel] 2345ms"</li>
     * </ul>
     */
    Time_Units {
        @Override
        public String getStartMessage(String trackingLabel, TimeTracker.Type type) {
            return label(trackingLabel) + "Tracking time";
        }
        @Override
        public String getCheckpointMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + "(" + elapsed + type.getUnits() + ")";
        }
        @Override
        public String getEndMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + elapsed + type.getUnits();
        }
    },
    /**
     * Predefined message formats.
     * <ul>
     *     <li>start = "[myTrackingLabel] Tracking time",
     * </li><li>checkpoint = "[myTrackingLabel] 1234ms",
     * </li><li>end = "[myTrackingLabel] 2345ms"</li>
     * </ul>
     */
    Time_Units_Plain {
        @Override
        public String getStartMessage(String trackingLabel, TimeTracker.Type type) {
            return label(trackingLabel) + "Tracking time";
        }
        @Override
        public String getCheckpointMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + elapsed + type.getUnits();
        }
        @Override
        public String getEndMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + elapsed + type.getUnits();
        }
    },
    /**
     * Predefined message formats.
     * <ul>
     *     <li>start = "[myTrackingLabel] Tracking time",
     * </li><li>checkpoint = "[myTrackingLabel]  (1234)",
     * </li><li>end = "[myTrackingLabel] 2345"</li>
     * </ul>
     */
    Time {
        @Override
        public String getStartMessage(String trackingLabel, TimeTracker.Type type) {
            return label(trackingLabel) + "Tracking time";
        }
        @Override
        public String getCheckpointMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + "(" + elapsed + ")";
        }
        @Override
        public String getEndMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + elapsed;
        }
    },
    /**
     * Predefined message formats.
     * <ul>
     *     <li>start = "[myTrackingLabel] Tracking time",
     * </li><li>checkpoint = "[myTrackingLabel]  1234",
     * </li><li>end = "[myTrackingLabel] 2345"</li>
     * </ul>
     */
    Time_Plain {
        @Override
        public String getStartMessage(String trackingLabel, TimeTracker.Type type) {
            return label(trackingLabel) + "Tracking time";
        }
        @Override
        public String getCheckpointMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + elapsed;
        }
        @Override
        public String getEndMessage(String trackingLabel, long elapsed, TimeTracker.Type type) {
            return label(trackingLabel) + elapsed;
        }
    };

    private static String label(String label) {
        return label == null ? "" : "[" + label + "] ";
    }
}
