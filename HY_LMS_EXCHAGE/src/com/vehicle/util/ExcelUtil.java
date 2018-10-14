/**
 * 项目名称:GPS_DATA
 * 包         名:com.vehicle.util
 * 文   件  名:ExcelUtil.java
 * 创 建日期:2017年4月14日-下午3:45:35
 * Copyright @ 2017-YouBus.com.cn
 */
package com.vehicle.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.vehicle.bean.VehicleInfoBean;

/**
 * 类名称:ExcelUtil
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年4月14日 下午3:45:35
 * 修改备注:
 * @version 1.0.0
 */
public class ExcelUtil {

	/**
	 * 方   法  名:main
	 * 方法描述:
	 * 参         数:@param args
	 * 返   回  值:void
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {//test
			OutputStream stream = new FileOutputStream("d://11output.xls");
			getExcleWorkbook(null).write(stream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String SHEET_TITILE="车辆信息";
	
	public static HSSFWorkbook getExcleWorkbook(List<VehicleInfoBean> list){
		
		 HSSFWorkbook workbook = new HSSFWorkbook();  
		 HSSFSheet sheet = workbook.createSheet(SHEET_TITILE);
		 sheet.setDefaultColumnWidth(12);  
		 sheet.createFreezePane( 0, 1, 0, 1 ); //冻结首行
		 //标题style
		 HSSFCellStyle style = workbook.createCellStyle();  
		 
		 style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);  
	     style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//	     style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	     style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	     style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
//	     style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	     style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	     style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	     
	     HSSFFont font = workbook.createFont();  
	     font.setColor(HSSFColor.DARK_BLUE.index);  
//	     font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	     font.setFontHeight((short)220);
	     font.setFontName("微软雅黑");
	     style.setFont(font); 
//	     
	     //正文style
	     HSSFCellStyle style2 = workbook.createCellStyle();  
//	     style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
//	     style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//	     style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
//	     style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
//	     style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
//	     style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
//	     style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	     style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
	     
	     HSSFFont font2 = workbook.createFont(); 
	     font2.setColor(HSSFColor.BLACK.index);  
//	     font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	     font2.setFontHeight((short)200);
	     font2.setFontName("微软雅黑");
	     style2.setFont(font2); 
		 
		 //首行 标题
	     HSSFCell cell;
	     int COLUMN_NUM=0;
		 HSSFRow row = (HSSFRow) sheet.createRow(0);
		 row.setHeight((short) (25 * 20)); //设置行高
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("车牌"));//carnum
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("车辆id"));//truckid
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("厢型"));//carriagetype
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("来源机构"));//fromorgcode
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("车辆类型"));//fromtype
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("里程 KM"));//gmileage
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("设备号"));//gpsno
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("车长"));//length
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("车辆品牌"));//name
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("机构"));//orgcode
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("状态"));//status
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("状态码"));//status_code
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("适用类型"));//usetype
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("车辆用途"));//usefor
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("使用部门"));//usedept
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("额定体积"));//volume
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("里程计算截止时间"));//counttime
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("运行时长"));//duration
		 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style);cell.setCellValue(new HSSFRichTextString("车重"));//weight
		 
		 /* */
		 for(int i=0;i<list.size();i++){
			 VehicleInfoBean bean=list.get(i);
			 row = (HSSFRow) sheet.createRow(i+1);
			 row.setHeight((short) (20 * 20)); //设置行高
			 COLUMN_NUM=0;
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getCarnum()));//carnum
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getTruckid()));//truckid
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getCarriagetype()));//carriagetype
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getFromorgcode()));//fromorgcode
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getFromtype()));//fromtype
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getGmileage()));//gmileage
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getGpsno()));//gpsno
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getLength()));//length
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getName()));//name
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getOrgcode()));//orgcode
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getStatus()));//status
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getStatus_code()));//status_code
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getUsetype()));//usetype
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getUsefor()));//usefor
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getUsedept()));//usedept
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getVolume()));//volume
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getCounttime()));//counttime
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getDuration()));//duration
			 cell = row.createCell(COLUMN_NUM++);cell.setCellStyle(style2);cell.setCellValue(new HSSFRichTextString(bean.getWeight()));//weight
		 }
		
		return workbook;
	}

}
