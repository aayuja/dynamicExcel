package com.excel.file.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.excel.file.model.Sheet1;

@Repository
public interface Sheet1Repo extends MongoRepository<Sheet1, String>{

}
