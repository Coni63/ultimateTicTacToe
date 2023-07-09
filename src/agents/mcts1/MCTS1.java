package agents.mcts1;

import java.util.List;
import java.util.Random;

import agents.AbstractAgent;
import game.Board;
import game.Pair;

public class MCTS1 extends AbstractAgent { 
		
	public MCTS1(int team, Board board)
	{
		super(team, board);
		this.name = "MCTS_v1";
	}
	
	public Pair<Integer, Integer> getMove()
	{
		Random rand = new Random();
		List<Pair<Integer, Integer>> positions = this.board.getAvailablePosition();
		
		if (positions.size() == 0)
		{
			return new Pair<Integer, Integer>(-1, -1);
		}
		
		return positions.get(rand.nextInt(positions.size()));
		
	}
}
