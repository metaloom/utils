package io.metaloom.utils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class FloatUtils {

	private FloatUtils() {
	}

	public static List<Float> toList(float[] input) {
		List<Float> list = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			float f = input[i];
			list.add(f);
		}
		return list;
	}

	public static double[] toDouble(float[] input) {
		double[] output = new double[input.length];
		for (int i = 0; i < input.length; i++) {
			output[i] = input[i];
		}
		return output;
	}

	public static float[] toArray(List<Float> input) {
		Objects.requireNonNull(input);
		float[] array = new float[input.size()];
		for (int i = 0; i < input.size(); i++) {
			array[i] = input.get(i);
		}
		return array;
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
