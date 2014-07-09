# (Poor Man's) Time Profiler for Java

Use your favorite time profiling technique (wink) now with cleaner code and a simple way to fully enable or disable it.

Transform this:
```java
long start = System.currentTimeMillis();

// my code …

if (logger.isDebugEnabled()) {
  long elapsed = System.currentTimeMillis() - start;
  logger.debug("Took: " + elapsed);
}
```
into this:
```java
TimeProfiler profiler = ProfilerLog4j.start(logger, DEBUG);

// my code …

profiler.end();
```
*PM Time Profiler* hides the nuances so that your code stays on target.

There are several approaches to properly and accurately time-profiling methods in your Java applications like AOP, JMX or TPTP, and you should always favor those options for serious time profiling. *PM Time Profiler* is there for those cases where you just want to get the number without the fuss (wink).


## Features
* Completely enable/disable time profiling via the active logging framework (for logger-based profilers)
* Hide nuances of calculating elapsed time, and building and writing message
* Generate Start, Checkpoint and End messages
* Fully configured out of the box
* Global and per-method configurable (change what you want when you want):
 * Precision: Millis vs. Nanos
 * Build message strings for: start, checkpoint, end
 * Whether to register profiling start


## Getting Started
1. Get a time profiler, indicate which logger to log events to and at what logging level the profiler becomes active
2. Use profiler to register checkpoints and end time.

```java
TimeProfiler profiler = ProfilerJLogger.start(logger, FINE);

// some code …

profiler.checkpoint();
// ...
profiler.checkpoint();

// more code ...

profiler.end();
```
Writes to log:
```stdout
2000-01-01 MyMethod [FINE] Tracking Time
...
2000-01-01 MyMethod [FINE] So far: 10ms
...
2000-01-01 MyMethod [FINE] So far: 12ms
...
2000-01-01 MyMethod [FINE] Took: 20ms
```


## Configuration

*PM Time Profiler* has a preset configuration so that you can use it out of the box. You can change this configuration globally via static methods in **ProfilingConfig** (do this during your applications's initialization):
```java
public static void setLogStartMessage(boolean logStart)
public static void setProfileMessageBuilder(MessageBuilder messageBuilder)
public static void setTimeTrackerType(TimeTracker.Type type)
```
and you can also specify a different behavior for certain time profilers:
```java
// presets
public static TimeProfiler start(...)

// use different tracker type
public static TimeProfiler start(..., TimeTracker.Type trackerType)

// use different message builder
public static TimeProfiler start(..., MessageBuilder messageBuilder)

// use different tracker type
// use different message builder
public static TimeProfiler start(..., TimeTracker.Type trackerType, MessageBuilder messageBuilder)
```


| Property | Default | Options |
| -------- | ------- | ------- |
| Time Tracker Type | MILLIS | MILLIS, NANOS; use **TimeTracker.Type** |
| Message Builder | MessageBuilders.Time_Units | Use an existing builder from **MessageBuilders** or provide your own implementation of **MessageBuilder** |


## Gotchas

### Time Profiling and Logging Levels
The key feature of *PM Time Profiler*... when a logging level is not met, *PM Time Profiler* gives you instead a **TimeProfiler** that does nothing at all: no tracking, no logging, no message building, no System.currentTimeMillis()/nanoTime(), nothing. You can confidently leave the profiling code in place and control profiling via the logging framework (the only used CPU cycles are the calls on *start*, *checkpoint* and *end*).

### StdOut vs Logging
* Use the StdOut-based **TimeProfiler** to quickly start profiling, before configuring a logging framework; but always consider using proper logging
* Enable/disable feature is only available for logger-based TimeProfilers (via logging levels)

### TimeProfilers
You request the desired TimeProfiler from one of the available factory-like providers:
* ProfilerStdOut
* ProfilerLog4j
* ProfilerJLogger

Separate providers (instead of a master one) allows supporting different logging frameworks while letting you only set up the the one you will actually use (include only the binaries you need).

### Logging Messages and Tracking Labels
All *start* methods have an overloaded version that takes a label
```java
public static TimeProfiler start(...)
```
vs.
```java
public static TimeProfiler start(..., String trackingLabel)
```
This label is meant to identify the **TimeProfiler** created (say, you provide as label the name of the method being profiled)
```java
public static TimeProfiler start(logger, DEBUG, “doWork”)
```
and it is passed to the MessageBuilder
```java
public String getStartMessage(String trackingLabel, TimeTracker.Type type)
public String getCheckpointMessage(String trackingLabel, long elapsed, TimeTracker.Type type)
public String getEndMessage(String trackingLabel, long elapsed, TimeTracker.Type type)
```
to be able to generate messages like
```stdout
2000-01-01 ... [DEBUG] doWork: Tracking...
2000-01-01 ... [DEBUG] doWork: So far 10ms
2000-01-01 ... [DEBUG] doWork: Took 20ms
```
If you use a logger and/or configure a log format that provides execution information, you may choose to skip the tracking label. If you are using the StdOut **TimeProfiler** you may want to provide it.

### Your Own MessageBuilder
* When you set a global **MessageBuilder**, the set instance is reused everywhere for performance reasons (i.e. it is not thread-safe)
* If you use one of the overloaded methods to provide a **MessageBuilder**, instead of creating one instance on each method call, store the instance globally (unless, of course, you are doing some fancy threaded work with it)
