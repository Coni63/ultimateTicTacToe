package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.MemoryTable;

public class Board {
	int[] grids;
	MemoryTable memory;
	
	public Board(MemoryTable refTable) {
		this.grids = new int[9];
		this.memory = refTable;
	}
	
	public void setState(int subgrid, int index, int state)
	{
		/*
		 * Update the state of this grid
		 * */
		this.grids[subgrid] = this.memory.getGrid(this.grids[subgrid], index, state).hash;
	}
	
	public List<Position> getPositionsWithState(int state) {
		/*
		 * Return a list of relative positions of a given state
		 * */
		List<Position> positions = new ArrayList<Position>();
		int value;
		int remaining;
		for (int i = 0 ; i < 9; i++)  // iterate over main boards
		{
			remaining = grids[i];
			for (int j = 8 ; j >= 0; j--) // iterate over the position in the sub-board
			{
				value = remaining % 10;
				remaining = (int) (remaining/10);
				if (value == state)
				{
					positions.add(Converter.to_absolute(i, j));
				}
			}
		}
		
		return positions;
	}
}
