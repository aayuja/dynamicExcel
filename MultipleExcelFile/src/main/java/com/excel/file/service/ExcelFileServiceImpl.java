package com.excel.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.excel.file.model.Sheet1;
import com.excel.file.repo.ExcelFileRepo;
import com.excel.file.repo.Sheet1Repo;

@Service
public class ExcelFileServiceImpl implements ExcelFileService {

	@Autowired
	private ExcelFileRepo excelFileRepo;
	
	@Autowired
	private Sheet1Repo sheet1Repo;

	Workbook workbook;

	public String uploadDir = "/tmp";

	public void uploadFile(MultipartFile file) {

        try {
        	System.out.println(file.getName());
            Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            System.out.println(copyLocation.toString());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }

	public List<?> getExcelDataAsList() {

		List<String> list = new ArrayList<String>();

		DataFormatter dataFormatter = new DataFormatter();
		try {
			workbook = WorkbookFactory.create(new File(uploadDir+"/dynamicData.xlsx"));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
				System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");
				int j =0;
				for(Sheet sheet: workbook) {
					int noOfColumns = sheet.getRow(0).getLastCellNum();
					int noOfRows = sheet.getPhysicalNumberOfRows();
					System.out.println("-------Sheet has '"+noOfColumns+"' columns------"+"----and---"+noOfRows+" rows----");
	
					boolean flag=false;
					
					
					
					for (Row row : sheet) {
						if(flag) {
							int i = 0;
							Sheet1 sheet1=new Sheet1();
							if(j==0) {
								for (Cell cell : row) {
		
									String cellValue = dataFormatter.formatCellValue(cell);
									if(i==0) {
										sheet1.setEEID(cellValue);
									}
									if(i==1) {
										sheet1.setFull_name(cellValue);
									}
									if(i==2) {
										sheet1.setJob_title(cellValue);
									}
									if(i==3) {
										sheet1.setDepartment(cellValue);
									}
									if(i==4) {
										sheet1.setBusiness_unit(cellValue);
									}
									if(i==5) {
										sheet1.setGender(cellValue);
									}
									if(i==6) {
										sheet1.setEthnicity(cellValue);
									}
									if(i==7) {
										sheet1.setAge(Integer.parseInt(cellValue));
									}
									if(i==8) {
										sheet1.setHire_date(cellValue);
									}
									if(i==9) {
										sheet1.setAnnual_salary(cellValue);
									}
									if(i==10) {
										sheet1.setBonus(cellValue);
									}
									if(i==11) {
										sheet1.setCountry(cellValue);
									}
									if(i==12) {
										sheet1.setCity(cellValue);
									}
									if(i==13) {
										sheet1.setExit_date(cellValue);
									}
									i++;
								}
								sheet1Repo.save(sheet1);
							}
						}
						flag=true;
					}
				}
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return list;
			} 
    
}
