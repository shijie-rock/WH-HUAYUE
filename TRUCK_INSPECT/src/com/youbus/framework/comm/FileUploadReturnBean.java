/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.mytestaction
 * 文   件  名:FileUploadReturnBean.java
 * 创 建日期:2015年6月9日-下午4:37:35
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * 类名称:FileUploadReturnBean
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2015年6月9日 下午4:37:35
 * 修改备注:
 * @version 1.0.0
 */
public class FileUploadReturnBean implements DataBean<Object> {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1064831090448103096L;

	private String srcFilePath;//上传原图位置
	private int srcFileId;//上传原图数据库存储id
	/**
	 * srcFilePath
	 *
	 * @return  the srcFilePath
	 * @since   1.0.0
	 */
	
	public String getSrcFilePath() {
		return srcFilePath;
	}

	/**
	 * @param srcFilePath the srcFilePath to set
	 */
	public void setSrcFilePath(String srcFilePath) {
		this.srcFilePath = srcFilePath;
	}

	/**
	 * srcFileId
	 *
	 * @return  the srcFileId
	 * @since   1.0.0
	 */
	
	public int getSrcFileId() {
		return srcFileId;
	}

	/**
	 * @param srcFileId the srcFileId to set
	 */
	public void setSrcFileId(int srcFileId) {
		this.srcFileId = srcFileId;
	}

	/**
	 * thumbFilePath
	 *
	 * @return  the thumbFilePath
	 * @since   1.0.0
	 */
	
	public String getThumbFilePath() {
		return thumbFilePath;
	}

	/**
	 * @param thumbFilePath the thumbFilePath to set
	 */
	public void setThumbFilePath(String thumbFilePath) {
		this.thumbFilePath = thumbFilePath;
	}

	/**
	 * thumbFileId
	 *
	 * @return  the thumbFileId
	 * @since   1.0.0
	 */
	
	public int getThumbFileId() {
		return thumbFileId;
	}

	/**
	 * @param thumbFileId the thumbFileId to set
	 */
	public void setThumbFileId(int thumbFileId) {
		this.thumbFileId = thumbFileId;
	}

	/**
	 * serialversionuid
	 *
	 * @return  the serialversionuid
	 * @since   1.0.0
	 */
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String thumbFilePath;//缩略图位置
	private int thumbFileId;// 缩略图数据库Id
	
	
	/**
	 * 创建一个新的实例 FileUploadReturnBean.
	 *
	 */
	public FileUploadReturnBean() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.infoservice.po.DataBean#toXMLString()
	 */
	@Override
	public String toXMLString() {
		// TODO Auto-generated method stub
		return POFactoryUtil.beanToXmlString(this);
	}

}
