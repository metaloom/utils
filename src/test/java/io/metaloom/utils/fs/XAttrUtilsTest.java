package io.metaloom.utils.fs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class XAttrUtilsTest {

	private static final Random RANDOM = new Random();

	@Test
	public void testReadWrite() throws IOException {
		assertDataValue(42L, Long.class);
		assertDataValue(42, Integer.class);
		assertDataValue(42.42d, Double.class);
		assertDataValue("hello world", String.class);
		assertDataValue(true, Boolean.class);
	}

	@Test
	@Disabled
	public void testLargeXattr() throws IOException {
		final String key = "test";
		Path testFile = File.createTempFile("test", "large-xattr").toPath();
		byte[] data = randomBytes(1024 * 1024); // 4096
		XAttrUtils.clearXAttr(testFile);
		XAttrUtils.writeBinXAttr(testFile, key, ByteBuffer.wrap(data));
		byte[] readData = XAttrUtils.readBinXAttr(testFile, key).array();
		assertTrue(Arrays.equals(data, readData), "Both data arrays should have the same data");
	}

	@Test
	public void testMissingXattr() throws IOException {
		Path testFile = File.createTempFile("test", "large-xattr").toPath();
		assertNull(XAttrUtils.readBinXAttr(testFile, "test"));
		assertNull(XAttrUtils.readXAttr(testFile, "test", String.class));
	}

	@Test
	public void testHasXattr() throws IOException {
		Path testFile = File.createTempFile("test", "large-xattr").toPath();
		assertFalse(XAttrUtils.hasXAttr(testFile, "blub"));
		XAttrUtils.writeXAttr(testFile, "blub", "hello");
		assertTrue(XAttrUtils.hasXAttr(testFile, "blub"));
	}

	private byte[] randomBytes(int chunkSize) {
		byte[] data = new byte[chunkSize];
		RANDOM.nextBytes(data);
		return data;
	}

	private <T> void assertDataValue(T value, Class<T> clazzOfT) throws IOException {
		String key = "abc_" + System.currentTimeMillis();
		Path testFile = File.createTempFile("test", "xattr").toPath();
		XAttrUtils.clearXAttr(testFile);
		XAttrUtils.writeXAttr(testFile, key, value);
		List<String> attrs = XAttrUtils.listAttr(testFile);
		assertEquals(1, attrs.size(), "The list should only contain one attr");
		String readKey = attrs.get(0);
		assertEquals(key, readKey, "The attribute key did not match");
		T result = XAttrUtils.readXAttr(testFile, key, clazzOfT);
		assertEquals(value, result, "The read back value was not correct");
	}
}
