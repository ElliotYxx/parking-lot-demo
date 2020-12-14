package com.sheva.parkinglotdemo.utils;

import com.sheva.parkinglotdemo.constant.Constants;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密工具类
 * @Author Sheva
 * @Date 2020/11/25
 */
public class EncryptUtil {

    public static String encryptPassword(String username, String password){
        return new SimpleHash(Constants.ALGORITHM_NAME, password, ByteSource.Util.bytes(username), Constants.HASH_ITERATIONS).toHex();
    }
}
