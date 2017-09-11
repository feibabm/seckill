package com.fndsoft.pool;

import com.fndsoft.pojo.QueueEntity;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by feibabm on 2017/9/11 0011.
 */
public class ExecutorPool {

    public static ConcurrentLinkedQueue<QueueEntity> queue = new ConcurrentLinkedQueue<>();

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void submit(Runnable task){
        executorService.submit(task);
    }
}
