package ultimateTicTacToe;

import game.Board;
import game.TreeState;
import game.MiniBoard;
import renderer.BoardRenderer;
import agents.mcts1.AgentMCTS1;
import agents.naive.AgentNaive1;
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
		
		AgentMCTS1 agent1 = new AgentMCTS1(1, board);
		AgentNaive1 agent2 = new AgentNaive1(-1, board);
		
		Simulator simulator = new Simulator(board, agent1, agent2, renderer);
		
		simulator.run(300, 10);
	}

}
