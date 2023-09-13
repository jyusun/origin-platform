package com.jyusun.origin.core.common.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.yaml.snakeyaml.Yaml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * Yaml工具类
 *
 * @author jyusun at 2023-08-23
 */
@UtilityClass
public class YamlUtil {

    /**
     * @return
     */
    public static Yaml creator() {
        return new Yaml();
    }

    public <T> T loadAs(InputStream inputStream, Class<T> clazz) {
        return creator().loadAs(inputStream, clazz);
    }

    @SneakyThrows
    public <T> T loadAs(File file, Class<T> clazz) {
        return creator().loadAs(new FileInputStream(file), clazz);
    }

    @SneakyThrows
    public <T> T loadAs(Path path, Class<T> clazz) {
        return creator().loadAs(new FileInputStream(path.toFile()), clazz);
    }

    /**
     * 生成Yaml
     * @param obj
     * @return
     */
    @SneakyThrows
    public InputStream dump(Object obj) {
        String content = creator().dump(obj);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成Yaml
     * @param obj 数据
     * @return
     */
    @SneakyThrows
    public File dump(File file, Object obj) {
        InputStream inputStream = dump(obj);
        FileUtil.copyInputStreamToFile(inputStream, file);
        return file;
    }

    /**
     * 生成Yaml
     * @param obj 数据
     * @return
     */
    @SneakyThrows
    public void dumpDown(String fileName, Object obj) {
        try (InputStream inputStream = dump(obj)) {

        }
    }

}
