package com.fykj.scaffold.support.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ApiMd5EncryptUtil
{
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * md5编码
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encode(String str) throws NoSuchAlgorithmException
    {
        // 生成一个MD5加密计算摘要
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
        byte[] results = md5.digest(str.getBytes());
        //将得到的字节数组变成字符串返回
        String result = byteArrayToHexString(results);

        return result;
    }

    private static String byteArrayToHexString(byte[] b)
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
