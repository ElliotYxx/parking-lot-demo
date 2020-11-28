package com.sheva.parkinglotdemo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密工具类
 * @Author Sheva
 * @Date 2020/11/25
 */
public class EncryptUtil {

    /**
     * 算法名
     */
    private static String algorithm = "MD5";
    private static int hashIterations = 5;

    public static String encryptPassword(String username, String password){
        return new SimpleHash(algorithm, password, ByteSource.Util.bytes(username), hashIterations).toHex();
    }
}
