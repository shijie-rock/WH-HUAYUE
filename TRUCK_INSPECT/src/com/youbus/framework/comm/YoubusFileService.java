/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YoubusFileService.java
 * 创 建日期:2015年5月14日-上午8:47:21
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import com.infoservice.fileserver.FileServer;
import com.infoservice.fileserver.FileServerImpl;
import com.infoservice.fileserver.po.FsFileInfoPO;
import com.infoservice.framework.Service;
import com.infoservice.framework.datacontext.FileObject;
import com.infoservice.framework.exceptions.FrameException;

/**
 * 类名称:YoubusFileService
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年5月14日 上午8:47:21
 * 修改备注:
 * @version 1.0.0
 */
public class YoubusFileService implements Service {

	private static YoubusFileService insObj = new YoubusFileService();
	private YoubusFileServerImpl server = new YoubusFileServerImpl();
//	private FileServer server = new YoubusFileServerImpl();
	
	public static YoubusFileService getInstance(){
		return insObj;
	}
	

	/* （非 Javadoc）
	 * @see com.infoservice.framework.Service#getName()
	 */
	public String getName() {
		return "YoubusFileService";
	}
	/**
	 * 创建一个新的实例 YoubusFileService.
	 */
	private YoubusFileService() {
		// TODO Auto-generated constructor stub
	}
	
	public FsFileInfoPO writeFileReturnObj(Connection conn,String AppID,String owner,String fname,InputStream ins,Date expireDate) throws Exception{
		return this.server.writeFileReturnObj(conn,AppID,owner,fname,ins,expireDate);
	}
	
	public Long writeFile(Connection conn,String AppID,String owner,String fname,InputStream ins,Date expireDate) throws Exception{
		return this.server.writeFile(conn,AppID,owner,fname,ins,expireDate);
	}
	
	public Long writeFile(Connection conn,String AppID,String owner,FileObject fobj,Date expireDate) throws Exception{
		return this.server.writeFile(conn,AppID,owner,fobj,expireDate);
	}
	
	public Long writeFile(Connection conn,String AppID,String owner,File fileName,Date expireDate) throws Exception{
		return this.server.writeFile(conn,AppID,owner,fileName,expireDate);
	}

	public boolean deleteFile(Connection conn, Long fileId,String AppId,String ownId) throws Exception{
		return this.server.deleteFile(conn,fileId,AppId,ownId);
	}
	
	public void readFile(Connection conn,Long fileId, String AppId, String ownId ,OutputStream out) throws Exception{
		this.server.readFile(conn,fileId,AppId,ownId,out);
	}
	
	public File getFile(Connection conn,Long fileId, String AppId, String ownId) throws Exception{
		return this.server.getFile(conn,fileId,AppId,ownId);
	}
	
	/**
	 * 
	 * 方   法  名:writeFileReturnObj
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param AppID
	 * 参         数:@param owner增加对象标示 比如车牌
	 * 参         数:@param fname
	 * 参         数:@param ins
	 * 参         数:@param expireDate
	 * 参         数:@param supType 增加大类 比如 CET_0010	CHECK_ENT_TYPE
	 * 参         数:@return
	 * 参         数:@throws Exception
	 * 返   回  值:FsFileInfoPO
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public FsFileInfoPO writeFileReturnObj(Connection conn,String AppID,String owner,String fname,InputStream ins,Date expireDate,String supType) throws Exception{
		return this.server.writeFileReturnObj(conn,AppID,owner,fname,ins,expireDate,supType);
	}
	
	
	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#destroyService()
	 */
	@Override
	public void destroyService() throws FrameException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.infoservice.framework.Service#initialize(java.util.Map)
	 */
	@Override
	public void initialize(Map arg0) throws FrameException {
		// TODO Auto-generated method stub

	}

}
