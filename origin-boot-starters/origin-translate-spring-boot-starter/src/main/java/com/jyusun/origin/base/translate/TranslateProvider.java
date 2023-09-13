package com.jyusun.origin.base.translate;

import com.jyusun.origin.core.common.model.DictDTO;

import java.util.List;

/**
 * 数据值来源
 *
 * <p>
 * 作用描述：自定义值翻译
 * </p>
 *
 * @author jyusun at 2020/6/9 18:57
 * @since 1.0.0
 */
public interface TranslateProvider {

    /**
     * 值翻译
     * @param dictCode 字典编号
     * @param dictValue 键值
     * @return 数据字典
     */
    DictDTO findDict(String dictCode, Object dictValue);

    /**
     * 数据字典集合
     * @param dictCode 字典编号
     * @return
     */
    List<DictDTO> findItems(String dictCode);

}
