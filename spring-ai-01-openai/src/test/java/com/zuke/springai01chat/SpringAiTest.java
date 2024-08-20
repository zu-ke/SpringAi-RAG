package com.zuke.springai01chat;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zukedog@163.com
 * @date 2024/7/30 16:14
 */
public class SpringAiTest {

    @Test
    public void getImgByte(){
        String filePath = "C:\\Users\\win10\\Desktop\\1.jpg";
        File file = new File(filePath);
        byte[] byteArray = null;

        try (FileInputStream fis = new FileInputStream(file)) {
            byteArray = new byte[(int) file.length()];
            fis.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (byte b : byteArray) {
            System.out.print(b);
        }
    }
}
