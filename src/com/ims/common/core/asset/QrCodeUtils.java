package com.ims.common.core.asset;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.util.ContentConverter;

import org.apache.commons.lang.StringUtils;

import com.swetake.util.Qrcode;
/**
 * QrCode.jar包二维码
 * @author chenyfing
 *
 */
public class QrCodeUtils {
	
    /** 
     * 创建二维码 
     * @param content  二维码内容
     * @param qrcodePath 二维码输出完整路径
     * @param format 图片格式
     * @param width 二维码宽度
     * @param height 二维码高度
     * @throws Exception 
     */  
    public static void createQrCode(String content,String qrcodePath) throws Exception {
        Qrcode qrcode = new Qrcode();  
        qrcode.setQrcodeErrorCorrect('M');  
        qrcode.setQrcodeEncodeMode('B');  
        qrcode.setQrcodeVersion(7);  
        byte[] bstr = content.getBytes("UTF-8");  
        BufferedImage bi = new BufferedImage(139, 139,BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = bi.createGraphics();  
        g.setBackground(Color.WHITE);   //背景颜色  
        g.clearRect(0, 0, 139, 139);  
        g.setColor(Color.BLACK);    //条码颜色  
        if (bstr.length > 0 && bstr.length < 123) {  
            boolean[][] b = qrcode.calQrcode(bstr);  
            for (int i = 0; i < b.length; i++) {  
                for (int j = 0; j < b.length; j++) {  
                    if (b[j][i]) {  
                        g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);  
                    }  
                }  
            }  
        }  
        g.dispose();  
        bi.flush();  
        File f = new File(qrcodePath);  
        ImageIO.write(bi, "png", f);  
    }  
    
   
  
      
    /** 
     * 解析二维条形码 
     * @param path  条形码图片的路径 
     * @throws Exception 
     */  
    public static void readQrCode(String path) throws Exception {  
        QRCodeDecoder decoder = new QRCodeDecoder();  
        BufferedImage image = null;  
        if (path.startsWith("http://")) {  
            image = ImageIO.read(new URL(path));  
        } else {  
            image = ImageIO.read(new File(path));  
        }  
        String decodedString = new String(decoder.decode(new J2SEImage(image)),"UTF-8");  
        decodedString = ContentConverter.convert(decodedString);  
        System.out.println("条码内容："+decodedString);  
    }  
    
    /*** 
     * 功能 :调整图片大小 开发：wuyechun 2011-7-22 
     * @param srcImgPath 原图片路径 
     * @param distImgPath  转换大小后图片路径 
     * @param width   转换后图片宽度 
     * @param height  转换后图片高度 
     */  
    public static void resizeImage(String srcImgPath, String distImgPath,  
            int width, int height) throws Exception {  
  
        File srcFile = new File(srcImgPath);  
        Image srcImg = ImageIO.read(srcFile);  
        BufferedImage buffImg = null;  
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        buffImg.getGraphics().drawImage(  
                srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,  
                0, null);  
  
        ImageIO.write(buffImg, "JPEG", new File(distImgPath));  
  
    }  
    
    
    /**
     * 案件地址
     * @param qrcodeAddress
     * 二维码模板图片地址
     * @param imageTemplateFile
     * 生成二维码图片地址
     * @param qrcodeFile
     * 生成新二维码图片地址
     * @param newQrcode
     */
    /**
     * @param qrcodeAddress
     * @param imageTemplateFile
     * @param qrcodeFile
     * @param newQrcode
     */
    public static void writeImage(String qrcodeAddress,String imageTemplateFile,String qrcodeFile,String newQrcode){
    	FileOutputStream fos = null; 
    	try {
			BufferedImage bimg=ImageIO.read(new FileInputStream(imageTemplateFile));
			//转换图片原始大小
			File srcFile = new File(qrcodeFile);  
	        Image srcImg = ImageIO.read(srcFile);  
	        int twoCodeBorder=370;
	        BufferedImage buffImg = new BufferedImage(twoCodeBorder, twoCodeBorder, BufferedImage.TYPE_INT_RGB);  
	        buffImg.getGraphics().drawImage(  
	                srcImg.getScaledInstance(twoCodeBorder, twoCodeBorder, Image.SCALE_SMOOTH), 0,  
	                0, null); 
			//得到Graphics2D 对象
			Graphics2D g2d=(Graphics2D)bimg.getGraphics();
			//设置颜色和画笔粗细
			 g2d.setColor(Color.BLACK);
			 g2d.setStroke(new BasicStroke(150));
			 Font font=new Font("黑体", Font.BOLD, 30);
			 g2d.setFont(font);
			 int leftmargin=155;
			 int startHeight=500;
			//绘制文字
			 if(StringUtils.isNotEmpty(qrcodeAddress)){
				 int wCnt=9;
				 if(qrcodeAddress.length()<=wCnt){
					 g2d.drawString(qrcodeAddress,leftmargin,startHeight);
				 }
				 if(qrcodeAddress.length()>wCnt&& qrcodeAddress.length()<=wCnt*2){
					 g2d.drawString(qrcodeAddress.substring(0,wCnt),leftmargin,startHeight);
					 g2d.drawString(qrcodeAddress.substring(wCnt,qrcodeAddress.length()), leftmargin,startHeight+50);
				 }
				 if(qrcodeAddress.length()>wCnt*2 && qrcodeAddress.length()<=wCnt*3){
					 g2d.drawString(qrcodeAddress.substring(0,wCnt),leftmargin,startHeight);
					 g2d.drawString(qrcodeAddress.substring(wCnt,wCnt*2), leftmargin,startHeight+50);
					 g2d.drawString(qrcodeAddress.substring(wCnt*2,qrcodeAddress.length()), leftmargin,startHeight+50*2);
				 }
				 if(qrcodeAddress.length()>wCnt*3&& qrcodeAddress.length()<=wCnt*4){
					 g2d.drawString(qrcodeAddress.substring(0,wCnt),leftmargin,startHeight);
					 g2d.drawString(qrcodeAddress.substring(wCnt,wCnt*2), leftmargin,startHeight+50);
					 g2d.drawString(qrcodeAddress.substring(wCnt*2,wCnt*3), leftmargin,startHeight+50*2);
					 g2d.drawString(qrcodeAddress.substring(wCnt*3,qrcodeAddress.length()), leftmargin,startHeight+50*3);
				 }
			 }else{
				 qrcodeAddress = "";
			 }
			//绘制图案
			g2d.drawImage(buffImg, null,72,55);
			//保存新图片
			fos = new FileOutputStream(newQrcode);
			ImageIO.write(bimg, "JPG",fos);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    public static void main(String[] args) {
    	try {
    		String address="夏茅二社彩诗化妆品公司";
    		String template="D:\\bycg_webserver\\apache-tomcat-7.0.27\\wtpwebapps\\urban\\images\\QcodeM.png";
    		String qrcodefile="D:\\bycg_webserver\\apache-tomcat-7.0.27\\wtpwebapps\\urban\\images\\qrcode.png";
    		String newqrcodefile="D:\\bycg_webserver\\apache-tomcat-7.0.27\\wtpwebapps\\urban\\upload\\qrcodes\\bbb.png";
    		writeImage(address,template,qrcodefile,newqrcodefile);
    		System.out.println(address.length());
    		System.out.println(address.substring(0,10));
    		System.out.println(address.substring(10,20));
    		System.out.println(address.substring(20,address.length()));
//    		System.out.println(address.substring(44,60));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
