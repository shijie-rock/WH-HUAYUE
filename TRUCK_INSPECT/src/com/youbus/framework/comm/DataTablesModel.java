/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.comm
 * ��   ��  ��:DataTablesModel.java
 * �� ������:2015��3��17��-����2:19:20
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.Serializable;
import java.util.List;

/**
 * ������:DataTablesModel
 * ������:
 * ������:suhaitao
 * �޸���:Administrator
 * �޸�ʱ��:2015��3��17�� ����2:19:20
 * �޸ı�ע:
 * @version 1.0.0
 */
public class DataTablesModel<M> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int iTotalRecords; 
	
    private int iTotalDisplayRecords; 
    
    private String sEcho; //�������
    
    private Object[] aaData;    //������ʾ����
	
    public DataTablesModel() {}
    
    public DataTablesModel(int iTotalRecords, int iTotalDisplayRecords, String sEcho, Object[] aaData) {
        super();
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        this.sEcho = sEcho;
        this.aaData = aaData;
    }
    
    
    public long getiTotalRecords() {
        return iTotalRecords;
    }
    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }
    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }
    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
    public String getsEcho() {
        return sEcho;
    }
    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }
    public Object[] getAaData() {
        return aaData;
    }
    public void setAaData(Object[] aaData) {
        this.aaData = aaData;
    }

}
