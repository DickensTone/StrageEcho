package com.echo.client.service.transportLog;


import com.echo.core.components.SingletonUtil;

import java.util.concurrent.LinkedBlockingDeque;

/**
 *  add the content to the Queue.
 *  <p>
 *      A ScheduleTask will get a content from the queue.
 *  </p>
 *
 *
 */
public class WriteQueue {
    private final LinkedBlockingDeque<StringBuffer> queue = SingletonUtil.linkedBlockingDeque;



    /**
     * SingleInstance MODE
     * @return WriteQueue's SINGLETON_INSTANCE
     */
    public static WriteQueue getInstance(){
        return Booster.SingleInstance;
    }


    /**
     * With the help of JVM, we can use static class to create single instance mode.
     *
     */
    private static class Booster {
        private static final WriteQueue SingleInstance = new WriteQueue();
    }

}
