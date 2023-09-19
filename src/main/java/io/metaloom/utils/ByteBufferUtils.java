package io.metaloom.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;

public final class ByteBufferUtils {

	private ByteBufferUtils() {
	}

	/**
	 * Convert the given value to bytes which will be held by the buytebuffer.
	 * 
	 * @param value
	 * @return
	 */
	public static ByteBuffer convertToByteBuffer(Object value) {
		Objects.requireNonNull(value);
		// TODO use pattern matching here when JDK 21 support ships for eclipse
		ByteBuffer buffer = null;
		if (value instanceof ByteBuffer bb) {
			buffer = bb;
		} else if (value instanceof Boolean b) {
			buffer = ByteBuffer.allocate(Integer.BYTES).putInt(b ? 1 : 0);
		} else if (value instanceof Character c) {
			buffer = ByteBuffer.allocate(Character.BYTES).putChar(c);
		} else if (value instanceof Integer i) {
			buffer = ByteBuffer.allocate(Integer.BYTES).putInt(i);
		} else if (value instanceof Long l) {
			buffer = ByteBuffer.allocate(Long.BYTES).putLong(l);
		} else if (value instanceof Double d) {
			buffer = ByteBuffer.allocate(Double.BYTES).putDouble(d);
		} else if (value instanceof Float f) {
			buffer = ByteBuffer.allocate(Float.BYTES).putFloat(f);
		} else if (value instanceof String s) {
			buffer = ByteBuffer.wrap(s.getBytes());
		} else {
			buffer = Charset.defaultCharset().encode(value.toString());
		}
		buffer.position(0);
		return buffer;
	}

	/**
	 * Convert the contents of the byte buffer to the provided type.
	 * 
	 * @param <T>
	 * @param buffer
	 * @param classOfT
	 * @return
	 */
	public static <T> T convertFromByteBuffer(ByteBuffer buffer, Class<T> classOfT) {
		if (classOfT.isAssignableFrom(String.class)) {
			return classOfT.cast(new String(buffer.array()));
		} else if (classOfT.isAssignableFrom(Long.class)) {
			return classOfT.cast(Long.valueOf(buffer.getLong()));
		} else if (classOfT.isAssignableFrom(Double.class)) {
			return classOfT.cast(Double.valueOf(buffer.getDouble()));
		} else if (classOfT.isAssignableFrom(Character.class)) {
			return classOfT.cast(Character.valueOf(buffer.getChar()));
		} else if (classOfT.isAssignableFrom(Float.class)) {
			return classOfT.cast(Float.valueOf(buffer.getFloat()));
		} else if (classOfT.isAssignableFrom(Integer.class)) {
			return classOfT.cast(Integer.valueOf(buffer.getInt()));
		} else if (classOfT.isAssignableFrom(Boolean.class)) {
			return classOfT.cast(Boolean.valueOf(buffer.getInt() == 0 ? false : true));
		} else {
			throw new RuntimeException("Invalid type");
		}
	}

}
