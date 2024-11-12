package io.metaloom.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FloatUtilsTest {

	@Test
	public void testToList() {
		float[] array = { 0.42f, 0.1f };
		List<Float> list = FloatUtils.toList(array);
		assertEquals(2, list.size());
		assertEquals(0.42f, list.get(0));
		assertEquals(0.1f, list.get(1));
	}

	@Test
	public void testToArray() {
		List<Float> list = new ArrayList<>();
		list.add(0.42f);
		list.add(0.1f);
		float[] array = FloatUtils.toArray(list);
		assertEquals(2, array.length);
		assertEquals(0.42f, array[0]);
		assertEquals(0.1f, array[1]);
	}
}
