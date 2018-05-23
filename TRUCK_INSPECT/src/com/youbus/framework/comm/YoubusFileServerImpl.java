/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:YoubusFileServerImpl.java
 * 创 建日期:2015年5月14日-上午8:51:58
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.infoservice.fileserver.FileServer;
import com.infoservice.fileserver.FileServerException;
import com.infoservice.fileserver.FileServerUtil;
import com.infoservice.fileserver.po.FsFileInfoPO;
import com.infoservice.fileserver.po.FsFileInfoPOFactory;
import com.infoservice.fileserver.po.FsFileSpacePO;
import com.infoservice.fileserver.po.FsFileSpacePOFactory;
import com.infoservice.framework.datacontext.FileObject;
import com.infoservice.po.POFactory;

/**
 * 类名称:YoubusFileServerImpl
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年5月14日 上午8:51:58
 * 修改备注:
 * @version 1.0.0
 */
public class YoubusFileServerImpl implements FileServer {

	/**
	 * 创建一个新的实例 YoubusFileServerImpl.
	 *
	 */
	public YoubusFileServerImpl() {
		// TODO Auto-generated constructor stub
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static long defFileSize = 1024 ;
	private static String ErrCode = "EC_FS_0001";

	public Long writeFile(Connection conn,String AppID,String owner,String fname,InputStream ins,Date expireDate) throws Exception{
		FsFileSpacePO space= FsFileSpacePOFactory.getValidSpaceByAppidAndFSize(conn,AppID,defFileSize);
		if ( space==null ) throw new FileServerException(ErrCode,"没有找到可以利用的存储空间或空间不足！[AppId="+AppID+"]",null);
		
		FsFileInfoPO finfo = new FsFileInfoPO();
		finfo.setFileSpaceId(space.getSpaceId());
		finfo.setAppId(AppID);
		finfo.setFileCreateDate(new Date());
		finfo.setFileId(POFactory.getLongPriKey(conn,finfo));
		finfo.setFileInvalidDate(expireDate);
		finfo.setFileName(fname);
		finfo.setFileOwner(owner);
		finfo.setNeedDelete(new Integer(0));
		
		String path= AppID==null?"":(File.separator+AppID);
		path += File.separator+sdf.format(new Date());
		path += owner==null?"":(File.separator+owner);	
		finfo.setFileRelationPath(path);
		
		String postFix = "";
		if ( fname.lastIndexOf(".") > 0 ){
			postFix = fname.substring(fname.lastIndexOf("."));
		}
		String newFileNameStr=UUID.randomUUID().toString().concat(postFix);
		finfo.setFileDiskName(newFileNameStr);
		File dir = new File(space.getSpacePath()+File.separator+finfo.getFileRelationPath());
		dir.mkdirs();
		try{
			int len=FileServerUtil.write2FileSpace(ins,dir.getAbsolutePath()+File.separator+finfo.getFileDiskName());
			finfo.setFileLength(new Long(len));
		}catch(Exception e){
			throw new FileServerException(ErrCode,"写文件失败!",e);
		}
		
		POFactory.insert(conn,finfo);
		//FsFileSpacePOFactory.updateFileSpaceUseSpace(conn,space,finfo.getFileLength().longValue());
		
		return finfo.getFileId();
	}
	
	/**
	 * 返回数据库 FsFileInfoPO对象
	 * 方   法  名:writeFileReturnObj
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param AppID
	 * 参         数:@param owner
	 * 参         数:@param fname
	 * 参         数:@param ins
	 * 参         数:@param expireDate
	 * 参         数:@return
	 * 参         数:@throws Exception
	 * 返   回  值:FsFileInfoPO
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public FsFileInfoPO writeFileReturnObj(Connection conn,String AppID,String owner,String fname,InputStream ins,Date expireDate) throws Exception{
		FsFileSpacePO space= FsFileSpacePOFactory.getValidSpaceByAppidAndFSize(conn,AppID,defFileSize);
		if ( space==null ) throw new FileServerException(ErrCode,"没有找到可以利用的存储空间或空间不足！[AppId="+AppID+"]",null);
		
		FsFileInfoPO finfo = new FsFileInfoPO();
		finfo.setFileSpaceId(space.getSpaceId());
		finfo.setAppId(AppID);
		finfo.setFileCreateDate(new Date());
		finfo.setFileId(POFactory.getLongPriKey(conn,finfo));
		finfo.setFileInvalidDate(expireDate);
		finfo.setFileName(fname);
		finfo.setFileOwner(owner);
		finfo.setNeedDelete(new Integer(0));
		
		String path= AppID==null?"":(File.separator+AppID);
		path += File.separator+sdf.format(new Date());
		path += owner==null?"":(File.separator+owner);	
		finfo.setFileRelationPath(path);
		
		String postFix = "";
		if ( fname.lastIndexOf(".") > 0 ){
			postFix = fname.substring(fname.lastIndexOf("."));
		}
		String newFileNameStr=UUID.randomUUID().toString().concat(postFix);
		finfo.setFileDiskName(newFileNameStr);
		File dir = new File(space.getSpacePath()+File.separator+finfo.getFileRelationPath());
		dir.mkdirs();
		try{
			int len=FileServerUtil.write2FileSpace(ins,dir.getAbsolutePath()+File.separator+finfo.getFileDiskName());
			finfo.setFileLength(new Long(len));
		}catch(Exception e){
			throw new FileServerException(ErrCode,"写文件失败!",e);
		}
		POFactory.insert(conn,finfo);
		//FsFileSpacePOFactory.updateFileSpaceUseSpace(conn,space,finfo.getFileLength().longValue());
		return finfo;
	}
	
	/**
	 * 
	 * 方   法  名:writeFileReturnObj
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param AppID
	 * 参         数:@param owner 增加对象标示 比如车牌
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
	public FsFileInfoPO writeFileReturnObj(Connection conn,String AppID,String owner,String fname,InputStream ins,Date expireDate,String supCode) throws Exception{
		FsFileSpacePO space= FsFileSpacePOFactory.getValidSpaceByAppidAndFSize(conn,AppID,defFileSize);
		if ( space==null ) throw new FileServerException(ErrCode,"没有找到可以利用的存储空间或空间不足！[AppId="+AppID+"]",null);
		
		FsFileInfoPO finfo = new FsFileInfoPO();
		finfo.setFileSpaceId(space.getSpaceId());
		finfo.setAppId(AppID);
		finfo.setFileCreateDate(new Date());
		finfo.setFileId(POFactory.getLongPriKey(conn,finfo));
		finfo.setFileInvalidDate(expireDate);
		finfo.setFileName(fname);
		finfo.setFileOwner(owner);
		finfo.setNeedDelete(new Integer(0));
		
		String path= AppID==null?"":(File.separator+AppID);
//		path += File.separator+sdf.format(new Date());
		path += supCode==null?"":(File.separator+supCode);	
		path += owner==null?"":(File.separator+owner);	
		finfo.setFileRelationPath(path);
		
		String postFix = "";
		if ( fname.lastIndexOf(".") > 0 ){
			postFix = fname.substring(fname.lastIndexOf("."));
		}
		String newFileNameStr=UUID.randomUUID().toString().concat(postFix);
		finfo.setFileDiskName(newFileNameStr);
		File dir = new File(space.getSpacePath()+File.separator+finfo.getFileRelationPath());
		dir.mkdirs();
		try{
			int len=FileServerUtil.write2FileSpace(ins,dir.getAbsolutePath()+File.separator+finfo.getFileDiskName());
			finfo.setFileLength(new Long(len));
		}catch(Exception e){
			throw new FileServerException(ErrCode,"写文件失败!",e);
		}
		POFactory.insert(conn,finfo);
		//FsFileSpacePOFactory.updateFileSpaceUseSpace(conn,space,finfo.getFileLength().longValue());
		return finfo;
	}
	
	
	/**
	 * 保存缩略图功能
	 * 方   法  名:writethumbFileReturnObj
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param AppID
	 * 参         数:@param owner
	 * 参         数:@param fname
	 * 参         数:@param ins
	 * 参         数:@param expireDate
	 * 参         数:@return
	 * 参         数:@throws Exception
	 * 返   回  值:FsFileInfoPO
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public FsFileInfoPO writethumbFileReturnObj(Connection conn,String AppID,String owner,String fname,InputStream ins,Date expireDate) throws Exception{
		FsFileSpacePO space= FsFileSpacePOFactory.getValidSpaceByAppidAndFSize(conn,AppID,defFileSize);
		if ( space==null ) throw new FileServerException(ErrCode,"没有找到可以利用的存储空间或空间不足！[AppId="+AppID+"]",null);
		
		FsFileInfoPO finfo = new FsFileInfoPO();
		finfo.setFileSpaceId(space.getSpaceId());
		finfo.setAppId(AppID);
		finfo.setFileCreateDate(new Date());
		finfo.setFileId(POFactory.getLongPriKey(conn,finfo));
		finfo.setFileInvalidDate(expireDate);
		finfo.setFileName(fname);
		finfo.setFileOwner(owner);
		finfo.setNeedDelete(new Integer(0));
		
		String path= AppID==null?"":(File.separator+AppID);
		path += File.separator+sdf.format(new Date());
		path += owner==null?"":(File.separator+owner);	
		path += File.separator+YBCommonContant.FILE_SPACE_THUMB_DIR;//
		finfo.setFileRelationPath(path);
		
		String postFix = "";
		if ( fname.lastIndexOf(".") > 0 ){
			postFix = fname.substring(fname.lastIndexOf("."));
		}
		String newFileNameStr=UUID.randomUUID().toString().concat(postFix);
		finfo.setFileDiskName(newFileNameStr);
		File dir = new File(space.getSpacePath()+File.separator+finfo.getFileRelationPath());
		dir.mkdirs();
		try{
			int len=FileServerUtil.write2FileSpace(ins,dir.getAbsolutePath()+File.separator+finfo.getFileDiskName());
			finfo.setFileLength(new Long(len));
		}catch(Exception e){
			throw new FileServerException(ErrCode,"写文件失败!",e);
		}
		POFactory.insert(conn,finfo);
		//FsFileSpacePOFactory.updateFileSpaceUseSpace(conn,space,finfo.getFileLength().longValue());
		return finfo;
	}
	
	
	public Long writeFile(Connection conn,String AppID,String owner,FileObject fobj,Date expireDate) throws Exception{
		FsFileSpacePO space= FsFileSpacePOFactory.getValidSpaceByAppidAndFSize(conn,AppID,fobj.getLength());
		if ( space==null ) throw new FileServerException(ErrCode,"没有找到可以利用的存储空间或空间不足！[AppId="+AppID+"]",null);
		
		FsFileInfoPO finfo = new FsFileInfoPO();
		finfo.setFileSpaceId(space.getSpaceId());
		finfo.setAppId(AppID);
		finfo.setFileCreateDate(new Date());
		finfo.setFileId(POFactory.getLongPriKey(conn,finfo));
		finfo.setFileInvalidDate(expireDate);
		finfo.setFileName(fobj.getFileName());
		finfo.setFileOwner(owner);
		finfo.setNeedDelete(new Integer(0));
		
		String path= AppID==null?"":(File.separator+AppID);
		path += File.separator+sdf.format(new Date());
		path += owner==null?"":(File.separator+owner);		
		finfo.setFileRelationPath(path);
		
		String postFix = "";
		if ( fobj.getFileName().lastIndexOf(".") > 0 ){
			postFix = fobj.getFileName().substring(fobj.getFileName().lastIndexOf("."));
		}
		finfo.setFileDiskName(finfo.getFileId().toString()+postFix);
		

		File dir = new File(space.getSpacePath()+File.separator+finfo.getFileRelationPath());
		dir.mkdirs();
		
		try{
			fobj.writeToFile(dir.getAbsolutePath()+File.separator+finfo.getFileDiskName());
			finfo.setFileLength(new Long(fobj.getLength()));
		}catch(Exception e){
			throw new FileServerException(ErrCode,"写文件失败!",e);
		}
		
		POFactory.insert(conn,finfo);
		//FsFileSpacePOFactory.updateFileSpaceUseSpace(conn,space,finfo.getFileLength().longValue());
		
		return finfo.getFileId();
	}
	
	public Long writeFile(Connection conn,String AppID,String owner,File file,Date expireDate) throws Exception{
		FsFileSpacePO space= FsFileSpacePOFactory.getValidSpaceByAppidAndFSize(conn,AppID,defFileSize);
		if ( space==null ) throw new FileServerException(ErrCode,"没有找到可以利用的存储空间或空间不足！[AppId="+AppID+"]",null);
		
		FsFileInfoPO finfo = new FsFileInfoPO();
		finfo.setFileSpaceId(space.getSpaceId());
		finfo.setAppId(AppID);
		finfo.setFileCreateDate(new Date());
		finfo.setFileId(POFactory.getLongPriKey(conn,finfo));
		finfo.setFileInvalidDate(expireDate);
		finfo.setFileName(file.getName());
		finfo.setFileOwner(owner);
		finfo.setNeedDelete(new Integer(0));
		
		String path= AppID==null?"":(File.separator+AppID);
		path += File.separator+sdf.format(new Date());
		path += owner==null?"":(File.separator+owner);		
		finfo.setFileRelationPath(path);
		
		String postFix = "";
		if ( file.getName().lastIndexOf(".") > 0 ){
			postFix = file.getName().substring(file.getName().lastIndexOf("."));
		}
		finfo.setFileDiskName(finfo.getFileId().toString()+postFix);
		

		File dir = new File(space.getSpacePath()+File.separator+finfo.getFileRelationPath());
		dir.mkdirs();
		
		InputStream is=null;
		try{
			is = new FileInputStream(file);
			int len=FileServerUtil.write2FileSpace(is,dir.getAbsolutePath()+File.separator+finfo.getFileDiskName());
			finfo.setFileLength(new Long(len));
		}catch(Exception e){
			throw new FileServerException(ErrCode,"写文件失败!",e);
		}finally{
			if ( is!=null ) is.close();
		}
		
		POFactory.insert(conn,finfo);
		//FsFileSpacePOFactory.updateFileSpaceUseSpace(conn,space,finfo.getFileLength().longValue());
		
		return finfo.getFileId();
	}

	/* （非 Javadoc）
	 * @see com.infoservice.fileserver.FileServer#deleteFile(java.sql.Connection, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public boolean deleteFile(Connection conn,Long fileId,String AppId,String ownId) throws Exception{
		FsFileInfoPO fpo = FsFileInfoPOFactory.getByPriKey(conn,fileId,AppId,ownId);
		if (fpo==null) return false;
		
		FsFileSpacePO spacePO = FsFileSpacePOFactory.getByPriKey(conn,fpo.getFileSpaceId(),fpo.getAppId());
		if ( spacePO==null ) return false;
		
		String file = FileServerUtil.getRealFile(spacePO,fpo);
		if ( FileServerUtil.deleteFile(file) ){
			FsFileInfoPOFactory.deleteByPriKey(conn,fileId,AppId,ownId,false);
		}else{
			FsFileInfoPOFactory.deleteByPriKey(conn,fileId,AppId,ownId,true);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see com.infoservice.fileserver.FileServer#readFile(java.lang.Long, java.lang.String, java.lang.String, java.io.OutputStream)
	 */
	public void readFile(Connection conn,Long fileId,String AppId,String ownId,OutputStream out) throws Exception{
		FsFileInfoPO fpo = FsFileInfoPOFactory.getByPriKey(conn,fileId,AppId,ownId);
		if (fpo==null) return ;
		
		FsFileSpacePO spacePO = FsFileSpacePOFactory.getByPriKey(conn,fpo.getFileSpaceId(),fpo.getAppId());
		if ( spacePO==null ) return ;

		String fi = FileServerUtil.getRealFile(spacePO,fpo);
		FileServerUtil.readFileToOutputStream(fi,out);
	}
	
	public File getFile(Connection conn,Long fileId,String AppId,String ownId) throws Exception{
		FsFileInfoPO fpo = FsFileInfoPOFactory.getByPriKey(conn,fileId,AppId,ownId);
		if (fpo==null) return null;
		
		FsFileSpacePO spacePO = FsFileSpacePOFactory.getByPriKey(conn,fpo.getFileSpaceId(),fpo.getAppId());
		if ( spacePO==null ) return null;

		String fi = FileServerUtil.getRealFile(spacePO,fpo);
		return new File(fi);
	}
	
	public static void main(String args[]) throws Exception{
	}

}
