package io.metaloom.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

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
		ByteBuffer buffer = switch (value) {
		case ByteBuffer b -> b;
		case Character c -> ByteBuffer.allocate(Character.BYTES).putChar(c);
		case Integer i -> ByteBuffer.allocate(Integer.BYTES).putInt(i);
		case Long l -> ByteBuffer.allocate(Long.BYTES).putLong(l);
		case Double d -> ByteBuffer.allocate(Double.BYTES).putDouble(d);
		case Float f -> ByteBuffer.allocate(Float.BYTES).putFloat(f);
		case String s -> ByteBuffer.wrap(s.getBytes());
		default -> Charset.defaultCharset().encode(value.toString());
		};
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
		} else {
			throw new RuntimeException("Invalid type");
		}
	}

}
