package com.control.situation.utils.crypto;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Demon-Coffee on 2018/2/6 0006.
 */
public class SSHAUtils {

	/**
	 * SSHA加密标识
	 */
	private static final String SSHA = "{SSHA}";

	private static final Random RANDOM = new SecureRandom();

	private static final int DEFAULT_SALT_SIZE = 10;

	/**
	 * 加密用户密码,生成默认的指定字节数
	 * <p>
	 * 调用 boolean verifySaltedPassword(String password, String entry); 方法验证
	 *
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSaltedPassword(String password) throws NoSuchAlgorithmException {
		if (null == password) {
			throw new IllegalArgumentException();
		}
		byte[] salt = new byte[DEFAULT_SALT_SIZE];
		RANDOM.nextBytes(salt);
		return getSaltedPassword(password.getBytes(), salt);
	}

	/**
	 * 加密用户密码
	 *
	 * @param password
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String getSaltedPassword(byte[] password, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA");
		digest.update(password);
		byte[] hash = digest.digest(salt);

		// Create an array with the hash plus the salt
		byte[] all = new byte[hash.length + salt.length];
		System.arraycopy(hash, 0, all, 0, hash.length);
		System.arraycopy(salt, 0, all, hash.length, salt.length);

		byte[] base64 = Base64.encodeBase64(all);
		return SSHA + new String(base64);
	}

	public static boolean verifySaltedPassword(String password, String entry) {
		try {
			return verifySaltedPassword(password.getBytes("UTF-8"), entry);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 验证密码
	 *
	 * @param password
	 * @param entry
	 */
	public static boolean verifySaltedPassword(byte[] password, String entry) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		// First, extract everything after {SSHA} and decode from Base64
		if (!entry.startsWith(SSHA)) {
			throw new IllegalArgumentException("Hash not prefixed by {SSHA}; is it really a salted hash?");
		}
		byte[] challenge = Base64.decodeBase64(entry.substring(6).getBytes("UTF-8"));

		// Extract the password hash and salt
		byte[] passwordHash = extractPasswordHash(challenge);
		byte[] salt = extractSalt(challenge);

		// Re-create the hash using the password and the extracted salt
		MessageDigest digest = MessageDigest.getInstance("SHA");
		digest.update(password);
		byte[] hash = digest.digest(salt);

		// See if our extracted hash matches what we just re-created
		return Arrays.equals(passwordHash, hash);
	}

	/**
	 * 提取密码哈希值
	 *
	 * @param digest 加密后的密码,Base64.decodeBase64(digest.substring(6).getBytes("UTF-8")
	 */
	protected static byte[] extractPasswordHash(byte[] digest) throws IllegalArgumentException {
		if (digest.length < 20) {
			throw new IllegalArgumentException("Hash was less than 20 characters; could not extract password hash!");
		}

		// Extract the password hash
		byte[] hash = new byte[20];
		for (int i = 0; i < 20; i++) {
			hash[i] = digest[i];
		}

		return hash;
	}

	/**
	 * 提取随机字节数
	 *
	 * @param digest 加密后的密码,Base64.decodeBase64(digest.substring(6).getBytes("UTF-8")
	 */
	protected static byte[] extractSalt(byte[] digest) throws IllegalArgumentException {
		if (digest.length <= 20) {
			throw new IllegalArgumentException("Hash was less than 21 characters; we found no salt!");
		}

		// Extract the salt
		byte[] salt = new byte[digest.length - 20];
		for (int i = 20; i < digest.length; i++) {
			salt[i - 20] = digest[i];
		}

		return salt;
	}

	public static void main(final String[] args) throws Exception {

//		 User wants to hash the password
		final String password = "1234561231231322123123123";
		String saltedPassword = getSaltedPassword(password);
		System.out.println(saltedPassword);
		System.out.println(saltedPassword.length());

		// User wants to verify an existing password
		System.out.println(verifySaltedPassword(password.getBytes("UTF-8"), saltedPassword));

	}
}
