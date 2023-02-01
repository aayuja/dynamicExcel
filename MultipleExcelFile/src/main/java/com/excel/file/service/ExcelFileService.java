package com.excel.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.excel.file.model.ExcelFile;

public interface ExcelFileService {

	void uploadFile(MultipartFile file);

	List<?> getExcelDataAsList();

}
