package org;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sickle on 17-7-28.
 */
public class ScheduledThreadPool {
    ExecutorService pool= Executors.newScheduledThreadPool(1000);

}
