package com.jyusun.origin.excel.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.jyusun.origin.core.common.util.ObjectUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.OutputStream;
import java.util.List;

/**
 * Excel处理
 *
 * @author jyusun at 2022-1-26 14:07:40
 */
@UtilityClass
public class ExcelUtil {

    /**
     * 简单导出
     * @param fileName 文件名称
     * @param sheetName sheet名称
     * @param datas 数据集合
     * @param clazz 转换类
     * @return
     */
    @SneakyThrows
    public static <T> boolean webSimpleExport(String fileName, String sheetName, List<T> datas, Class<T> clazz) {
        HttpServletResponse response = WebUtil.getResponse();
        WebUtil.setFileNameHeader(response, fileName);
        EasyExcel.write(response.getOutputStream(), clazz).useDefaultStyle(false).sheet(sheetName).doWrite(datas);
        return true;
    }

    /**
     * 导出excel
     * @param outputStream 输出流
     * @param datas 导出的数据
     * @param clazz 模板类
     * @param sheetName sheetName
     * @param cellWriteHandlers 样式处理类
     */
    public static <T> boolean writeExcelWithModel(OutputStream outputStream, List<T> datas, Class<T> clazz,
            String sheetName, CellWriteHandler... cellWriteHandlers) {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle,
                contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream, clazz)
            .sheet(sheetName)
            .registerWriteHandler(horizontalCellStyleStrategy);
        if (ObjectUtil.isNotEmpty(cellWriteHandlers)) {
            for (CellWriteHandler cellWriteHandler : cellWriteHandlers) {
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandler);
            }
        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(datas);
        return true;
    }

}