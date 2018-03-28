package com.control.situation.utils.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * 字符串的加密解密
 *
 * Created by demon on 2017/7/1 0001.
 */
public class DESEncryptionUtils {

    private DESEncryptionUtils() { }

    private final static String DES = "DES";
    private static final String key = "DE6K9MO7N";

    /**
     * 加密数据，采用默认密钥
     * @param data 加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data) throws Exception {
        return encrypt(data, key.getBytes());
    }

    /**
     * 加密数据
     * @param data 加密的数据
     * @param key 密钥
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {  //加密代码
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }

    /**
     * 解密数据
     * @param data 需要解密的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data) throws Exception { //解密代码
        return decrypt(data, key.getBytes());
    }

    /**
     * 解密数据
     * @param data 需要解密的数据
     * @param key 密钥，必须与加密时的密钥匹配
     * @return 解密后的数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        byte[] _data =  cipher.doFinal(data);
        return _data;
    }

    /**
     * 加密数据
     * @param data 加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static String encryptToString(byte[] data) throws Exception {
        return new String(encrypt(data, key.getBytes()));
    }

    /**
     * 加密数据
     * @param data 加密的数据
     * @param key 密钥
     * @return 加密后的数据
     * @throws Exception
     */
    public static String encryptToString(byte[] data, byte[] key) throws Exception {
        return new String(encrypt(data, key));
    }

    /**
     * 解密数据
     * @param data 需要解密的数据
     * @return 解密后的数据
     * @throws Exception
     */
    public static String decryptToString(byte[] data) throws Exception {
        return new String(decrypt(data, key.getBytes()));
    }

    /**
     * 解密数据
     * @param data 需要解密的数据
     * @param key 密钥，必须与加密时的密钥匹配
     * @return 解密后的数据
     * @throws Exception
     */
    public static String decryptToString(byte[] data, byte[] key) throws Exception {
        return new String(decrypt(data, key));
    }

    public static void main(String[] args) throws Exception {
        byte[] data = encrypt("P@ssw0rd".getBytes());
        System.out.println("encode=" + new String(data));
        data = decrypt(data);
        System.out.println("decode=" + new String(data));
    }
}
