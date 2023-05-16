package io.metaloom.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class ConvertUtilsTest {

	@Test
	public void testTime() {
		assertEquals("1.0 sec", ConvertUtils.toHumanTime(1000));
		assertEquals("3.3 sec", ConvertUtils.toHumanTime(3333));
		assertEquals("35.3 sec", ConvertUtils.toHumanTime(35333));
		assertEquals("92.0 min", ConvertUtils.toHumanTime(5522333));
		assertEquals("500 ms", ConvertUtils.toHumanTime(500));
	}

	@Test
	public void testToHumanSize() {
		assertEquals("50 KB", ConvertUtils.toHumanSize(1024 * 50));
		assertEquals("1 MB", ConvertUtils.toHumanSize(1024 * 1024));
		assertEquals("10 MB", ConvertUtils.toHumanSize(1024 * 1024 * 10));
		assertEquals("124 MB", ConvertUtils.toHumanSize(1024 * 1024 * 124));
		assertEquals("1 GB", ConvertUtils.toHumanSize(1024L * 1024 * 1024));
		assertEquals("42 TB", ConvertUtils.toHumanSize(1024L * 1024 * 1024 * 1024 * 42));
	}

	@Test
	public void testFromHumanSize() {
		assertEquals(42L, ConvertUtils.fromHumanSize("42"));
		assertEquals(42L, ConvertUtils.fromHumanSize("42b"));
		assertEquals(42L, ConvertUtils.fromHumanSize("42bytes"));
		assertEquals(1024L, ConvertUtils.fromHumanSize("1KB"));
		assertEquals(1024L * 1024, ConvertUtils.fromHumanSize("1MB"));
		assertEquals(42L * 1024 * 1024 * 1024, ConvertUtils.fromHumanSize("42GB"));
		assertEquals(42L * 1024 * 1024 * 1024, ConvertUtils.fromHumanSize("42 GB"));
		assertEquals(42L * 1024 * 1024 * 1024, ConvertUtils.fromHumanSize("42GB"));
		assertEquals(42L * 1024 * 1024 * 1024 * 1024, ConvertUtils.fromHumanSize("42TB"));
		assertNull(ConvertUtils.fromHumanSize("abcdfeg"));
	}

}
