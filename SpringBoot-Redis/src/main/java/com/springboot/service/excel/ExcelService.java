package com.springboot.service.excel;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
	
    void batchImport(String fileName, MultipartFile file) throws Exception;
}
