package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Board {
	MiniBoard[] grids;
	MiniBoard major;
	MemoryTable memory;
	Pair<Integer, Integer> lastMove;
	private MiniBoard refRoot;
	
	public Board(MiniBoard emptyBoard)
	{
		this.refRoot = emptyBoard;
		this.grids = new MiniBoard[9];
		
		reset();
	}
	
	public void play(int subgrid, int index, int team)
	{
		/*
		 * Update the state of this grid
		 * */
		this.grids[subgrid] = this.grids[subgrid].getChild(team, index);
		
		if (this.grids[subgrid].winner > 0)
		{
			this.major = this.major.getChild(this.grids[subgrid].winner, subgrid);
		}
		
		lastMove = new Pair<Integer, Integer>(subgrid, index);
	}
	
	public int winner()
	{
		return this.major.winner;
	}
	
	public void reset()
	{		
		for (int i = 0; i < 9; i++)
		{
			this.grids[i] = this.refRoot;
		}
		
		this.major = this.refRoot;
		this.lastMove = new Pair<Integer, Integer>(-1, -1);
	}
	
	public List<Pair<Integer, Integer>> getAvailablePosition()
	{
		int previousIndex = lastMove.second();
		List<Pair<Integer, Integer>> positions = new LinkedList<Pair<Integer, Integer>>();

		if (previousIndex == -1 || this.grids[previousIndex].isOver)
		{
			for (int subboard = 0; subboard < 9; subboard++)
			{
				for (int index: this.grids[subboard].getIndexWithStates(0))
				{
					positions.add(new Pair(subboard, index));
				}
			}			
		}
		else
		{
			for (int index: this.grids[previousIndex].getIndexWithStates(0))
			{
				positions.add(new Pair(previousIndex, index));
			}
		}
		
		return positions;
	}
	
	public boolean isOver()
	{
		return this.major.isOver;
	}
	
	public List<Pair<Integer, Integer>> getPositionsWithState(int state) {
		/*
		 * Return a list of relative positions of a given state
		 * */
		List<Pair<Integer, Integer>> positions = new ArrayList<Pair<Integer, Integer>>();
		for (int subgrid = 0; subgrid < 9; subgrid++)
		{
			for (int index: this.grids[subgrid].getIndexWithStates(state))
			{
				positions.add(Converter.toAbsolute(subgrid, index));
			}
		}
		
		return positions;
	}
	
	public List<Integer> getMajorPositionsWithState(int state) {
		/*
		 * Return a list of relative positions of a given state
		 * */
		
		return this.major.getIndexWithStates(state);
	}
}
