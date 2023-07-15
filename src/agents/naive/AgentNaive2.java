package agents.naive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import agents.AbstractAgent;
import game.Board;
import game.Pair;

public class AgentNaive2 extends AbstractAgent { 
		
	public AgentNaive2(int team, Board board)
	{
		super(team, board);
		this.name = "Naive_v2";
	}
	
	public Pair<Integer, Integer> getMove()
	{
		List<Pair<Integer, Integer>> bestMoves = new ArrayList<Pair<Integer, Integer>>();
		float bestScore = 10f;
		for (Pair<Integer, Integer> position: this.board.getAvailablePosition())
		{
			Board copy = board.Clone();
			copy.play(position.first(), position.second(), team);
			
			float stats = Math.abs(team - copy.getStats());
 			if (stats < bestScore)
			{
 				bestScore = stats;
 				bestMoves.clear();
 				bestMoves.add(position);
			}
 			else if (stats == bestScore)
 			{
 				bestMoves.add(position);
 			}
		}
		
		Random rand = new Random();
		return bestMoves.get(rand.nextInt(bestMoves.size()));	
		
	}
}
