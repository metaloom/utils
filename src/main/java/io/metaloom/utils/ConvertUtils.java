package io.metaloom.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConvertUtils {

	private static Pattern FIRST_INT_NUMBER_PATTERN = Pattern.compile("(\\d+)\\s*(.*)");

	private ConvertUtils() {
	}

	public static final long ONE_KB = 1024L;

	public static final BigInteger ONE_KB_BI = BigInteger.valueOf(ONE_KB);

	public static final long ONE_MB = 1024L * 1024;

	public static final BigInteger ONE_MB_BI = BigInteger.valueOf(ONE_MB);

	public static final long ONE_GB = 1024L * 1024 * 1024;

	public static final BigInteger ONE_GB_BI = BigInteger.valueOf(ONE_GB);

	public static final long ONE_TB = 1024L * 1024 * 1024 * 1024;

	public static final BigInteger ONE_TB_BI = BigInteger.valueOf(ONE_TB);

	public static String toGB(long size) {
		return String.valueOf(BigInteger.valueOf(size).divide(ONE_GB_BI)) + " GB";
	}

	public static String toHumanSize(long size) {
		if (size < ONE_KB) {
			return String.valueOf(size) + " B";
		} else if (size < ONE_MB) {
			return String.valueOf(BigInteger.valueOf(size).divide(ONE_KB_BI)) + " KB";
		} else if (size < ONE_GB) {
			return String.valueOf(BigInteger.valueOf(size).divide(ONE_MB_BI)) + " MB";
		} else if (size < ONE_TB) {
			return String.valueOf(BigInteger.valueOf(size).divide(ONE_GB_BI)) + " GB";
		} else {
			return String.valueOf(BigInteger.valueOf(size).divide(ONE_TB_BI)) + " TB";
		}
	}

	public static Long fromHumanSize(String humanReadableSize) {
		Matcher match = FIRST_INT_NUMBER_PATTERN.matcher(humanReadableSize);
		if (!match.matches()) {
			return null;
		}
		String numberStr = match.group(1);
		String suffix = match.group(2);
		long number = Integer.valueOf(numberStr);

		if (suffix.endsWith("TB")) {
			return number*1024*1024*1024*1024;
		} else if (suffix.endsWith("GB")) {
			return number*1024*1024*1024;
		} else if (suffix.endsWith("MB")) {
			return number*1024*1024;
		} else if (suffix.endsWith("KB")) {
			return number*1024;
		} else {
			return number;
		}
	}

	public static String toHumanTime(long ms) {
		if (ms < 900) {
			return ms + " ms";
		} else if (ms < 1000 * 60) {
			double sec = (double) ms / 1000;
			return String.format("%.1f", sec) + " sec";
		} else {
			double min = (double) ms / 1000 / 60;
			return String.format("%.1f", min) + " min";
		}
	}

	public static double[] convertFloatsToDoubles(float[] input) {
		if (input == null) {
			return null; // Or throw an exception - your choice
		}
		double[] output = new double[input.length];
		for (int i = 0; i < input.length; i++) {
			output[i] = input[i];
		}
		return output;
	}

	public static float[] bytesToFloats(byte[] bytes) {
		if (bytes.length % Float.BYTES != 0) {
			throw new RuntimeException("Illegal length");
		}
		float floats[] = new float[bytes.length / Float.BYTES];
		ByteBuffer.wrap(bytes).asFloatBuffer().get(floats);
		return floats;
	}

	public static byte[] floatsToBytes(float[] floats) {
		byte bytes[] = new byte[Float.BYTES * floats.length];
		ByteBuffer.wrap(bytes).asFloatBuffer().put(floats);
		return bytes;
	}

}
