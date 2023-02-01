package com.excel.file.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.excel.file.model.ExcelFile;

@Repository
public interface ExcelFileRepo extends MongoRepository<ExcelFile, String> {

}
