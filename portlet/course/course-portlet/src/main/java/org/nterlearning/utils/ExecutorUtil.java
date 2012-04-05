/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */


package org.nterlearning.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Apparently, a ThreadPoolExecutor will sporadically throw a RejectedExecutionException,
 * even on a completely unbounded thread pool with an unbounded queue.  The best explanation
 * for this is that the various checks that could cause a RejectedExecution are concurrent
 * but not synchronized (fail-fast), and the service throws the exception when it detects
 * another thread in the same code and fails.
 * <p/>
 * Several bugs on bugs.sun.com that Doug Lea himself fixed, and his code samples always
 * include an infinite loop around an Executor.execute command that buries
 * the exception and tries again.
 * <p/>
 * This class is a central location for that code pattern.
 *
 */
public class ExecutorUtil {

    public static void safeExecute(ExecutorService service, Runnable task) {
        try {
            service.execute(task);
        }
        catch (Exception e) {
            // ignore and bury
        }
    }

    public static <T> Future<T> safeSubmit(ExecutorService service, Callable<T> task) {
        try {
            return service.submit(task);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Future<?> safeSubmit(ExecutorService service, Runnable task) {
        try {
            return service.submit(task);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static <T> Future<T> safeSubmit(ExecutorCompletionService<T> service, Callable<T> task) {
        try {
            return service.submit(task);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static ScheduledFuture<?> safeSubmit(ScheduledExecutorService service,
            Runnable task) {
        try {
            return service.schedule(task, 0L, TimeUnit.MILLISECONDS);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static ScheduledFuture<?> safeSubmitFixedDelay(ScheduledExecutorService service,
            Runnable task, long delay, long interval) {

        return safeSubmitFixedDelay(service, task, delay, interval, TimeUnit.MILLISECONDS);
    }

    public static ScheduledFuture<?> safeSubmitFixedDelay(ScheduledExecutorService service,
            Runnable task, long interval) {

        return safeSubmitFixedDelay(service, task, 0L, interval, TimeUnit.MILLISECONDS);
    }

    public static ScheduledFuture<?> safeSubmitFixedDelay(ScheduledExecutorService service,
                Runnable task, long delay, long interval, TimeUnit timeUnit) {
        try {
            return service.scheduleWithFixedDelay(task, delay, interval, timeUnit);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static ScheduledFuture<?> safeSubmitFixedRate(ScheduledExecutorService service,
            Runnable task, long delay, long period) {

        return safeSubmitFixedRate(service, task, delay, period, TimeUnit.MILLISECONDS);
    }

    public static ScheduledFuture<?> safeSubmitFixedRate(ScheduledExecutorService service,
            Runnable task, long period) {

        return safeSubmitFixedRate(service, task, 0L, period, TimeUnit.MILLISECONDS);
    }

    public static ScheduledFuture<?> safeSubmitFixedRate(ScheduledExecutorService service,
                Runnable task, long delay, long period, TimeUnit timeUnit) {
        try {
            return service.scheduleAtFixedRate(task, delay, period, timeUnit);
        }
        catch (Exception e) {
            return null;
        }
    }
}