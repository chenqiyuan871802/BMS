package com.ims.common.core.asset;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import sun.misc.BASE64Encoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author： 陈骑元 @company：
 * @ClassName:功能描述：文件操作<br>
 * 
 * @version V1.0? @time：2012-5-31 上午11:05:02
 * 
 */
public final class FileOperation {
	 private static final Logger log = Logger.getLogger(FileOperation.class);
	private static final int BUFFER = 1024;
	
	
	/**
	 * 
	 * 简要说明：获取文件类型
	 * 编写者：陈骑元
	 * 创建时间：2016年7月23日 上午9:45:40
	 * @param 说明
	 * @return 说明
	 */
	public static String getFileType(String fileName) { // 获取文件名后缀名
		if(IMSUtils.isEmpty(fileName)){
			throw new RuntimeException("文件名称为空");
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	/**
	 * 判断文件是否为图片<br>
	 * <br>
	 * 
	 * @param filename
	 *            文件名<br>
	 *            判断具体文件类型<br>
	 * @return 检查后的结果<br>
	 * @throws Exception
	 */
	public static boolean isPicture(String filename) {
		// 文件名称为空的场合
		if (IMSUtils.isEmpty(filename)) {
			// 返回不和合法
			return false;
		}
		// 获得文件后缀名
		//String tmpName = getExtend(filename);
		String tmpName = filename;
		// 声明图片后缀名数组
		String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" },
				{ "gif", "2" }, { "jfif", "3" }, { "jpe", "4" },
				{ "jpeg", "5" }, { "jpg", "6" }, { "png", "7" },
				{ "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
		// 遍历名称数组
		for (int i = 0; i < imgeArray.length; i++) {
			// 判断单个类型文件的场合
			if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * 简要说明：递归创建文件夹
	 * 编写者：陈骑元
	 * 创建时间：2016年9月6日 下午4:46:30
	 * @param 说明
	 * @return 说明
	 */
	public static boolean createFolder(String folderPath) {
		File folderFile = new File(folderPath); // 如果文件夹不存在 则创建文件夹
		if(folderFile.getParentFile().exists()){
			folderFile.mkdir();
		  }else{
			  createFolder(folderFile.getParentFile().getPath());
			  folderFile.mkdir();
		}
	   
		return true;
	}

	/**
	 * 如果文件夹不存在，则创建
	 * 
	 * @param file
	 *            文件
	 * @param folderPath文件夹路径
	 * @param fileName文件名称
	 * @return
	 */
	public static boolean buildFile(File file, String folderPath, String fileName) {
		boolean result = false;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		long fileLen = file.length();
		try {
			File folderFile = new File(folderPath);
			if (!folderFile.exists() && !folderFile.isDirectory()) {
				folderFile.mkdir();
			}
			String outFilePath = folderPath + File.separator + fileName;
			fos = new FileOutputStream(outFilePath);
			if (fileLen == 0) {
				file.createNewFile();
			}
			fis = new FileInputStream(file); // 读取文件内容
			bis = new BufferedInputStream(fis); // 使用流读出
			byte[] buf = new byte[BUFFER];
			int len = 0;
			while ((len = bis.read(buf)) != -1) {
				fos.write(buf, 0, len); // 写入到压缩包
				fos.flush();
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {

			try {
				if (fos != null) {
					fos.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				result = false;
				e.printStackTrace();

			}
		}

		return result;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 *            String 要检查文件的绝对路径
	 * @return boolean true存在，false不存在
	 */
	public static boolean isExist(String fileName) throws Exception {
		if (IMSUtils.isEmpty(fileName))
			return false;

		try {
			File file = new File(fileName);
			return file.exists();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * 创建一个新文件
	 * 
	 * @param fileName
	 *            String 文件绝对路径
	 * @param isReplace
	 *            boolean 如果存在是否替换 true替换，false保留
	 * @return boolean 是否创建成功
	 */
	public static boolean createFile(String fileName, boolean isReplace) throws Exception {
		if (IMSUtils.isEmpty(fileName))
			return false;

		try {
			File file = new File(fileName);
			// 如果替换的话，先删除再创建
			if (!file.exists()) {
				file.createNewFile();
			}
			// 如果替换的话，先删除再创建
			else if (isReplace) {
				file.delete();
				file.createNewFile();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            String 要删除文件的绝对路径
	 * @return boolean
	 */
	public static boolean deleteFile(String fileName)  {
		if (IMSUtils.isEmpty(fileName))
			return false;

		try {
			File file = new File(fileName);

			if (file.exists()) {
				return file.delete();
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return false;
	}

	/**
	 * 重命名文件
	 * 
	 * @param desFileName
	 *            String
	 * @throws Exception
	 * @return boolean
	 */
	public static boolean renameFile(String srcFileName, String desFileName) throws Exception {
		if (IMSUtils.isEmpty(desFileName))
			return false;

		try {
			File file = new File(desFileName);
			File src_file = new File(srcFileName);
			if (src_file.exists()) {
				return src_file.renameTo(file);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * 文件拷贝
	 * 
	 * @param sourceFile
	 *            String 源文件绝对路径
	 * @param desFile
	 *            String 目标文件绝对路径
	 * @param isReplace
	 *            boolean 如目标文件存在是否替换
	 * @return boolean
	 */
	public static boolean copyFile(String sourceFile, String desFile, boolean isReplace) throws Exception {
		try {
			int bytesum = 0;
			int byteread = 0;

			// 如目标文件存在并且要替换的话，先删除再复制，否则不进行任何操作
			if (isExist(desFile)) {
				if (isReplace)
					deleteFile(desFile);
				else
					return true;
			}

			InputStream inStream = new FileInputStream(sourceFile);
			FileOutputStream fs = new FileOutputStream(desFile);
			byte[] buffer = new byte[1444];
			int length;
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			return true;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (IOException ex1) {
			ex1.printStackTrace();
			throw ex1;
		}
	}

	/**
	 * 读取文件信息到字符数组中，文件中一行作为数组的一个元素。文件中每行长度不能超过60000字节
	 * 
	 * @param fileName
	 *            String 文件绝对路径
	 * @throws Exception
	 * @return String[] 文件内容
	 */
	public static String[] readFile(String fileName) throws Exception {
		try {
			ArrayList list = new ArrayList();
			String oneLine = "";
			String temp = "";
			FileInputStream fileInputStream = new FileInputStream(new File(fileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
			while ((oneLine = br.readLine()) != null) {
				// 略过空行
				if (oneLine.equals(""))
					continue;
				if (oneLine.length() > 60000)
					throw new Exception("文件中记录过长");
				list.add(oneLine);
			}
			if (list.size() == 0)
				return new String[0];

			String[] strData = new String[list.size()];
			String aRecord = "";
			for (int i = 0; i < list.size(); i++) {
				temp = (String) list.get(i);
				strData[i] = temp;
			}
			return strData;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.toString());
		}
	}

	/**
	 * 将文本内容写入到指定文件中
	 * 
	 * @param fileName
	 *            String 文件绝对路径
	 * @param content
	 *            String 要写入的内容
	 * @param isCreate
	 *            boolean 文件不存在是否创建
	 * @throws Exception
	 * @return boolean
	 */
	public static boolean writeFile(String fileName, String content, boolean isCreate) throws Exception {
		try {
			if (!isExist(fileName)) {
				if (!isCreate)
					return false;
				else
					createFile(fileName, true);
			}

			// 读文件内容,获取文件长度并定义数组
			File fl = new File(fileName);
			int flen = (int) fl.length();
			char file_content[] = new char[flen];

			// 读文件内容
			try {
				if (fl.canRead()) {
					FileReader fis = new FileReader(fileName);
					flen = fis.read(file_content);
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("系统写日志时产生错误: " + e.toString());
			}

			// 写文件内容
			try {
				FileWriter pw = new FileWriter(fileName);

				pw.write(file_content, 0, flen);
				if (flen > 0)
					pw.write("\r\n");
				pw.write(content);

				pw.close();
			} catch (IOException e) {
				System.out.println("系统写日志时产生错误: " + e.toString());
			}

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.toString());
		}
	}

	/**
	 * 取得指定目录下的所有文件信息
	 * 
	 * @param path
	 *            String 路径名称
	 * @throws Exception
	 * @return String[]
	 */
	public static String[] getFiles(String path) throws Exception {
		try {
			File d = new File(path); // 建立当前目录中文件的File对象
			File list[] = d.listFiles(); // 取得代表目录中所有文件的File对象数组

			String[] files = new String[list.length];
			for (int i = 0; i < list.length; i++) {
				if (list[i].isFile()) {
					files[i] = list[i].getName();
				}
			}
			return files;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * 获取文件大小,单位为k
	 * 
	 * @param fileName
	 *            String
	 * @throws Exception
	 * @return double
	 */
	public static double getFileSize(String fileName) throws Exception {
		try {
			File file = new File(fileName);

			if (file.exists() && file.isFile()) {
				return file.length() / 1024;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return 0;
	}

	/**
	 * 读取到字节数组
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {

			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArrayBig(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param response
	 * @param filePath
	 *            //文件完整路径(包括文件名和扩展名)
	 * @param fileName
	 *            //下载后看到的文件名
	 * @return 文件名
	 */
	public static void downloadFile(final HttpServletRequest request,final HttpServletResponse response, String filePath, String fileName) {

		OutputStream outputStream = null;
		try {
			byte[] data = toByteArray(filePath);
			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {  //解决IE和火狐文件名兼容的问题
				fileName=new String( fileName.getBytes("utf-8"),"ISO-8859-1");//解决中文乱码
		      } else {
		    	   fileName=URLEncoder.encode( fileName,"UTF-8");;
		     }
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * 直接指定压缩后的宽高： (先保存原文件，再压缩、上传) 壹拍项目中用于二维码压缩
	 * 
	 * @param oldFile
	 *            要进行压缩的文件全路径
	 * @param width
	 *            压缩后的宽度
	 * @param height
	 *            压缩后的高度
	 * @param quality
	 *            压缩质量
	 * @param smallIcon
	 *            文件名的小小后缀(注意，非文件后缀名称),入压缩文件名是yasuo.jpg,则压缩后文件名是yasuo(+smallIcon
	 *            ).jpg
	 * @return 返回压缩后的文件的全路径
	 */
	public static String zipImageFile(String oldFile, int width, int height, float quality, String smallIcon) {
		if (oldFile == null) {
			return null;
		}
		String newImage = null;
		try {
			/** 对服务器上的临时文件进行处理 */
			Image srcFile = ImageIO.read(new File(oldFile));
			/** 宽,高设定 */
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
			String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
			/** 压缩后的文件名 */
			newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());
			/** 压缩之后临时存放位置 */
			FileOutputStream out = new FileOutputStream(newImage);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			/** 压缩质量 */
			jep.setQuality(quality, true);
			encoder.encode(tag, jep);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newImage;
	}

	/**
	 * 保存文件到服务器临时路径(用于文件上传)
	 * 
	 * @param fileName
	 * @param is
	 * @return 文件全路径
	 */
	public static String writeFile(String fileName, InputStream is) {
		if (fileName == null || fileName.trim().length() == 0) {
			return null;
		}
		try {
			/** 首先保存到临时文件 */
			FileOutputStream fos = new FileOutputStream(fileName);
			byte[] readBytes = new byte[512];// 缓冲大小
			int readed = 0;
			while ((readed = is.read(readBytes)) > 0) {
				fos.write(readBytes, 0, readed);
			}
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 等比例压缩算法： 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
	 * 
	 * @param srcURL
	 *            原图地址
	 * @param deskURL
	 *            缩略图地址
	 * @param comBase
	 *            压缩基数
	 * @param scale
	 *            压缩限制(宽/高)比例 一般用1：
	 *            当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=
	 *            comBase,height按原图宽高比例
	 * @throws Exception
	 * @author 
	 * @createTime 2014-12-16
	 * @lastModifyTime 2014-12-16
	 */
	public static void saveMinPhoto(String srcURL, String deskURL, double comBase, double scale)  {
		FileOutputStream deskImage=null;
		try {
			File srcFile = new java.io.File(srcURL);
			Image src = ImageIO.read(srcFile);
			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);
			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽
			double srcScale = (double) srcHeight / srcWidth;
			/** 缩略图宽高算法 */
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}
			BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
			tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
			deskImage= new FileOutputStream(deskURL); // 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
			encoder.encode(tag); // 近JPEG编码
			deskImage.close();
		} catch (IOException e) {
		 	// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(deskImage!=null){
				try {
					deskImage.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
	/**
	 * 
	 * 简要说明：输出字节数组
	 * 编写者：陈骑元
	 * 创建时间：2016年8月10日 上午10:02:33
	 * @param 说明 
	 * @return 说明
	 */
	public static String  getZipPhotoByteStr(String srcURL,  double comBase, double scale)  {
		
		ByteArrayOutputStream out=null;
		try {
			File srcFile = new java.io.File(srcURL);
			if (!srcFile.exists()) {
				return null;
			}
			Image src = ImageIO.read(srcFile);
			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);
			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽
			double srcScale = (double) srcHeight / srcWidth;
			/** 缩略图宽高算法 */
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}
			BufferedImage image = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
			image.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
			out = new ByteArrayOutputStream();  
			String ext=FileOperation.getFileType(srcURL);
			ImageIO.write(image, ext, out);  
			BASE64Encoder encoder = new BASE64Encoder();
			return  encoder.encode(out.toByteArray());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}
	/**
	 * 
	 * 简要说明：输出字节数组
	 * 编写者：陈骑元
	 * 创建时间：2016年8月10日 上午10:02:33
	 * @param 说明 
	 * @return 说明
	 */
	public static byte[]  getZipPhotoByte(String srcURL,  double comBase, double scale)  {
		
		ByteArrayOutputStream out=null;
		try {
			File srcFile = new java.io.File(srcURL);
			if (!srcFile.exists()) {
				return null;
			}
			Image src = ImageIO.read(srcFile);
			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);
			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽
			double srcScale = (double) srcHeight / srcWidth;
			/** 缩略图宽高算法 */
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}
			BufferedImage image = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
			image.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
			out = new ByteArrayOutputStream();  
			String ext=FileOperation.getFileType(srcURL);
			ImageIO.write(image, ext, out);  
			
			return  out.toByteArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}
	  /**
     * 将压缩编码后的obj字符串对象，反传为原对象
     * 
     * @param str
     * 将压缩编码后的obj字符串
     * @return Object 原对象
     */
    public static final Object unzipobj(String str) {
        String objstr = unzip(str);
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(IMSUtils.decodeHex(objstr));
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "";
    }
    
    /**
    * 使用zip进行解压缩
    * 
    * @param compressed
    * 压缩后的文本
    * @return
    * 解压后的字符串
    */
   public static final String unzip(String compressedStr) {
       if (IMSUtils.isEmpty(compressedStr)) {
           return null;
       }
       ByteArrayOutputStream out = null;
       ByteArrayInputStream in = null;
       ZipInputStream zin = null;
       String decompressed = null;
       try {
           byte[] compressed = IMSUtils.decodeHex(compressedStr);
           out = new ByteArrayOutputStream();
           in = new ByteArrayInputStream(compressed);
           zin = new ZipInputStream(in);
           zin.getNextEntry();
           byte[] buffer = new byte[1024];
           int offset = -1;
           while ((offset = zin.read(buffer)) != -1) {
               out.write(buffer, 0, offset);
           }
           decompressed = out.toString();
       } catch (Exception e) {
           decompressed = null;
           log.error("文件进行Zip压缩失败："+e);
       
       } finally {
           try {
			if (zin != null) {
				zin.close();
			}
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
       }
       return decompressed;
   }
   
   /**
    * 将Object对象转换为压缩后的Base16编码字符串
    * 
    * @param obj
    * 对象必须实现Serializable接口
    * @return 压缩编码后的数据
    */
   public static final String zipobj(Object obj) {
       ByteArrayOutputStream tmpbaos = new ByteArrayOutputStream();
       try {
           ObjectOutputStream out = new ObjectOutputStream(tmpbaos);
           out.writeObject(obj);
           out.flush();
       } catch (IOException e) {
         log.error("压缩失败:"+e);
       }
       return zip(IMSUtils.encodeHex(tmpbaos.toByteArray()));
   }
   /**
    * 使用zip进行压缩
    * 
    * @param str
    * 压缩前的文本
    * @return
    * 返回压缩后的文本
    */
   public static final String zip(String str) {
       if (IMSUtils.isEmpty(str)) {
           return null;
       }
       byte[] compressed;
       ByteArrayOutputStream out = null;
       ZipOutputStream zout = null;
       String compressedStr = null;
       try {
           out = new ByteArrayOutputStream();
           zout = new ZipOutputStream(out);
           zout.putNextEntry(new ZipEntry("0"));
           zout.write(str.getBytes());
           zout.closeEntry();
           compressed = out.toByteArray();
           compressedStr =IMSUtils.encodeHex(compressed);
       } catch (IOException e) {
          log.error("压缩文本失败："+e);
       } finally {
           try {
			if (zout != null) {
				zout.close();
			}
			if (out != null) {
				out.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
       }
       return compressedStr;
   }
   /**
    * 读取文件路径，返回DataSource容器
    * 
    * @param attFiles
    * @return
    */
   public static final List<DataSource> fileDirsToDatasource(List<File> attFiles) {
       DataSource dc = null;
       List<DataSource> dcs = new ArrayList<DataSource>();
       if (IMSUtils.isNotEmpty(attFiles)) {
           for (File file : attFiles) {
               dc = new FileDataSource(file);
               dcs.add(dc);
           }
       }
       return dcs;
   }
   /**
	 * 根据链接地址去下载文件
	 * 
	 * @param urlString
	 *            链接地址
	 * @param filePath
	 *            文件存储路径
	 * @throws Exception
	 */
	public static boolean  downloadUrlFile(String urlString, String filePath){
		File file = new File(filePath);
		// 如果替换的话，先删除再创建
		if (file.exists()) {  //如果存在，则先删除
			file.delete();
		}
		OutputStream os =null;
		GZIPInputStream gis=null;
		InputStream is=null;
		try {
			URL  url = new URL(urlString);
			URLConnection con = url.openConnection(); // 打开连接
			is = con.getInputStream(); // 输入流
			String code = con.getHeaderField("Content-Encoding");  // 获取文件编码
			byte[] bs = new byte[1024];// 读取到的数据长度
			int len=0;
			if ((null != code) && code.equals("gzip")) {    
			    gis= new GZIPInputStream(is); // 1K的数据缓冲
		        os = new FileOutputStream(filePath); // 输出的文件流
				// 开始读取
				while ((len = gis.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
			
			} else {
				os= new FileOutputStream(filePath);// 输出的文件流
				while ((len = is.read(bs)) != -1) {	// 开始读取
					os.write(bs, 0, len);
				}
			
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 完毕，关闭所有链接
				try {
					if(gis!=null){
					gis.close();
				   }
					if(os!=null){
						os.close();
					}
					if(is!=null){
						is.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		return false;
		
	}

	/**
	 * 测试主应用
	 * 
	 * @param arg
	 *            String[]
	 */
	public static void main(String[] arg) {
		FileOperation file = new FileOperation();
				file.downloadUrlFile("http://wx.qlogo.cn/mmopen/ajNVdqHZLLDdoI57M1CA3DicIz2vU2icweW7X1GvGgibaOrlzN9NEDnyNicOf0YejNNFSsMWbBZUGqjXJyibTF8jX9UjqCqe3JbXKlQDpAOib0VEo/0",
						"e:/dao/a.png");
		/*
		 * try { FileOperation file = new FileOperation();
		 * System.out.println("==" + String.valueOf(file.getFileSize("c:/aa")));
		 * } catch (Exception ex) { ex.printStackTrace(); }
		 */
	}
}
