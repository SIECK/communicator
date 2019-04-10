package com.sieck.communicator.services;

import java.io.FileNotFoundException;
import java.util.List;

public interface MessageInterface<T> {
    List<T> getAll();
    T getById(String id);
    void send(T obj);
    void deleteById(String id);
}
