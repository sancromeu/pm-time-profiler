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
 * Time profiler that does nothing (key feature of PM Time Profiler, i.e. disable profiling via underlying logger)
 *
 * @author sancromeu
*/
final class ProfilerNil implements TimeProfiler {
    @Override
    public void checkpoint() {
        //do nothing
    }

    @Override
    public void end() {
        //do nothing
    }
}
