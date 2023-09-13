package com.jyusun.origin.core.common.util;

import com.jyusun.origin.core.common.constant.ResultConstant;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.ValidateException;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * AssertUtil
 * <p>
 * 作用描述：断言
 * </p>
 *
 * @author jyusun
 * @date 2020/12/8 13:30
 * @since 1.0.0
 */
@UtilityClass
public class AssertUtil extends Assert {

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new ValidateException(ResultConstant.MESSAGE_BAD_REQUEST, message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new ValidateException(ResultConstant.MESSAGE_BAD_REQUEST, message);
        }
    }

    public static void isTrue(boolean expression, SystemResultEnum resultEnum) {
        if (!expression) {
            throw new ValidateException(resultEnum.code(), resultEnum.desc());
        }
    }

    public static void isNull(@Nullable Object object, String message) {
        if (ObjectUtil.isNotEmpty(object)) {
            throw new ValidateException(ResultConstant.MESSAGE_BAD_REQUEST, message);
        }
    }

    public static void notNull(@Nullable Object object, String message) {
        if (ObjectUtil.isEmpty(object)) {
            throw new ValidateException(ResultConstant.MESSAGE_BAD_REQUEST, message);
        }
    }

}
