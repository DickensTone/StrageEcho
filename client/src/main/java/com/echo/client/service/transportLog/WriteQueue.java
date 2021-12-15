package com.echo.client.service.transportLog;

import com.echo.client.schedule.dumpInterface.DumpAgent;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *  add the content to the Queue.
 *  <p>
 *      A ScheduleTask will get a content from the queue.
 *  </p>
 *
 *
 */
public class WriteQueue {
    private final Queue<StringBuffer> queue;

    private final Set<DumpAgent> dumpAgent;

    private WriteQueue(){
        queue = new LinkedList<>();
        dumpAgent = new HashSet<>();
    }

    /**
     * add the content to queue.
     * @param sb ready to write
     */
    public void addContent(StringBuffer sb){
        queue.offer(sb);
        this.invokeDumpEvent();
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
     * get the last Object of the queue.
     * <h3>Attention</h3>
     * Every time invoke this method, the queue will reduce the object that return.
     *
     *
     * @return content in the queue.
     */
    public StringBuffer getContent(){
        return queue.poll();
    }

    public boolean needDump()  {
        return queue.size() > 0;
    }

    /**
     * SingleInstance MODE
     * @return WriteQueue's SINGLEINSTANCE
     */
    public static WriteQueue getInstance(){
        return Booster.SingleInstance;
    }

    /** register the Listener.
     * The DumpAgent will invoke when the add method is called.
     *
     * @param agent
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
