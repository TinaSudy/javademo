package com.springboot.service.excel.Impl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.service.excel.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Override
	public void batchImport(String fileName, MultipartFile file)
			throws Exception {
		
		List<String[]> list = new ArrayList<String[]>();
		
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
        	System.out.println("上传文件格式错误...");
            //throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook workbook = null;
        if (isExcel2003) {
        	workbook = new HSSFWorkbook(is);
        } else {
        	workbook = new XSSFWorkbook(is);
        }
    	try {
			//workbook = WorkbookFactory.create(is);
			int numberOfSheets = workbook.getNumberOfSheets();
			if (numberOfSheets > 0) {
				Sheet sheet = workbook.getSheetAt(0);
				if (null != sheet) {
					// 获得当前sheet的开始行
					int firstRowNum = sheet.getFirstRowNum();
					// 获得当前sheet的结束行
					int lastRowNum = sheet.getLastRowNum();

					for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
						// 获得当前行
						Row row = sheet.getRow(rowNum);
						if (row == null) {
							continue;
						}
						// 获得当前行的开始列
						int firstCellNum = row.getFirstCellNum();
						// 获得当前行的列数
						int lastCellNum = row.getLastCellNum();
						String[] cells = new String[row.getLastCellNum()];
						// 循环当前行
						for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
							Cell cell = row.getCell(cellNum);
							cells[cellNum] = getCellValue(cell);
						}
						list.add(cells);
					}
				}
			}
			
			//插入数据
			if (!list.isEmpty()) {
				for (String[] strs : list) {
					
			    	Date date = new Date();
					String name = strs[0];
					String dept_userType = strs[1];
					String phone = strs[2];
					// 写对象 数据入库
					
				}
			}
		} catch (EncryptedDocumentException e1) {
			e1.printStackTrace();
		} finally {
			if (null != workbook) {
				workbook.close();
			}
			if (null != is) {
				is.close();
			}
		}
    }
	
	/**
	 * 根据不同类型获取值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case NUMERIC: // 数字
			cellValue = stringDateProcess(cell);
			break;
		case STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case BLANK: // 空值
			cellValue = "";
			break;
		case ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}
	
	/**
	 * 时间格式转换
	 * 
	 * @param cell
	 * @return
	 */
	public static String stringDateProcess(Cell cell) {
		String result = new String();
		if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
			SimpleDateFormat sdf = null;
			if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
				sdf = new SimpleDateFormat("HH:mm ");
			} else {// 日期
				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			}
			Date date = cell.getDateCellValue();
			result = sdf.format(date);
		} else if (cell.getCellStyle().getDataFormat() == 58) {
			// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			double value = cell.getNumericCellValue();
			Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
			result = sdf.format(date);
		} else {
			double value = cell.getNumericCellValue();
			CellStyle style = cell.getCellStyle();
			DecimalFormat format = new DecimalFormat();
			String temp = style.getDataFormatString();
			// 单元格设置成常规
			if (temp.equals("General")) {
				format.applyPattern("#");
			}
			result = format.format(value);
		}

		return result;
	}
	
	
}
