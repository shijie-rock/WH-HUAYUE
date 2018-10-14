/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:ExcelExportFactory.java
 * �� ������:2015��5��14��-����11:18:46
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
 * ������:ExcelExportFactory ������: ������:Zero �޸���:Zero �޸�ʱ��:2015��5��14�� ����11:18:46
 * �޸ı�ע:
 * 
 * @version 1.0.0
 */
public class ExcelExportFactory {

	/**
	 * �� �� ��:main ��������: �� ��:@param args �� �� ֵ:void �� �� ��:Zero
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static File exportTravel(Integer mainId) {
		File file = new File(YBCommonContant.TRAVEL_EXCEL_PATH);
		// ����ļ��в������򴴽�
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//������");
			file.mkdir();

			HSSFWorkbook wb = new HSSFWorkbook();
			// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
			HSSFSheet sheet = wb.createSheet("�г̵�һ");
			// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
			HSSFRow row = sheet.createRow((int) 0);
			// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
			HSSFCellStyle style = wb.createCellStyle();

			// style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ

			String[] strs = { "������", "�ó�����", "��������", "�ͻ�����", "�ͻ��绰", "��Ʒ����",
					"���", "Ŀ�ĵ�", "��λ��", "����", "��ϵ��", "��ϵ�绰", "��ϵ��ס", "�г�����",
					"�г�ʱ��", "�г̵ص�", "�г̱�ע" };
			HSSFCell cell = row.createCell((short) 0);

			for (int i = 0; i < strs.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(strs[i]);
				// cell.setCellStyle(style);
			}

			// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
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
							// ���Ĳ���������Ԫ�񣬲�����ֵ
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
						// ���Ĳ���������Ԫ�񣬲�����ֵ
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
			// �����������ļ��浽ָ��λ��
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
			System.out.println("//Ŀ¼����");

			HSSFWorkbook wb = new HSSFWorkbook();
			// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
			HSSFSheet sheet = wb.createSheet("�г̵�һ");
			// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
			HSSFRow row = sheet.createRow((int) 0);
			// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
			HSSFCellStyle style = wb.createCellStyle();

			// style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ

			String[] strs = { "������", "�ó�����", "��������", "�ͻ�����", "�ͻ��绰", "�ó�����",
					"���", "Ŀ�ĵ�", "����", "��ϵ��", "��ϵ�绰", "��ϵ��ס", "�г�����",
					"�г�ʱ��", "�г̵ص�", "�г̱�ע" };
			HSSFCell cell = row.createCell((short) 0);

			for (int i = 0; i < strs.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(strs[i]);
				// cell.setCellStyle(style);
			}

			// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
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
							// ���Ĳ���������Ԫ�񣬲�����ֵ
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
						// ���Ĳ���������Ԫ�񣬲�����ֵ
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
			// �����������ļ��浽ָ��λ��
			try {
				Integer random = (int) (Math.random() * 100000);
				String fileStr = file.getPath() + "/�г̵�"+main.getBusTripNo()+"-" + random + ".xls";
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


