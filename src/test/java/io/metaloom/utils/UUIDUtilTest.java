package io.metaloom.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UUIDUtilTest {

	@Test
	public void testUtils() {
		UUID uuid = UUID.randomUUID();
		assertTrue(UUIDUtils.isUUID(uuid.toString()), "The value should be a uuid");

		UUID uuid2 = UUID.fromString("56ac17f2-a003-413a-ad38-303a05808eb7");
		assertTrue(UUIDUtils.isUUID(uuid2.toString()));
	}

}
