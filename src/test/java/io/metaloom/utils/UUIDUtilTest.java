package io.metaloom.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UUIDUtilTest {

	@Test
	public void testUtils() {
		UUID uuid = UUID.randomUUID();
		assertTrue(UUIDUtils.isUUID(uuid.toString()), "The value should be a uuid");
	}
}
