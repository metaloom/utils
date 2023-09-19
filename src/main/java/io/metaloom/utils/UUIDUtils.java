package io.metaloom.utils;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Centralized UUID utility to handle creator and transformation of UUIDs.
 */
public final class UUIDUtils {

	private static Pattern p = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");

	public static UUID randomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}

	public static UUID fromString(String uuid) {
		return UUID.fromString(uuid);
	}

	/**
	 * Check whether the given text is a uuid.
	 * 
	 * @param text
	 *            String to be checked
	 * @return true if the text represents a uuid
	 */
	public static boolean isUUID(String text) {
		if (text == null || text.length() != 36) {
			return false;
		} else {
			return p.matcher(text).matches();
		}
	}
}
