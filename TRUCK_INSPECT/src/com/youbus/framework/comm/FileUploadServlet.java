/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.mytestaction
 * ��   ��  ��:FileUploadDemoAction.java
 * �� ������:2015��3��18��-����3:48:37
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.comm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import sun.misc.BASE64Decoder;

/**
 * ������:FileUploadDemoAction
 * ������:
 * ������:suhaitao
 * �޸���:Administrator
 * �޸�ʱ��:2015��3��18�� ����3:48:37
 * �޸ı�ע:
 * @version 1.0.0
 */
public class FileUploadServlet extends HttpServlet{

    /**
	 * serialVersionUID:TODO����һ�仰�������������ʾʲô��
	 *
	 * @since 1.0.0
	 */
	private static Logger log=Logger.getLogger(FileUploadServlet.class);
	private static final long serialVersionUID = -3382747710148469904L;
	private String uploadDir = "";  // �ļ��ϴ�Ŀ¼,�������������ļ���
    private String tempUploadDir = "";  // �ļ���ʱ���Ŀ¼���Ự���ٺ��ɼ������Զ�ɾ�����������������ļ���
    private String thumbImageDir = ""; //����ͼ���·��
    private String AppID=TruckInsNativeCacheService.APP_CODE; //AGENT_0001
    
    public void init() throws ServletException {
    	//TODO: �ݶ�
    	String contextPath = getServletContext().getRealPath("/");
   		
    	uploadDir=contextPath+"upload/";
    	
    	uploadDir=YBCommonContant.UPLOAD_TEMP_PATH;
//    	uploadDir="E:/YOUBUSFILEDATA/upload/";//�ݶ�
    	
    	File uFile=new File(uploadDir);
        if (!uFile.exists()) {
        	uFile.mkdirs();
        }
    	
    	tempUploadDir=uploadDir+"temp/";
    	File tFile=new File(tempUploadDir);
        if (!tFile.exists()) {
        	tFile.mkdirs();
        }
    	
    	thumbImageDir=uploadDir+"thumb/";
    	File thFile=new File(thumbImageDir);
        if (!thFile.exists()) {
        	thFile.mkdirs();
        }
    	
    	/* ��ȡ����Ŀ������ʵӲ��Ŀ¼
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.indexOf("WEB-INF"));
        // �ж�Ŀ���Ƿ���ڣ������ھͽ���
        String uploadDir = path.concat(this.getInitParameter("uploadDir"));
        String tempUploadDir = path.concat(this.getInitParameter("tempUploadDir"));
        File f_uploadDir = new File(uploadDir);
        File f_tempUploadDir = new File(tempUploadDir);
        if (!f_uploadDir.exists()) {
            f_uploadDir.mkdirs();
        }
        if (!f_tempUploadDir.exists()) {
            f_tempUploadDir.mkdirs();
        }
        // ��������ֵ
        this.uploadDir = uploadDir;
        this.tempUploadDir = tempUploadDir;
        */
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		 	response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json;charset=UTF-8");
	        response.setHeader("cache-control", "no-cache");
	        List<String> filePathList = new ArrayList<String>(); //�ŵ�session��
	        
	        String imgType=request.getParameter("type");

	        if("base64".equals(imgType)){ //����base64
		        String imgStr=request.getParameter("image_data");
		        //data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAA...   ;//data:image/png;base64
		        
		        if(StringUtils.isNotEmpty(imgStr)){
		        	
		        	String imgDataStr=imgStr.substring(imgStr.indexOf("base64,")+7,imgStr.length());//ȥ��ǰ׺
		        	String imgFileType=imgStr.substring(imgStr.indexOf("image")+6,imgStr.indexOf("base64,")-1);//jpeg ,png, ͼƬ�ļ���׺��
		        	imgFileType="jpeg".equals(imgFileType)?"jpg":imgFileType;
		            BASE64Decoder decoder = new BASE64Decoder();  
		            try{  
		                //Base64����  
		                byte[] b = decoder.decodeBuffer(imgDataStr);  
		                for(int i=0;i<b.length;++i){  
		                    if(b[i]<0){//�����쳣����  
		                        b[i]+=256;  
		                    }  
		                }  
		                //����jpegͼƬ  
		                String imgFilePath = "d:\\"+System.currentTimeMillis()+"."+imgFileType;//�����ɵ�ͼƬ  
//		                String imgFilePath = "d:\\222.jpg";//�����ɵ�ͼƬ  
		                OutputStream out = new FileOutputStream(imgFilePath);      
		                out.write(b);  
		                out.flush();  
		                out.close();  
		                
		                System.out.println("imgFilePath:="+imgFilePath);//�ļ�id ��Ҫ���ء�
		                FileUploadReturnBean returnBean=new FileUploadReturnBean();
		                returnBean.setSrcFilePath(imgFilePath);//���·��//fileserver/AGENT_0001/20150514/1/333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
//			            String returnJsonStr=null;
			            ObjectMapper objectMapper =new ObjectMapper(); 
			            response.getWriter().print(objectMapper.writeValueAsString(returnBean));
		                
		            	}catch (Exception e){  
		            		e.printStackTrace();
		            	}  
		        	}

	        }
	        /*
	        String s_resultJSON = org.apache.commons.lang.StringUtils.EMPTY;
	        
	            // ��鱾���Ƿ�һ���ļ��ϴ�����
	            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	            String returnStr=null;
	            if (isMultipart) {
	                DiskFileItemFactory factory = new DiskFileItemFactory(); // ����һ���������ڴ��̵��ļ���
	                factory.setRepository(new File(tempUploadDir)); // ���ô���⣨ȷ����ȫ����ʱλ��ʱ��
	                ServletFileUpload upload = new ServletFileUpload(factory); // ����һ���µ��ļ��ϴ��������
	                //��������Ҫ��ߴ����ƣ�����ǰ��̨�ֱ����ã���Ϊǰ��̨�õ��˲�ͬ�Ĳ���� �������������ļ���
	                upload.setSizeMax(1024 * 1024 * 100); 
	                List<FileItem> items = upload.parseRequest(request); // ��������
	                Iterator<FileItem> iter = items.iterator(); // �����ϴ�����Ŀ
	                Connection conn=DBService.getInstance().getConnection();
	                
	                while (iter.hasNext()) { //�����һ�����ϴ�����ļ����������ֱ�ȥ����
	                    FileItem item = iter.next();
	                    if (!item.isFormField()) { // ���˱���ķ��ļ������ֶ�
	                        if (!"".equals(item.getName())) { // ���˷��ļ����͵�input
	                            String s_name = item.getName(); // ���ԭʼ�ļ���
	                            int position = s_name.lastIndexOf(".");
	                            String s_fileType = s_name.substring(position, s_name.length()); // ����ļ���׺
	                            FsFileInfoPO fileInfo=YoubusFileService.getInstance().writeFileReturnObj(conn, AppID,String.valueOf(YBUtility.getMemberId(request.getSession())), s_name, item.getInputStream(), null);
	                            //getMemberId ���� ������Ա���������Ա���û��������û��ȵ�
//	                            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
//	                            String sdir = uploadDir.concat("/").concat(date).concat("/"); //Ŀ¼/����/
//	                            
//	                            //���ﰴ���ڷ�Ŀ¼�����ļ�
//	                            File sf = new File(sdir);
//	                            if (!sf.exists()) {
//	                                sf.mkdirs();
//	                            }
//	                            
//	                            String newFileNameStr=UUID.randomUUID().toString().concat(s_name);
//	                            String s_filePath = sdir.concat(newFileNameStr);
//	                            File path = new File(s_filePath);
//	                            item.write(path);
//	                            FsFileInfoPO finfo = new FsFileInfoPO();
//	                            finfo.setFileId(fileId);
//	                            finfo.setAppId(appId);
//	                            finfo=POFactory.getByPriKey(conn, finfo);
	                            
	                            String realPath=fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();  //������space·��
	                            filePathList.add(realPath);//
	                            
	                            String srcFileServerPath="/fileserver/"+fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();
	                            System.out.println("realPath :"+realPath); // \AGENT_0001\20150514\1\333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	                            System.out.println("srcFileServerPath :"+srcFileServerPath); // \AGENT_0001\20150514\1\333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	                            //http://localhost:8088/fileserver/AGENT_0001/20150514/1/333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	                            item.delete();
	                            
//	                            String thumbDir = thumbImageDir.concat(date).concat("/")  ; //Ŀ¼/����/
//	                            File thumbFile = new File(thumbDir);
//	                            if (!thumbFile.exists()) {
//	                            	thumbFile.mkdirs();
//	                            }
	                            int srcFileId=fileInfo.getFileId().intValue();
	                            File srcFile=YoubusFileService.getInstance().getFile(conn, fileInfo.getFileId(), AppID, fileInfo.getFileOwner());
	                            if(YBCommonContant.OSS_ISREADY){
	                            	String agentId=String.valueOf(YBUtility.getAgentId(request.getSession()));
	                            	if(srcFile!=null){
	                            		String key=OSSClinentFactory.getInstant().putObject(srcFile.getName(), srcFile,agentId);
//	                            		String key=OSSClinentFactory.getInstant().putObject(srcFile.getName(), srcFile);
	                            		log.debug("�ϴ�OSS�ɹ�:="+key);
	                            		srcFileServerPath="http://"+YBCommonContant.OSS_BUCKET+"."+YBCommonContant.OSS_ENDPOINT_OUT+"/"+agentId+"/"+key;
	                            	}
	                            }else{
	                            	    log.debug("��OSS����");
	                            }
//	                            String thumbPath = thumbDir.concat(newFileNameStr);
//	                    		String srcFilePath=srcFile.getAbsolutePath();
	                    		String thumbPath=srcFile.getParent()+File.separator+YBCommonContant.FILE_SPACE_THUMB_DIR;//path
	                    		String thumbName=null;
	                    		String diskFileName=fileInfo.getFileDiskName();
	                    		if(diskFileName.lastIndexOf(".")!=-1){
	                    			thumbName=diskFileName.substring(0, diskFileName.lastIndexOf("."))+YBCommonContant.FILE_SPACE_THUMB_SUFFIX+"."+YBCommonContant.FILE_SAPCE_THUMB_TYPE;
	                    		}
	                    		else{
	                    			thumbName=diskFileName+YBCommonContant.FILE_SPACE_THUMB_SUFFIX+"."+YBCommonContant.FILE_SAPCE_THUMB_TYPE;
	                    		}
	                    		String thumbFullPath=thumbPath+File.separator+thumbName; //path+name
	                    		
	                    		File thumbPathDir=new File(thumbPath);
	                    		if(!thumbPathDir.exists())thumbPathDir.mkdir();
	                    		
	                            boolean isThumbSuccess = ImageUtil.makeThumbnailImage(srcFile, thumbFullPath);
	                            if(isThumbSuccess){
	                            	File thumbFile=new File(thumbFullPath);
	                            	fileInfo.setFileCreateDate(new Date());
	                            	fileInfo.setFileId(POFactory.getLongPriKey(conn,fileInfo));
//	                            	fileInfo.setFileInvalidDate(null);
	                            	fileInfo.setNeedDelete(new Integer(0));
	                            	fileInfo.setFileRelationPath(fileInfo.getFileRelationPath()+File.separator+YBCommonContant.FILE_SPACE_THUMB_DIR);
	                            	fileInfo.setFileDiskName(thumbName);
	                            	fileInfo.setFileLength(thumbFile.length());
	                            	POFactory.insert(conn, fileInfo);	                            	
//	                            String contextPath=	getServletContext().getContextPath().replace("/", "").replace("\\", "");
	                            String fileserverpath="/fileserver/"+fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();
	                            returnStr=fileserverpath+","+fileInfo.getFileId();
//	                            s_resultJSON = "/fileserver/"+fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();
//	                            s_resultJSON = "/fileserver/"+fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();
	                            System.out.println("s_resultJSON:="+s_resultJSON);//�ļ�id ��Ҫ���ء�
	                            FileUploadReturnBean returnBean=new FileUploadReturnBean();
	                            returnBean.setThumbFileId(fileInfo.getFileId().intValue());
	                            returnBean.setThumbFilePath(fileserverpath);
	                            returnBean.setSrcFileId(srcFileId);
	                            returnBean.setSrcFilePath(srcFileServerPath);//���·��//fileserver/AGENT_0001/20150514/1/333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	             	           
	            	            String returnJsonStr=null;
	            	            ObjectMapper objectMapper =new ObjectMapper(); 
	            	            response.getWriter().print(objectMapper.writeValueAsString(returnBean));
	                            }
	                            
	                        } else {
	                        	
	                            break;
	                        }
	                    }
	                }
	            }
	           //TODO: ����Ҫ�Ĵ���������session
//	            response.getWriter().print(returnStr);

//	            response.getWriter().print(s_resultJSON);
 */
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	response.getWriter().flush();
	        	response.getWriter().close();
	        }
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter out = response.getWriter();
		  out.print("{'error':'-1'}"); // �Ƿ��ύ��ʽ
	}
	
	public static void main(String args[]){
		
        String imgStr="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAA..."   ;//data:image/png;base64
        
        if(StringUtils.isNotEmpty(imgStr)){
        	
        	String imgDataStr=imgStr.substring(imgStr.indexOf("base64,")+7,imgStr.length());
        	String imgType=imgStr.substring(imgStr.indexOf("image")+6,imgStr.indexOf("base64,")-1);
        	System.out.println(imgDataStr);
        	System.out.println(imgType);
        	System.out.println(imgStr.indexOf("image"));
        }		
	}
	
}
