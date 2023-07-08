package game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class MemoryTable {
	
	public static void computeChilds(MiniBoard root)
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
		Map<String, MiniBoard> lookupTable = new HashMap<String, MiniBoard>();
		
		Set<String> done = new HashSet<String>();
		Queue<MiniBoard> to_process = new LinkedList<MiniBoard>();
		to_process.add(root);
		lookupTable.put(root.hash, root);
		
		while (!to_process.isEmpty())
		{
			MiniBoard currentBoard = to_process.remove();
			
			// if we already explored this state, skip
			if (done.contains(currentBoard.hash)) {
				continue;
			}
			
			if (currentBoard.isOver)
			{
				continue;
			}
			
			// compute every childs
			for (int team = 1; team < 3; team++)
			{
				for (int index = 0; index < 9; index++)
				{
					if (currentBoard.grid[index] == 0) {             // if the position is free
						
						int[] newGrid = new int[9];
						System.arraycopy(currentBoard.grid, 0, newGrid, 0, 9);
						newGrid[index] = team;
						
						String newHash = MiniBoard.getHashFromArray(newGrid);
						if (!lookupTable.containsKey(newHash)) {
							lookupTable.put(newHash, new MiniBoard(newGrid));
						}
						
						to_process.add(lookupTable.get(newHash));
						currentBoard.setChild(team, index, lookupTable.get(newHash));
					}
				}	
			}
			
			done.add(currentBoard.hash);
		}
		
		System.out.println(lookupTable.size());
	}
	
}
