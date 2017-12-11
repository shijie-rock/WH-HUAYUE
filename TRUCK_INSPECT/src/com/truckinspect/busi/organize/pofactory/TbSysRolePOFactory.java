/**
 * 项目名称:TRUCK_INSPECT
 * 包         名:com.truckinspect.busi.organize.pofactory
 * 文   件  名:TbSysRolePOFactory.java
 * 创 建日期:2017年7月25日-下午8:34:51
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.busi.organize.pofactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.info.base.po.TbSysRoleActionPO;
import com.info.base.po.TbSysRolePO;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;
import com.infoservice.po.PageQuery;
import com.youbus.framework.comm.YBUtility;
import com.youbus.framework.pofactory.PageQueryTabMySql;
import com.youbus.framework.util.DBConUtil;

/**
 * 类名称:TbSysRolePOFactory
 * 类描述:
 * 创建人:rock
 * 修改人:rock
 * 修改时间:2017年7月25日 下午8:34:51
 * 修改备注:
 * @version 1.0.0
 */
public class TbSysRolePOFactory extends POFactory {
	private static Logger log=Logger.getLogger(TbSysRolePOFactory.class);
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
	 * 方   法  名:queryRoleList
	 * 方法描述:分页查询角色列表
	 * 参         数:@param conn
	 * 参         数:@param roleName 角色名称
	 * 参         数:@param roleCode 角色代码
	 * 参         数:@param includeStop 包含已冻结
	 * 参         数:@return
	 * 返   回  值:PageQuery
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static PageQuery queryRoleList(Connection conn,String roleName,String roleCode,boolean includeStop,int customPageSize){
		
		String sql=" SELECT * FROM TB_SYS_ROLE WHERE 1=1 AND STATUS='1'  ";
		if(StringUtils.isNotBlank(roleName))sql+=" AND ROLE_NAME LIKE '%"+roleName+"%' ";
		if(StringUtils.isNotBlank(roleCode))sql+=" AND ROLE_CODE LIKE '%"+roleCode+"%' ";
		if(!includeStop)sql+=" AND FREEZE_TAG ='0' ";
		sql+=" ORDER BY ROLE_CODE ASC ";
		log.debug("sql:="+sql);
		
		return new PageQueryTabMySql(conn, sql.toString(), new ArrayList(),customPageSize);
	}
	
	/**
	 * 根据角色代码判断角色是否存在
	 * 方   法  名:roleIsExist
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param roleCode
	 * 参         数:@return
	 * 返   回  值:boolean true存在； false不存在
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static boolean roleIsExist(Connection conn,String roleCode){
		
//		if(StringUtils.isBlank(roleCode)){  //action check param //TODO
//			log.error("INPUT ROLE CODE IS NULL ");
//			return false;
//		}
		TbSysRolePO rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleCode(roleCode);
		return getByPriKey(conn, rolePOCon)!=null;
	}
	
	/**
	 * 根据角色代码判断角色是否存在
	 * 方   法  名:roleIsExist
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param roleName
	 * 参         数:@return
	 * 返   回  值:boolean true存在； false不存在
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static boolean roleNameIsExist(Connection conn,String roleName){
		
		TbSysRolePO rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setRoleName(roleName);
		return getByPriKey(conn, rolePOCon)!=null;
	}
	
	/**
	 * 新增角色
	 * 方   法  名:saveRole
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@param roleCode
	 * 参         数:@param roleName
	 * 参         数:@param roleDesc
	 * 参         数:@param actionCodeStr ","split
	 * 参         数:@param optMemberId
	 * 参         数:@param optMemberName
	 * 参         数:@return
	 * 返   回  值:int
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int addRole(Connection conn,String roleCode,String roleName,String roleDesc,String actionCodeStr,Integer optMemberId,String optMemberName){
		
//		TB_SYS_ROLE
		TbSysRolePO rolePOCon=new TbSysRolePO();
		rolePOCon.setStatus("1");
		rolePOCon.setCreateBy(optMemberId);
		rolePOCon.setCreateTime(YBUtility.now());
		rolePOCon.setImportTag("0");
		rolePOCon.setRoleCode(roleCode);
		rolePOCon.setRoleDesc(roleDesc);
		rolePOCon.setRoleName(roleName);
		rolePOCon.setSysTag("0");
		rolePOCon.setFreezeTag("0");
		
		insert(conn, rolePOCon);
		
//		TB_SYS_ROLE_ACTION
//		String[] actionCodeArray=actionCodeStr.split(",");
		String actionCodeSql="'"+actionCodeStr.replaceAll(",", "','")+"'";//xxx,xxx->'xxx','xxx'
		
		String sql=" SELECT DISTINCT "
				 + " B.OPTION_CODE,B.ACTION_CODE,A.FUN_CODE "
				 + " FROM TM_SYS_ACTION A,TM_SYS_OPTION B "
				 + " WHERE A.STATUS='1' AND B.STATUS='1' "
				 + " AND A.ACTION_CODE=B.ACTION_CODE "
				 + " AND A.ACTION_CODE IN("+actionCodeSql+")";
		List<DynaBean> list;
		try {
			list = DBConUtil.getResult(conn, sql, "ACTION_OPTION_BEAN");
			
			if(list!=null&&list.size()>0){

				TbSysRoleActionPO actionPOCon=new TbSysRoleActionPO();
				actionPOCon.setStatus("1");
				actionPOCon.setCreateBy(optMemberId);
				actionPOCon.setCreateTime(YBUtility.now());
				actionPOCon.setRoleCode(roleCode);
				actionPOCon.setImportTag("0");
				actionPOCon.setSysTag("0");
				
				for(DynaBean bean:list){
					String actionCode=bean.getString("ACTION_CODE");
					String funCode=bean.getString("FUN_CODE");
					String optionCode=bean.getString("OPTION_CODE");
					
					actionPOCon.setId(getIntPriKey(conn, actionPOCon));
					actionPOCon.setActionCode(actionCode);
					actionPOCon.setOptionCode(optionCode);
					actionPOCon.setFunCode(funCode);
					
					insert(conn, actionPOCon);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("addRole faild "+e.getMessage());
			return 0;
		}
		return 1;
//		TB_OPT_LOGS //ACTION RECORD //TODO
	}
	
	
	public static int editRole(Connection conn,String roleName,String roleDesc,String actionCodeStr,Integer optMemberId,TbSysRolePO rolePOCon){
		
//		TB_SYS_ROLE
		TbSysRolePO rolePOValue=new TbSysRolePO();
		rolePOValue.setUpdateBy(optMemberId);
		rolePOValue.setUpdateTime(YBUtility.now());
		rolePOValue.setVer(rolePOCon.getVer()+1);
		rolePOValue.setRoleDesc(roleDesc);
		rolePOValue.setRoleName(roleName);
		
		int excuteResult=update(conn, rolePOCon,rolePOValue);
		if(excuteResult<1){
			log.error("数据版本不一致");
			return 0;
		}
		
//		TB_SYS_ROLE_ACTION
		TbSysRoleActionPO roleActionPOCon=new TbSysRoleActionPO();
		roleActionPOCon.setRoleCode(rolePOCon.getRoleCode());
		
		delete(conn, roleActionPOCon);
		
//		String[] actionCodeArray=actionCodeStr.split(",");
		String actionCodeSql="'"+actionCodeStr.replaceAll(",", "','")+"'";//xxx,xxx->'xxx','xxx'
		
		String sql=" SELECT DISTINCT "
				 + " B.OPTION_CODE,B.ACTION_CODE,A.FUN_CODE "
				 + " FROM TM_SYS_ACTION A,TM_SYS_OPTION B "
				 + " WHERE A.STATUS='1' AND B.STATUS='1' "
				 + " AND A.ACTION_CODE=B.ACTION_CODE "
				 + " AND A.ACTION_CODE IN("+actionCodeSql+")";
		List<DynaBean> list;
		try {
			list = DBConUtil.getResult(conn, sql, "ACTION_OPTION_BEAN");
			
			if(list!=null&&list.size()>0){

				TbSysRoleActionPO actionPOCon=new TbSysRoleActionPO();
				actionPOCon.setStatus("1");
				actionPOCon.setCreateBy(optMemberId);
				actionPOCon.setCreateTime(YBUtility.now());
				actionPOCon.setRoleCode(rolePOCon.getRoleCode());
				actionPOCon.setImportTag("0");
				actionPOCon.setSysTag("0");
				
				for(DynaBean bean:list){
					String actionCode=bean.getString("ACTION_CODE");
					String funCode=bean.getString("FUN_CODE");
					String optionCode=bean.getString("OPTION_CODE");
					
					actionPOCon.setId(getIntPriKey(conn, actionPOCon));
					actionPOCon.setActionCode(actionCode);
					actionPOCon.setOptionCode(optionCode);
					actionPOCon.setFunCode(funCode);
					
					insert(conn, actionPOCon);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("addRole faild "+e.getMessage());
			return 0;
		}
		return 1;
//		TB_OPT_LOGS //ACTION RECORD //TODO
	}
	
	
	/**
	 * 生成排序的function action option DynaBean对象集合
	 * 方   法  名:queryFunActOptList
	 * 方法描述:
	 * 参         数:@param conn
	 * 参         数:@return
	 * 返   回  值:List<DynaBean>
	 * 创   建  人:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List<DynaBean> queryFunActOptList(Connection conn){
		
		String sql=" SELECT DISTINCT F.FUN_CODE,F.FUN_NAME,F.FUN_DESC,F.FUN_ICON,F.SORT AS FUN_SORT,"
				 + " A.ACTION_CODE,A.ACTION_NAME,A.ACTION_DESC,A.ACTION_URL,A.SORT AS ACT_SORT,"
				 + " O.OPTION_CODE,O.OPTION_NAME,O.OPTION_DESC,O.OPTION_URL,O.BUTTON_ID,O.FR_ACTION_ID "
				 + " FROM TM_SYS_FUNCTION F,TM_SYS_ACTION A,TM_SYS_OPTION O "
				 + " WHERE F.STATUS='1' AND A.STATUS='1' AND O.STATUS='1'"
				 + " AND F.FUN_CODE=A.FUN_CODE "
				 + " AND A.ACTION_CODE=O.ACTION_CODE "
				 + " ORDER BY F.SORT,F.FUN_CODE, "
				 + " A.SORT,A.ACTION_CODE, "
				 + " O.OPTION_CODE ASC "
				 + "";
		try {
			return DBConUtil.getResult(conn, sql, "FUN_ACT_OPT_BEAN");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
