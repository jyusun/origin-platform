package com.jyusun.origin.excel.ext.converters;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.jyusun.origin.core.common.util.DateUtil;

import java.time.LocalTime;

public class LocalTimeStrConverter implements Converter<LocalTime> {

    /**
     * @return
     */
    @Override
    public Class<?> supportJavaTypeKey() {
        return LocalTime.class;
    }

    /**
     * @return
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * @param cellData
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public LocalTime convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return DateUtil.toLocalTime(cellData.getStringValue(), DateUtil.PATTERN_HH_MM_SS);
    }

    /**
     * @param context
     * @return
     * @throws Exception
     */
    @Override
    public LocalTime convertToJavaData(ReadConverterContext<?> context) throws Exception {
        return Converter.super.convertToJavaData(context);
    }

    /**
     * @param value
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public WriteCellData<?> convertToExcelData(LocalTime value, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData<>(DateUtil.toStr(value, DateUtil.PATTERN_HH_MM_SS));

    }

    /**
     * @param context
     * @return
     * @throws Exception
     */
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<LocalTime> context) throws Exception {
        return Converter.super.convertToExcelData(context);
    }

}