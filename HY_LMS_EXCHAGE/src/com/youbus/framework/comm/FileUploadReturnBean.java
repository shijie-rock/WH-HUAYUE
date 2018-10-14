/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.mytestaction
 * ��   ��  ��:FileUploadReturnBean.java
 * �� ������:2015��6��9��-����4:37:35
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import com.infoservice.po.DataBean;
import com.infoservice.po.POFactoryUtil;

/**
 * ������:FileUploadReturnBean
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��6��9�� ����4:37:35
 * �޸ı�ע:
 * @version 1.0.0
 */
public class FileUploadReturnBean implements DataBean<Object> {

	/**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1064831090448103096L;

	private String srcFilePath;//�ϴ�ԭͼλ��
	private int srcFileId;//�ϴ�ԭͼ���ݿ�洢id
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

	private String thumbFilePath;//����ͼλ��
	private int thumbFileId;// ����ͼ���ݿ�Id
	
	
	/**
	 * ����һ���µ�ʵ�� FileUploadReturnBean.
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
