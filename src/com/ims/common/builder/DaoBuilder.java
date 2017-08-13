package com.ims.common.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ims.common.builder.asset.BuilderUtils;
import com.ims.common.builder.metainfo.DBMetaInfoUtils;
import com.ims.common.builder.metainfo.vo.ColumnVO;
import com.ims.common.builder.metainfo.vo.TableVO;
import com.ims.common.builder.resources.BuilderResources;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.support.velocity.VelocityHelper;

/**
 * 
 * 类描述： 数据访问层代码生成器
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-6-10 下午03:15:35
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class DaoBuilder {

	private static Log log = LogFactory.getLog(DaoBuilder.class);

	/**
	 * 生成*PO.java
	 * 
	 * @param inDto
	 */
	public static void buildPO(Dto inDto) {
		TableVO tableVO = (TableVO) inDto.get("tableVO");
		Dto tableDto = BuilderUtils.convertTableVO(tableVO);
		@SuppressWarnings("unchecked")
		List<ColumnVO> columnVOs = (List<ColumnVO>) inDto.get("columnVOs");
		List<Dto> columnDtos = BuilderUtils.convertColumnVO(columnVOs);
		String packageString = inDto.getString("package") + ".po";
		Dto vmDto = Dtos.newDto();
		vmDto.put("columnDtos", columnDtos);
		vmDto.put("tableDto", tableDto);
		vmDto.put("package", packageString);
		vmDto.put("importDto", BuilderUtils.getImportDto(columnDtos));
		vmDto.put("author", inDto.getString("author"));
		vmDto.put("className", inDto.getString("className"));
		vmDto.put("sysdate", IMSUtils.getDateTimeStr());
		StringWriter writer = VelocityHelper.mergeFileTemplate(BuilderResources.PO_JAVA_VM, vmDto);
		try {
			String outPath = inDto.getString("outPath") + "//po//";
			FileUtils.forceMkdir(new File(outPath));
			FileOutputStream fos = new FileOutputStream(outPath +  inDto.getString("className") + "PO.java");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write(writer.toString());   
		    osw.flush();   
			if (log.isInfoEnabled()) {
				log.info("PO文件[" + packageString + "." +  inDto.getString("className") + "PO.java]生成成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成*Mapper.java
	 * 
	 * @param inDto
	 */
	public static void buildJavaMapper(Dto inDto) {
		TableVO tableVO = (TableVO) inDto.get("tableVO");
		Dto tableDto = BuilderUtils.convertTableVO(tableVO);
		@SuppressWarnings("unchecked")
		List<ColumnVO> columnVOs = (List<ColumnVO>) inDto.get("columnVOs");
		String packageString = inDto.getString("package") + ".mapper";
		Dto vmDto = Dtos.newDto();
		vmDto.put("bykey", BuilderUtils.getByKeyStr(columnVOs));
		vmDto.put("bykeyParam", BuilderUtils.getByKeyParam(columnVOs));
		vmDto.put("tableDto", tableDto);
		vmDto.put("package", packageString);
		vmDto.put("package_base", inDto.getString("package"));
		vmDto.put("author", inDto.getString("author"));
		vmDto.put("sysdate", IMSUtils.getDateTimeStr());
		List<Dto> columnDtos = BuilderUtils.convertColumnVO(columnVOs);
		vmDto.put("importDto", BuilderUtils.getImportDto(columnDtos));
		vmDto.put("className", inDto.getString("className"));
		vmDto.put("paramName", IMSUtils.firstToLetter(inDto.getString("className")));
		StringWriter writer = VelocityHelper.mergeFileTemplate(BuilderResources.MAPPER_JAVA_VM, vmDto);
		try {
			String outPath = inDto.getString("outPath") + "//mapper//";
			FileUtils.forceMkdir(new File(outPath));
			FileOutputStream fos = new FileOutputStream(outPath + inDto.getString("className") + "Mapper.java");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write(writer.toString());   
		    osw.flush();   
			if (log.isInfoEnabled()) {
				log.info("Mapper Java文件[" + packageString + "." + inDto.getString("className") + "Mapper.java]生成成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成*Service.java
	 * 
	 * @param inDto
	 */
	public static void buildJavaService(Dto inDto) {
		TableVO tableVO = (TableVO) inDto.get("tableVO");
		Dto tableDto = BuilderUtils.convertTableVO(tableVO);
		@SuppressWarnings("unchecked")
		List<ColumnVO> columnVOs = (List<ColumnVO>) inDto.get("columnVOs");
		String packageString = inDto.getString("package") + ".service";
		Dto vmDto = Dtos.newDto();
		vmDto.put("bykey", BuilderUtils.getByKey(columnVOs));
		vmDto.put("bykeyParam", BuilderUtils.getByKeyParam(columnVOs));
		vmDto.put("upkey", StringUtils.capitalize(BuilderUtils.getByKeyParam(columnVOs)));
		vmDto.put("tableDto", tableDto);
		vmDto.put("package", packageString);
		vmDto.put("package_base", inDto.getString("package"));
		vmDto.put("author", inDto.getString("author"));
		vmDto.put("sysdate", IMSUtils.getDateTimeStr());
		vmDto.put("className", inDto.getString("className"));
		vmDto.put("paramName", IMSUtils.firstToLetter(inDto.getString("className")));
		List<Dto> columnDtos = BuilderUtils.convertColumnVO(columnVOs);
		vmDto.put("importDto", BuilderUtils.getImportDto(columnDtos));
		StringWriter writer = VelocityHelper.mergeFileTemplate(BuilderResources.SERVICE_JAVA_VM, vmDto);
		try {
			String outPath = inDto.getString("outPath") + "//service//";
			FileUtils.forceMkdir(new File(outPath));
			FileOutputStream fos = new FileOutputStream(outPath + inDto.getString("className") + "Service.java");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write(writer.toString());   
		    osw.flush();   
			if (log.isInfoEnabled()) {
				log.info("Service Java文件[" + packageString + "." + inDto.getString("className") + "Service.java]生成成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成*Service.java
	 * 
	 * @param inDto
	 */
	public static void buildJavaController(Dto inDto) {
		TableVO tableVO = (TableVO) inDto.get("tableVO");
		Dto tableDto = BuilderUtils.convertTableVO(tableVO);
		@SuppressWarnings("unchecked")
		List<ColumnVO> columnVOs = (List<ColumnVO>) inDto.get("columnVOs");
		String packageString = inDto.getString("package") + ".controller";
		Dto vmDto = Dtos.newDto();
		vmDto.put("bykey", BuilderUtils.getByKey(columnVOs));
		vmDto.put("bykeyParam", BuilderUtils.getByKeyParam(columnVOs));
		vmDto.put("tableDto", tableDto);
		vmDto.put("package", packageString);
		vmDto.put("package_base", inDto.getString("package"));
		vmDto.put("author", inDto.getString("author"));
		vmDto.put("sysdate", IMSUtils.getDateTimeStr());
		vmDto.put("className", inDto.getString("className"));
		vmDto.put("paramName", IMSUtils.firstToLetter(inDto.getString("className")));
		List<Dto> columnDtos = BuilderUtils.convertColumnVO(columnVOs);
		vmDto.put("importDto", BuilderUtils.getImportDto(columnDtos));
		StringWriter writer = VelocityHelper.mergeFileTemplate(BuilderResources.CONTROLLER_JAVA_VM, vmDto);
		try {
			String outPath = inDto.getString("outPath") + "//controller//";
			FileUtils.forceMkdir(new File(outPath));
			FileOutputStream fos = new FileOutputStream(outPath + inDto.getString("className") + "Controller.java");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
			osw.write(writer.toString());   
			osw.flush();   
			if (log.isInfoEnabled()) {
				log.info("Controller Java文件[" + packageString + "." + inDto.getString("className") + "Controller.java]生成成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成*Mapper.xml
	 * 
	 * @param inDto
	 */
	@SuppressWarnings("unchecked")
	public static void buildXmlMapper(Dto inDto) {
		TableVO tableVO = (TableVO) inDto.get("tableVO");
		List<ColumnVO> columnVOs = (List<ColumnVO>) inDto.get("columnVOs");
		tableVO = BuilderUtils.isAutoIncreamentColAsPK(columnVOs, tableVO);
		Dto tableDto = BuilderUtils.convertTableVO(tableVO);
		List<Dto> columnDtos = BuilderUtils.convertColumnVO(columnVOs);
		List<ColumnVO> pkeyColumnVOs = (List<ColumnVO>) inDto.get("pkeyColumnVOs");
		List<Dto> pkeyColumnDtos = BuilderUtils.convertColumnVO(pkeyColumnVOs);
		String packageString = inDto.getString("package") + ".mapper";
		Dto vmDto = Dtos.newDto();
		vmDto.put("columnDtos", columnDtos);
		vmDto.put("pkeyColumnDtos", pkeyColumnDtos);
		vmDto.put("bykeyParam", BuilderUtils.getByKeyParam(columnVOs));
		vmDto.put("bykeywhere", BuilderUtils.getByKeyWhereStr(columnVOs));
		vmDto.put("tableDto", tableDto);
		vmDto.put("package_base", inDto.getString("package"));
		vmDto.put("package", packageString);
		vmDto.put("author", inDto.getString("author"));
		vmDto.put("sysdate", IMSUtils.getDateTimeStr());
		vmDto.put("className", inDto.getString("className"));
		StringWriter writer = VelocityHelper.mergeFileTemplate(BuilderResources.MAPPER_XML_VM, vmDto);
		try {
			String outPath = inDto.getString("outPath") + "//mapper//";
			FileUtils.forceMkdir(new File(outPath));
			FileOutputStream fos = new FileOutputStream(outPath + inDto.getString("className") + "Mapper.xml");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write(writer.toString());   
		    osw.flush();   
		
			if (log.isInfoEnabled()) {
				log.info("Mapper Xml文件[" + packageString + ".sqlmap." + inDto.getString("className")
						+ "Mapper.xml]生成成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成Dao代码
	 * 
	 * @param inDto
	 * @throws SQLException
	 */
	public static void build(Connection connection, Dto inDto) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		String tablesString = IMSUtils.trimAll(inDto.getString("tables"));
		// ,号分隔的多张表
		String[] tables = tablesString.split(",");
		for (String tableName : tables) {
			TableVO tableVO = DBMetaInfoUtils.getTableVO(databaseMetaData, tableName);
			if (IMSUtils.isEmpty(tableVO)) {
				throw new RuntimeException("表[" + tableName + "]不存在。");
			}
			inDto.put("tableVO", tableVO);
			List<ColumnVO> columnVOs = DBMetaInfoUtils.listColumnVOs(databaseMetaData, tableName);
			//仅生成XMLMapper时需要
			List<ColumnVO> pkeyColumnVOs = DBMetaInfoUtils.getPKColumnVOs(columnVOs);
			inDto.put("columnVOs", columnVOs);
			inDto.put("pkeyColumnVOs", pkeyColumnVOs);
			DaoBuilder.buildPO(inDto);
			DaoBuilder.buildJavaMapper(inDto);
			DaoBuilder.buildJavaService(inDto);
			DaoBuilder.buildJavaController(inDto);
			DaoBuilder.buildXmlMapper(inDto);
		}
 
	}


}
