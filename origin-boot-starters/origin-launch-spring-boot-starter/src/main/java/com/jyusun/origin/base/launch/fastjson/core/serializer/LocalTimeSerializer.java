package com.jyusun.origin.base.launch.fastjson.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;

import java.lang.reflect.Type;
import java.time.LocalTime;

/**
 * <p>
 * 作用描述：
 *
 * @author jyusun
 * @date 2021/12/1 15:50
 * @since 1.0.0
 */
public class LocalTimeSerializer implements ObjectSerializer {

    public void write(JSONSerializer jsonSerializer, Object obj, Object o1, Type type, int i) {
        SerializeWriter out = jsonSerializer.getWriter();
        if (ObjectUtil.isEmpty(obj)) {
            out.writeNull();
        }
        else {
            out.writeString(DateUtil.toStr((LocalTime) obj, DateUtil.PATTERN_HH_MM_SS));
        }

    }

}
