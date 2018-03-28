package com.control.situation.utils.crypto;

import com.control.situation.utils.crypto.CharsetUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.*;
import java.security.Key;

/**
 * 编码工具类
 *
 * Created by Demon on 2017/7/22 0022.
 */
public final class EncodeUtil {

    private EncodeUtil() {
    }

    /**
     * 字符串 MD5 加密
     *
     * @param text
     * @return String
     */
    public static String encryptMD5(String text) {
        return DigestUtils.md5Hex(text);
    }

    /**
     * 字符串 SHA1 加密
     *
     * @param text
     * @return String
     */
    public static String encryptSHA(String text) {
        return DigestUtils.sha1Hex(text);
    }

    /**
     * 字符串 Base64 编码
     *
     * @param text
     * @param charset
     * @return String
     */
    public static String encodeBASE64(String text, String charset) {
        try {
            return Base64.encodeBase64URLSafeString(text.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 默认utf-8字符串 Base64 编码
     *
     * @param text
     * @return String
     */
    public static String encodeBASE64(String text) {
        return encodeBASE64(text, com.control.situation.utils.crypto.CharsetUtil.UTF_8);
    }

    /**
     * 字符串 Base64 解码
     *
     * @param text
     * @param charset
     * @return String
     */
    public static String decodeBASE64(String text, String charset) {
        try {
            return new String(Base64.decodeBase64(text), charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 默认utf-8字符串 Base64 解码
     *
     * @param text
     * @return String
     */
    public static String decodeBASE64(String text) {
        return decodeBASE64(text, CharsetUtil.UTF_8);
    }

    /**
     * 文件 file 进行加密并保存目标文件 destFile 中
     *
     * @param file
     *            要加密的文件 如 c:/test/srcFile.txt
     * @param destFile
     *            加密后存放的文件名 如 c:/ 加密后文件 .txt
     */
    public void encryptFile(String file, String destFile, Key key) throws Exception {
        Cipher cipher;
        InputStream is = null;
        OutputStream out = null;
        CipherInputStream cis = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            is = new FileInputStream(file);
            out = new FileOutputStream(destFile);
            cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (cis != null) {
				cis.close();
			}
            if (is != null) {
				is.close();
			}
            if (out != null) {
				out.close();
			}
        }
    }

    /**
     * 文件采用 DES 算法解密文件
     *
     * @param file 已加密的文件 如 c:/ 加密后文件 .txt *
     * @param dest 解密后存放的文件名 如 c:/ test/ 解密后文件 .txt
     */
    public void decryptFile(String file, String dest, Key key) throws IOException {
        Cipher cipher;
        InputStream is = null;
        OutputStream out = null;
        CipherOutputStream cos = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            is = new FileInputStream(file);
            out = new FileOutputStream(dest);
            cos = new CipherOutputStream(out, cipher);

            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) >= 0) {
                cos.write(buffer, 0, r);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                is.close();
            }
            if (out != null) {
                out.close();
            }
            if (cos != null) {
                cos.close();
            }
        }
    }
}
