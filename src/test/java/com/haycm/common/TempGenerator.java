package com.haycm.common;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author: TaoLei
 * date: 2015/12/29.
 * F&M: 在举手投降以前，让我再陪你一段 --Eason
 * description:
 */
public class TempGenerator {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\taolei\\.IntelliJIdea14\\config\\fileTemplates\\includes\\";
        String source = "source.txt";

        List<String> content = readFile(generateFile(path, source));

        FileWriter writer = null;
        StringBuilder builder = null;
        for (int i = 0; i < 60; i++) {
            String name = "";
            builder = new StringBuilder(name);
            if (i < 10) {
                builder = builder.append("0");
            }
            name = builder.append(i).append(".say").toString();
            writer = new FileWriter(generateFile(path, name));
            if (content.size() == i) {
                break;
            }
            writer.write(content.get(i) + "\n\r");
            writer.close();
        }
        System.out.println("success");
    }

    public static File generateFile(String distDir, String name) throws IOException {
        File dir = new File(distDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(distDir + name);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static List<String> readFile(File file) throws IOException {
        List<String> content = new ArrayList<>();
        BufferedReader reader =  new BufferedReader(new FileReader(file));;
        String lineTxt = null;
        while ((lineTxt = reader.readLine()) != null) {
            content.add(lineTxt);
        }
        reader.close();
        return content;
    }

}
