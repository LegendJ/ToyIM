package com.just.toyim.netty.timeline;

import java.util.List;

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
