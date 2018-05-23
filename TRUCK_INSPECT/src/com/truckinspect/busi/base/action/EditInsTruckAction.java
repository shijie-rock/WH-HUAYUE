/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:EditMemberAction.java
 * 创 建日期:2017年9月2日-上午9:25:12
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.base.action;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;

import com.infoservice.framework.ActionImpl;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.truckinspect.busi.base.po.TmInsTruckInfoPO;
import com.truckinspect.busi.base.po.TtInsTruckImgPO;
import com.truckinspect.busi.base.po.TtInsTruckMidEntMapPO;
import com.truckinspect.busi.base.po.TtInsTruckSubObjMapPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.truckinspect.common.util.TruckInsFileUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:EditInsTruckAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:25:12
 * 修改备注:
 * @version 1.0.0
 */
public class EditInsTruckAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		Integer truckId=atx.getIntValue("TRUCK_ID",0);
		String truckImgData=atx.getStringValue("TRUCK_IMG_DATA");
		String truckFileName=atx.getStringValue("TRUCK_IMG_FILE_NAME");
		
		String truckType=atx.getStringValue("TRUCK_TYPE");
//		String truckLicense=atx.getStringValue("TRUCK_LICENSE");
		String truckVin=atx.getStringValue("TRUCK_VIN");
		String truckDLevel=atx.getStringValue("TRUCK_D_LEVEL");
		String truckDriverName=atx.getStringValue("TRUCK_DRIVER_NAME");
		String truckBelongType=atx.getStringValue("TRUCK_BELONG_TYPE");
		String truckBrand=atx.getStringValue("TRUCK_BRAND");
		String truckModel=atx.getStringValue("TRUCK_MODEL");
		String truckMakeDate=atx.getStringValue("TRUCK_MAKE_DATE");
		String truckLicenseDate=atx.getStringValue("TRUCK_LICENSE_DATE");
		String truckDesc=atx.getStringValue("TRUCK_DESC");
		String truckLength=atx.getStringValue("TRUCK_LENGTH");
		String truckHeight=atx.getStringValue("TRUCK_HEIGHT");
		String truckWeight=atx.getStringValue("TRUCK_WEIGHT");
		String truckWidth=atx.getStringValue("TRUCK_WIDTH");
		String truckColor=atx.getStringValue("TRUCK_COLOR");
		
		String subObjStr=atx.getStringValue("SUB_OBJ_STR");//objMidCode$$objSubcode,objMidCode$$objSubcode
		String subEntStr=atx.getStringValue("SUB_ENT_STR");//midCode$$subCode,midCode$$subCode

		//check param
		if(truckId==null||truckId==0){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_EDIT_ACTION_ERR_1000", "编辑车辆：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		TmInsTruckInfoPO truckPOCon=new TmInsTruckInfoPO();
		truckPOCon.setId(truckId);
		truckPOCon.setStatus("1");
		
		TmInsTruckInfoPO truckPOResult=POFactory.getByPriKey(conn, truckPOCon);
		if(truckPOResult==null){
			logger.error(" TRUCK NOT EXIST .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_EDIT_ACTION_ERR_2000", "编辑车辆：车辆不存在", null);
			return 0;
		}
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		int version=truckPOResult.getVer();
		truckPOCon.setVer(version);

		TmInsTruckInfoPO truckPOValue=new TmInsTruckInfoPO();
		
		truckPOValue.setUpdateBy(optMemberId);
		truckPOValue.setUpdateTime(YBUtility.now());
		truckPOValue.setVer(version+1);
		
		if(StringUtils.isNotBlank(truckType))
			truckPOValue.setTruckType(truckType);
		
		truckPOValue.setBrandCode(truckBrand);
		truckPOValue.setColor(truckColor);
		if(StringUtils.isNotBlank(truckLicenseDate))
			truckPOValue.setLicenseDate(DBConUtil.string2TimeYMD(truckLicenseDate));
		
		if(StringUtils.isNotBlank(truckMakeDate))
			truckPOValue.setMakeDate(DBConUtil.string2TimeYMD(truckMakeDate));
		
		truckPOValue.setModelCode(truckModel);
		truckPOValue.setTruckBelongType(truckBelongType);
		truckPOValue.setTruckDangerLevel(truckDLevel);
		truckPOValue.setTruckDesc(truckDesc);
		
		if(StringUtils.isNotBlank(truckHeight))
			truckPOValue.setTruckHeight(Float.valueOf(truckHeight));
		
		if(StringUtils.isNotBlank(truckLength))
			truckPOValue.setTruckLength(Float.valueOf(truckLength));
		
//		truckPOValue.setTruckLicense(truckLicense);
		truckPOValue.setTruckType(truckType);
		
		if(StringUtils.isNotBlank(truckWeight))
			truckPOValue.setTruckWeight(Float.valueOf(truckWeight));
		
		if(StringUtils.isNotBlank(truckWidth))
			truckPOValue.setTruckWidth(Float.valueOf(truckWidth));
		truckPOValue.setVin(truckVin);
		truckPOValue.setDriverName(truckDriverName);
		
		int fileId=0;
		//img parse
		if(StringUtils.isNotBlank(truckImgData)&&truckImgData.startsWith("data:")&&StringUtils.isNotBlank(truckFileName)){
			DynaBean fileBean=TruckInsFileUtil.saveUploadImgFile(conn, truckFileName, truckImgData, TruckInsCommonCanstant.CHECK_ENT_TYPE_TRUCK, truckPOResult.getTruckLicense());
			if(fileBean!=null){
				truckPOValue.setMainPicId(Long.valueOf(fileBean.getLong("FILE_ID")).intValue());
				truckPOValue.setMainPicUrl(fileBean.getString("FILE_URL"));
				
				//TT_INS_TRUCK_IMG
				TtInsTruckImgPO imgPOCon=new TtInsTruckImgPO();
				imgPOCon.setStatus("1");
				imgPOCon.setFreezeTag("0");
				imgPOCon.setCreateBy(optMemberId);
				imgPOCon.setCreateTime(YBUtility.now());
				imgPOCon.setId(POFactory.getIntPriKey(conn, imgPOCon));
				imgPOCon.setVer(1);
				imgPOCon.setTruckId(truckPOCon.getId());
				imgPOCon.setSrcPicId(truckPOValue.getMainPicId());
				imgPOCon.setSrcPicUrl(truckPOValue.getMainPicUrl());
				imgPOCon.setRemark("车辆基本信息上传图片");
				
				POFactory.insert(conn, imgPOCon);
				
				fileId=truckPOValue.getMainPicId();
			}
		}
//		POFactory.insert(conn, truckPOCon);
		//entSubParse
//		TT_INS_TRUCK_MID_ENT_MAP：冗余处理
		TtInsTruckMidEntMapPO entMapPOCon=new TtInsTruckMidEntMapPO();
		entMapPOCon.setStatus("1");
		entMapPOCon.setFreezeTag("0");
		entMapPOCon.setTruckId(truckPOCon.getId());
		
		TtInsTruckMidEntMapPO entMapPOValue=new TtInsTruckMidEntMapPO();
		entMapPOValue.setStatus("0");
		entMapPOValue.setUpdateBy(optMemberId);
		entMapPOValue.setUpdateTime(YBUtility.now());
		entMapPOValue.setRemark("编辑车辆，作废原数据");
		
		POFactory.update(conn, entMapPOCon, entMapPOValue);
		
		//insert new
		entMapPOCon.setCreateBy(optMemberId);
		entMapPOCon.setCreateTime(YBUtility.now());		
		entMapPOCon.setVer(1);
		
		if(StringUtils.isNotBlank(subEntStr)){
			String[] subEntArray=subEntStr.split(",");
			if(subEntArray!=null&&subEntArray.length>0){
				for(String midSubEntStr:subEntArray){
					if(StringUtils.isNotBlank(midSubEntStr)){
						String[] midSubEntArray=midSubEntStr.split("[$][$]");//$$需要转义
						if(midSubEntArray!=null&&midSubEntArray.length>1){
							String entSub=midSubEntArray[1];
							String entMid=midSubEntArray[0];
							
							entMapPOCon.setId(POFactory.getIntPriKey(conn, entMapPOCon));
							entMapPOCon.setCheckEntMidCode(entMid);
							entMapPOCon.setCheckEntSubCode(entSub);
							
							POFactory.insert(conn, entMapPOCon);
							logger.debug("编辑车辆，同步增加检查项目小类["+entSub+"]");
						}
					}
				}
			}
		}
		
		//objSubParse
//		TT_INS_TRUCK_SUB_OBJ_MAP：冗余处理
		TtInsTruckSubObjMapPO objMapPOCon=new TtInsTruckSubObjMapPO();
		objMapPOCon.setStatus("1");
		objMapPOCon.setFreezeTag("0");
		objMapPOCon.setTruckId(truckPOCon.getId());
		
		TtInsTruckSubObjMapPO objMapPOValue=new TtInsTruckSubObjMapPO();
		objMapPOValue.setStatus("0");
		objMapPOValue.setUpdateBy(optMemberId);
		objMapPOValue.setUpdateTime(YBUtility.now());
		objMapPOValue.setRemark("编辑车辆，作废原数据");
		
		POFactory.update(conn, objMapPOCon, objMapPOValue);
		
		//insert new
		objMapPOCon.setCreateBy(optMemberId);
		objMapPOCon.setCreateTime(YBUtility.now());		
		objMapPOCon.setVer(1);
		
		if(StringUtils.isNotBlank(subObjStr)){
			System.out.println(1);
			String[] subObjArray=subObjStr.split(",");
			System.out.println(2);
			if(subObjArray!=null&&subObjArray.length>0){
				System.out.println(3);
				for(String midSubObjStr:subObjArray){
					if(StringUtils.isNotBlank(midSubObjStr)){
						System.out.println(4);
						System.out.println(midSubObjStr);
						String[] midSubArray=midSubObjStr.split("[$][$]");//$$需要转义
						if(midSubArray!=null&&midSubArray.length>1){
							System.out.println(5);
							String objSub=midSubArray[1];
							String objMid=midSubArray[0];
							
							objMapPOCon.setId(POFactory.getIntPriKey(conn, objMapPOCon));
							objMapPOCon.setObjClassSubCode(objSub);
							
							POFactory.insert(conn, objMapPOCon);
							logger.debug("编辑车辆，同步增加检查项目小类["+objSub+"]");
						}
					}
				}
			}
		}
		
		int excuteResult=POFactory.update(conn, truckPOCon, truckPOValue);
		
		if(excuteResult<1){
			logger.error(" DATA["+truckPOCon.getId()+"] CHANGED,NO DATA UPDATE,PLEASE RETRY FOR LATER .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_EDIT_ACTION_ERR_8000", "编辑车辆：车辆["+truckPOResult.getTruckLicense()+"]已变更，无法完成操作，请稍后重试", null);
			return 0;
		}
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_TRUCK_MOD, "", "编辑车辆");
		
		//set return msg
		atx.setStringValue("MSG", "编辑车辆：车牌["+truckPOResult.getTruckLicense()+"]成功");
		return 1;
	}
}
