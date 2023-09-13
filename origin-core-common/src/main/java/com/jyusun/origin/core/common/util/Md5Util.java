package com.jyusun.origin.core.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 作用描述： - MD5操作
 *
 * @author JyuSun at 2019/4/14 12:40
 * @version 1.0.0
 */
public class Md5Util {

    /**
     * 字符补位
     */
    private final static String STRING_0 = "0";

    /**
     * 文本长度
     */
    private final static Integer CODE_LENGTH = 32;

    /**
     * 作用描述： - 对字符串进行MD5加密处理
     * @param plainText 明文字符串
     * @return String
     * @author JyuSun at 2019/4/14 12:40
     */
    public static String getMd5(String plainText) {
        // 定义一个字节数组
        byte[] byteArray;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对字符串进行加密
            md.update(plainText.getBytes());
            // 获得加密后的数据
            byteArray = md.digest();

        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法异常");
        }

        /*
         * digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
         * BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
         * 一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
         */
        String md5code = new BigInteger(1, byteArray).toString(16);

        // 如果生成数字未满32位，需要前面补0
        StringBuilder returnCode = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH - md5code.length(); i++) {
            returnCode.append(STRING_0);
        }
        returnCode.append(md5code);
        return returnCode.toString();

    }

}
