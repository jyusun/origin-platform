package com.jyusun.origin.base.oss.context;


import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssRule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * OSS 上下文
 *
 * @author jyusun at 2022-3-22 17:02:27
 */
@Getter
@RequiredArgsConstructor
public abstract class OssContext {

    protected final OssProperties ossProperties;
    protected final OssRule ossRule;
}
