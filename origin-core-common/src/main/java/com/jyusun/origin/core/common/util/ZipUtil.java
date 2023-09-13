package com.jyusun.origin.core.common.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip压缩工具。
 *
 * @author jyusun at 2023-01-05 15:53:57
 */
@UtilityClass
public class ZipUtil {

    /**
     * 文件后缀。
     */
    private static final String FILE_SUFFIX = ".zip";

    /**
     * 缓冲区大小 字节。
     */
    private static final Integer BUFFER_SIZE = 1024 * 2;

    /**
     * URL分隔符。
     */
    private static final String URL_SEPARATOR = "/";

    /**
     * 将指定URL路径下所有文件及文件夹 添加文件到ZIP压缩包
     * @param zipFilePath zip文件存放路径
     * @param zipFileName 生成zip文件名称
     * @param srcFileDir 指定打包文件目录
     * @param coverFlag 文件覆盖标识 true-覆盖（当文件存在默认为覆盖），false-不覆盖
     * @return zipFile 压缩后的文件用于http请求响应
     */
    @SneakyThrows
    public static File fileDirToZip(String zipFilePath, String zipFileName, String srcFileDir, Boolean coverFlag) {
        AssertUtil.isTrue(!zipFilePath.equals(srcFileDir), "ZIP包生成路径不能与源文件目录相同");
        // 如果目录不存在则创建目录
        File zipPath = new File(zipFilePath);
        if (!zipPath.exists()) {
            FileUtil.forceMkdir(zipPath);
        }
        AssertUtil.isTrue(StringUtil.hasText(srcFileDir), "指定打包源文件或目录不存在：" + srcFileDir);
        // ZIP压缩文件
        File zipFile = new File(zipFilePath + URL_SEPARATOR + zipFileName + FILE_SUFFIX);

        if (zipFile.exists()) {
            // 如果存在则删除，重新生成zip文件
            if (coverFlag) {
                FileUtil.delete(zipFile);
            }
            AssertUtil.isTrue(coverFlag, "指定ZIP文件包已存在，请清理后重新生成");
        }

        File srcFile = new File(srcFileDir);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
            fileWriteZipHandle(zipOutputStream, srcFile, srcFile.getName());
        }
        return zipFile;
    }

    /**
     * 将指定文件或目录集合进行打包处理
     * @param zipFilePath zip文件存放路径
     * @param zipFileName 生成zip文件名称
     * @param srcFiles 指定打包源文件集合
     * @param coverFlag 文件覆盖标识 true-覆盖（当文件存在默认为覆盖），false-不覆盖
     * @return zipFile 压缩后的文件用于http请求响应
     */
    @SneakyThrows
    public static File filesToZip(String zipFilePath, String zipFileName, final List<String> srcFiles,
            Boolean coverFlag) {
        // 如果目录不存在则创建目录
        File zipPath = new File(zipFilePath);
        if (!zipPath.exists()) {
            zipPath.mkdir();
        }
        // ZIP压缩文件
        File zipFile = new File(zipFilePath + URL_SEPARATOR + zipFileName + FILE_SUFFIX);

        if (zipFile.exists()) {
            // 如果存在则删除，重新生成zip文件
            if (coverFlag) {
                FileUtil.delete(zipFile);
            }
            AssertUtil.isTrue(coverFlag, "指定ZIP文件包已存在，请清理后重新生成");
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (String strFile : srcFiles) {
                AssertUtil.isTrue(StringUtil.hasText(strFile.replace(" ", "")), "指定打包源文件或目录不存在：" + strFile);
                File srcFile = new File(strFile);
                fileWriteZipHandle(zipOutputStream, srcFile, srcFile.getName());
            }
        }
        return zipFile;
    }

    /**
     * 文件写入ZIP包-递归处理
     * @param srcFile 源文件
     * @param zipOutputStream zip输出流
     * @param srcFileName 文件或文件夹名称
     * @throws IOException IO处理异常
     */
    private static void fileWriteZipHandle(ZipOutputStream zipOutputStream, File srcFile, String srcFileName)
            throws IOException {

        if (srcFile.isDirectory()) {
            dirHandle(zipOutputStream, srcFile, srcFileName);
        }
        else {
            fileHandle(zipOutputStream, srcFile, srcFileName);
        }
    }

    /**
     * 文件处理
     * @param zipOutputStream zip输出流
     * @param srcFile 源文件
     * @param srcFileName 源文件名称
     */
    @SneakyThrows
    private static void fileHandle(ZipOutputStream zipOutputStream, File srcFile, String srcFileName) {
        byte[] buf = new byte[BUFFER_SIZE];
        try (FileInputStream fileInputStream = new FileInputStream(srcFile)) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zipOutputStream.putNextEntry(new ZipEntry(srcFileName));
            // 起始值
            int start = 0;
            // 偏移量
            int len;

            while ((len = fileInputStream.read(buf)) > 0) {
                zipOutputStream.write(buf, start, len);
            }
            // IoUtil.copy(srcFile.toURI().toURL(),zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }

    /**
     * 目录处理
     * @param zipOutputStream zip输出流
     * @param srcFile 源文件
     * @param srcFileName 源文件名称
     */
    @SneakyThrows
    private static void dirHandle(ZipOutputStream zipOutputStream, File srcFile, String srcFileName) {
        File[] listFiles = srcFile.listFiles();
        if (Objects.isNull(listFiles) || listFiles.length == 0) {
            // 空文件夹创建目录
            zipOutputStream.putNextEntry(new ZipEntry(srcFileName + URL_SEPARATOR));
            zipOutputStream.closeEntry();
        }
        else {
            // 递归文件夹中的文件
            for (File file : listFiles) {
                fileWriteZipHandle(zipOutputStream, file, srcFileName + URL_SEPARATOR + file.getName());
            }
        }
    }

}
