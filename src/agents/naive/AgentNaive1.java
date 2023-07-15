package agents.naive;

import java.util.List;
import java.util.Random;

import agents.AbstractAgent;
import game.Board;
import game.Pair;

public class AgentNaive1 extends AbstractAgent { 
		
	public AgentNaive1(int team, Board board)
	{
		super(team, board);
		this.name = "Naive_v1";
	}
	
	public Pair<Integer, Integer> getMove()
	{
		Pair<Integer, Integer> bestMove = new Pair<Integer, Integer>(0, 0);
		int bestScore = -1;
		for (Pair<Integer, Integer> position: this.board.getAvailablePosition())
		{
			Board copy = board.Clone();
			copy.play(position.first(), position.second(), team);
			
			int captured = copy.getMajorPositionsWithState(team).size();
 			if (captured > bestScore)
			{
 				bestScore = captured;
 				bestMove = position;
			}
		}
		
		return bestMove;
		
	}
}
