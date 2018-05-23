/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:YoubusFileService.java
 * �� ������:2015��5��14��-����8:47:21
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
 * ������:YoubusFileService
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��5��14�� ����8:47:21
 * �޸ı�ע:
 * @version 1.0.0
 */
public class YoubusFileService implements Service {

	private static YoubusFileService insObj = new YoubusFileService();
	private YoubusFileServerImpl server = new YoubusFileServerImpl();
//	private FileServer server = new YoubusFileServerImpl();
	
	public static YoubusFileService getInstance(){
		return insObj;
	}
	

	/* ���� Javadoc��
	 * @see com.infoservice.framework.Service#getName()
	 */
	public String getName() {
		return "YoubusFileService";
	}
	/**
	 * ����һ���µ�ʵ�� YoubusFileService.
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
	 * ��   ��  ��:writeFileReturnObj
	 * ��������:
	 * ��         ��:@param conn
	 * ��         ��:@param AppID
	 * ��         ��:@param owner���Ӷ����ʾ ���糵��
	 * ��         ��:@param fname
	 * ��         ��:@param ins
	 * ��         ��:@param expireDate
	 * ��         ��:@param supType ���Ӵ��� ���� CET_0010	CHECK_ENT_TYPE
	 * ��         ��:@return
	 * ��         ��:@throws Exception
	 * ��   ��  ֵ:FsFileInfoPO
	 * ��   ��  ��:rock
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
