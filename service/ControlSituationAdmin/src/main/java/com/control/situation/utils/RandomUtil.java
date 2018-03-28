package com.control.situation.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.util.UUID;

public class RandomUtil {
    
    public static String uuidBase64() {
        long uuid = UUID.randomUUID().getMostSignificantBits();
        byte[] uuidBytes = ByteBuffer.allocate(8).putLong(uuid).array();
        return Base64.encodeBase64URLSafeString(uuidBytes);
    }

    /**
     * 获取请求唯一标识，长度12位
     */
    public static String getRequestId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12)
                .replace("0", "2").replace("o", "a")
                .replace("O", "b");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(UUID.randomUUID().getMostSignificantBits());
        }
    }
    
}
