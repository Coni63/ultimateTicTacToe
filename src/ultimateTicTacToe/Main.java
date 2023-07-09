package ultimateTicTacToe;

import game.Board;
import game.TreeState;
import game.MiniBoard;
import renderer.BoardRenderer;

import agents.random.AgentRandom;

public class Main {

	public static void main(String[] args) {
		
		long start1 = System.nanoTime();
		MiniBoard root = new MiniBoard();
		TreeState.computeChilds(root);
		long end1 = System.nanoTime();      
        System.out.println("Tree generated in "+ (end1-start1)/1000000 + "ms.");      
		
		Board board = new Board(root);
		BoardRenderer renderer = new BoardRenderer(board);
		
		AgentRandom agent1 = new AgentRandom(1, board);
		AgentRandom agent2 = new AgentRandom(2, board);
		
		Simulator simulator = new Simulator(board, agent1, agent2, renderer);
		
		simulator.run();
	}

}
