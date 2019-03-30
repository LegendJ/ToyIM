package com.just.toyim.netty.timeline;

import java.util.List;

//TODO(just): 用于持久化存储未同步消息,实现消息漫游等功能
public class PersistTimeline<T> implements Timeline<T> {
    @Override
    public void add(T msg) {

    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
