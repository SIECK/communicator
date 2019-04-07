package com.sieck.communicator.services;

import java.util.List;

public interface ServiceInterface<T> {
    T saveMessage(T obj);
    //List<T> read();
    //T update(T obj);
    //T delete(String);
}
