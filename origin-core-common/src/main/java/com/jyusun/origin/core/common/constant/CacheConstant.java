package com.jyusun.origin.core.common.constant;

import com.jyusun.origin.core.common.util.StringUtil;
import lombok.experimental.UtilityClass;

/**
 * 系统字典类型常量
 * <p>
 * 作用描述：REDIS 缓存常量
 * </p>
 *
 * @author jyusun
 * @date 2020/11/23 17:29
 * @since 1.0.0
 */
@UtilityClass
public class CacheConstant implements BaseConstants {

    /**
     * 缓存前缀
     */
    public static final String REDIS_PREFIX = "admins";

    /**
     * 字典类型-字典
     */
    public static final String TYPE_DEFAULT = REDIS_PREFIX + StringUtil.COLON + "dicts";

    /**
     * 字典类型-系统用户
     */
    public static final String TYPE_USER = REDIS_PREFIX + StringUtil.COLON + "users";

    /**
     * 字典类型-参数
     */
    public static final String TYPE_PARAM = REDIS_PREFIX + StringUtil.COLON + "params";

    /**
     * 字典：集合数据
     */
    public static final String DICT_LIST = TYPE_DEFAULT + StringUtil.COLON + "list";

    /**
     * 字典：单个值
     */
    public static final String DICT_VALUE = TYPE_DEFAULT + StringUtil.COLON + "value";

}
