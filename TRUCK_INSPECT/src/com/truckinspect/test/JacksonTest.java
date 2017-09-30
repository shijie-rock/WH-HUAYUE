/**
 * ��Ŀ����:TRUCK_INSPECT
 * ��         ��:com.truckinspect.test
 * ��   ��  ��:JacksonTest.java
 * �� ������:2017��9��12��-����8:28:49
 * Copyright @ 2017-YouBus.com.cn
 */
package com.truckinspect.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.youbus.framework.comm.remoteclient.ParamBean;

/**
 * ������:JacksonTest
 * ������:jaskson ת��ʽ
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2017��9��12�� ����8:28:49
 * �޸ı�ע:
 * @version 1.0.0
 */
public class JacksonTest {
	
	public static String remoteQueryCardListByMobile(String entityCode,String mobile,String entityServerUrl){
		
		List<ParamBean> params=new ArrayList<ParamBean>();
		ParamBean pb;
		pb=new ParamBean("ENTITY_CODE",String.valueOf(entityCode));params.add(pb);
		pb=new ParamBean("MOBILE",String.valueOf(mobile));params.add(pb);
		
//		String synResponse=RemoteClientFactory.send(entityServerUrl,YBCommonContant.REMOTE_SERVER_CHANNEL,"RT_QUERY_CARD_LIST_BY_MOBILE_ACTION",  params);
		
		String synResponse="{\"SUCCESS\":1,\"enterpriseCardAmount\":120,\"enterpriseCardDetailList\":[{\"cardNo\":\"4564565455\",\"cardType\":\"���˿�\",\"name\":\"������\"},{\"cardNo\":\"4564565456\",\"cardType\":\"���˿�\",\"name\":\"���ɽ\"}],\"enterpriseCardName\":\"����׶�԰\",\"enterpriseCardPhone\":\"13812345678\"}";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node=null;
		String returnMsg="";
		try {
			node = objectMapper.readTree(synResponse);
			JsonNode contents=node.get("SUCCESS");
			if(1==contents.getIntValue()){
				JsonNode mobileNode=node.get("enterpriseCardPhone");
				JsonNode nameNode=node.get("enterpriseCardName");
				JsonNode amountNode=node.get("enterpriseCardAmount");
				
				ObjectMapper mapper = new ObjectMapper(); 
				ObjectNode root = mapper.createObjectNode(); 
				ObjectNode custNode = mapper.createObjectNode();//
				if(mobileNode!=null)
				custNode.put("enterpriseCardPhone",mobileNode.getTextValue());
				if(nameNode!=null)
				custNode.put("enterpriseCardName",nameNode.getTextValue());
				if(amountNode!=null)
				custNode.put("enterpriseCardAmount",amountNode.getDoubleValue());
				
				JsonNode cardListNode=node.get("enterpriseCardDetailList");
				if(cardListNode!=null){
					cardListNode.isArray();
					ArrayNode cardArrayNode= mapper.createArrayNode(); 
					cardArrayNode.add(cardListNode);
					custNode.put("enterpriseCardDetailList", cardListNode);
				}
				root.put("CUSTOMER_INFO",custNode);
				root.put("SUCCESS",1);
				System.out.println(root.toString());
			}else{
				JsonNode msgNode=node.get("MSG");//У���쳣����
				if(msgNode!=null)returnMsg=msgNode.getTextValue();
			}
		}catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMsg="Զ����ҵӦ���쳣";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMsg="Զ����ҵӦ���쳣";
		}
		
		return null;
	}
	
	
	private static final String TREE_MODEL_BINDING = " {\"SUCCESS\":1,\"CUSTOMER_INFO\":{\"enterpriseCardAmount\":120,\"enterpriseCardDetailList\""
			+ ":[{\"cardNo\":\"4564565455\",\"cardType\":\"���˿�\",\"name\":\"������\"},"
			+ "{\"cardNo\":\"4564565456\",\"cardType\":\"���˿�\",\"name\":\"���ɽ\"}],\"enterpriseCardName\":\"����׶�԰\",\"enterpriseCardPhone\":\"13812345678\"}}"; 
	public void treeModelBinding() throws Exception{ 
	    ObjectMapper mapper = new ObjectMapper(); 
	    JsonNode rootNode = mapper.readTree(TREE_MODEL_BINDING); 
	    //path��get������ͬ,���ǵ��Ҳ����ýڵ��ʱ��,����missing node������Null. 
	    String treekey2value = rootNode.path("treekey2").getTextValue();// 
	    System.out.println("treekey2value:" + treekey2value); 
	    JsonNode childrenNode = rootNode.path("children"); 
	    String childkey1Value = childrenNode.get(0).path("childkey1").getTextValue(); 
	    System.out.println("childkey1Value:"+childkey1Value); 
	      
	    //�������ڵ� 
	    ObjectNode root = mapper.createObjectNode(); 
	    //�����ӽڵ�1 
	    ObjectNode node1 = mapper.createObjectNode(); 
	    node1.put("nodekey1",1); 
	    node1.put("nodekey2",2); 
	    //���ӽڵ�1 
	    root.put("child",node1); 
	    //����ڵ� 
	    ArrayNode arrayNode = mapper.createArrayNode(); 
	    arrayNode.add(node1); 
	    arrayNode.add(1); 
	    //������ڵ� 
	    root.put("arraynode", arrayNode); 
	    //JSON�������ڵ� 
	    JsonNode valueToTreeNode = mapper.valueToTree(TREE_MODEL_BINDING); 
	    //��JSON�ڵ� 
	    root.put("valuetotreenode",valueToTreeNode); 
	    //JSON�󶨵�JSON�ڵ���� 
	    JsonNode bindJsonNode = mapper.readValue(TREE_MODEL_BINDING, JsonNode.class);//��JSON��JSON�ڵ����. 
	    //��JSON�ڵ� 
	    root.put("bindJsonNode",bindJsonNode); 
	    System.out.println(mapper.writeValueAsString(root)); 
	  } 
	  
	  public static void main(String[] args){
		  
		  remoteQueryCardListByMobile("1", "1", "1");
		  
		  ObjectMapper mapper = new ObjectMapper(); 
		//�������ڵ� 
		  ObjectNode root = mapper.createObjectNode(); 
		  root.put("SUCCESS",1);
		  ObjectNode custNode = mapper.createObjectNode();//
		  custNode.put("enterpriseCardAmount", 120); 
		  custNode.put("enterpriseCardName", "����׶�԰"); 
		  custNode.put("enterpriseCardPhone", "13812345678"); 
		  
		  root.put("CUSTOMER_INFO", custNode);
		  
		  ArrayNode arrayNode = mapper.createArrayNode(); 
		  ObjectNode listNode1 = mapper.createObjectNode();// 
		  listNode1.put("cardNo", "4564565455"); 
		  listNode1.put("cardType", "���˿�");  
		  listNode1.put("name", "������");  
		  
		  ObjectNode listNode2 = mapper.createObjectNode();// 
		  listNode2.put("cardNo", "4564565455"); 
		  listNode2.put("cardType", "���˿�");  
		  listNode2.put("name", "������");  
		  
		  arrayNode.add(listNode1);
		  arrayNode.add(listNode2);
		  
		  root.put("enterpriseCardDetailList", arrayNode);
		  try {
			System.out.println(mapper.writeValueAsString(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	  }
}
