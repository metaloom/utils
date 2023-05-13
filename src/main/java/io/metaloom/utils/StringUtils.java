package io.metaloom.utils;

import java.security.SecureRandom;

public final class StringUtils {

	private static final SecureRandom random = new SecureRandom();

	/**
	 * Generate a random string which does not include characters which are hard to differentiate.
	 * 
	 * @param len
	 * @return
	 */
	public static String randomHumanString(int len) {
		// Characters not included which are hard to be differentiated (0 vs O, 1 vs. I)
		String chars = "abcdefghjkmnpqrstuvwxyz";
		String nums = "23456789";
		String passSymbols = chars + nums + chars.toUpperCase();

		char[] password = new char[len];
		for (int i = 0; i < len; i++) {
			password[i] = passSymbols.charAt(random.nextInt(passSymbols.length()));
		}
		return new String(password);
	}
}
