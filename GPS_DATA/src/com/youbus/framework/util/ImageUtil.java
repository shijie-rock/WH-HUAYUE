/**
 * 项目名称:AGENT_CENTER
 * 包         名:com.youbus.framework.util
 * 文   件  名:ImageUtil.java
 * 创 建日期:2015年3月19日-上午10:01:18
 * Copyright @ 2015-YouBus.com.cn
 */
package com.youbus.framework.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 类名称:ImageUtil
 * 类描述:图片处理常用类
 * 创建人:suhaitao
 * 修改人:Administrator
 * 修改时间:2015年3月19日 上午10:01:18
 * 修改备注:
 * @version 1.0.0
 */
public class ImageUtil {
    /**
     * 方   法  名:makethumbImage
     * 方法描述: 生成缩略图
     * 参         数:@param srcImageFile 源图片文件的File实例 File file = new File("文件名")
     * 参         数:@param dstImageFileName 待生成的缩略图片完整路径(生成的格式为：image/imgName.jpg);
     * 参         数:@throws Exception
     * 返   回  值:void
     * 创   建  人:suhaitao
     * @exception
     * @since  1.0.0
    */
    public static boolean makeThumbnailImage(File srcImageFile,String dstImageFileName) throws Exception {
    	boolean isSuccess=true;
    	
        FileOutputStream fileOutputStream = null;
        JPEGImageEncoder encoder = null;
        BufferedImage tagImage = null;
        Image srcImage = null;
        try{
            srcImage = ImageIO.read(srcImageFile);
            int srcWidth = srcImage.getWidth(null);//原图片宽度
            int srcHeight = srcImage.getHeight(null);//原图片高度
            int dstMaxSize = 120;//目标缩略图的最大宽度/高度，宽度与高度将按比例缩写
            int dstWidth = srcWidth;//缩略图宽度
            int dstHeight = srcHeight;//缩略图高度
           
            //计算缩略图的宽和高
            float scale = 0;
            if(srcWidth>dstMaxSize){
                dstWidth = dstMaxSize;
                scale = (float)srcWidth/(float)dstMaxSize;
                dstHeight = Math.round((float)srcHeight/scale);
            }
            srcHeight = dstHeight;
            if(srcHeight>dstMaxSize){
                dstHeight = dstMaxSize;
                scale = (float)srcHeight/(float)dstMaxSize;
                dstWidth = Math.round((float)dstWidth/scale);
            }
            
            //生成缩略图
            tagImage = new BufferedImage(dstWidth,dstHeight,BufferedImage.TYPE_INT_RGB);
            tagImage.getGraphics().drawImage(srcImage,0,0,dstWidth,dstHeight,null);
            fileOutputStream = new FileOutputStream(dstImageFileName);
            encoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
            encoder.encode(tagImage);
            fileOutputStream.close();
            fileOutputStream = null;
        }catch(Exception e){
        	e.printStackTrace();
        	//TODO: dolog
        	isSuccess = false;
        }
        finally{
            if(fileOutputStream!=null){
                try{
                    fileOutputStream.close();
                }catch(Exception e){
                }
                fileOutputStream = null;
            }
            encoder = null;
            tagImage = null;
            srcImage = null;
            System.gc();
        }
        
        return isSuccess;
    }

    /**
     * 方   法  名:makeThumbnailImageBatch
     * 方法描述:批量生成缩略图
     * 过滤非图片格式
     * 参         数:@param srcImageFiles 图片来源
     * 参         数:@param dstImageFileNames 可转换的图片集合(路径+图片名)
     * 参         数:@return
     * 参         数:@throws Exception
     * 返   回  值:List<String>
     * 创   建  人:suhaitao
     * @exception
     * @since  1.0.0
    */
    public static boolean makeThumbnailImageBatch(List<File> srcImageFiles,List<String> dstImageFileNames) throws Exception{
    	boolean isSuccess = true;
    	List<String> successImageList = new ArrayList<String>(dstImageFileNames.size());
    	try{
    		for (int i = 0,len=srcImageFiles.size(); i < len; i++) {
    			File srcFile = srcImageFiles.get(i);
    			String fileName = srcFile.getName().toLowerCase();
    			
    			if(fileName.endsWith(".jpg")||fileName.endsWith(".jpeg")||fileName.endsWith(".bmp")||fileName.endsWith(".gif")){
    				String dstImageFileName = dstImageFileNames.get(0);
    				makeThumbnailImage(srcFile,dstImageFileName);
    				successImageList.add(dstImageFileName);
    			}
			}
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		isSuccess=false;
    	}
    	
    	dstImageFileNames.clear();
    	dstImageFileNames.addAll(successImageList);
    	
    	return isSuccess;
    }
}
