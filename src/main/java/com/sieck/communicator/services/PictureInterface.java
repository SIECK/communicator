package com.sieck.communicator.services;

import com.sieck.communicator.domain.Picture;

import java.io.FileNotFoundException;

public interface PictureInterface {
    String getById(String id);
    void send(Picture picture) throws FileNotFoundException;
    void deleteById(String id);
}
