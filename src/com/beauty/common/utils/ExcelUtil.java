package com.beauty.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import com.beauty.common.constant.BeautyCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.po.DictionaryPO;

/**
 * 
 * 类名:com.beauty.common.utils.ExcelUtil 描述:Excel导出功能 编写者:陈骑元 创建时间:2017年6月15日
 * 上午12:13:22 修改说明:
 */
public class ExcelUtil {

	/**
	 * 
	 * 简要说明：导出后台营业记录 编写者：陈骑元 创建时间：2017年6月15日 上午12:20:04
	 * 
	 * @param 说明
	 * @return 说明
	 */

	public static boolean createSystemFinanceExcel(List<Dto> financeList, String excelPath) {

		boolean result = false;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			out = new FileOutputStream(excelPath);
			workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("营业记录");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16));
			sheet.setColumnWidth(0, 1500);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 4500);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 8500);
			sheet.setColumnWidth(5, 4000);
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 4500);
			sheet.setColumnWidth(9, 3500);
			sheet.setColumnWidth(10, 5500);
			sheet.setColumnWidth(11, 5500);
			sheet.setColumnWidth(12, 5500);
			sheet.setColumnWidth(13, 5500);
			sheet.setColumnWidth(14, 5500);
			sheet.setColumnWidth(15, 3500);
			sheet.setColumnWidth(15, 3500);

			HSSFCellStyle titleStyle = getCellStyle(workbook, (short) 20, true, false);
			HSSFRow titleRow = sheet.createRow(0);

			HSSFCell titleCell = titleRow.createCell(0); // 创建标题列
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("营业记录");
			String[] headers = new String[] { "序号", "订单编号", "顾客账号", "顾客姓名", "消费内容", "价格（元）", "支付方式", "实付金额（元）", "消耗颜值",
					"店铺编号", "店铺名称", "服务员工", "最后操作员工", "支付时间", "完成时间", "状态", "订单来源" };
			HSSFCellStyle readerStyle = getCellStyle(workbook, (short) 12, true, false);
			HSSFRow row = sheet.createRow(1);
			row.setHeight((short) 480);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(readerStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			List<DictionaryPO> dicList = IMSCxt.getDictionaryList("pay_way");
			List<DictionaryPO> orderStatusList = IMSCxt.getDictionaryList("order_status");
			List<DictionaryPO> orderSourceList = IMSCxt.getDictionaryList("order_source");
			// 添加数据列
			HSSFCellStyle cellStyle = getCellStyle(workbook, (short) 12, false, false);
			for (int j = 0; j < financeList.size(); j++) {
				Dto dataDto = financeList.get(j);
				row = sheet.createRow(j + 2);
				row.setHeight((short) 480);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(j + 1);
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(dataDto.getString("order_id"));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(dataDto.getString("mobile"));
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(dataDto.getString("username"));
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(dataDto.getString("order_content"));
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(cellStyle);
				cell5.setCellValue(dataDto.getString("order_money"));
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(cellStyle);
				cell6.setCellValue(IMSCxt.getDicValue(dicList, dataDto.getString("pay_way")));
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue(dataDto.getString("pay_money"));
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue(dataDto.getString("extend_beauty_num"));
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(cellStyle);
				cell9.setCellValue(dataDto.getString("shop_id"));
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(cellStyle);
				cell10.setCellValue(dataDto.getString("shop_name"));

				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(cellStyle);
				cell11.setCellValue(dataDto.getString("server_name"));
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(cellStyle);
				cell12.setCellValue(dataDto.getString("account"));
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(cellStyle);
				cell13.setCellValue(IMSUtils.date2String(dataDto.getTimestamp("pay_time"), "yyyy-MM-dd HH:mm"));
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(cellStyle);
				cell14.setCellValue(IMSUtils.date2String(dataDto.getTimestamp("finish_time"), "yyyy-MM-dd HH:mm"));
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(cellStyle);
				cell15.setCellValue(IMSCxt.getDicValue(orderStatusList, dataDto.getString("order_status")));
				HSSFCell cell16 = row.createCell(16);
				cell16.setCellStyle(cellStyle);
				cell16.setCellValue(IMSCxt.getDicValue(orderSourceList, dataDto.getString("order_source")));
			}
			workbook.write(out);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// if (workbook != null) {
				// workbook.close();
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 简要说明：导出现金流水记录 编写者：陈骑元 创建时间：2017年6月15日 上午12:20:04
	 * 
	 * @param 说明
	 * @return 说明
	 */

	public static boolean createCashRecordExcel(List<Dto> recordList, String excelPath) {

		boolean result = false;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			out = new FileOutputStream(excelPath);
			workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("现金流水");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
			sheet.setColumnWidth(0, 2500);
			sheet.setColumnWidth(1, 5500);
			sheet.setColumnWidth(2, 5500);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 4000);
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 6000);
			sheet.setColumnWidth(8, 4500);
			sheet.setColumnWidth(9, 7000);

			HSSFCellStyle titleStyle = getCellStyle(workbook, (short) 20, true, false);
			HSSFRow titleRow = sheet.createRow(0);

			HSSFCell titleCell = titleRow.createCell(0); // 创建标题列
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("现金流水");
			String[] headers = new String[] { "序号", "流水号", "订单号", "顾客账号", "姓名", "颜值数量", "支付方式", "实付消费金额（元）", "分类",
					"支付时间" };
			HSSFCellStyle readerStyle = getCellStyle(workbook, (short) 12, true, false);
			HSSFRow row = sheet.createRow(1);
			row.setHeight((short) 480);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(readerStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			List<DictionaryPO> dicList = IMSCxt.getDictionaryList("pay_way");
			List<DictionaryPO> recordTypeList = IMSCxt.getDictionaryList("cash_record_type");
			// 添加数据列
			HSSFCellStyle cellStyle = getCellStyle(workbook, (short) 12, false, false);
			for (int j = 0; j < recordList.size(); j++) {
				Dto dataDto = recordList.get(j);
				row = sheet.createRow(j + 2);
				row.setHeight((short) 480);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(j + 1);
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(dataDto.getString("record_id"));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(dataDto.getString("order_id"));
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(dataDto.getString("mobile"));
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(dataDto.getString("username"));
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(cellStyle);
				String beauty_num = dataDto.getString("beauty_num");
				if (IMSUtils.isNotEmpty(beauty_num)) {
					cell5.setCellValue("-" + beauty_num);
				} else {
					cell5.setCellValue("");
				}
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(cellStyle);
				cell6.setCellValue(IMSCxt.getDicValue(dicList, dataDto.getString("pay_way")));
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(cellStyle);
				String money = dataDto.getString("money");
				String cash_type = dataDto.getString("cash_type");
				if (BeautyCons.PAY_RECORD_TYPE_REFUND.equals(cash_type)) {
					cell7.setCellValue("-" + money);
				} else {
					cell7.setCellValue("+" + money);
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue(IMSCxt.getDicValue(recordTypeList, dataDto.getString("record_type")));
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(cellStyle);
				cell9.setCellValue(IMSUtils.date2String(dataDto.getTimestamp("pay_time"), "yyyy-MM-dd HH:mm:ss"));

			}
			workbook.write(out);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// if (workbook != null) {
				// workbook.close();
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 简要说明：导出颜值流水记录 编写者：陈骑元 创建时间：2017年6月15日 上午12:20:04
	 * 
	 * @param 说明
	 * @return 说明
	 */

	public static boolean createBeautyRecordExcel(List<Dto> recordList, String excelPath) {

		boolean result = false;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			out = new FileOutputStream(excelPath);
			workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("颜值流水");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			sheet.setColumnWidth(0, 2500);
			sheet.setColumnWidth(1, 5500);
			sheet.setColumnWidth(2, 5500);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 6000);
			sheet.setColumnWidth(6, 6000);
			sheet.setColumnWidth(7, 4500);
			sheet.setColumnWidth(8, 7000);

			HSSFCellStyle titleStyle = getCellStyle(workbook, (short) 20, true, false);
			HSSFRow titleRow = sheet.createRow(0);

			HSSFCell titleCell = titleRow.createCell(0); // 创建标题列
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("颜值流水");
			String[] headers = new String[] { "序号", "流水号", "订单号", "顾客账号", "姓名", "颜值数量(免费颜值数量) ", "实付消费金额（元）", "分类",
					"支付时间" };
			HSSFCellStyle readerStyle = getCellStyle(workbook, (short) 12, true, false);
			HSSFRow row = sheet.createRow(1);
			row.setHeight((short) 480);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(readerStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			List<DictionaryPO> recordTypeList = IMSCxt.getDictionaryList("beauty_type");
			// 添加数据列
			HSSFCellStyle cellStyle = getCellStyle(workbook, (short) 12, false, false);
			for (int j = 0; j < recordList.size(); j++) {
				Dto dataDto = recordList.get(j);
				row = sheet.createRow(j + 2);
				row.setHeight((short) 480);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(j + 1);
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(dataDto.getString("record_id"));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(dataDto.getString("order_id"));
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(dataDto.getString("mobile"));
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(dataDto.getString("username"));
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(cellStyle);
				String give_num = dataDto.getString("give_num");
				String beauty_num = dataDto.getString("beauty_num");
				String record_type = dataDto.getString("record_type");
				String str = "";
				if (BeautyCons.BEAUTY_RECORD_TYPE_XF.equals(record_type)) {
					str = "+";
				} else {
					str = "-";
				}
				String returnVal = str + beauty_num;
				if (IMSUtils.isNotEmpty(give_num)) {
					if (!"0".equals(give_num)) {
						returnVal += '(' + str + give_num + ')';
					}
				}
				cell5.setCellValue(returnVal);
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(cellStyle);
				String money = dataDto.getString("total_money");

				if (BeautyCons.BEAUTY_RECORD_TYPE_GM.equals(record_type)) {
					cell6.setCellValue("+" + money);
				} else if (BeautyCons.BEAUTY_RECORD_TYPE_XF.equals(record_type)) {
					cell6.setCellValue("-" + money);
				} else {
					cell6.setCellValue("");
				}

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue(IMSCxt.getDicValue(recordTypeList, dataDto.getString("record_type")));
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue(IMSUtils.date2String(dataDto.getTimestamp("pay_time"), "yyyy-MM-dd HH:mm:ss"));

			}
			workbook.write(out);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// if (workbook != null) {
				// workbook.close();
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * 简要说明：导出颜值流水记录 编写者：陈骑元 创建时间：2017年6月15日 上午12:20:04
	 * 
	 * @param 说明
	 * @return 说明
	 */
	
	public static boolean createShareBagRecordExcel(List<Dto> recordList, String excelPath) {
		
		boolean result = false;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			out = new FileOutputStream(excelPath);
			workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("礼包分享记录");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			sheet.setColumnWidth(0, 2500);
			sheet.setColumnWidth(1, 5500);
			sheet.setColumnWidth(2, 5500);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 6000);
			sheet.setColumnWidth(6, 5500);
			sheet.setColumnWidth(7, 5500);
			sheet.setColumnWidth(8, 7000);
			
			HSSFCellStyle titleStyle = getCellStyle(workbook, (short) 20, true, false);
			HSSFRow titleRow = sheet.createRow(0);
			
			HSSFCell titleCell = titleRow.createCell(0); // 创建标题列
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("礼包分享记录");
			String[] headers = new String[] { "序号", "流水号", "分享者账号", "分享者姓名", "礼包编号 ", "礼包名称", "被分享者账号",
					 "被分享者姓名","分享时间" };
			HSSFCellStyle readerStyle = getCellStyle(workbook, (short) 12, true, false);
			HSSFRow row = sheet.createRow(1);
			row.setHeight((short) 480);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(readerStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			
			// 添加数据列
			HSSFCellStyle cellStyle = getCellStyle(workbook, (short) 12, false, false);
			for (int j = 0; j < recordList.size(); j++) {
				Dto dataDto = recordList.get(j);
				row = sheet.createRow(j + 2);
				row.setHeight((short) 480);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(j + 1);
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(dataDto.getString("record_id"));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(dataDto.getString("share_mobile"));
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(dataDto.getString("share_username"));
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(dataDto.getString("bag_id"));
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(cellStyle);
				cell5.setCellValue(dataDto.getString("bag_name"));
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(cellStyle);
				cell6.setCellValue(dataDto.getString("receive_mobile"));
				
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue(dataDto.getString("receive_username"));
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue(IMSUtils.date2String(dataDto.getTimestamp("bag_time"), "yyyy-MM-dd HH:mm:ss"));
				
			}
			workbook.write(out);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// if (workbook != null) {
				// workbook.close();
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * 简要说明：导出财务总控
	 * 编写者：陈骑元
	 * 创建时间：2017年7月29日 上午10:51:14
	 * @param 说明
	 * @return 说明
	 */
	public static boolean createPlatformSumExcel(List<Dto> recordList, String excelPath) {
		
		boolean result = false;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			out = new FileOutputStream(excelPath);
			workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("平台财务总控");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
			sheet.setColumnWidth(0, 2000);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 6500);
			sheet.setColumnWidth(3, 6500);
			sheet.setColumnWidth(4, 4500);
			sheet.setColumnWidth(5, 6000);
			sheet.setColumnWidth(6, 3500);
			sheet.setColumnWidth(7, 4500);
			sheet.setColumnWidth(8, 6000);
			sheet.setColumnWidth(9, 3500);
			sheet.setColumnWidth(10, 4500);
			
			HSSFCellStyle titleStyle = getCellStyle(workbook, (short) 20, true, false);
			HSSFRow titleRow = sheet.createRow(0);
			
			HSSFCell titleCell = titleRow.createCell(0); // 创建标题列
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("平台财务总控");
			String[] headers = new String[] { "序号", "店铺编号", "店铺名称", "店铺简称", "店铺现金收入 ", "现金收入目标业绩", "完成率",
					"店铺消耗收入","消耗收入目标业绩 " ,"完成率","免费颜值收入"};
			HSSFCellStyle readerStyle = getCellStyle(workbook, (short) 12, true, false);
			HSSFRow row = sheet.createRow(1);
			row.setHeight((short) 480);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(readerStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			
			// 添加数据列
			HSSFCellStyle cellStyle = getCellStyle(workbook, (short) 12, false, false);
			for (int j = 0; j < recordList.size(); j++) {
				Dto dataDto = recordList.get(j);
				row = sheet.createRow(j + 2);
				row.setHeight((short) 480);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(j + 1);
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(dataDto.getString("shop_id"));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(dataDto.getString("shop_name"));
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(dataDto.getString("short_name"));
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(dataDto.getString("total_cash_income"));
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(cellStyle);
				cell5.setCellValue(dataDto.getString("month_cash_in"));
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(cellStyle);
				String per_cash_income=dataDto.getString("per_cash_income");
				if("0".equals(per_cash_income)){
					cell6.setCellValue("0");
				}else{
					cell6.setCellValue(per_cash_income+"%");
				}
				
				
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue(dataDto.getString("total_extend_income"));
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue(dataDto.getString("month_expend_in"));
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(cellStyle);
				String per_extend_income=dataDto.getString("per_extend_income");
				if("0".equals(per_extend_income)){
					cell9.setCellValue("0");
				}else{
					cell9.setCellValue(per_extend_income+"%");
				}
				HSSFCell cell10= row.createCell(10);
				cell10.setCellStyle(cellStyle);
				cell10.setCellValue(dataDto.getString("total_free_num"));
				
			}
			workbook.write(out);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// if (workbook != null) {
				// workbook.close();
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 简要说明：创建店铺财务总况报表 编写者：陈骑元 创建时间：2017年6月15日 上午12:20:04
	 * 
	 * @param 说明
	 * @return 说明
	 */

	public static boolean createShopFinanceExcel(List<Dto> financeList, String excelPath) {

		boolean result = false;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			out = new FileOutputStream(excelPath);
			workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("财务总况");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
			sheet.setColumnWidth(0, 1500);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 6000);
			sheet.setColumnWidth(3, 5000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 8500);
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 4500);
			sheet.setColumnWidth(9, 3500);
			sheet.setColumnWidth(10, 5500);
			sheet.setColumnWidth(11, 4500);
			sheet.setColumnWidth(12, 3500);
			sheet.setColumnWidth(13, 3500);

			HSSFCellStyle titleStyle = getCellStyle(workbook, (short) 20, true, false);
			HSSFRow titleRow = sheet.createRow(0);

			HSSFCell titleCell = titleRow.createCell(0); // 创建标题列
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("财务总况");
			String[] headers = new String[] { "序号", "订单编号", "交易时间", "顾客账号", "顾客姓名", "交易内容", "价格（元）", "实付金额（元）", "消耗颜值",
					"支付方式 ", "现金收入（元）", "消耗收入（元）", "服务员工", "经手员工" };
			HSSFCellStyle readerStyle = getCellStyle(workbook, (short) 12, true, false);
			HSSFRow row = sheet.createRow(1);
			row.setHeight((short) 480);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(readerStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			List<DictionaryPO> dicList = IMSCxt.getDictionaryList("pay_way");
			// 添加数据列
			HSSFCellStyle cellStyle = getCellStyle(workbook, (short) 12, false, false);
			for (int j = 0; j < financeList.size(); j++) {
				Dto dataDto = financeList.get(j);
				row = sheet.createRow(j + 2);
				row.setHeight((short) 480);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(j + 1);
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(dataDto.getString("order_id"));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(IMSUtils.date2String(dataDto.getTimestamp("pay_time"), "yyyy-MM-dd HH:mm"));
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(dataDto.getString("mobile"));
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(dataDto.getString("username"));
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(cellStyle);
				cell5.setCellValue(dataDto.getString("order_content"));
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(cellStyle);
				cell6.setCellValue(dataDto.getString("order_money"));
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue(dataDto.getString("pay_money"));
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue(dataDto.getString("extend_beauty_num"));
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(cellStyle);
				cell9.setCellValue(IMSCxt.getDicValue(dicList, dataDto.getString("pay_way")));
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(cellStyle);
				cell10.setCellValue(dataDto.getString("cash_income"));
				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(cellStyle);
				cell11.setCellValue(dataDto.getString("extend_income"));
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(cellStyle);
				cell12.setCellValue(dataDto.getString("server_name"));
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(cellStyle);
				cell13.setCellValue(dataDto.getString("account"));

			}
			workbook.write(out);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// if (workbook != null) {
				// workbook.close();
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 简要说明：创建店铺财务总况报表 编写者：陈骑元 创建时间：2017年6月15日 上午12:20:04
	 * 
	 * @param 说明
	 * @return 说明
	 */

	public static boolean createShopUserCountExcel(List<Dto> shopUserCountList, String month, String excelPath) {

		boolean result = false;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			out = new FileOutputStream(excelPath);
			workbook = new HSSFWorkbook();
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet("员工工资及业绩");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
			sheet.setColumnWidth(0, 2000);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 5000);
			sheet.setColumnWidth(3, 5000);
			sheet.setColumnWidth(4, 5000);
			sheet.setColumnWidth(5, 5000);
			sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 5000);
			HSSFCellStyle titleStyle = getCellStyle(workbook, (short) 20, true, false);
			HSSFRow titleRow = sheet.createRow(0);

			HSSFCell titleCell = titleRow.createCell(0); // 创建标题列
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("员工工资及业绩");
			String[] headers = new String[] { "序号", "姓名", "工号", "职位", "手工费", "现金收入", "消耗收入", "统计月份" };
			HSSFCellStyle readerStyle = getCellStyle(workbook, (short) 12, true, false);
			HSSFRow row = sheet.createRow(1);
			row.setHeight((short) 480);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(readerStyle);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			// 添加数据列
			HSSFCellStyle cellStyle = getCellStyle(workbook, (short) 12, false, false);
			for (int j = 0; j < shopUserCountList.size(); j++) {
				Dto dataDto = shopUserCountList.get(j);
				row = sheet.createRow(j + 2);
				row.setHeight((short) 480);
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue(j + 1);
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue(dataDto.getString("username"));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue(dataDto.getString("work_number"));
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue(dataDto.getString("post_name"));
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue(dataDto.getString("manual_money"));
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(cellStyle);
				cell5.setCellValue(dataDto.getString("total_cash_income"));
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(cellStyle);
				cell6.setCellValue(dataDto.getString("total_extend_income"));
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue(month);

			}
			workbook.write(out);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// if (workbook != null) {
				// workbook.close();
				// }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * 简要说明：创建字体样式 编写者：陈骑元 创建时间：2016年8月5日 上午9:32:50
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook, short fontSize, boolean isBold, boolean isWrap) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style.setWrapText(isWrap); // 设置换行
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints(fontSize);
		if (isBold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}

		// 把字体应用到当前的样式
		style.setFont(font);

		return style;
	}

	public static HSSFCellStyle getCellYellowStyle(HSSFWorkbook workbook, short fontSize, boolean isBold,
			boolean isWrap) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style.setWrapText(isWrap); // 设置换行
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints(fontSize);
		if (isBold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}

		// 把字体应用到当前的样式
		style.setFont(font);

		return style;
	}

	/**
	 * 
	 * 简要说明：去掉实线 编写者：陈骑元 创建时间：2016年8月22日 下午6:24:29
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static HSSFCellStyle getCellStyleNoLine(HSSFWorkbook workbook, short fontSize, boolean isBold,
			boolean isWrap) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style.setWrapText(isWrap); // 设置换行
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints(fontSize);
		if (isBold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}

		// 把字体应用到当前的样式
		style.setFont(font);

		return style;
	}

	/*
	 * 列数据信息单元格样式
	 */
	public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 12);
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		return style;

	}

}
