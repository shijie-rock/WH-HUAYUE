/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.util
 * ��   ��  ��:DBConUtil.java
 * �� ������:2015��4��15��-����11:05:27
 * Copyright @ 2015-YouBus.com.cn
 */
package com.cn.gpslms.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Element;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.infoservice.fileserver.po.FsFileInfoPO;
import com.infoservice.framework.datacontext.ActionContext;
import com.infoservice.framework.exceptions.ActionException;
import com.infoservice.po.DataBean;
import com.infoservice.po.DynaBean;
import com.infoservice.po.POFactory;

/**
 * ������:DBConUtil
 * ������:
 * ������:rock
 * �޸���:rock
 * �޸�ʱ��:2015��4��15�� ����11:15:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class DBConUtil {

	/**
	 * ����һ���µ�ʵ�� DBConUtil.
	 *
	 */
	private static Logger log=Logger.getLogger(DBConUtil.class);
	public DBConUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��   ��  ��:main
	 * ��������:
	 * ��         ��:@param args
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */


	private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
	
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat sdf5 = new SimpleDateFormat("MM/dd");
	private static SimpleDateFormat sdf7 = new SimpleDateFormat("MM-dd");
	private static SimpleDateFormat sdf6 = new SimpleDateFormat("HH:mm");
	
	
	public static String handleTimestamp2(Date inTime) {
		if (inTime == null) {
			
			return "";
		} else {
			return sdf2.format(new java.util.Date(inTime.getTime()));
		}
	}
	public static String handleTimestamp4(Date inTime) {
		if (inTime == null) {
			
			return "";
		} else {
			return sdf3.format(new java.util.Date(inTime.getTime()));
		}
	}
	
	public static String handleTimestamp3(Date inTime) {
		if (inTime == null) {
			
			return "";
		} else {
			return sdf1.format(new java.util.Date(inTime.getTime()));
		}
	}
	public static String handleTimestamp5(Date inTime) {
		if (inTime == null) {
			
			return "";
		} else {
			return sdf5.format(new java.util.Date(inTime.getTime()));
		}
	}
	public static String handleTimestamp7(Date inTime) {
		if (inTime == null) {
			
			return "";
		} else {
			return sdf7.format(new java.util.Date(inTime.getTime()));
		}
	}
	public static String handleTimestamp6(Date inTime) {
		if (inTime == null) {
			
			return "";
		} else {
			return sdf6.format(new java.util.Date(inTime.getTime()));
		}
	}
	
	public static Timestamp getTimeStamp(String index) throws Exception {
		Timestamp iRtn;
		if (index == null || index.trim().equals("")) {
			return null;
		} else if (index.length() == 10) {
			index += " 00:00:00.000000000";
		} else if (index.length() == 19) {
			index += ".000000000";
		}
		return Timestamp.valueOf(index);
	}
	
	public static String handleFormatDate(java.util.Date date) {
		if (date == null) {
			return "";
		} else {
			return sdf.format(date);
		}
	}
	/**
	 * Method ��������ʾ��ʱ���ʽΪyyyy-MM-dd HH:mm:ss
	 * @param inTime
	 * @return
	 */
	public static String handleTimestamp(Timestamp inTime) {
		if (inTime == null) {
			
			return "";
		} else {
			return sdf.format(new java.util.Date(inTime.getTime()));
		}
	}
	public static Timestamp string2Time(String str) throws ParseException {
		if (str == null) {
			return null;
		} else {
			return new Timestamp(sdf.parse(str).getTime());
		}
	}
	/**
	 * 
	 * ��   ��  ��:string2TimeYMD
	 * ��������: yyyy-MM-dd תΪ time
	 * ��         ��:@param str
	 * ��         ��:@return
	 * ��         ��:@throws ParseException
	 * ��   ��  ֵ:Timestamp
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static Timestamp string2TimeYMD(String str) {
		if (str == null) {
			return null;
		} else {
			try {
				return new Timestamp(sdf2.parse(str).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	
	
	public static void close(ResultSet rs,Statement stm,Connection conn){
	    try {
			if(rs!=null)rs.close();
			if(stm!=null)stm.close();
			if(conn!=null&&!conn.isClosed())conn.close();
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			System.err.println("Close ResultSet/Statement/Connection Error!");
//			AppLog.getInstance().getAppLog().error("Close ResultSet/Statement/Connection Error!", e);
		}     		
	}
	
	public static void close(Connection conn){
	    try {
			if(conn!=null&&!conn.isClosed())conn.close();
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			System.err.println("Close Connection Error!");
//			AppLog.getInstance().getAppLog().error("Close Connection Error!", e);
		}     
		
	}

	/**
	 * 
	 * @param conn
	 * @param sql
	 * @param beanName
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public static List<DynaBean> getResult(Connection conn,String sql,String beanName) throws Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
		AppLog.getInstance().getAppLog().debug(sql);
		log.debug(sql);
		System.out.println("DBConUtil sql:= "+sql);
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		return POFactoryUtil.getDynaBeansFromResultSet(beanName, rs);		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			close(rs,pstmt,null);
		}
//		return null;
	} 
	
	/**
	 * ����list��һ��dyna����
	 * ��   ��  ��:getSingleResult
	 * ��������:
	 * ��         ��:@param conn
	 * ��         ��:@param sql
	 * ��         ��:@param beanName
	 * ��         ��:@return
	 * ��         ��:@throws Exception
	 * ��   ��  ֵ:DynaBean
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static DynaBean getSingleResult(Connection conn,String sql,String beanName) throws Exception {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
		AppLog.getInstance().getAppLog().debug(sql);
		log.debug(sql);
		System.out.println("DBConUtil sql:= "+sql);
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		List<DynaBean> list= POFactoryUtil.getDynaBeansFromResultSet(beanName, rs);	
		if(list!=null&&list.size()>0)return list.get(0);
		else return null;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			close(rs,pstmt,null);
		}
//		return null;
	} 
	
	/**
	 * 
	 * ��   ��  ��:update
	 * ��������:ִ��Update Delete �����
	 * ��         ��:@param conn
	 * ��         ��:@param sql
	 * ��         ��:@return
	 * ��         ��:@throws Exception
	 * ��   ��  ֵ:int
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int update(Connection conn,String sql) throws Exception{
		PreparedStatement pstmt=null;
		try{
		System.out.println(sql);
		AppLog.getInstance().getAppLog().debug(sql);
		pstmt=conn.prepareStatement(sql);
		return pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			close(null,pstmt,null);
		}
	}
	/**
	 * 
	 * ��   ��  ��:splitList
	 * ��������:��ȡ�Ӵ�
	 * ��         ��:@param input
	 * ��         ��:@param beginIndex
	 * ��         ��:@param stepSize
	 * ��         ��:@return
	 * ��   ��  ֵ:List
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static List  splitList(List input,int beginIndex,int stepSize){
		List output=new ArrayList();
		if(null==input||input.size()<1||beginIndex>=input.size())return null;
		return output=input.subList(beginIndex, beginIndex+stepSize-1);
		
	}
	
    /**
     * 
     * ��   ��  ��:returnE
     * ��������: action �����쳣
     * ��         ��:@param atx
     * ��         ��:@param errCode
     * ��         ��:@param errMsg
     * ��   ��  ֵ:void
     * ��   ��  ��:rock
     * @exception
     * @since  1.0.0
     */
	public static void returnE(ActionContext atx,String errCode,String errMsg){
		atx.setErrorContext(errCode, errMsg, new ActionException(errCode));
	}
	
	/**
	 * 
	 * ��   ��  ��:conversStr
	 * ��������:�ַ����滻
	 * ��         ��:@param input
	 * ��         ��:@param fromStr
	 * ��         ��:@param toStr
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String conversStr(String input,String fromStr,String toStr){
		if(input==null||input.trim().length()==0) return "";
		if(fromStr==null||fromStr.trim().length()==0) return "";
		if(input.equals(fromStr))return toStr;
		return input;
	}
	/**
	 * 
	 * ��   ��  ��:getDynaBeanSize
	 * ��������:��ö�̬bean �����Ը���
	 * ��         ��:@param temp
	 * ��         ��:@return
	 * ��   ��  ֵ:int
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static int getDynaBeanSize(DynaBean temp){
		if(temp==null)return 0;
		int i=0;
		Iterator iter=temp.getNames();
		while(iter.hasNext()){
			iter.next();
			i++;
		}
		return i;		
	}
	
	/**
	 * 
	 * ��   ��  ��:replaceNull
	 * ��������:null�滻Ϊ""
	 * ��         ��:@param o
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String replaceNull(Object o){
		return o==null?"":o.toString();	
}
	
	/**
	 * 
	 * ��   ��  ��:MD5Encode
	 * ��������:MD5 ����
	 * ��         ��:@param sourceString
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
	
	/**
	 * 
	 * ��   ��  ��:getServerNameAndPor
	 * ��������:��÷��������ּ��˿�
	 * ��         ��:@param req
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static String getServerNameAndPor(HttpServletRequest req){
		String servNameString=req.getServerName();
		int port=req.getServerPort();	
		System.out.println("http://"+servNameString+":"+port);
		return "http://"+servNameString+":"+port;
		
	}
	
	/**
	 * 
	 * ��   ��  ��:fromUnicode
	 * ��������: unicodeת��
	 * ��         ��:@param sb
	 * ��         ��:@return
	 * ��   ��  ֵ:StringBuffer
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
	public static StringBuffer fromUnicode(StringBuffer sb) {   
	   int length=sb.length();   
	   int index=0;   
	   char aChar;   
	    StringBuffer result=new StringBuffer();   
	       
	    while (index<length) {   
	        aChar = sb.charAt(index++);   
	        if (aChar == '\\') {   
	            aChar = sb.charAt(index++);   
	            if (aChar == 'u') {   
	                // Read the xxxx   
	                int value = 0;   
	                for (int i = 0; i < 4; i++) {   
	                    aChar = sb.charAt(index++);   
	                    switch (aChar) {   
	                    case '0':   
	                    case '1':   
	                    case '2':   
	                    case '3':   
	                    case '4':   
	                    case '5':   
	                    case '6':   
	                    case '7':   
	                    case '8':   
	                    case '9':   
	                        value = (value << 4) + aChar - '0';   
	                        break;   
	                    case 'a':   
	                    case 'b':   
	                    case 'c':   
	                    case 'd':   
	                    case 'e':   
	                    case 'f':   
	                        value = (value << 4) + 10 + aChar - 'a';   
	                        break;   
	                    case 'A':   
	                    case 'B':   
	                    case 'C':   
	                    case 'D':   
	                    case 'E':   
	                    case 'F':   
	                        value = (value << 4) + 10 + aChar - 'A';   
	                        break;   
	                    default:   
	                        throw new IllegalArgumentException(   
	                                "Malformed \\uxxxx encoding.");   
	                    }   
	                }   
	                result.append((char) value);   
	            } else {   
	                if (aChar == 't') {   
	                    aChar = '\t';   
	                } else if (aChar == 'r') {   
	                    aChar = '\r';   
	                } else if (aChar == 'n') {   
	                    aChar = '\n';   
	                } else if (aChar == 'f') {   
	                    aChar = '\f';   
	                }   
	                result.append( aChar);   
	            }   
	        } else {   
	            result.append((char) aChar);   
	        }   
	    }   
	    return result;         
	}
	
	/**
	 * 
	 * ��   ��  ��:toUnicode
	 * ��������: �ַ���תunicode
	 * ��         ��:@param theString
	 * ��         ��:@param escapeSpace
	 * ��         ��:@return
	 * ��   ��  ֵ:String
	 * ��   ��  ��:rock
	 * @exception
	 * @since  1.0.0
	 */
    public static String toUnicode(String theString, boolean escapeSpace) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for(int x=0; x<len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\'); outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch(aChar) {
                case ' ':
                    if (x == 0 || escapeSpace)
                        outBuffer.append('\\');
                    outBuffer.append(' ');
                    break;
                case '\t':outBuffer.append('\\'); outBuffer.append('t');
                          break;
                case '\n':outBuffer.append('\\'); outBuffer.append('n');
                          break;
                case '\r':outBuffer.append('\\'); outBuffer.append('r');
                          break;
                case '\f':outBuffer.append('\\'); outBuffer.append('f');
                          break;
                case '=': // Fall through
                case ':': // Fall through
                case '#': // Fall through
                case '!':
                    outBuffer.append('\\'); outBuffer.append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >>   8) & 0xF));
                        outBuffer.append(toHex((aChar >>   4) & 0xF));
                        outBuffer.append(toHex( aChar         & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }
    private static final char[] hexDigit = {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };
   
    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }
    
    /**
     * ��ñ����ʽ  //error
     * @param input
     * @return
     */
    public static String getCharset(String str){

        String encode = "GB2312";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {  
                String s = encode;  
                return s;  
            }  
        } catch (Exception exception) {  
        }  
        encode = "ISO-8859-1";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {  
                String s1 = encode;  
                return s1;  
            }  
        } catch (Exception exception1) {  
        }  
        encode = "UTF-8";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {  
                String s2 = encode;  
                return s2;  
            }  
        } catch (Exception exception2) {  
        }  
        encode = "GBK";  
        try {  
            if (str.equals(new String(str.getBytes(encode), encode))) {  
                String s3 = encode;  
                return s3;  
            }  
        } catch (Exception exception3) {  
        }  
        return "";  
    	
    }
    /**
    *
    * @param str
    *  ��Ҫ���˵��ַ���
    * @return
    * @Description:��������������ַ�
    */
   public static String filterUnNumber(String str) {
       //ֻ������
       String regEx = "[^0-9]";
       Pattern p = Pattern.compile(regEx);
       Matcher m = p.matcher(str);
      //�滻��ģʽƥ��������ַ����������ֵ��ַ�����""�滻��
       return m.replaceAll("").trim();
   }
   
   /**
    * �жϼ����Ƿ���Ԫ��
    * ��   ��  ��:listNotNULL
    * ��������:
    * ��         ��:@param col
    * ��         ��:@return
    * ��   ��  ֵ:boolean
    * ��   ��  ��:rock
    * @exception
    * @since  1.0.0
    */
   public static boolean listNotNULL(Collection col){
	   return col!=null&&!col.isEmpty();
   }
   
   /**
    * �ж��ַ����Ƿ���ֵ��ȥ�ո�
    * ��   ��  ��:stringNotNULL
    * ��������:
    * ��         ��:@param str
    * ��         ��:@return
    * ��   ��  ֵ:boolean
    * ��   ��  ��:rock
    * @exception
    * @since  1.0.0
    */
   public static boolean stringNotNULL(String str){
	   return str!=null&&str.trim().length()>0;
   }
   
  
   /**
    * 
    * ��   ��  ��:getFilePath
    * ��������: �����ļ�id��ȡ�ļ�·�� 
    *  /fileserver/"+fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName() 
    *  http://localhost:8088/fileserver//AGENT_0001/20150611/121/cd5653b1-4c12-437c-9b53-2061bff5e07e.jpg
    * ��         ��:@param conn
    * ��         ��:@param fileId
    * ��         ��:@return
    * ��   ��  ֵ:String
    * ��   ��  ��:rock
    * @exception
    * @since  1.0.0
    */
   public static String getFilePath(Connection conn,int fileId){
	  FsFileInfoPO ffPO=new FsFileInfoPO();
	  ffPO.setFileId(Long.valueOf(fileId));
	  ffPO=POFactory.getByPriKey(conn, ffPO);
	  if(ffPO!=null)
		  return "/fileserver/"+ffPO.getFileRelationPath()+File.separator+ffPO.getFileDiskName();
	  return null;
   }
   
   public static String getFileName(Connection conn,int fileId){
	   FsFileInfoPO ffPO=new FsFileInfoPO();
	   ffPO.setFileId(Long.valueOf(fileId));
	   ffPO=POFactory.getByPriKey(conn, ffPO);
	   if(ffPO!=null)
		   return ffPO.getFileDiskName();
	   return null;
   }
   
   /**
    * gzip ��ѹ
    * ��   ��  ��:uncompress
    * ��������:
    * ��         ��:@param str
    * ��         ��:@return
    * ��         ��:@throws IOException
    * ��   ��  ֵ:String
    * ��   ��  ��:rock
    * @exception
    * @since  1.0.0
    */
	public static String uncompress(String str) throws IOException {   
	    if (str == null || str.length() == 0) {   
	      return str;   
	    }   
	   ByteArrayOutputStream out = new ByteArrayOutputStream();   
	   
	   ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));   //ISO-8859-1
//	   ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("utf-8"));   //ISO-8859-1
	   GZIPInputStream gunzip = new GZIPInputStream(in);   
	    byte[] buffer = new byte[1024];   
	    int n;   
	    while ((n = gunzip.read(buffer))>= 0) {   
	    	out.write(buffer, 0, n);   
	    }   
	    // toString()ʹ��ƽ̨Ĭ�ϱ��룬Ҳ������ʽ��ָ����toString("GBK";)  
	    
//	    InputStreamReader isr = new InputStreamReader(gunzip);  
////	    InputStreamReader isr = new InputStreamReader(gunzip,"ISO-8859-1");  
//	    java.io.BufferedReader br = new java.io.BufferedReader(isr);  
//        StringBuffer sb = new StringBuffer();  
//        String tempbf;  
//        while ((tempbf = br.readLine()) != null) {  
//            sb.append(tempbf);  
//           // sb.append("\r\n");  
//        }  
//        isr.close();  
        gunzip.close();  
        return out.toString();  
//	    return out.toString("GBK");   
	  }
	
	/**
	 * ����ѹ�����ַ���
	 * ��   ��  ��:ungzipFile
	 * ��������:
	 * ��         ��:@param file
	 * ��   ��  ֵ:void
	 * ��   ��  ��:rock
	 * @throws IOException 
	 * @exception
	 * @since  1.0.0
	 */
	public static void ungzipFile(File file) throws IOException{

//		   BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//		   BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		   FileInputStream fis=new FileInputStream(file);
		   ByteArrayOutputStream out = new ByteArrayOutputStream();  
		   byte[] buffer = new byte[1024]; 
		    int n; 
		    while ((n = fis.read(buffer))>= 0) {   
		    	out.write(buffer, 0, n);   
		    } 
		   System.out.println(out.toString().getBytes("ISO-8859-1"));
		   ByteArrayInputStream in = new ByteArrayInputStream(out.toString().getBytes("ISO-8859-1"));   //ISO-8859-1
//		   ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("utf-8"));   //ISO-8859-1
//		   ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("UTF-8"));   //ISO-8859-1
		   GZIPInputStream gunzip;
		try {
			out = new ByteArrayOutputStream();  
			gunzip = new GZIPInputStream(in);
		    buffer = new byte[1024];   
		    n=0;   
		    while ((n = gunzip.read(buffer))>= 0) {   
		    	out.write(buffer, 0, n);   
		    }    
	        gunzip.close();  
	        System.out.println(out.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

	}
	
	
	public static String gzip(String primStr) throws UnsupportedEncodingException {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
//			gzip.write(primStr.getBytes("UTF-8"));//
//			gzip.write(primStr.getBytes("ISO-8859-1"));//
			gzip.write(primStr.getBytes("GBK"));
//			gzip.write(primStr.getBytes());
//			gzip.write(new String(primStr.getBytes("UTF-8")).getBytes("ISO-8859-1"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//		return new String(out.toByteArray());
//		return new sun.misc.BASE64Encoder().encode(out.toByteArray());
		System.out.println(out.toString().length());
		return new String(new Base64().encode(out.toByteArray()));
//		return out.toString("ISO-8859-1");
	}
	
	public static String unGzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = new Base64().decode(compressedStr);
//			compressed = new Base64().decode(compressedStr.getBytes("UTF-8"));//��Ч
//			compressed = new Base64().decode(compressedStr);
//			in = new ByteArrayInputStream(new String(compressed).getBytes("UTF-8"));//����
//			in = new ByteArrayInputStream(new String(compressed).getBytes("GBK"));//����
//			compressed=compressedStr.getBytes("ISO-8859-1");
			in = new ByteArrayInputStream(compressed);//ʹ��ԭʼ�ֽ�����
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString("GBK");
//			decompressed = out.toString("UTF-8");
//			decompressed = new String(out.toString().getBytes("ISO-8859-1"),"GBK");
//			decompressed = out.toString("ISO-8859-1");
//			decompressed = out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}
	
	public static double Distance(double long1, double lat1, double long2, double lat2) {  
	    double a, b, R;  
	    R = 6378137; // ����뾶  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2  
	            * R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}  
	
	public static void main(String[] args) {
//		System.out.println(MD5Encode("666666"));
//		System.out.println("DTS_0080".compareTo("DTS_0070"));
//		System.out.println("DTS_0080".compareTo("DTS_0090"));
//		System.out.println(YBUtility.Distance(121.323963, 31.247211, 121.38781, 31.237262));
//		System.out.println(YBUtility.Distance(121.388887, 31.237862, 121.38781, 31.237262));
//		System.out.println(YBUtility.Distance(121.323963, 31.247211, 121.38781, 31.237262));
		System.out.println(Distance(121.327288, 31.24717, 121.38869, 31.237752));
//		System.out.println(YBUtility.Distance(121.327288, 31.24717, 121.323996, 31.24723));
		
	}

	
	public static void main1(String[] args) {
		// TODO Auto-generated method stub
//		String str=MD5Encode("111111");
		
		
		
		String str="%��һ��zip��ʽ����һ5B%7B%22lastUpdateTime%22%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221��һ���ַ�������zip��ʽѹ���ͽ�ѹ��";
		str+="%5B%7B%22lastUpdateTime%2345432522%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221��һ���ַ�������zip��ʽѹ���ͽ�ѹ��";
		str+="%5B%7B%22lastUpdateTime%2345432522%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221��һ���ַ�������zip��ʽѹ���ͽ�ѹ��";
		str+="%5B%7B%22lastUpdateTime%2345432522%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221��һ���ַ�������zip��ʽѹ���ͽ�ѹ��";
		str="{\"cashPayAmount\":1300,\"cashPayAmountR\":1300,\"comments\":\"�й���\",\"feeModelList\":[],\"priceStringList\":\"˾��,˾��ס��\",\"reasonList\":[{\"first\":\"����\",\"second\":\"REA_0010\"}]}";
		try {
			str=gzip(str);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(str.length());
		System.out.println(str);
		str=unGzip(str);
		System.out.println(str.length());
		System.out.println(str);
		
		
		String gbk = "������";   
		String iso;
		try {
			iso = new String(gbk.getBytes("UTF-8"),"ISO-8859-1");
			String utf8=new String(iso.getBytes("ISO-8859-1"),"UTF-8");  
			
			System.out.println(utf8);
			
			String chinese = "������";//java�ڲ�����
			String gbkChinese = new String(chinese.getBytes("GBK"),"ISO-8859-1");//ת����gbk����
			String unicodeChinese = new String(gbkChinese.getBytes("ISO-8859-1"),"GBK");//java�ڲ�����
			System.out.println(unicodeChinese);//����
			String utf8Chinese = new String(unicodeChinese.getBytes("UTF-8"),"ISO-8859-1");//utf--8����
			System.out.println(utf8Chinese);//����
			unicodeChinese = new String(utf8Chinese.getBytes("ISO-8859-1"),"UTF-8");//java�ڲ�����
			System.out.println(unicodeChinese);//����
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

	   try {
		ungzipFile(new File("f://gzip.gz"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	/**
	 * 
	 * @param conn
	 * @param sql
	 * @param beanName
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public static <T extends DataBean> List<T> getPOResult(Connection conn, String sql, T t) throws Exception {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			log.debug(sql);
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			return POFactoryUtil.getDataBeansFromResultSet(t, rs);		
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			close(rs,pstmt,null);
		}
	} 
	
//	@SuppressWarnings("unchecked")
//	public static <T> T execute(Connection conn, String sql, CallBackWrapper<T> wrapper) throws Exception {
//		
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		try{
//			log.debug(sql);
//			pstmt=conn.prepareStatement(sql);
//			rs=pstmt.executeQuery();
//			
//			return (T) wrapper.doIt(rs);		
//			
//		} catch(Exception e){
//			e.printStackTrace();
//			throw e;
//		} finally{
//			close(rs,pstmt,null);
//		}
//	}
}

///** 
// * ���ظ����ӵ�ַ��html���� 
// *  
// * @param urlStr 
// * @return 
// * @throws CommonException 
// */  
//public static String doGet(String urlStr) throws CommonException  
//{  
//    StringBuffer sb = new StringBuffer();  
//    try  
//    {  
//        URL url = new URL(urlStr);  
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
//        conn.setRequestMethod("GET");  
//        conn.setConnectTimeout(5000);  
//        conn.setDoInput(true);  
//        conn.setDoOutput(true);  
//
//        if (conn.getResponseCode() == 200)  
//        {  
//            InputStream is = conn.getInputStream();  
//            InputStreamReader isr = new InputStreamReader(is,"UTF-8");  
//            int len = 0;  
//            char[] buf = new char[1024];  //���ַ���ȡ
//
//            while ((len = isr.read(buf)) != -1)  
//            {  
//                sb.append(new String(buf, 0, len));  
//            }  
//
//            is.close();  
//            isr.close();  
//        } else  
//        {  
//            throw new CommonException("��������ʧ�ܣ�");  
//        }  
//
//    } catch (Exception e)  
//    {  
//        throw new CommonException("��������ʧ�ܣ�");  
//    }  
//    return sb.toString();  
//}  


