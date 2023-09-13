package com.jyusun.origin.excel.ext.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/5/23 14:00
 * @since 1.0.0
 */
public class CustomEventListener<T> extends AnalysisEventListener<T> {

    @Getter
    private final List<T> datas = Lists.newArrayList();

    /**
     * @param t
     * @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        datas.add(t);
    }

    /**
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

}
