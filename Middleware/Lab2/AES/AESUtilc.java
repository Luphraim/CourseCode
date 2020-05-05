/*************************************************************************
	> File Name: AESUtil.java
	> Author: Jason
	> Mail: 24320172203182@stu.xmu.edu.cn
	> Created Time: Fri 13 Mar 2020 09:27:37 AM CST
 ************************************************************************/

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtilc {
	// 共通鍵
	private static final String ENCRYPTION_KEY = "RwcmlVpg";
	private static final String ENCRYPTION_IV = "4e5Wa71fYoT7MFEX";
	
	public static void encrypt(String src) {
		String encrypted = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, makeKey(), makeIv());
			encrypted = Base64.encodeBytes(cipher.doFinal(src.getBytes()));
            System.out.println("Encrypted: " + encrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void decrypt(String src) {
		String decrypted = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, makeKey(), makeIv());
			decrypted = new String(cipher.doFinal(Base64.decode(src)));
            System.out.println("Decrypted: " + decrypted);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	static AlgorithmParameterSpec makeIv() {
		try {
			return new IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static Key makeKey() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] key = md.digest(ENCRYPTION_KEY.getBytes("UTF-8"));
			return new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
