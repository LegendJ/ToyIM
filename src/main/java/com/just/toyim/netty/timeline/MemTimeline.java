package com.just.toyim.netty.timeline;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class MemTimeline<T> implements Timeline<T> {

    private static final int MAX_STORE_CAPACITY = 10000;
    //TODO(just) : 每次读完数据不移除，移动指针实现客户端读取最近消息。可以使用Guava.Cache
    LinkedBlockingQueue<T> queue = new LinkedBlockingQueue<>();


    @Override
    public void add(T msg) {
        if (queue.size() >= MAX_STORE_CAPACITY) {
            queue.poll();
        }
        queue.offer(msg);
    }

    @Override
    public List<T> getAll() {
        List<T> elements = new LinkedList<>();
        queue.drainTo(elements);
        return elements;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
