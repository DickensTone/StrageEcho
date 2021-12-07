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
     * after add the param;
     * @param sb ready to write
     */
    public void addContent(StringBuffer sb){
        queue.add(sb);
    }

    public StringBuffer getContent(){
        return queue.poll();
    }

    public boolean needDump()  {
        return queue.size() > 0;
    }
}
