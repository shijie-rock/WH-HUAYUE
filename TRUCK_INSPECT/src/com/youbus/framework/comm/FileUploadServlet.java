/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.mytestaction
 * 文   件  名:FileUploadDemoAction.java
 * 创 建日期:2015年3月18日-下午3:48:37
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
 * 类名称:FileUploadDemoAction
 * 类描述:
 * 创建人:suhaitao
 * 修改人:Administrator
 * 修改时间:2015年3月18日 下午3:48:37
 * 修改备注:
 * @version 1.0.0
 */
public class FileUploadServlet extends HttpServlet{

    /**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	private static Logger log=Logger.getLogger(FileUploadServlet.class);
	private static final long serialVersionUID = -3382747710148469904L;
	private String uploadDir = "";  // 文件上传目录,后期移至配置文件中
    private String tempUploadDir = "";  // 文件临时存放目录（会话销毁后由监听器自动删除）后期移至配置文件中
    private String thumbImageDir = ""; //缩略图存放路径
    private String AppID=TruckInsNativeCacheService.APP_CODE; //AGENT_0001
    
    public void init() throws ServletException {
    	//TODO: 暂定
    	String contextPath = getServletContext().getRealPath("/");
   		
    	uploadDir=contextPath+"upload/";
    	
    	uploadDir=YBCommonContant.UPLOAD_TEMP_PATH;
//    	uploadDir="E:/YOUBUSFILEDATA/upload/";//暂定
    	
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
    	
    	/* 获取本项目所在真实硬盘目录
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.indexOf("WEB-INF"));
        // 判断目标是否存在，不存在就建立
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
        // 给变量赋值
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
	        List<String> filePathList = new ArrayList<String>(); //放到session中
	        
	        String imgType=request.getParameter("type");

	        if("base64".equals(imgType)){ //处理base64
		        String imgStr=request.getParameter("image_data");
		        //data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAA...   ;//data:image/png;base64
		        
		        if(StringUtils.isNotEmpty(imgStr)){
		        	
		        	String imgDataStr=imgStr.substring(imgStr.indexOf("base64,")+7,imgStr.length());//去掉前缀
		        	String imgFileType=imgStr.substring(imgStr.indexOf("image")+6,imgStr.indexOf("base64,")-1);//jpeg ,png, 图片文件后缀名
		        	imgFileType="jpeg".equals(imgFileType)?"jpg":imgFileType;
		            BASE64Decoder decoder = new BASE64Decoder();  
		            try{  
		                //Base64解码  
		                byte[] b = decoder.decodeBuffer(imgDataStr);  
		                for(int i=0;i<b.length;++i){  
		                    if(b[i]<0){//调整异常数据  
		                        b[i]+=256;  
		                    }  
		                }  
		                //生成jpeg图片  
		                String imgFilePath = "d:\\"+System.currentTimeMillis()+"."+imgFileType;//新生成的图片  
//		                String imgFilePath = "d:\\222.jpg";//新生成的图片  
		                OutputStream out = new FileOutputStream(imgFilePath);      
		                out.write(b);  
		                out.flush();  
		                out.close();  
		                
		                System.out.println("imgFilePath:="+imgFilePath);//文件id 需要返回。
		                FileUploadReturnBean returnBean=new FileUploadReturnBean();
		                returnBean.setSrcFilePath(imgFilePath);//相对路径//fileserver/AGENT_0001/20150514/1/333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
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
	        
	            // 检查本次是否一个文件上传请求
	            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	            String returnStr=null;
	            if (isMultipart) {
	                DiskFileItemFactory factory = new DiskFileItemFactory(); // 创建一个工厂基于磁盘的文件项
	                factory.setRepository(new File(tempUploadDir)); // 配置储存库（确保安全的临时位置时）
	                ServletFileUpload upload = new ServletFileUpload(factory); // 创建一个新的文件上传处理程序
	                //设置总体要求尺寸限制（建议前后台分别设置，因为前后台用到了不同的插件） 后期移至配置文件中
	                upload.setSizeMax(1024 * 1024 * 100); 
	                List<FileItem> items = upload.parseRequest(request); // 解析请求
	                Iterator<FileItem> iter = items.iterator(); // 处理上传的项目
	                Connection conn=DBService.getInstance().getConnection();
	                
	                while (iter.hasNext()) { //如果是一次性上传多个文件，那这里会分别去保存
	                    FileItem item = iter.next();
	                    if (!item.isFormField()) { // 过滤表单里的非文件类型字段
	                        if (!"".equals(item.getName())) { // 过滤非文件类型的input
	                            String s_name = item.getName(); // 获得原始文件名
	                            int position = s_name.lastIndexOf(".");
	                            String s_fileType = s_name.substring(position, s_name.length()); // 获得文件后缀
	                            FsFileInfoPO fileInfo=YoubusFileService.getInstance().writeFileReturnObj(conn, AppID,String.valueOf(YBUtility.getMemberId(request.getSession())), s_name, item.getInputStream(), null);
	                            //getMemberId 适配 各种人员，比如管理员，用户，车队用户等等
//	                            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
//	                            String sdir = uploadDir.concat("/").concat(date).concat("/"); //目录/日期/
//	                            
//	                            //这里按日期分目录保存文件
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
	                            
	                            String realPath=fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();  //不包含space路径
	                            filePathList.add(realPath);//
	                            
	                            String srcFileServerPath="/fileserver/"+fileInfo.getFileRelationPath()+File.separator+fileInfo.getFileDiskName();
	                            System.out.println("realPath :"+realPath); // \AGENT_0001\20150514\1\333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	                            System.out.println("srcFileServerPath :"+srcFileServerPath); // \AGENT_0001\20150514\1\333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	                            //http://localhost:8088/fileserver/AGENT_0001/20150514/1/333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	                            item.delete();
	                            
//	                            String thumbDir = thumbImageDir.concat(date).concat("/")  ; //目录/日期/
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
	                            		log.debug("上传OSS成功:="+key);
	                            		srcFileServerPath="http://"+YBCommonContant.OSS_BUCKET+"."+YBCommonContant.OSS_ENDPOINT_OUT+"/"+agentId+"/"+key;
	                            	}
	                            }else{
	                            	    log.debug("无OSS服务。");
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
	                            System.out.println("s_resultJSON:="+s_resultJSON);//文件id 需要返回。
	                            FileUploadReturnBean returnBean=new FileUploadReturnBean();
	                            returnBean.setThumbFileId(fileInfo.getFileId().intValue());
	                            returnBean.setThumbFilePath(fileserverpath);
	                            returnBean.setSrcFileId(srcFileId);
	                            returnBean.setSrcFilePath(srcFileServerPath);//相对路径//fileserver/AGENT_0001/20150514/1/333a9f02-0191-4bbb-b3d1-842fe4935e76.pdf
	             	           
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
	           //TODO: 将需要的处理结果放入session
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
		  out.print("{'error':'-1'}"); // 非法提交方式
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
