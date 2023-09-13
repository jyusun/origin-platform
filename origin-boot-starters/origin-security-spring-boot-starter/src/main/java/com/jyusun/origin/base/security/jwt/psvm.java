package com.jyusun.origin.base.security.jwt;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class psvm {

    @SneakyThrows
    public static void main(String[] args) {
        File file = new File("C:\\Users\\sun\\Desktop\\新建文本文档.txt");
        List<String> strings = IOUtils.readLines(new FileInputStream(file), StandardCharsets.UTF_8);
        String a1 = "000000";
        String a2 = "";
        String a3 = "";
        for (int i = 0; i < strings.size(); i++) {
            String str = strings.get(i);
            if (str.split("\t")[0].contains("0000")) {
                a2 = str.split("\t")[0];
                System.out.println(str.split("\t")[0] + "," + str.split("\t")[1] + "," + a1 + ",1");
            }
            else if (str.split("\t ")[0].contains("00")) {
                a3 = str.split("\t")[0];
                System.out.println(str.split("\t")[0] + "," + str.split("\t")[1].trim() + "," + a2 + ",2");
            }
            else {
                System.out.println(str.split("\t")[0] + "," + str.split("\t")[1].trim() + "," + a3 + ",3");
            }
        }
    }

}
