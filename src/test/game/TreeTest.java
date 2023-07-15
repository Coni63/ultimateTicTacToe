package test.game;

import static org.junit.jupiter.api.Assertions.*;
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
import game.BoardEvaluator;
import game.MiniBoard;
import game.Pair;
import game.TreeState;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TreeTest {

	private static Map<String, MiniBoard> lookupTable;
	private static MiniBoard root;
	
	@BeforeAll
	public static void setupTree() {
		root = new MiniBoard();
		lookupTable = TreeState.computeChilds(root);
		BoardEvaluator.computeScore(lookupTable);
	}

	@Test
	void testLookupTable() {
		// Test the lookup table here even if its's another script but there is 1 function and it runs for those tests
		assertEquals(lookupTable.size(), 18753);
		
		// check the validity of a random child
		MiniBoard node = lookupTable.get("OX-X--XOX");
		
		MiniBoard neigh = node.getChild(-1, 2);
		assertEquals(neigh.hash, "OXOX--XOX");
		
		MiniBoard error = node.getChild(2, 0); // play an invalid move
		assertEquals(error.hash, node.hash);   // in that case, we return self as the move is invalid
	}

	@Test
	void testEvaluator() {
		MiniBoard node;
		
		node = lookupTable.get("OXOO-OXOX");
		assertEquals(-1, node.balanceEndstate); // only O can win
		assertEquals(2, node.totalEndstate);
		
		node = lookupTable.get("OXOOXOXOX");
		assertEquals(0, node.balanceEndstate); // only O can win
		assertEquals(1, node.totalEndstate);
		
		node = lookupTable.get("X--X--X--");
		assertEquals(1, node.balanceEndstate);
		assertEquals(1, node.totalEndstate);
		
		node = lookupTable.get("---------");
		assertEquals(0, node.balanceEndstate);  // game is even -- only because in ultimate 1 can play each position of the subboard
		assertEquals(58242432, node.totalEndstate);
	}
	
	@Test
	void testChilds() {
		MiniBoard node;
		
		node = lookupTable.get("OXOO-OXOX");
		assertEquals(8, node.getParents().size());
		assertEquals(2, node.getChilds().size());
		
		node = lookupTable.get("OXOOXOXOX");
		assertEquals(9, node.getParents().size());
		assertEquals(0, node.getChilds().size());
		
		node = lookupTable.get("X--X--X--");
		assertEquals(3, node.getParents().size());
		assertEquals(0, node.getChilds().size());
		
		node = lookupTable.get("---------");
		assertEquals(0, node.getParents().size());
		assertEquals(18, node.getChilds().size());
	}
	
}
