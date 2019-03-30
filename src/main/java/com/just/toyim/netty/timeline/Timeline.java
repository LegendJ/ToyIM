package com.just.toyim.netty.timeline;


import java.util.List;

public interface Timeline<T> {

    void add(T msg);

    List<T> getAll();

    boolean isEmpty();



}
