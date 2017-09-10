package com.cui.common.utils;

import com.cui.common.errorMessage.UtilErrorMessageFactory;
import com.cui.common.exception.UtilException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.StringJoiner;

/**
 * MD5工具类
 * Created by cuishixiang on 2017-09-08.
 */
public class MD5Util {

    /**
     * 错误工厂
     */
    private static final UtilErrorMessageFactory errorMessageFactory = UtilErrorMessageFactory.getInstance();

    /**
     * MD5加密
     *
     * @param texts 多个文本连接后一起加密
     * @return 加密后的字符
     */
    public static String eccrypt(String... texts) {
        StringJoiner joinText = new StringJoiner("、");
        for (String text : texts) {
            joinText.add(text);
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5Password = messageDigest.digest(joinText.toString().getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(md5Password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new UtilException(errorMessageFactory.createMd5ErrorMessage());
        }
    }

//    /**
//     * MD5加密
//     *
//     * @param texts 多个文本连接后一起加密
//     * @return 加密后的字符
//     * @throws NoSuchAlgorithmException
//     * @throws UnsupportedEncodingException
//     */
//    public static String eccrypt2(String... texts) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        StringJoiner joinText = new StringJoiner("、");
//        for (String text : texts) {
//            joinText.add(text);
//        }
//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        byte[] md5Password = messageDigest.digest(joinText.toString().getBytes("UTF-8"));
//        return new String(md5Password,"UTF-8");
//    }
}
