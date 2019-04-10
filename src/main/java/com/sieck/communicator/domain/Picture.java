package com.sieck.communicator.domain;

import lombok.Data;

@Data
public class Picture {
    private String name;
    private String path;

    public Picture(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
