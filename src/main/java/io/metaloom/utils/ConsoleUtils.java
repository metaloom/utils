package io.metaloom.utils;

import java.io.IOException;

public final class ConsoleUtils {

	private ConsoleUtils() {
	}

	public static boolean ask(String text) throws IOException {
		System.out.println(text + " Yes/No?");
		while (true) {
			char c = (char) System.in.read();
			switch (c) {
			case 'y':
			case 'Y':
				return true;
			case 'n':
			case 'N':
				return false;
			default:
				continue;
			}
		}
	}

}
