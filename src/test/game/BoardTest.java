package test.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.Board;
import game.MiniBoard;
import game.Pair;
import game.TreeState;



class BoardTest {
	private static Map<String, MiniBoard> lookupTable;
	private static MiniBoard root;
	
	@BeforeAll
	public static void setupTree() {
		root = new MiniBoard();
		lookupTable = TreeState.computeChilds(root);
	}

	@Test
	void testLookupTable() {
		// Test the lookup table here even if its's another script but there is 1 function and it runs for those tests
		assertEquals(lookupTable.size(), 18753);
		
		// check the validity of a random child
		MiniBoard node = lookupTable.get("210100121");
		
		MiniBoard neigh = node.getChild(2, 2);
		assertEquals(neigh.hash, "212100121");
		
		MiniBoard error = node.getChild(2, 0); // play an invalid move
		assertEquals(error.hash, node.hash);   // in that case, we return self as the move is invalid
	}

	@Test
	void testPlay() {
		Board board = new Board(root);
		
		board.play(0, 0, 2);
		board.play(2, 0, 1);
		board.play(0, 1, 2);
		board.play(0, 2, 2);
		
		List<Integer> mainBoardWon = board.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon, Arrays.asList(0));
		
		List<Pair<Integer, Integer>> movesAvailables = board.getAvailablePosition();
		assertEquals(movesAvailables.size(), 8);  // check that we only have 1 sub-board to play which is the 2nd one
	}
	
	@Test
	void testPlayFinishedBoard() {
		Board board = new Board(root);
		
		board.play(0, 2, 2);
		board.play(0, 1, 2);
		board.play(0, 0, 2);
		
		List<Integer> mainBoardWon = board.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon, Arrays.asList(0));
		
		List<Pair<Integer, Integer>> movesAvailables = board.getAvailablePosition();
		assertEquals(movesAvailables.size(), 72);  // check that we only have 1 sub-board to play which is the 2nd one
	}
	
	@Test
	void testWinner() {
		Board board = new Board(root);
		
		board.play(0, 0, 2);
		board.play(0, 1, 2);
		board.play(0, 2, 2);
		
		assertEquals(board.winner(), 0);
		assertFalse(board.isOver());
		
		board.play(1, 0, 2);
		board.play(1, 1, 2);
		board.play(1, 2, 2);
		
		assertEquals(board.winner(), 0);
		assertFalse(board.isOver());
		
		board.play(2, 0, 2);
		board.play(2, 1, 2);
		board.play(2, 2, 2);
		
		assertEquals(board.winner(), 2);
		assertTrue(board.isOver());
	}
	
	@Test
	void testReset() {
		Board board = new Board(root);
		
		board.play(0, 0, 2);
		board.play(2, 0, 1);
		board.play(0, 1, 2);
		board.play(0, 2, 2);
		
		List<Integer> mainBoardWon = board.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon, Arrays.asList(0));
		
		List<Pair<Integer, Integer>> movesAvailables = board.getAvailablePosition();
		assertEquals(movesAvailables.size(), 8);  // check that we only have 1 sub-board to play which is the 2nd one
		
		board.reset();
		
		mainBoardWon = board.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon.size(), 0);
	}
	
	@Test
	void testGetPosition() {
		Board board = new Board(root);
		
		board.play(0, 0, 2);
		board.play(2, 0, 1);
		board.play(0, 1, 2);
		board.play(0, 2, 2);
		
		List<Pair<Integer, Integer>> positionsConquered = board.getPositionsWithState(2);
		assertEquals(positionsConquered.size(), 3);
		assertEquals(positionsConquered.stream().map(position -> position.second()).collect(Collectors.toList()), Arrays.asList(0, 1, 2));
	}
	
	@Test
	void testClone()
	{
		Board board = new Board(root);
		
		board.play(0, 0, 2);
		board.play(2, 0, 1);
		board.play(0, 1, 2);
		board.play(0, 2, 2);
		
		Board copy = board.Clone();
		
		// update only the copy to check that only the copy changed
		copy.play(1, 0, 2);  // col 3 row 0 in absolute
		copy.play(1, 1, 2);  // col 4 row 0 in absolute
		copy.play(1, 2, 2);  // col 5 row 0 in absolute
		
		// check that the initial board did not change
		List<Pair<Integer, Integer>> positionsConquered = board.getPositionsWithState(2);
		assertEquals(positionsConquered.size(), 3);
		assertEquals(positionsConquered.stream().map(position -> position.second()).collect(Collectors.toList()), Arrays.asList(0, 1, 2));
		
		List<Integer> mainBoardWon = board.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon.size(), 1);
		
		// check that the copy is updated
		positionsConquered = copy.getPositionsWithState(2);
		assertEquals(positionsConquered.size(), 6);
		assertEquals(positionsConquered.stream().map(position -> position.second()).collect(Collectors.toList()), Arrays.asList(0, 1, 2, 3, 4, 5));
		
		mainBoardWon = copy.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon.size(), 2);
		
		// check reset
		copy.reset();
		
		mainBoardWon = copy.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon.size(), 0);
		
		mainBoardWon = board.getMajorPositionsWithState(2);
		assertEquals(mainBoardWon.size(), 1);
	}
}
