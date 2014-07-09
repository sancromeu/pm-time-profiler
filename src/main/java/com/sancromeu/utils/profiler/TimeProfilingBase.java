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
 * Translate TimeProfiler requests into actual time tracking and logging. (bridge)
 *
 * @author sancromeu
 */
public class TimeProfilingBase implements TimeProfiler {

    /**
     * Indicate no label is desired in the event message
     * (it's better to use methods requiring no label argument instead)
     */
    public static final String NO_LABEL = null;

    private TrackEventLogger logger;
    private TimeTracker tracker;
    private MessageBuilder messageBuilder;
    private String trackingLabel;

    /**
     * TimeTracker from configuration.
     * MessageBuilder from configuration.
     * No label in event messages.
     */
    TimeProfilingBase(TrackEventLogger logger) {
        this(logger, NO_LABEL, ProfilingConfig.tracker, ProfilingConfig.messageBuilder);
    }

    /**
     * TimeTracker from configuration.
     * MessageBuilder from configuration.
     */
    TimeProfilingBase(TrackEventLogger logger, String trackingLabel) {
        this(logger, trackingLabel, ProfilingConfig.tracker, ProfilingConfig.messageBuilder);
    }

    /**
     * TimeTracker provided.
     * MessageBuilder from configuration.
     * No label in event messages.
     */
    TimeProfilingBase(TrackEventLogger logger, TimeTracker.Type trackerType) {
        this(logger, NO_LABEL, trackerType, ProfilingConfig.messageBuilder);
    }

    /**
     * TimeTracker provided.
     * MessageBuilder from configuration.
     */
    TimeProfilingBase(TrackEventLogger logger, String trackingLabel, TimeTracker.Type trackerType) {
        this(logger, trackingLabel, trackerType, ProfilingConfig.messageBuilder);
    }

    /**
     * TimeTracker from configuration.
     * MessageBuilder provided.
     * No label in event messages.
     */
    TimeProfilingBase(TrackEventLogger logger, MessageBuilder messageBuilder) {
        this(logger, NO_LABEL, ProfilingConfig.tracker, messageBuilder);
    }

    /**
     * TimeTracker from configuration.
     * MessageBuilder provided.
     */
    TimeProfilingBase(TrackEventLogger logger, String trackingLabel, MessageBuilder messageBuilder) {
        this(logger, trackingLabel, ProfilingConfig.tracker, messageBuilder);
    }

    /**
     * TimeTracker provided.
     * MessageBuilder provided.
     * No label in event messages.
     */
    TimeProfilingBase(TrackEventLogger logger, TimeTracker.Type trackerType, MessageBuilder messageBuilder) {
        this(logger, NO_LABEL, trackerType, messageBuilder);
    }

    /**
     * TimeTracker provided.
     * MessageBuilder provided.
     */
    TimeProfilingBase(TrackEventLogger logger, String trackingLabel, TimeTracker.Type trackerType, MessageBuilder messageBuilder) {
        this.logger = logger;
        this.trackingLabel = trackingLabel;
        tracker = trackerType.get();
        this.messageBuilder = messageBuilder;
        tracker.startTracking();
        if (ProfilingConfig.logStart) {
            logger.logEvent(messageBuilder.getStartMessage(trackingLabel, tracker.type()));
        }
    }

    /*
     * Log a checkpoint elapsed time event
     */
    @Override
    public void checkpoint() {
        logger.logEvent(messageBuilder
                .getCheckpointMessage(trackingLabel, tracker.getElapsedTime(), tracker.type())
        );
    }

    /*
     * Log a final elapsed time event
     */
    @Override
    public void end() {
        logger.logEvent(messageBuilder
                .getEndMessage(trackingLabel, tracker.getElapsedTime(), tracker.type())
        );
    }
}
