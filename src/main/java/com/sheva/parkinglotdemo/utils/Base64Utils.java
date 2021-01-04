package com.sheva.parkinglotdemo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import java.io.*;

/**
 * @Author Sheva
 * @Date 2020/12/1
 */
@Slf4j
public class Base64Utils {

    private static Base64 base64 = new Base64();

    /**
     * 图片转化成base64字符串
     * @param imgPath
     * @return
     */
    public static String GetImageStr(String imgPath) {
        String imgFile = imgPath;
        InputStream in = null;
        byte[] data = null;
        String encode = null;

        try {
            // read image byte array
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = new String(base64.encode(data));
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64字符串转化成图片
     *
     * @param imgData
     *            图片编码
     * @param imgFilePath
     *            存放到本地路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException {
        if (imgData == null) {
            log.error("Image data is empty...");
            return false;
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64 decode
            byte[] b = base64.decode(imgData);
            for (int i = 0; i < b.length; ++i) {
                // adjust data
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        String imageStr = Base64Utils.GetImageStr("/Users/sheva/Pictures/pap.er/05Wgbxcb4GE.jpg");
        System.out.println(imageStr);
        Base64Utils.GenerateImage(imageStr, "/Users/sheva/Pictures/pap.er/test01.jpg");
    }

}
