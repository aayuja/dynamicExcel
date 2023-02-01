package com.excel.file.controller;

import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.excel.file.model.ExcelFile;
import com.excel.file.service.ExcelFileService;

@RestController
public class ExcelFileController {
	
	@Autowired
	private ExcelFileService excelFileService;
	
	
	Workbook workbook;
	
	
	@PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		excelFileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
            "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return "redirect:/";
    }
	

	
	 @GetMapping("/getData")
	    public List<?> saveExcelData(Model model) {
	    	
		 	return excelFileService.getExcelDataAsList();
	    }
	
	
}




