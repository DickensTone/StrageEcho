package com.echo.client.service.transportLog;

import com.echo.client.schedule.dumpInterface.DumpAgent;
import com.echo.core.components.SingletonUtil;

import java.util.HashSet;
import java.util.Set;
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

    private final Set<DumpAgent> dumpAgent;

    private WriteQueue(){
        dumpAgent = new HashSet<>();
    }

    /**
     *
     * invoke the agent event when the addContent is called.
     */
    private void invokeDumpEvent() {
        for(DumpAgent agent : dumpAgent){
            agent.start();
        }
    }

    /**
     * SingleInstance MODE
     * @return WriteQueue's SINGLETON_INSTANCE
     */
    public static WriteQueue getInstance(){
        return Booster.SingleInstance;
    }

    /** register the Listener.
     * The DumpAgent will invoke when the add method is called.
     *
     * @param agent the listener loaded after add
     */
    public void registerListener(DumpAgent agent){
        dumpAgent.add(agent);
    }

    /**
     * With the help of JVM, we can use static class to create single instance mode.
     *
     */
    private static class Booster {
        private static final WriteQueue SingleInstance = new WriteQueue();
    }

}
