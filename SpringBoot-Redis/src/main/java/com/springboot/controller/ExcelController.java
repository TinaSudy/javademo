package com.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.service.excel.ExcelService;


/**
 * 
 * @author Excel文件上传 解析
 */
@RestController
@Api(description = "Excel文件处理类")
@RequestMapping("/test/")
public class ExcelController {
	 
	    @Autowired
	    private ExcelService excelService;
	 
	    @PostMapping("/import")
	    @ApiOperation("Excel文件上传")
	    public boolean addUser(@RequestParam("file") MultipartFile file) {
	        boolean a = false;
	        String fileName = file.getOriginalFilename();
	        try {
	             excelService.batchImport(fileName, file);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return  a;
	    }
	 

}
