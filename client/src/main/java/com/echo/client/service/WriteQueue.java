package com.echo.client.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  add the content to the Queue.
 *  <p>
 *      A ScheduleTask will get a content from the queue.
 *  </p>
 *
 *
 */
@Service
public class WriteQueue {
    private final Queue<StringBuffer> queue;

    public WriteQueue(){
        queue = new LinkedList<>();
    }

    /**
     * add the content to queue.
     * @param sb ready to write
     */
    public void addContent(StringBuffer sb){
        queue.add(sb);
    }

    /**
     * get the last Object of the queue.
     * <h3>Attention</h3>
     * Every invoke this method, the queue will reduce the object that return.
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
}
