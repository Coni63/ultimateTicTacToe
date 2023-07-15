package test.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import game.MiniBoard;

class MiniBoardTest {

	@Test
	void test_initialization() {
		// new Board
		MiniBoard board = new MiniBoard();
		
		assertFalse(board.isOver);
	}

	@Test
	void test_initialization_2() {
		// Won board
		MiniBoard board = new MiniBoard(new int[] {-1, 1, 0, -1, 0, 0, -1, 0, 0});
		
		assertTrue(board.isOver);
		assertEquals(board.winner, -1);
	}
	
	@Test
	void test_initialization_3() {
		// Tie
		MiniBoard board = new MiniBoard(new int[] {-1, 1, -1, 1, -1, 1, 1, -1, 1});
		
		assertTrue(board.isOver);
		assertEquals(board.winner, 0);
	}
	
	@Test
	void test_states() {
		// Won board
		MiniBoard board = new MiniBoard(new int[] {-1, 1, 0, -1, 0, 0, -1, 0, 0});
		
		assertEquals(board.getIndexWithStates(0).size(), 0); // in case of winner, there is no places remaining to play
		assertEquals(board.getIndexWithStates(1).size(), 1);
		assertEquals(board.getIndexWithStates(-1).size(), 3);
		assertEquals(board.getIndexWithStates(-1), Arrays.asList(0, 3, 6));
	}
	
	@Test
	void test_states_2() {
		// active board
		MiniBoard board = new MiniBoard(new int[] {-1, 1, 0, 1, 0, 0, -1, 0, 0});
		
		assertEquals(board.getIndexWithStates(0).size(), 5); // in case of winner, there is no places remaining to play
		assertEquals(board.getIndexWithStates(1).size(), 2);
		assertEquals(board.getIndexWithStates(-1).size(), 2);
		assertEquals(board.getIndexWithStates(0), Arrays.asList(2, 4, 5, 7, 8));
	}
	
	@Test
	void test_hash() {
		// active board
		MiniBoard board = new MiniBoard(new int[] {-1, 1, 0, 1, 0, 0, -1, 0, 0});
		
		assertEquals(board.hash, "OX-X--O--");
		assertEquals(MiniBoard.getHashFromArray(new int[] {-1, 1, 0, 1, 0, 0, -1, 0, 0}), "OX-X--O--");
	}
	
	@Test
	void test_childs() {
		// Test to set a child and retrieve it
		MiniBoard board     = new MiniBoard(new int[] {-1, 1, 0, 1, 0, 0, -1, 0, 0});
		MiniBoard nextBoard = new MiniBoard(new int[] {-1, 1, -1, 1, 0, 0, -1, 0, 0});
		//                                                   ^
		
		board.setChild(2, 2, nextBoard);
		MiniBoard result = board.getChild(2, 2);
		
		assertEquals(nextBoard, result);
	}
}
