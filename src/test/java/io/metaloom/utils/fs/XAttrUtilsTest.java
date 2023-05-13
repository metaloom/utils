package io.metaloom.utils.fs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

public class XAttrUtilsTest {

	@Test
	public void testReadWrite() throws IOException {
		assertDataValue(42L, Long.class);
		assertDataValue(42, Integer.class);
		assertDataValue(42.42d, Double.class);
		assertDataValue("hello world", String.class);
		assertDataValue(true, Boolean.class);
	}

	private <T> void assertDataValue(T value, Class<T> clazzOfT) throws IOException {
		String key = "abc_" + System.currentTimeMillis();
		Path testFile = File.createTempFile("test", "xattr").toPath();
		XAttrUtils.clearXAttr(testFile);
		XAttrUtils.writeAttr(testFile, key, value);
		List<String> attrs = XAttrUtils.listAttr(testFile);
		assertEquals(1, attrs.size(), "The list should only contain one attr");
		String readKey = attrs.get(0);
		assertEquals(key, readKey, "The attribute key did not match");
		T result = XAttrUtils.readAttr(testFile, key, clazzOfT);
		assertEquals(value, result, "The read back value was not correct");
	}
}
