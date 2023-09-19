package io.metaloom.utils;

import java.nio.ByteBuffer;

public final class FloatUtils {

	private FloatUtils() {
	}

	public static byte[] floatToByte(float[] input) {
		byte[] ret = new byte[input.length * 4];
		for (int x = 0; x < input.length; x++) {
			ByteBuffer.wrap(ret, x * 4, 4).putFloat(input[x]);
		}
		return ret;
	}

	public static float[] byteToFloat(byte[] input) {
		float[] ret = new float[input.length / 4];
		for (int x = 0; x < input.length; x += 4) {
			ret[x / 4] = ByteBuffer.wrap(input, x, 4).getFloat();
		}
		return ret;
	}

	public static Float[] floatToFloat(float[] vector) {
		Float[] array = new Float[vector.length];
		for (int i = 0; i < vector.length; i++) {
			array[i] = vector[i];
		}
		return array;
	}
}
