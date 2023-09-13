package com.jyusun.origin.base.oss.context;


import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.ServiceException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;

import java.util.Optional;

/**
 * 存取器
 * <p>
 * 作用描述：
 *
 * @author jyusun at 2021/7/12 13:28
 * @since 1.0.0
 */
@AllArgsConstructor
public class OssAccessor implements InitializingBean {

    @Getter(AccessLevel.PROTECTED)
    private final OssFactory ossFactory;

    @Override
    public void afterPropertiesSet() {
        Optional.ofNullable(ossFactory)
                .orElseThrow(() -> {
                    SystemResultEnum resultEnum = SystemResultEnum.INTERNAL_SERVER_ERROR;
                    return new ServiceException(resultEnum.code(), resultEnum.desc());
                });
    }

}
