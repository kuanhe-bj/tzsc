package io.renren.vas.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * MD5工具类
 */
public class Md5Util {

    public final static String MD5 = "MD5";

    public final static String UTF8 = "utf-8";

    /**
     * MD5加密
     *
     * @param plainText 需要加密的内容
     * @return String 32位MD5加密数据
     */
    public static String Md5(String plainText) {
        String afterStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(plainText.getBytes(UTF8));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            afterStr = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return afterStr;
    }

    public static String MD5Encode(String origin, String charset) {
        try {
            return DigestUtils.md5Hex(origin.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * MD5加密byte数组
     *
     * @param bytes
     * @return
     */
    public static String Md5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(bytes);
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
