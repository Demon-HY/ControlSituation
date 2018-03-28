/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/
 * java/org/apache/http/util/ByteArrayBuffer.java $
 * $Revision: 496070 $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package com.control.situation.utils.conversion;

// TODO 使用自带的 java.nio.ByteBuffer ？

/**
 * A resizable byte array.(一个大小可调整的字节数组)
 * 
 * @author <a href="mailto:oleg@ural.ru">Oleg Kalnichevski</a>
 * 
 * @version $Revision: 496070 $
 * 
 * @since 4.0
 */
public final class ByteArrayBuffer {

	private byte[] buffer;
	private int len;

	/**
	 * 生成一个字节数组
	 * @param capacity 指定字节数组的长度
	 */
	public ByteArrayBuffer(int capacity) {
		super();
		if (capacity < 0) {
			throw new IllegalArgumentException(
					"Buffer capacity may not be negative");
		}
		this.buffer = new byte[capacity];
	}

	/**
	 * 字节数组扩容
	 * @param newlen 追加的长度
	 */
	private void expand(int newlen) {
		byte newbuffer[] = new byte[Math.max(this.buffer.length << 1, newlen)];
		System.arraycopy(this.buffer, 0, newbuffer, 0, this.len);
		this.buffer = newbuffer;
	}

	/**
	 * 向数组中添加一个字节数组
	 * @param b
	 * @param off
	 * @param len
	 */
	public void append(final byte[] b, int off, int len) {
		if (b == null) {
			return;
		}
		if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) < 0)
				|| ((off + len) > b.length)) {
			throw new IndexOutOfBoundsException();
		}
		if (len == 0) {
			return;
		}
		int newlen = this.len + len;
		if (newlen > this.buffer.length) {
			expand(newlen);
		}
		System.arraycopy(b, off, this.buffer, this.len, len);
		this.len = newlen;
	}

	public void append(int b) {
		int newlen = this.len + 1;
		if (newlen > this.buffer.length) {
			expand(newlen);
		}
		this.buffer[this.len] = (byte) b;
		this.len = newlen;
	}

	public void append(final char[] b, int off, int len) {
		if (b == null) {
			return;
		}
		if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) < 0)
				|| ((off + len) > b.length)) {
			throw new IndexOutOfBoundsException();
		}
		if (len == 0) {
			return;
		}
		int oldlen = this.len;
		int newlen = oldlen + len;
		if (newlen > this.buffer.length) {
			expand(newlen);
		}
		for (int i1 = off, i2 = oldlen; i2 < newlen; i1++, i2++) {
			this.buffer[i2] = (byte) b[i1];
		}
		this.len = newlen;
	}

	public void clear() {
		this.len = 0;
	}

	public byte[] toByteArray() {
		byte[] b = new byte[this.len];
		if (this.len > 0) {
			System.arraycopy(this.buffer, 0, b, 0, this.len);
		}
		return b;
	}

	public int byteAt(int i) {
		return this.buffer[i];
	}

	public int capacity() {
		return this.buffer.length;
	}

	public int length() {
		return this.len;
	}

	public void setLength(int len) {
		if (len < 0 || len > this.buffer.length) {
			throw new IndexOutOfBoundsException();
		}
		this.len = len;
	}

	public boolean isEmpty() {
		return this.len == 0;
	}

	public boolean isFull() {
		return this.len == this.buffer.length;
	}

}
