/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.common.util
 * 文   件  名:TruckInsFileUtil.java
 * 创 建日期:2018年3月1日-下午4:55:04
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
 * 类名称:TruckInsFileUtil
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2018年3月1日 下午4:55:04
 * 修改备注:
 * @version 1.0.0
 */
public class TruckInsFileUtil {
	private static Logger log=Logger.getLogger(TruckInsFileUtil.class);
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

	}
	
	/**
	 * 
	 * 方   法  名:saveUploadImgFile
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param fileName 原始文件名称
	 * 参         数:@param fileBase64 图片base64字符串
	 * 参         数:@param imgSubType 图片大类：比如车辆 ：CHECK_ENT_TYPE :CET_0010
	 * 参         数:@param imgObjCode 图片小类：代码：比如车牌号等
	 * 参         数:@return
	 * 返   回  值:DynaBean
	 * 创   建  人:rock
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
			// Base64解码
			byte[] b = decoder.decodeBuffer(fileBase64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
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
