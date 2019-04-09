package com.sieck.communicator.services;

import java.io.FileNotFoundException;
import java.util.List;

public interface ServiceInterface<T> {
    T send(T obj) throws FileNotFoundException;
    List<T> getAll();
    T getById(String id);
    void deleteById(String id);
}
