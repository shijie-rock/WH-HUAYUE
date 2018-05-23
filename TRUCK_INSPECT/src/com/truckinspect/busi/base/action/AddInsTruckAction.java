/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.action
 * 文   件  名:AddMemberAction.java
 * 创 建日期:2017年9月2日-上午9:24:53
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
import com.truckinspect.busi.entity.po.TmInsCheckEntitySupperPO;
import com.truckinspect.common.TruckInsCommonCanstant;
import com.truckinspect.common.util.OptLogUtil;
import com.truckinspect.common.util.TruckInsFileUtil;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:AddInsTruckAction
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年9月2日 上午9:24:53
 * 修改备注:
 * @version 1.0.0
 */
public class AddInsTruckAction extends ActionImpl {

	/* (non-Javadoc)
	 * @see com.infoservice.framework.ActionImpl#performExecute(com.infoservice.framework.datacontext.ActionContext)
	 */
	@Override
	protected int performExecute(ActionContext atx) {
		// TODO Auto-generated method stub
		String truckImgData=atx.getStringValue("TRUCK_IMG_DATA");
		String truckFileName=atx.getStringValue("TRUCK_IMG_FILE_NAME");
		
		String truckType=atx.getStringValue("TRUCK_TYPE");
		String truckLicense=atx.getStringValue("TRUCK_LICENSE");
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
		if(StringUtils.isBlank(truckLicense)){
			logger.error(" PARAM IS EMTPY .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_ADD_ACTION_ERR_1000", "新增车辆：参数为空", null);
			return 0;
		}
		
		Connection conn=atx.getConnection();
		
		Integer optMemberId=YBUtility.getMemberId(atx);
		String optMemberName=YBUtility.getMemberName(atx);
		
		TmInsTruckInfoPO truckPOCon=new TmInsTruckInfoPO();
		truckPOCon.setTruckLicense(truckLicense);
		truckPOCon.setStatus("1");
		truckPOCon.setFreezeTag("0");
		
		TmInsTruckInfoPO truckPOResult=POFactory.getByPriKey(conn, truckPOCon);
		if(truckPOResult!=null){
			logger.error(" TRUCK LICENSE EXIST ALREADY .");
			atx.setErrorContext("BASE_DATA_INS_TRUCK_ADD_ACTION_ERR_2000", "新增车辆：车牌号码["+truckLicense+"]已经存在", null);
			return 0;
		}
		
//		TM_INS_CHECK_ENTITY_SUPPER
		TmInsCheckEntitySupperPO entSupPOCon=new TmInsCheckEntitySupperPO();
		entSupPOCon.setStatus("1");
		entSupPOCon.setCheckEntTypeCode(TruckInsCommonCanstant.CHECK_ENT_TYPE_TRUCK);//检查对象类型代码：车辆
		entSupPOCon.setFreezeTag("0");
		
		TmInsCheckEntitySupperPO entSupPOResult=POFactory.getByPriKey(conn, entSupPOCon);
		
		truckPOCon.setCreateBy(optMemberId);
		truckPOCon.setCreateTime(YBUtility.now());
		truckPOCon.setId(POFactory.getIntPriKey(conn, truckPOCon));
		truckPOCon.setVer(1);
		
		if(StringUtils.isNotBlank(truckType))
			truckPOCon.setTruckType(truckType);
		
		truckPOCon.setBrandCode(truckBrand);
		if(entSupPOResult!=null)
		truckPOCon.setCheckEntCode(entSupPOResult.getCheckEntCode());//车辆所属检查对象大类代码
		truckPOCon.setCheckEntTypeCode("CET_0010");//
		
		truckPOCon.setColor(truckColor);
		if(StringUtils.isNotBlank(truckLicenseDate))
		truckPOCon.setLicenseDate(DBConUtil.string2TimeYMD(truckLicenseDate));
		
		if(StringUtils.isNotBlank(truckMakeDate))
		truckPOCon.setMakeDate(DBConUtil.string2TimeYMD(truckMakeDate));
		
		truckPOCon.setModelCode(truckModel);
		truckPOCon.setTruckBelongType(truckBelongType);
		truckPOCon.setTruckDangerLevel(truckDLevel);
		truckPOCon.setTruckDesc(truckDesc);
		
		if(StringUtils.isNotBlank(truckHeight))
		truckPOCon.setTruckHeight(Float.valueOf(truckHeight));
		
		if(StringUtils.isNotBlank(truckLength))
		truckPOCon.setTruckLength(Float.valueOf(truckLength));
		
		truckPOCon.setTruckLicense(truckLicense);
		truckPOCon.setTruckType(truckType);
		
		if(StringUtils.isNotBlank(truckWeight))
		truckPOCon.setTruckWeight(Float.valueOf(truckWeight));
		
		if(StringUtils.isNotBlank(truckWidth))
		truckPOCon.setTruckWidth(Float.valueOf(truckWidth));
		truckPOCon.setVin(truckVin);
		truckPOCon.setDriverName(truckDriverName);
		
		int fileId=0;
		//img parse
		if(StringUtils.isNotBlank(truckImgData)&&truckImgData.startsWith("data:")&&StringUtils.isNotBlank(truckFileName)){
			DynaBean fileBean=TruckInsFileUtil.saveUploadImgFile(conn, truckFileName, truckImgData, TruckInsCommonCanstant.CHECK_ENT_TYPE_TRUCK, truckLicense);
			if(fileBean!=null){
				truckPOCon.setMainPicId(Long.valueOf(fileBean.getLong("FILE_ID")).intValue());
				truckPOCon.setMainPicUrl(fileBean.getString("FILE_URL"));
				
				//TT_INS_TRUCK_IMG
				TtInsTruckImgPO imgPOCon=new TtInsTruckImgPO();
				imgPOCon.setStatus("1");
				imgPOCon.setFreezeTag("0");
				imgPOCon.setCreateBy(optMemberId);
				imgPOCon.setCreateTime(YBUtility.now());
				imgPOCon.setId(POFactory.getIntPriKey(conn, imgPOCon));
				imgPOCon.setVer(1);
				imgPOCon.setTruckId(truckPOCon.getId());
				imgPOCon.setSrcPicId(truckPOCon.getMainPicId());
				imgPOCon.setSrcPicUrl(truckPOCon.getMainPicUrl());
				imgPOCon.setRemark("车辆基本信息上传图片");
				
				POFactory.insert(conn, imgPOCon);
				
				fileId=truckPOCon.getMainPicId();
			}
		}
		POFactory.insert(conn, truckPOCon);
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
		entMapPOValue.setRemark("新增车辆，作废原数据");
		
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
							logger.debug("新增车辆，同步增加检查项目小类["+entSub+"]");
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
		objMapPOValue.setRemark("新增车辆，作废原数据");
		
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
							logger.debug("新增车辆，同步增加检查项目小类["+objSub+"]");
						}
					}
				}
			}
		}
		
		
		//build optInfo
		OptLogUtil.bindOptContext(atx, TruckInsCommonCanstant.OPT_TYPE_TRUCK_ADD, "", "新增车辆["+truckLicense+"]"+(fileId!=0?"并上传图片":""));
		
		//set return msg
		atx.setStringValue("MSG", "新增车辆["+truckLicense+"]成功");
		return 1;
	}

	public static void main(String[] args){
		String s="CHE_0102$$CHE_010201";
//		s=s.split("[$][$]")[1];
		s=s.split("[$][$]")[0];
		System.out.println(s);
	}

}
