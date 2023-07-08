package game;

import java.util.ArrayList;
import java.util.List;


public class Board {
	MiniBoard[] grids;
	MiniBoard major;
	MemoryTable memory;
	
	public Board(MiniBoard emptyBoard) {
		this.grids = new MiniBoard[9];
		
		for (int i = 0; i < 9; i++)
		{
			this.grids[i] = emptyBoard;
		}
		
		this.major = emptyBoard;
	}
	
	public void play(int subgrid, int index, int team)
	{
		/*
		 * Update the state of this grid
		 * */
		this.grids[subgrid] = this.grids[subgrid].getChild(team, index);
	}
	
	public List<Pair<Integer, Integer>> getPositionsWithState(int state) {
		/*
		 * Return a list of relative positions of a given state
		 * */
		List<Pair<Integer, Integer>> positions = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < 9; i++)
		{
			for (int index: this.grids[i].getIndexWithStates(state))
			{
				positions.add(Converter.to_absolute(i, index));
			}
		}
		
		return positions;
	}
}
