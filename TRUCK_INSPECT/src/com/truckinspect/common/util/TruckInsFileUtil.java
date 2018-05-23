/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.common.util
 * ��   ��  ��:TruckInsFileUtil.java
 * �� ������:2018��3��1��-����4:55:04
 * Copyright @ 2018-YouBus.com.cn
 */
package com.truckinspect.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

import com.infoservice.fileserver.po.FsFileInfoPO;
import com.infoservice.po.DynaBean;
import com.youbus.framework.comm.TruckInsNativeCacheService;
import com.youbus.framework.comm.YoubusFileService;

/**
 * ������:TruckInsFileUtil
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2018��3��1�� ����4:55:04
 * �޸ı�ע:
 * @version 1.0.0
 */
public class TruckInsFileUtil {
	private static Logger log=Logger.getLogger(TruckInsFileUtil.class);
	/**
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * ��   ��  ��:saveUploadImgFile
	 * ��������:
	 * ��         ��:@param conn
	 * ��         ��:@param fileName ԭʼ�ļ�����
	 * ��         ��:@param fileBase64 ͼƬbase64�ַ���
	 * ��         ��:@param imgSubType ͼƬ���ࣺ���糵�� ��CHECK_ENT_TYPE :CET_0010
	 * ��         ��:@param imgObjCode ͼƬС�ࣺ���룺���糵�ƺŵ�
	 * ��         ��:@return
	 * ��   ��  ֵ:DynaBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean saveUploadImgFile(Connection conn,String fileName,String fileBase64,String imgSubType,String imgObjCode){
		
		String imgType = "jpg";
		if(fileName.lastIndexOf(".")!=-1){
			imgType = fileName.substring(fileName.lastIndexOf(".")+1);
		}
		if(fileBase64.split("base64,")[0].indexOf("png")!=-1){
			imgType = "png";
		}
		if (fileBase64.indexOf("base64,") != -1) {
			fileBase64 = fileBase64.substring(fileBase64.indexOf("base64,")
					+ "base64,".length());
		}
		BASE64Decoder decoder = new BASE64Decoder();
		ByteArrayInputStream input=null;
		try {
			// Base64����
			byte[] b = decoder.decodeBuffer(fileBase64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// �����쳣����
					b[i] += 256;
				}
			}
			// ����jpegͼƬ
			input = new ByteArrayInputStream(b);
			input.close();
//			ByteArrayInputStream input = new ByteArrayInputStream(imgStr.getBytes());
			/*out.write(b);
			out.flush();
			out.close();*/
			
			FsFileInfoPO fileInfo=YoubusFileService.getInstance().writeFileReturnObj(conn, TruckInsNativeCacheService.APP_CODE,imgObjCode, fileName, input, null,imgSubType);
			String realPath=fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();
			log.debug("realPath:="+realPath);
			String srcFileServerPath=TruckInsNativeCacheService.FILE_SERVER_PRIFIX_URL+fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();
			log.debug("srcFileServerPath:="+srcFileServerPath);
			if(StringUtils.isNotBlank(srcFileServerPath)){
				
				DynaBean bean=new DynaBean("FILE_INFO");
				bean.add("FILE_URL", srcFileServerPath);
				bean.add("FILE_ID", fileInfo.getFileId());
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("PARSE IMG ERROR");
		}finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	

}
