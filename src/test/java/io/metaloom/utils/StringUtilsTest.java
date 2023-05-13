package io.metaloom.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {

	@Test
	public void testRandomHumanString() {
		String str = StringUtils.randomHumanString(10);
		assertNotNull(str);
		assertFalse(str.isEmpty());
		assertEquals(10, str.length());
		System.out.println(str);
	}
}
