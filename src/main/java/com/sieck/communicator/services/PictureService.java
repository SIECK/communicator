package com.sieck.communicator.services;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.sieck.communicator.domain.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class PictureService implements PictureInterface{

    @Autowired
    GridFsOperations gridFsOperations;

    @Override
    public void send(Picture picture) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(picture.getPath());
        gridFsOperations.store(inputStream, picture.getName());
    }

    @Override
    public String getById(String id) {
        GridFSFile gridFSFile = gridFsOperations.findOne(Query.query(Criteria.where("_id").is(id)));
        return gridFSFile.toString();
    }

    @Override
    public void deleteById(String id) {
        gridFsOperations.delete(new Query(Criteria.where("_id").is(id)));
    }
}
