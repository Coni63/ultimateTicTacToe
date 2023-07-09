package agents;

import game.Pair;
import game.Board;

public abstract class AbstractAgent {
	public int team;
	public Board board;
	public String name;
	
	public AbstractAgent(int team, Board board)
	{
		this.team = team;
		this.board = board;
	}
	
	public abstract Pair<Integer, Integer> getMove();
	
	public String getName()
	{
		return this.name;
	}
}
