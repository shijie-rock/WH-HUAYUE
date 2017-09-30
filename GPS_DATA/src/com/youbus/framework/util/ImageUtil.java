/**
 * ��Ŀ����:AGENT_CENTER
 * ��         ��:com.youbus.framework.util
 * ��   ��  ��:ImageUtil.java
 * �� ������:2015��3��19��-����10:01:18
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
 * ������:ImageUtil
 * ������:ͼƬ��������
 * ������:suhaitao
 * �޸���:Administrator
 * �޸�ʱ��:2015��3��19�� ����10:01:18
 * �޸ı�ע:
 * @version 1.0.0
 */
public class ImageUtil {
    /**
     * ��   ��  ��:makethumbImage
     * ��������: ��������ͼ
     * ��         ��:@param srcImageFile ԴͼƬ�ļ���Fileʵ�� File file = new File("�ļ���")
     * ��         ��:@param dstImageFileName �����ɵ�����ͼƬ����·��(���ɵĸ�ʽΪ��image/imgName.jpg);
     * ��         ��:@throws Exception
     * ��   ��  ֵ:void
     * ��   ��  ��:suhaitao
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
            int srcWidth = srcImage.getWidth(null);//ԭͼƬ���
            int srcHeight = srcImage.getHeight(null);//ԭͼƬ�߶�
            int dstMaxSize = 120;//Ŀ������ͼ�������/�߶ȣ������߶Ƚ���������д
            int dstWidth = srcWidth;//����ͼ���
            int dstHeight = srcHeight;//����ͼ�߶�
           
            //��������ͼ�Ŀ�͸�
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
            
            //��������ͼ
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
     * ��   ��  ��:makeThumbnailImageBatch
     * ��������:������������ͼ
     * ���˷�ͼƬ��ʽ
     * ��         ��:@param srcImageFiles ͼƬ��Դ
     * ��         ��:@param dstImageFileNames ��ת����ͼƬ����(·��+ͼƬ��)
     * ��         ��:@return
     * ��         ��:@throws Exception
     * ��   ��  ֵ:List<String>
     * ��   ��  ��:suhaitao
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
