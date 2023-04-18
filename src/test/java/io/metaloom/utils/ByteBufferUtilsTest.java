package io.metaloom.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

public class ByteBufferUtilsTest {

	@Test
	public void testString() {
		ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer("test");
		String value = ByteBufferUtils.convertFromByteBuffer(buffer, String.class);
		assertEquals("test", value);
	}

	@Test
	public void testLongString() {
		String str = "1test test test 123 test test test test 42a";
		ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer(str);
		String value = ByteBufferUtils.convertFromByteBuffer(buffer, String.class);
		assertEquals(str, value);
	}

	@Test
	public void testLong() {
		ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer(42L);
		Long value = ByteBufferUtils.convertFromByteBuffer(buffer, Long.class);
		assertEquals(42L, value.longValue());
	}

	@Test
	public void testDouble() {
		ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer(4.2d);
		Double value = ByteBufferUtils.convertFromByteBuffer(buffer, Double.class);
		assertEquals(4.2d, value.doubleValue(), 0d);
	}

	@Test
	public void testFloat() {
		ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer(42f);
		Float value = ByteBufferUtils.convertFromByteBuffer(buffer, Float.class);
		assertEquals(42f, value.floatValue(), 0d);
	}

	@Test
	public void testChar() {
		ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer('c');
		Character value = ByteBufferUtils.convertFromByteBuffer(buffer, Character.class);
		assertEquals('c', value.charValue());
	}

	@Test
	public void testInteger() {
		ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer(42);
		Integer value = ByteBufferUtils.convertFromByteBuffer(buffer, Integer.class);
		assertEquals(42, value.intValue());
	}
}
