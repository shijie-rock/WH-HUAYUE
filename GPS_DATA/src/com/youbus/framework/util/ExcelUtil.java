package com.youbus.framework.util;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.infoservice.po.DynaBean;
import com.youbus.framework.comm.excel.ExcelConstant;
import com.youbus.framework.comm.excel.entity.ExcelCondition;
import com.youbus.framework.comm.excel.entity.ExcelConditionArea;
import com.youbus.framework.comm.excel.entity.ExcelObj;
import com.youbus.framework.comm.excel.entity.ExcelTableBodyArea;
import com.youbus.framework.comm.excel.entity.ExcelTableFoot;
import com.youbus.framework.comm.excel.entity.ExcelTableFootArea;
import com.youbus.framework.comm.excel.entity.ExcelTableHead;
import com.youbus.framework.comm.excel.entity.ExcelTableHeadArea;
import com.youbus.framework.comm.excel.entity.ExcelTitle;
import com.youbus.framework.comm.excel.entity.ExcelTitleArea;
import com.youbus.framework.comm.excel.entity.ExcelXSSFCellStyle;
import com.youbus.framework.comm.excel.entity.ExcelXSSFFont;
import com.youbus.framework.comm.excel.exception.ExcelNullException;
import com.youbus.framework.comm.excel.exception.ExcelRangeBeyondException;

/**
 * excel导入导出工具类
 * 
 * @author Bill Tang
 *
 */
public class ExcelUtil {
	
	private List<ExcelTableHead> excelTableHeadList = new LinkedList<ExcelTableHead>();
	private int excelTableBodyStartRow = 0;
	
	public <T> XSSFWorkbook exportExcelData(ExcelObj<T> excelObj) throws Exception {
		
		//Step 1:创建一个webbook，对应一个Excel文件  
		XSSFWorkbook xswb = new XSSFWorkbook();
		//Step 2:在webbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = xswb.createSheet((excelObj.getSheetName()==null || "".equals(excelObj.getSheetName()))?"New Sheet":excelObj.getSheetName());
				
		//创建行数记录对象
		int rowCount = 0;
		
		//创建标题
		rowCount = createExcelTitleArea(excelObj, xswb, sheet, rowCount);		
		//创建条件
		rowCount = createExcelConditonArea(excelObj, xswb, sheet, rowCount);
		//创建表格头部
		rowCount = createExcelTableHeadArea(excelObj, xswb, sheet, rowCount);	
		//创建表格内容
		excelTableBodyStartRow = rowCount;
		rowCount = createExcelTableBodyArea(excelObj, xswb, sheet, rowCount);
		//创建表格页脚
		rowCount = createExcelTableFootArea(excelObj, xswb, sheet, rowCount);
					
		return xswb;
	}
	
	public <T> void createFile(ExcelObj<T> excelObj) throws Exception {
		
		XSSFWorkbook xswb = exportExcelData(excelObj);

		//Step 7:生成excel文件
		if(excelObj.getFilePath()!=null && !"".equals(excelObj.getFilePath())) {

			FileOutputStream fout = new FileOutputStream(excelObj.getFilePath()+((excelObj.getFileName()!=null && !"".equals(excelObj.getFileName()))?excelObj.getFileName():"Excel-")+((excelObj.getFileSuffix()!=null && !"".equals(excelObj.getFileSuffix()))?excelObj.getFileSuffix():"xls"));
			xswb.write(fout);  
	        fout.close();  
		}
		
		if(xswb!=null) xswb.close();
	}
	
	//创建标题区域对象
	private <T> int createExcelTitleArea(ExcelObj<T> excelObj, XSSFWorkbook xswb, XSSFSheet sheet, int rowCount) throws Exception {
		
		ExcelTitleArea excelTitleArea = excelObj.getExcelTitleArea();
		
		if(excelTitleArea!=null) {
			
			XSSFRow rowTitle = null;
			XSSFCell cellTitle = null;
			
			//获取起始行是否小于excel记录的起始行
			int flag = excelTitleArea.getStartRow().compareTo(Integer.valueOf(rowCount));
			
			if(flag<0) throw new ExcelRangeBeyondException("起始行小于excel记录的起始行，不能正确导出Excel");
			
			//循环长度
			int length = (int) excelTitleArea.getStartRow() - rowCount;
			//设置起始行的单元格的宽度和高度
			for(int i=0;i<length;i++) {

				//Step 3:在sheet中添加表头第0行
				rowTitle = sheet.createRow(rowCount);
				rowTitle.setHeightInPoints((short)(10));
				sheet.setColumnWidth(0, (short)(0.7*256));
				rowCount++;
				//Step 4:在row中创建单元格
				cellTitle = rowTitle.createCell(0);
				cellTitle.setCellValue("");
			}
			
			//添加标题
			for(int i=excelTitleArea.getStartRow();i<=excelTitleArea.getEndRow();i++) {

				//Step 3:在sheet中添加表头第0行
				rowTitle = sheet.createRow(rowCount);
				
				//当前行的单元格索引位置
				int cellRowIndex = 0;
				
				if(excelTitleArea.isFromTowToLast()) {

					//Step 4:在row中创建单元格
					cellTitle = rowTitle.createCell(0);
					sheet.setColumnWidth(0, (short)(0.7*256));
					cellTitle.setCellValue("");
					
					cellRowIndex = 1;
				} 

				List<ExcelTitle> excelTitleMap = excelTitleArea.getExcelTitleMap().get(String.valueOf(i));
				
				if(excelTitleMap!=null && excelTitleMap.size()>0) {

					for(ExcelTitle excelTitle : excelTitleMap) {

						if(excelTitle.getRegion()!=null) sheet.addMergedRegion(excelTitle.getRegion());
						cellTitle = rowTitle.createCell(cellRowIndex);
						cellTitle.setCellValue(excelTitle.getTitileName());
						//创建标题样式类
						XSSFCellStyle cellTitleStyle = xswb.createCellStyle();
						XSSFFont cellTitleFont = xswb.createFont();	
						convertCellTitleFont(excelTitle.getCellTitleFont(), cellTitleFont);
						convertCellTitleStyle(excelTitle.getCellTitleStyle(), cellTitleStyle);
						cellTitleStyle.setFont(cellTitleFont);
						cellTitle.setCellStyle(cellTitleStyle);
						
						cellRowIndex++;
					}
				}
				
				rowCount++;
			}			
		}
		
		return rowCount;
	}
	
	private void convertCellTitleFont(ExcelXSSFFont excelCellTitleFont, XSSFFont cellTitleFont) {
		
		cellTitleFont.setBold((excelCellTitleFont==null)?true:excelCellTitleFont.isBold());
		cellTitleFont.setColor((excelCellTitleFont==null || excelCellTitleFont.getColor()==0)?ExcelConstant.EXCEL_TITLE_FONT_COLOR:excelCellTitleFont.getColor());
		cellTitleFont.setFontHeightInPoints((excelCellTitleFont==null || excelCellTitleFont.getFontHeightInPoint()==0)?ExcelConstant.EXCEL_TITLE_FONT_SIZE:excelCellTitleFont.getFontHeightInPoint());
	}

	private void convertCellTitleStyle(ExcelXSSFCellStyle excelCellTitleStyle, XSSFCellStyle cellTitleStyle) {
		
		cellTitleStyle.setAlignment((excelCellTitleStyle==null || excelCellTitleStyle.getAlignment()==0)?ExcelConstant.EXCEL_TITLE_STYLE_ALIGNMENT:excelCellTitleStyle.getAlignment());
		cellTitleStyle.setBorderBottom((excelCellTitleStyle==null)?0:excelCellTitleStyle.getBorderBottom());
		cellTitleStyle.setBorderTop((excelCellTitleStyle==null)?0:excelCellTitleStyle.getBorderTop());
		cellTitleStyle.setBorderLeft((excelCellTitleStyle==null)?0:excelCellTitleStyle.getBorderLeft());
		cellTitleStyle.setBorderRight((excelCellTitleStyle==null)?0:excelCellTitleStyle.getBorderRight());
		if(excelCellTitleStyle!=null) {
			cellTitleStyle.setFillForegroundColor(excelCellTitleStyle.getFillForegroundColor());
			cellTitleStyle.setFillPattern(excelCellTitleStyle.getFillPattern());
		}
	}
	
	//创建条件区域对象
	private <T> int createExcelConditonArea(ExcelObj<T> excelObj, XSSFWorkbook xswb, XSSFSheet sheet, int rowCount) throws Exception {
		
		ExcelConditionArea excelConditionArea = excelObj.getExcelConditionArea();
		
		if(excelConditionArea!=null) {
			
			XSSFRow rowCondition = null;
			XSSFCell cellCondition = null;
			
			//获取起始行是否小于excel记录的起始行
			int flag = excelConditionArea.getStartRow().compareTo(Integer.valueOf(rowCount));
			
			if(flag<0) throw new ExcelRangeBeyondException("起始行小于excel记录的起始行，不能正确导出Excel");
			
			//添加条件
			for(int i=excelConditionArea.getStartRow();i<=excelConditionArea.getEndRow();i++) {

				//Step 3:在sheet中添加表头第0行
				rowCondition = sheet.createRow(rowCount);
				
				//当前行的单元格索引位置
				int cellRowIndex = 0;
				
				//开始要空行
				if(excelConditionArea.isFromTowToLast()) {

					//Step 4:在row中创建单元格
					cellCondition = rowCondition.createCell(0);
					sheet.setColumnWidth(0, (short)(0.7*256));
					cellCondition.setCellValue("");
					
					cellRowIndex = 1;
				} 

				List<ExcelCondition> excelConditionMap = excelConditionArea.getExcelConditionMap().get(String.valueOf(i));
				
				if(excelConditionMap!=null && excelConditionMap.size()>0) {

					for(ExcelCondition excelCondition : excelConditionMap) {

						if(excelCondition.getRegion()!=null) sheet.addMergedRegion(excelCondition.getRegion());
						cellCondition = rowCondition.createCell(cellRowIndex);
						cellCondition.setCellValue(excelCondition.getValue());
						//创建条件样式类
						XSSFCellStyle cellConditionStyle = xswb.createCellStyle();
						XSSFFont cellConditionFont = xswb.createFont();	
						convertCellConditionFont(excelCondition.getCellConditionFont(), cellConditionFont);
						convertCellConditionStyle(excelCondition.getCellConditionStyle(), cellConditionStyle);
						cellConditionStyle.setFont(cellConditionFont);
						cellCondition.setCellStyle(cellConditionStyle);
						
						cellRowIndex++;
					}
				}
				
				rowCount++;
			}			
		}
		
		return rowCount;
	}
	
	private void convertCellConditionFont(ExcelXSSFFont excelCellConditionFont, XSSFFont cellConditionFont) {
		
		cellConditionFont.setBold((excelCellConditionFont==null)?true:excelCellConditionFont.isBold());
		cellConditionFont.setColor((excelCellConditionFont==null || excelCellConditionFont.getColor()==0)?ExcelConstant.EXCEL_CONDITION_FONT_COLOR:excelCellConditionFont.getColor());
		cellConditionFont.setFontHeightInPoints((excelCellConditionFont==null || excelCellConditionFont.getFontHeightInPoint()==0)?ExcelConstant.EXCEL_CONDITION_FONT_SIZE:excelCellConditionFont.getFontHeightInPoint());
	}

	private void convertCellConditionStyle(ExcelXSSFCellStyle excelCellConditionStyle, XSSFCellStyle cellConditionStyle) {
		
		cellConditionStyle.setAlignment((excelCellConditionStyle==null || excelCellConditionStyle.getAlignment()==0)?ExcelConstant.EXCEL_CONDITION_STYLE_ALIGNMENT:excelCellConditionStyle.getAlignment());
		cellConditionStyle.setBorderBottom((excelCellConditionStyle==null)?0:excelCellConditionStyle.getBorderBottom());
		cellConditionStyle.setBorderTop((excelCellConditionStyle==null)?0:excelCellConditionStyle.getBorderTop());
		cellConditionStyle.setBorderLeft((excelCellConditionStyle==null)?0:excelCellConditionStyle.getBorderLeft());
		cellConditionStyle.setBorderRight((excelCellConditionStyle==null)?0:excelCellConditionStyle.getBorderRight());
		if(excelCellConditionStyle!=null) {
			cellConditionStyle.setFillForegroundColor(excelCellConditionStyle.getFillForegroundColor());
			cellConditionStyle.setFillPattern(excelCellConditionStyle.getFillPattern());
		}
	}
	
	//创建表格头部区域对象
	private <T> int createExcelTableHeadArea(ExcelObj<T> excelObj, XSSFWorkbook xswb, XSSFSheet sheet, int rowCount) throws Exception {
		
		ExcelTableHeadArea excelTableHeadArea = excelObj.getExcelTableHeadArea();
		
		if(excelTableHeadArea!=null) {
			
			XSSFRow rowTableHead = null;
			XSSFCell cellTableHead = null;
			
			//获取起始行是否小于excel记录的起始行
			int flag = excelTableHeadArea.getStartRow().compareTo(Integer.valueOf(rowCount));
			
			if(flag<0) throw new ExcelRangeBeyondException("起始行小于excel记录的起始行，不能正确导出Excel");
			
			int endRow = excelTableHeadArea.getExcelTableHeadMap().size()>0?(excelTableHeadArea.getExcelTableHeadMap().size() - 1 + rowCount):excelTableHeadArea.getEndRow();
			
			//添加条件
			for(int i=excelTableHeadArea.getStartRow();i<=endRow;i++) {

				//Step 3:在sheet中添加表头第0行
				rowTableHead = sheet.createRow(rowCount);
				
				//当前行的单元格索引位置
				int cellRowIndex = 0;
				
				//开始要空行
				if(excelTableHeadArea.isFromTowToLast()) {

					//Step 4:在row中创建单元格
					cellTableHead = rowTableHead.createCell(0);
					sheet.setColumnWidth(0, (short)(0.7*256));
					cellTableHead.setCellValue("");
					
					cellRowIndex = 1;
				} 

				List<ExcelTableHead> excelTableHeadMap = excelTableHeadArea.getExcelTableHeadMap().get(String.valueOf(i));
				
				if(excelTableHeadMap!=null && excelTableHeadMap.size()>0) {
					
					excelTableHeadList.clear();
					
					for(ExcelTableHead excelTableHead : excelTableHeadMap) {

						if(excelTableHead.getRegion()!=null) sheet.addMergedRegion(excelTableHead.getRegion());
						if(excelTableHead.getCellColumnWidth()!=0) sheet.setColumnWidth(cellRowIndex, excelTableHead.getCellColumnWidth());
						cellTableHead = rowTableHead.createCell(cellRowIndex);
						cellTableHead.setCellValue(excelTableHead.getValue());
						//设置自动增长列的宽度
						if(excelTableHead.getName()!=null && ExcelConstant.EXCEL_TABLE_HEAD_NAME_AUTO_ID.equals(excelTableHead.getName())) 
							setCellTableAutoColumn(sheet, cellRowIndex);
						//创建条件样式类
						XSSFCellStyle cellTableHeadStyle = xswb.createCellStyle();
						XSSFFont cellTableHeadFont = xswb.createFont();	
						convertCellTableHeadFont(excelTableHead.getCellTableHeadFont(), cellTableHeadFont);
						convertCellTableHeadStyle(excelTableHead.getCellTableHeadStyle(), cellTableHeadStyle);
						//设置表格头部背景
						convertCellTableHeadStyleByForeground(excelTableHead, cellTableHeadStyle);

						cellTableHeadStyle.setFont(cellTableHeadFont);
						cellTableHead.setCellStyle(cellTableHeadStyle);
						
						cellRowIndex++;
						
						excelTableHeadList.add(excelTableHead);
					}
				}
				
				rowCount++;
			}			
		}
		
		return rowCount;
	}
		
	private void convertCellTableHeadFont(ExcelXSSFFont excelCellTableHeadFont, XSSFFont cellTableHeadFont) {
		
		cellTableHeadFont.setBold((excelCellTableHeadFont==null)?true:excelCellTableHeadFont.isBold());
		cellTableHeadFont.setColor((excelCellTableHeadFont==null || excelCellTableHeadFont.getColor()==0)?ExcelConstant.EXCEL_TABLE_HEAD_FONT_COLOR:excelCellTableHeadFont.getColor());
		cellTableHeadFont.setFontHeightInPoints((excelCellTableHeadFont==null || excelCellTableHeadFont.getFontHeightInPoint()==0)?ExcelConstant.EXCEL_TABLE_HEAD_FONT_SIZE:excelCellTableHeadFont.getFontHeightInPoint());
	}

	private void convertCellTableHeadStyle(ExcelXSSFCellStyle excelCellTableHeadStyle, XSSFCellStyle cellTableHeadStyle) {
		
		cellTableHeadStyle.setAlignment((excelCellTableHeadStyle==null || excelCellTableHeadStyle.getAlignment()==0)?ExcelConstant.EXCEL_TABLE_HEAD_STYLE_ALIGNMENT:excelCellTableHeadStyle.getAlignment());
		cellTableHeadStyle.setVerticalAlignment((excelCellTableHeadStyle==null)?XSSFCellStyle.VERTICAL_CENTER:excelCellTableHeadStyle.getVerticalAlignment());
		cellTableHeadStyle.setBorderBottom((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderBottom());
		cellTableHeadStyle.setBorderTop((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderTop());
		cellTableHeadStyle.setBorderLeft((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderLeft());
		cellTableHeadStyle.setBorderRight((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderRight());
		cellTableHeadStyle.setWrapText((excelCellTableHeadStyle==null)?true:excelCellTableHeadStyle.isWrapText());
		if(excelCellTableHeadStyle!=null) {
			cellTableHeadStyle.setFillForegroundColor(excelCellTableHeadStyle.getFillForegroundColor());
			cellTableHeadStyle.setFillPattern(excelCellTableHeadStyle.getFillPattern());
		}
	}
	
	//设置表格头部背景
	private void convertCellTableHeadStyleByForeground(ExcelTableHead excelTableHead, XSSFCellStyle cellTableHeadStyle) {
		if(excelTableHead.isHaveForeground()) {
			if(excelTableHead.isHaveSpecialForeground()) {

				cellTableHeadStyle.setFillForegroundColor(ExcelConstant.EXCEL_TABLE_HEAD_SPECIAL_FILL_FOREGROUND_COLOR);
			
			} else {

				cellTableHeadStyle.setFillForegroundColor(ExcelConstant.EXCEL_TABLE_HEAD_FILL_FOREGROUND_COLOR);
			}
			cellTableHeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
	}
	
	//设置自动增长列的宽度
	private void setCellTableAutoColumn(XSSFSheet sheet, int cellRowIndex) {
		sheet.setColumnWidth(cellRowIndex, (short)(4*256));
	}
	
	//创建表格内容区域对象
	private <T> int createExcelTableBodyArea(ExcelObj<T> excelObj, XSSFWorkbook xswb, XSSFSheet sheet, int rowCount) throws Exception {
		
		ExcelTableBodyArea<T> excelTableBodyArea = excelObj.getExcelTableBodyArea();
		
		if(excelTableBodyArea!=null) {
			
			XSSFRow rowTableBody = null;
			XSSFCell cellTableBody = null;
						
			if(excelTableBodyArea.getExcelDataList() == null || excelTableBodyArea.getExcelDataList().size()==0)
				throw new ExcelNullException("表格数据内容为空，不能正确导出Excel");
			
			int startRow = rowCount;
			int endRow = excelTableBodyArea.getExcelDataList().size() - 1 + rowCount;
			
			//添加条件
			for(int i=startRow;i<=endRow;i++) {

				//Step 3:在sheet中添加表头第0行
				rowTableBody = sheet.createRow(rowCount);
				
				//当前行的单元格索引位置
				int cellRowIndex = 0;
				
				//开始要空行
				if(excelTableBodyArea.isFromTowToLast()) {

					//Step 4:在row中创建单元格
					cellTableBody = rowTableBody.createCell(0);
					sheet.setColumnWidth(0, (short)(0.7*256));
					cellTableBody.setCellValue("");
					
					cellRowIndex = 1;
				} 
				
				if(excelTableHeadList!=null && excelTableHeadList.size()>0) {

					for(int j=0;j<excelTableHeadList.size();j++) {

						ExcelTableHead excelTableHead = excelTableHeadList.get(j);
						
						if(excelTableHead.getRegion()!=null) sheet.addMergedRegion(excelTableHead.getRegion());
						cellTableBody = rowTableBody.createCell(cellRowIndex);
						//设置自动增长列的宽度
						if(excelTableHead.getName()!=null && ExcelConstant.EXCEL_TABLE_HEAD_NAME_AUTO_ID.equals(excelTableHead.getName())) {

							setCellTableAutoColumn(sheet, cellRowIndex);
							cellTableBody.setCellValue((i-startRow+1));
							
						} else {

							setCellTableBodyContent(cellTableBody, excelTableBodyArea.getExcelDataList().get((i-startRow)), excelTableHead, i);
						}
						
						//创建条件样式类
						XSSFCellStyle cellTableBodyStyle = xswb.createCellStyle();
						convertCellTableBodyStyle(excelTableHead.getCellTableHeadStyle(), cellTableBodyStyle);
						//设置表格头部背景
						convertCellTableHeadStyleByForeground(excelTableHead, cellTableBodyStyle);

						cellTableBody.setCellStyle(cellTableBodyStyle);
						
						cellRowIndex++;
					}
				}
				
				rowCount++;
			}			
		}
		
		return rowCount;
	}
	
	private void convertCellTableBodyStyle(ExcelXSSFCellStyle excelCellTableHeadStyle, XSSFCellStyle cellTableBodyStyle) {
		
		cellTableBodyStyle.setVerticalAlignment((excelCellTableHeadStyle==null)?XSSFCellStyle.VERTICAL_CENTER:excelCellTableHeadStyle.getVerticalAlignment());
		cellTableBodyStyle.setBorderBottom((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderBottom());
		cellTableBodyStyle.setBorderTop((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderTop());
		cellTableBodyStyle.setBorderLeft((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderLeft());
		cellTableBodyStyle.setBorderRight((excelCellTableHeadStyle==null)?0:excelCellTableHeadStyle.getBorderRight());
		cellTableBodyStyle.setWrapText((excelCellTableHeadStyle==null)?true:excelCellTableHeadStyle.isWrapText());
	}
	
	private <T> void setCellTableBodyContent(XSSFCell cellTableBody, T t, ExcelTableHead excelTableHead, int rowCount) throws Exception {
		
		if (t instanceof DynaBean) {
			
			getCellTableBodyConentByDynaBean(cellTableBody, (DynaBean) t, excelTableHead, rowCount);
		} 
	}
	
	private void getCellTableBodyConentByDynaBean(XSSFCell cellTableBody, DynaBean bean, ExcelTableHead excelTableHead, int rowCount) throws Exception {
		
		if(excelTableHead.getCellFormula()!=null && !"".equals(excelTableHead.getName())) {
			
			cellTableBody.setCellType(XSSFCell.CELL_TYPE_FORMULA);
			cellTableBody.setCellFormula(excelTableHead.getCellFormula().replaceAll(",", String.valueOf(rowCount+1)));

			return;
		}
		
		if (bean == null)
			return;
		
		String[] keyArray = excelTableHead.getName().split(",");
		Object val = null;
		StringBuffer str = new StringBuffer("");
		
		for(String key : keyArray) {
			val = bean.get(key);
			if (val == null)
				continue;
			if (val instanceof String)
				str.append(val).append("\n");
			else if (val instanceof Integer)
				cellTableBody.setCellValue(bean.getInt(key));
			else if (val instanceof Long)
				cellTableBody.setCellValue(bean.getLong(key));
			else if (val instanceof Float)
				cellTableBody.setCellValue(bean.getFloat(key));
			else if (val instanceof Double)
				cellTableBody.setCellValue(bean.getDouble(key));
			else if (val instanceof Date)
				cellTableBody.setCellValue(bean.getDate(key));
			else
				throw new Exception("not supported type : " + val.getClass().getName());
		}
		
		if(!"".equals(str.toString())) {
			cellTableBody.setCellValue(str.toString().substring(0, str.toString().length()-1));
		}
	}
	
	//创建表格页脚区域对象
	private <T> int createExcelTableFootArea(ExcelObj<T> excelObj, XSSFWorkbook xswb, XSSFSheet sheet, int rowCount) throws Exception {
		
		ExcelTableFootArea excelTableFootArea = excelObj.getExcelTableFootArea();
		
		if(excelTableFootArea!=null) {
			
			XSSFRow rowTableFoot = null;
			XSSFCell cellTableFoot = null;
						
//			if(excelTableBodyArea.getExcelDataList() == null || excelTableBodyArea.getExcelDataList().size()==0)
//				throw new ExcelRangeBeyondException("表格数据内容为空，不能正确导出Excel");
			
			int startRow = rowCount;
			int endRow = excelTableFootArea.getEndRow() - 1 + rowCount;
			
			//添加条件
			for(int i=startRow;i<=endRow;i++) {

				//Step 3:在sheet中添加表头第0行
				rowTableFoot = sheet.createRow(rowCount);
				
				//当前行的单元格索引位置
				int cellRowIndex = 0;
				
				//开始要空行
				if(excelTableFootArea.isFromTowToLast()) {

					//Step 4:在row中创建单元格
					cellTableFoot = rowTableFoot.createCell(0);
					sheet.setColumnWidth(0, (short)(0.7*256));
					cellTableFoot.setCellValue("");
					
					cellRowIndex = 1;
				} 
				
				List<ExcelTableFoot> excelTableFootMap = excelTableFootArea.getExcelTableFootMap().get("0");
				
				if(excelTableFootMap!=null && excelTableFootMap.size()>0) {
										
					for(ExcelTableFoot excelTableFoot : excelTableFootMap) {

						if(excelTableFoot.getRegion()!=null) {
							int firstRow = rowCount;
							int lastRow = rowCount;
							int firstCol = excelTableFoot.getRegion().getFirstColumn();
							int lastCol = excelTableFoot.getRegion().getLastColumn();
							CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
							sheet.addMergedRegion(region);
							//sheet.addMergedRegion(excelTableFoot.getRegion());
						}
						cellTableFoot = rowTableFoot.createCell(cellRowIndex);
						//cellTableFoot.setCellValue(excelTableFoot.getValue());
						setCellTableFootContent(cellTableFoot, excelTableFoot, rowCount);
						
						//创建条件样式类
						XSSFCellStyle cellTableFootStyle = xswb.createCellStyle();
						XSSFFont cellTableFootFont = xswb.createFont();	
						convertCellTableFootFont(excelTableFoot.getCellTableFootFont(), cellTableFootFont);
						convertCellTableFootStyle(excelTableFoot.getCellTableFootStyle(), cellTableFootStyle);
						//设置表格头部背景
						convertCellTableFootStyleByForeground(excelTableFoot, cellTableFootStyle);

						cellTableFootStyle.setFont(cellTableFootFont);
						cellTableFoot.setCellStyle(cellTableFootStyle);
						
						cellRowIndex++;
						
					}
				}
				
				rowCount++;
			}			
		}
		
		return rowCount;
	}
	
	private void convertCellTableFootFont(ExcelXSSFFont excelCellTableFootFont, XSSFFont cellTableFootFont) {
		
		cellTableFootFont.setBold((excelCellTableFootFont==null)?true:excelCellTableFootFont.isBold());
		cellTableFootFont.setColor((excelCellTableFootFont==null || excelCellTableFootFont.getColor()==0)?ExcelConstant.EXCEL_TABLE_FOOT_FONT_COLOR:excelCellTableFootFont.getColor());
		cellTableFootFont.setFontHeightInPoints((excelCellTableFootFont==null || excelCellTableFootFont.getFontHeightInPoint()==0)?ExcelConstant.EXCEL_TABLE_FOOT_FONT_SIZE:excelCellTableFootFont.getFontHeightInPoint());
	}

	private static void convertCellTableFootStyle(ExcelXSSFCellStyle excelCellTableFootStyle, XSSFCellStyle cellTableFootStyle) {
		
		cellTableFootStyle.setAlignment((excelCellTableFootStyle==null || excelCellTableFootStyle.getAlignment()==0)?ExcelConstant.EXCEL_TABLE_FOOT_STYLE_ALIGNMENT:excelCellTableFootStyle.getAlignment());
		cellTableFootStyle.setBorderBottom((excelCellTableFootStyle==null)?0:excelCellTableFootStyle.getBorderBottom());
		cellTableFootStyle.setBorderTop((excelCellTableFootStyle==null)?0:excelCellTableFootStyle.getBorderTop());
		cellTableFootStyle.setBorderLeft((excelCellTableFootStyle==null)?0:excelCellTableFootStyle.getBorderLeft());
		cellTableFootStyle.setBorderRight((excelCellTableFootStyle==null)?0:excelCellTableFootStyle.getBorderRight());
		cellTableFootStyle.setWrapText((excelCellTableFootStyle==null)?true:excelCellTableFootStyle.isWrapText());
	}
	
	//设置表格页脚背景
	private void convertCellTableFootStyleByForeground(ExcelTableFoot excelTableFoot, XSSFCellStyle cellTableFootStyle) {
		if(excelTableFoot.isHaveForeground()) {
			if(excelTableFoot.isHaveSpecialForeground()) {

				cellTableFootStyle.setFillForegroundColor(ExcelConstant.EXCEL_TABLE_FOOT_SPECIAL_FILL_FOREGROUND_COLOR);
			
			} else {

				cellTableFootStyle.setFillForegroundColor(ExcelConstant.EXCEL_TABLE_FOOT_FILL_FOREGROUND_COLOR);
			}
			cellTableFootStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
	}
	
	private void setCellTableFootContent(XSSFCell cellTableFoot, ExcelTableFoot excelTableFoot, int rowCount) throws Exception {
		
		if(excelTableFoot.getCellFormula()!=null && !"".equals(excelTableFoot.getName())) {
			
			cellTableFoot.setCellType(XSSFCell.CELL_TYPE_FORMULA);
			cellTableFoot.setCellFormula(excelTableFoot.getCellFormula().replaceFirst(",", String.valueOf(excelTableBodyStartRow+1)).replaceFirst(",", String.valueOf(rowCount)));

		} else {
			
			cellTableFoot.setCellValue(excelTableFoot.getValue());
		}
	}
}
