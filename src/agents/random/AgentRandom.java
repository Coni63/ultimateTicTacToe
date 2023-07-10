package agents.random;

import java.util.List;
import java.util.Random;

import agents.AbstractAgent;
import game.Board;
import game.Pair;


public class AgentRandom extends AbstractAgent {
		
	public AgentRandom(int team, Board board)
	{
		super(team, board);
		this.name = "Random_v1";
	}
	
	public Pair<Integer, Integer> getMove()
	{
		Random rand = new Random();
		
		List<Pair<Integer, Integer>> positions = this.board.getAvailablePosition();
		return positions.get(rand.nextInt(positions.size()));			

	}
}
