package game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class MemoryTable {
	
	public Map<Integer, MiniBoard> lookupTable;
	
	
	public MemoryTable()
	{
		long start1 = System.nanoTime();
		this.computeMapping();
		long end1 = System.nanoTime();      
        System.out.println("Generated the mapping in "+ (end1-start1)/1000000 + "ms.");      
	}
	
	private void computeMapping()
	{	
		/*
		 * Create a Table and compute every grid combinations (3^9)
		 * A grid is a 9 digit number containing the team at each position
		 * A move is the team (1 or 2) and the index played (0 to 8)
		 * A move is 10 * team + index
		 * can be seen a {
		 * 	0: {
		 * 		10: 100000000    # the team 1 play in index 0. On an empty board, the result is a board 100000000
		 * 		... and this for all the 18 moves (if valid)
		 * 	}
		 * }
		 * */
		this.lookupTable = new HashMap<Integer, MiniBoard>();
		
		Queue<Integer> to_process = new LinkedList<Integer>();
		to_process.add(0);
		
		while (!to_process.isEmpty())
		{
			int boardState = to_process.remove();
			
			// if we already explored this state, skip
			if (this.lookupTable.containsKey(boardState)) {
				continue;
			}
			
			// create the dictionary for the results
			MiniBoard miniBoard = new MiniBoard(boardState);
			this.lookupTable.put(boardState, miniBoard);
			
			// compute every childs
			for (int team = 1; team < 3; team++)
			{
				for (int index = 0; index < 9; index++)
				{
					int offset = (int)Math.pow(10, 8-index);                // position of the team play
					if ((int)(boardState / offset) % 10 == 0) {             // if the position is free
						int newBoardState = boardState + offset * team;     // create the new state
						MiniBoard nextMiniBoard = new MiniBoard(newBoardState);
						int move = team * 10 + index;                       // compute the equivalent move value
						miniBoard.setChild(team, index, nextMiniBoard);
						
						to_process.add(newBoardState);                      // we need to explore the new state 
					}
				}	
			}
		}
	}
	
	public MiniBoard getGrid(int fromGrid, int index, int team)
	{
		/*
		 * Return the new grid encoded based on the state of the board, the player and the index played
		 * */
		return this.lookupTable.get(fromGrid).getChild(team, index);
	}
	
	
}
