package ultimateTicTacToe;

import game.Board;
import game.BoardEvaluator;
import game.TreeState;
import game.MiniBoard;
import renderer.BoardRenderer;

import java.util.Map;

import agents.mcts1.AgentMCTS1;
import agents.naive.AgentNaive1;
import agents.naive.AgentNaive2;
import agents.random.AgentRandom;

public class Main {

	public static void main(String[] args) {
		
		long start1 = System.nanoTime();
		MiniBoard root = new MiniBoard();
		Map<String, MiniBoard> lookupTable = TreeState.computeChilds(root);
		BoardEvaluator.computeScore(lookupTable);
		long end1 = System.nanoTime();      
        System.out.println("Tree generated in "+ (end1-start1)/1000000 + "ms.");      
		
		Board board = new Board(root);
		BoardRenderer renderer = new BoardRenderer(board);
		
		AgentNaive1 agent1 = new AgentNaive1(1, board);
		AgentNaive2 agent2 = new AgentNaive2(-1, board);
		
		Simulator simulator = new Simulator(board, agent1, agent2, renderer);
		
		simulator.run(300, 1);
	}

}
