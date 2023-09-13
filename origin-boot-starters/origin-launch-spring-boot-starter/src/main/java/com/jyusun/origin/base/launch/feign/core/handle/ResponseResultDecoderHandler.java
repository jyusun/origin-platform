package com.jyusun.origin.base.launch.feign.core.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jyusun.origin.core.common.model.result.AbstractResult;
import feign.Response;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 重写Decode 解决Feign请求响应对象赋值问题
 *
 * @author jyusun at 2022-02-07 13:43:38
 */
@RequiredArgsConstructor
public class ResponseResultDecoderHandler implements Decoder {

    private final SpringDecoder decoder;

    @SneakyThrows
    @Override
    public Object decode(Response response, Type type) {

        Method method = response.request().requestTemplate().methodMetadata().method();

        // 如果Feign接口的返回值不是 AbstractResult{code:0,...} 结构类型，并且远程响应又是这个结构
        boolean isResultObj = method.getReturnType() == AbstractResult.class;
        // 如果feign的结果不是 result 对象
        if (!isResultObj) {
            String json = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);

            AbstractResult<Object> abstractResult = JSONObject.parseObject(json, AbstractResult.class);
            Object data = abstractResult.getData();
            if (method.getReturnType() == Boolean.class) {
                return Boolean.parseBoolean(String.valueOf(data));
            }
            JSONObject toJson = (JSONObject) JSON.toJSON(data);
            return JSONObject.toJavaObject(toJson, method.getReturnType());
        }
        return this.decoder.decode(response, type);
    }

}
