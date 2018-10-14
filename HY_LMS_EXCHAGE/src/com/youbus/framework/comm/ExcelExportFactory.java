/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:ExcelExportFactory.java
 * 创 建日期:2015年5月14日-上午11:18:46
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.List;

import net.sf.ehcache.Element;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.infoservice.framework.services.DBService;
import com.infoservice.po.POFactory;
import com.youbus.paycenter.base.po.TsDataDicPO;

/**
 * 类名称:ExcelExportFactory 类描述: 创建人:Zero 修改人:Zero 修改时间:2015年5月14日 上午11:18:46
 * 修改备注:
 * 
 * @version 1.0.0
 */
public class ExcelExportFactory {

	/**
	 * 方 法 名:main 方法描述: 参 数:@param args 返 回 值:void 创 建 人:Zero
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static File exportTravel(Integer mainId) {
		File file = new File(YBCommonContant.TRAVEL_EXCEL_PATH);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在");
			file.mkdir();

			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("行程单一");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();

			// style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			String[] strs = { "订单号", "用车日期", "结束日期", "客户姓名", "客户电话", "产品类型",
					"起点", "目的地", "座位数", "车型", "联系人", "联系电话", "联系备住", "行程日期",
					"行程时间", "行程地点", "行程备注" };
			HSSFCell cell = row.createCell((short) 0);

			for (int i = 0; i < strs.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(strs[i]);
				// cell.setCellStyle(style);
			}

			// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
			Connection conn = DBService.getInstance().getConnection();

			TmBusTripMainPO mainPO = new TmBusTripMainPO();
			mainPO.setId(mainId);
			mainPO.setStatus("1");

			TmBusTripMainPO main = POFactory.getByPriKey(conn, mainPO);

			TmBusTripContectPO contectPO = new TmBusTripContectPO();
			contectPO.setBusTripNo(main.getBusTripNo());
			contectPO.setStatus("1");

			TmBusTripContectPO contect = POFactory.getByPriKey(conn, contectPO);

			TmBusTripDailyPO dailyPO = new TmBusTripDailyPO();
			dailyPO.setBusTripNo(main.getBusTripNo());
			dailyPO.setStatus("1");

			List<TmBusTripDailyPO> dailyPOs = POFactory.select(conn, dailyPO);
			System.out.println("busTripNo==" + main.getBusTripNo());
			System.out.println("dailyPOs==" + dailyPOs.size());
			int i = 0;
			if (dailyPOs.size() > 0) {
				System.out.println("==" + dailyPOs.size());
				for (TmBusTripDailyPO daily : dailyPOs) {
					TmBusTripViaPO viaPO = new TmBusTripViaPO();
					viaPO.setBusTripDId(daily.getId());
					viaPO.setStatus("1");

					List<TmBusTripViaPO> viaPOs = POFactory.select(conn, viaPO);
					System.out.println("==" + viaPOs.size());
					if (viaPOs.size() > 0) {
						for (TmBusTripViaPO via : viaPOs) {
							row = sheet.createRow((int) i + 1);
							// 第四步，创建单元格，并设置值
							if (i == 0) {
								row.createCell((short) 0).setCellValue(
										main.getTripTaskNo());
								row.createCell((short) 1).setCellValue(
										Conversion.getStrFormat(main
												.getTripBeginTime()).substring(0,10));
								row.createCell((short) 2).setCellValue(
										Conversion.getStrFormat(main
												.getTripEndTime()).substring(0,10));
								row.createCell((short) 3).setCellValue(
										main.getCustName());
								row.createCell((short) 4).setCellValue(
										main.getCustMobile());
								row.createCell((short) 5).setCellValue(
										getStr("TRIP_TYPE",main.getTripType()));
								row.createCell((short) 6).setCellValue(
										main.getCity());
								row.createCell((short) 7).setCellValue(
										main.getCityTarget());
								row.createCell((short) 8).setCellValue(
										getStr("BUS_TYPE",main.getBusType()));
								row.createCell((short) 9).setCellValue(
										contect.getContactName());
								row.createCell((short) 10).setCellValue(
										contect.getContactPhone());
								row.createCell((short) 11).setCellValue(
										contect.getRemark());
							}
							row.createCell((short) 12).setCellValue(
									Conversion.getStr(daily.getDate()));
							row.createCell((short) 13).setCellValue(
									Conversion.getStrFormat(via.getPlanTime())
											.substring(11, 16));
							row.createCell((short) 14).setCellValue(
									via.getAddress());
							row.createCell((short) 15).setCellValue(
									via.getRemark());

							i++;
						}
					} else {
						System.out.println(i + "==="
								+ Conversion.getStr(daily.getDate()));
						row = sheet.createRow((int) i + 1);
						// 第四步，创建单元格，并设置值
						if (i == 0) {
							row.createCell((short) 0).setCellValue(
									main.getTripTaskNo());
							row.createCell((short) 1).setCellValue(
									Conversion.getStrFormat(main
											.getTripBeginTime()).substring(0,10));
							row.createCell((short) 2).setCellValue(
									Conversion.getStrFormat(main
											.getTripEndTime()).substring(0,10));
							row.createCell((short) 3).setCellValue(
									main.getCustName());
							row.createCell((short) 4).setCellValue(
									main.getCustMobile());
							row.createCell((short) 5).setCellValue(
									getStr("TRIP_TYPE",main.getTripType()));
							row.createCell((short) 6).setCellValue(
									main.getCity());
							row.createCell((short) 7).setCellValue(
									main.getCityTarget());
							row.createCell((short) 8).setCellValue(
									getStr("BUS_TYPE",main.getBusType()));
							row.createCell((short) 9).setCellValue(
									contect.getContactName());
							row.createCell((short) 10).setCellValue(
									contect.getContactPhone());
							row.createCell((short) 11).setCellValue(
									contect.getRemark());
						}
						row.createCell((short) 13).setCellValue(
								Conversion.getStr(daily.getDate()));

						i++;
					}
				}
			}
			// 第六步，将文件存到指定位置
			try {
				Integer random = (int) (Math.random() * 100000);
				String fileStr = file.getPath() + "/travel-" + random + ".xls";
				System.out.println("fileStr==" + fileStr);
				FileOutputStream fout = new FileOutputStream(fileStr);
				wb.write(fout);
				File resultFile = new File(fileStr);
				fout.close();
				return resultFile;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("//目录存在");

			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("行程单一");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();

			// style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			String[] strs = { "订单号", "用车日期", "结束日期", "客户姓名", "客户电话", "用车类型",
					"起点", "目的地", "车型", "联系人", "联系电话", "联系备住", "行程日期",
					"行程时间", "行程地点", "行程备注" };
			HSSFCell cell = row.createCell((short) 0);

			for (int i = 0; i < strs.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(strs[i]);
				// cell.setCellStyle(style);
			}

			// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
			Connection conn = DBService.getInstance().getConnection();

			TmBusTripMainPO mainPO = new TmBusTripMainPO();
			mainPO.setId(mainId);
			mainPO.setStatus("1");

			TmBusTripMainPO main = POFactory.getByPriKey(conn, mainPO);

			TmBusTripContectPO contectPO = new TmBusTripContectPO();
			contectPO.setBusTripNo(main.getBusTripNo());
			contectPO.setStatus("1");

			TmBusTripContectPO contect = POFactory.getByPriKey(conn, contectPO);
			System.out.println("busTripNo==" + main.getBusTripNo());
			TmBusTripDailyPO dailyPO = new TmBusTripDailyPO();
			dailyPO.setBusTripNo(main.getBusTripNo());
			dailyPO.setStatus("1");

			List<TmBusTripDailyPO> dailyPOs = POFactory.select(conn, dailyPO);
			System.out.println("dailyPOs==" + dailyPOs.size());

			int i = 0;
			if (dailyPOs.size() > 0 && dailyPOs != null) {
				for (TmBusTripDailyPO daily : dailyPOs) {
					TmBusTripViaPO viaPO = new TmBusTripViaPO();
					viaPO.setBusTripDId(daily.getId());
					viaPO.setStatus("1");

					List<TmBusTripViaPO> viaPOs = POFactory.select(conn, viaPO);
					
					if (viaPOs.size() > 0 && viaPOs != null) {
						for (TmBusTripViaPO via : viaPOs) {
							row = sheet.createRow((int) i + 1);
							// 第四步，创建单元格，并设置值
							if (i == 0) {
								row.createCell((short) 0).setCellValue(
										main.getTripTaskNo());
								row.createCell((short) 1).setCellValue(
										Conversion.getStrFormat(main
												.getTripBeginTime()).substring(0,10));
								row.createCell((short) 2).setCellValue(
										Conversion.getStrFormat(main
												.getTripEndTime()).substring(0,10));
								row.createCell((short) 3).setCellValue(
										main.getCustName());
								row.createCell((short) 4).setCellValue(
										main.getCustMobile());
								row.createCell((short) 5).setCellValue(
										getStr("TRIP_TYPE",main.getTripType()));
								row.createCell((short) 6).setCellValue(
										main.getCity());
								row.createCell((short) 7).setCellValue(
										main.getCityTarget());
								row.createCell((short) 8).setCellValue(
										getStr("BUS_TYPE",main.getBusType()));
								if (contect != null) {
									row.createCell((short) 9).setCellValue(
											contect.getContactName());
									row.createCell((short) 10).setCellValue(
											contect.getContactPhone());
									row.createCell((short) 11).setCellValue(
											contect.getRemark());
								}
							}

							if (daily != null) {
								row.createCell((short) 12).setCellValue(
										Conversion.getStr(daily.getDate()));
							}
							if (via != null) {
								row.createCell((short) 13).setCellValue(
										Conversion.getStrFormat(
												via.getPlanTime()).substring(
												11, 16));
								row.createCell((short) 14).setCellValue(
										via.getAddress());
								row.createCell((short) 15).setCellValue(
										via.getRemark());
							}
							i++;
						}
					} else {
						System.out.println(i + "==="
								+ Conversion.getStr(daily.getDate()));
						row = sheet.createRow((int) i + 1);
						// 第四步，创建单元格，并设置值
						if (i == 0) {
							row.createCell((short) 0).setCellValue(
									main.getTripTaskNo());
							row.createCell((short) 1).setCellValue(
									Conversion.getStrFormat(main
											.getTripBeginTime()).substring(0,10));
							row.createCell((short) 2).setCellValue(
									Conversion.getStrFormat(main
											.getTripEndTime()).substring(0,10));
							row.createCell((short) 3).setCellValue(
									main.getCustName());
							row.createCell((short) 4).setCellValue(
									main.getCustMobile());
							row.createCell((short) 5).setCellValue(
									getStr("TRIP_TYPE",main.getTripType()));
							row.createCell((short) 6).setCellValue(
									main.getCity());
							row.createCell((short) 7).setCellValue(
									main.getCityTarget());
							
							row.createCell((short) 8).setCellValue(
									getStr("BUS_TYPE",main.getBusType()));
							row.createCell((short) 9).setCellValue(
									contect.getContactName());
							row.createCell((short) 10).setCellValue(
									contect.getContactPhone());
							row.createCell((short) 11).setCellValue(
									contect.getRemark());
						}
						row.createCell((short) 12).setCellValue(
								Conversion.getStr(daily.getDate()));

						i++;

					}
				}
			}
			// 第六步，将文件存到指定位置
			try {
				Integer random = (int) (Math.random() * 100000);
				String fileStr = file.getPath() + "/行程单"+main.getBusTripNo()+"-" + random + ".xls";
				System.out.println("fileStr==" + fileStr);
				FileOutputStream fout = new FileOutputStream(fileStr);
				wb.write(fout);
				File resultFile = new File(fileStr);
				fout.close();
				return resultFile;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public static String getStr(String str,String value){
		Element e1=YoubusNativeCacheService.DATA_DIC_CACHE.get(str);
		List<TsDataDicPO> busGradeS=(List<TsDataDicPO>)e1.getValue();
		
		String type="";
		if(busGradeS!=null&&busGradeS.size()>0){
			for(TsDataDicPO dicPO:busGradeS){
				if(dicPO.getCode().equals(value)){
					type=dicPO.getCodeDesc();
				}
			}
				
		}
		
		return type;
	}

}


