package test.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Converter;
import game.Pair;

class ConverterTest {

	@Test
	void testToAbsolute() {
		Pair<Integer, Integer> result = Converter.toAbsolute(0, 0);
		Pair<Integer, Integer> expected = new Pair<Integer, Integer>(0, 0);
		assertTrue(result.equals(expected));
		
		result = Converter.toAbsolute(4, 1);
		expected = new Pair<Integer, Integer>(3, 4);
		assertTrue(result.equals(expected));
		
		result = Converter.toAbsolute(4, 8);
		expected = new Pair<Integer, Integer>(5, 5);
		assertTrue(result.equals(expected));
		
		result = Converter.toAbsolute(2, 4);
		expected = new Pair<Integer, Integer>(1, 7);
		assertTrue(result.equals(expected));
		
		result = Converter.toAbsolute(8, 8);
		expected = new Pair<Integer, Integer>(8, 8);
		assertTrue(result.equals(expected));
	}

	@Test
	void testToRelative() {
		Pair<Integer, Integer> result = Converter.toRelative(0, 0);
		Pair<Integer, Integer> expected = new Pair<Integer, Integer>(0, 0);
		assertTrue(result.equals(expected));
		
		result = Converter.toRelative(3, 4);
		expected = new Pair<Integer, Integer>(4, 1);
		assertTrue(result.equals(expected));
		
		result = Converter.toRelative(5, 5);
		expected = new Pair<Integer, Integer>(4, 8);
		assertTrue(result.equals(expected));
		
		result = Converter.toRelative(1, 7);
		expected = new Pair<Integer, Integer>(2, 4);
		assertTrue(result.equals(expected));
		
		result = Converter.toRelative(8, 8);
		expected = new Pair<Integer, Integer>(8, 8);
		assertTrue(result.equals(expected));
	}
}
