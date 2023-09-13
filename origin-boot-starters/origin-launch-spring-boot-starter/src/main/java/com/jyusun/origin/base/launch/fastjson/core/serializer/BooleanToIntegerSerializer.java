package com.jyusun.origin.base.launch.fastjson.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.jyusun.origin.core.common.util.ObjectUtil;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 布尔类型序列化 false=0,true=1
 * <p>
 * 作用描述：
 *
 * @author jyusun
 * @date 2021/12/1 15:50
 * @since 1.0.0
 */
public class BooleanToIntegerSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        SerializeWriter out = jsonSerializer.getWriter();
        int value;
        if (ObjectUtil.isEmpty(o)) {
            value = 0;
        }
        else {
            value = Boolean.parseBoolean(String.valueOf(o)) ? 1 : 0;
        }
        out.writeInt(value);
    }

}
