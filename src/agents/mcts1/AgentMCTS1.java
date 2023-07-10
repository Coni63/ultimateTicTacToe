package agents.mcts1;

import java.util.List;
import java.util.Random;

import agents.AbstractAgent;
import game.Board;
import game.Pair;

public class AgentMCTS1 extends AbstractAgent { 
		
	public AgentMCTS1(int team, Board board)
	{
		super(team, board);
		this.name = "MCTS_v1";
	}
	
	public Pair<Integer, Integer> getMove()
	{
		Random rand = new Random();
		List<Pair<Integer, Integer>> positions = this.board.getAvailablePosition();
		
		return positions.get(rand.nextInt(positions.size()));
		
	}
}
