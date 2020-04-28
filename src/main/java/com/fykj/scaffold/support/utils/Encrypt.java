package com.fykj.scaffold.support.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密工具类
 *
 * md5加密出来的长度是32位
 *
 * sha加密出来的长度是40位
 *
 * base64加密可以指定字符集，可以解密
 *
 * @author
 *
 */
@Slf4j
public class Encrypt {

	private Encrypt() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 加密
	 *
	 * @param inputText
	 * @return
	 */
	public static String e(String inputText) {
		return md5(inputText);
	}

	/**
	 * 加密
	 *
	 * @param inputText
	 * @param charsetName
	 * @return
	 */
	public static String base64Encode(String inputText,String charsetName){
		byte [] binaryData = new byte[0];
		try {
			binaryData = inputText.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			log.error("加密失败" + e.getMessage());
		}
		return Base64.getEncoder().encodeToString(binaryData);
	}

	/**
	 * 解密
	 *
	 * @param encodedText
	 * @param charsetName
	 * @return
	 */
	public static String base64Decode(String encodedText,String charsetName) throws UnsupportedEncodingException {
		byte [] binaryData = new byte[0];
		try {
			binaryData = encodedText.getBytes(charsetName);
		} catch (UnsupportedEncodingException e) {
			log.error("解密失败" + e.getMessage());
		}
		return new String(Base64.getDecoder().decode(binaryData), "UTF-8");
	}


	/**
	 * 二次加密，应该破解不了了吧？
	 *
	 * @param inputText
	 * @return
	 */
	public static String md5AndSha(String inputText) {
		return sha(md5(inputText));
	}

	/**
	 * md5加密
	 *
	 * @param inputText
	 * @return
	 */
	public static String md5(String inputText) {
		return encrypt(inputText, "md5");
	}

	/**
	 * sha加密
	 *
	 * @param inputText
	 * @return
	 */
	public static String sha(String inputText) {
		return encrypt(inputText, "sha-1");
	}

	/**
	 * md5或者sha-1加密
	 *
	 * @param inputText
	 *            要加密的内容
	 * @param algorithmName
	 *            加密算法名称：md5或者sha-1，不区分大小写
	 * @return
	 */
	private static String encrypt(String inputText, String algorithmName) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes(StandardCharsets.UTF_8));
			return hex(m.digest());
		} catch (NoSuchAlgorithmException e) {
			log.error("md5或者sha-1加密失败" + e.getMessage());
		}
		return encryptText;
	}

	/**
	 * 返回十六进制字符串
	 *
	 * @param arr
	 * @return
	 */
	private static String hex(byte[] arr) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
		return new String(a);

    }

}
