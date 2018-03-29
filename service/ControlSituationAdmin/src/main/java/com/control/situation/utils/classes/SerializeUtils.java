package com.control.situation.utils.classes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Java 对象序列化对象
 * Created by Administrator on 2018/3/29 0029.
 */
public class SerializeUtils {

	/**
	 * 序列化
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos;
		ByteArrayOutputStream baos;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
