/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.comm
 * 文   件  名:DataTablesModel.java
 * 创 建日期:2015年3月17日-下午2:19:20
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.Serializable;
import java.util.List;

/**
 * 类名称:DataTablesModel
 * 类描述:
 * 创建人:suhaitao
 * 修改人:Administrator
 * 修改时间:2015年3月17日 下午2:19:20
 * 修改备注:
 * @version 1.0.0
 */
public class DataTablesModel<M> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int iTotalRecords; 
	
    private int iTotalDisplayRecords; 
    
    private String sEcho; //请求次数
    
    private Object[] aaData;    //画面显示行数
	
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
