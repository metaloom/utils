package io.metaloom.utils;

import java.util.UUID;

/**
 * Centralized UUID utility to handle creator and transformation of UUIDs.
 */
public final class UUIDUtils {

	public static UUID randomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}

	public static UUID fromString(String uuid) {
		return UUID.fromString(uuid);
	}
}
