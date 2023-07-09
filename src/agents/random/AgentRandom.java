package agents.random;

import java.util.List;
import java.util.Random;

import agents.AbstractAgent;
import game.Board;
import game.GameOverException;
import game.Pair;


public class AgentRandom extends AbstractAgent {
		
	public AgentRandom(int team, Board board)
	{
		super(team, board);
		this.name = "Random_v1";
	}
	
	public Pair<Integer, Integer> getMove() throws GameOverException
	{
		Random rand = new Random();
		
		try
		{
			List<Pair<Integer, Integer>> positions = this.board.getAvailablePosition();
			return positions.get(rand.nextInt(positions.size()));			
		}
		catch (GameOverException e)
		{
			System.out.println("flkjdgsh");
			throw e;
		}
		
		
	}
}
