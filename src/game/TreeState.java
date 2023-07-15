package game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class TreeState {
	
	public static Map<String, MiniBoard> computeChilds(MiniBoard root)
	{	
		// For each miniBoard, compute all childs.
		// Multiple moves combinations can lead to the same state so a lookup table is created
		
		Map<String, MiniBoard> lookupTable = new HashMap<String, MiniBoard>();  // mapping for a state -> instance to avoid duplicates
		lookupTable.put(root.hash, root);

		Set<String> done = new HashSet<String>();  // store all the states processed in term of exploration
		
		Queue<MiniBoard> to_process = new LinkedList<MiniBoard>();  // queue of boards to evaluate -- BFS 
		to_process.add(root);
		
		int[] teams = new int[] {-1, 1};
		
		while (!to_process.isEmpty())
		{
			MiniBoard currentBoard = to_process.remove();
			
			// if we already explored this state or the board is an end state
			if (currentBoard.isOver || done.contains(currentBoard.hash)) {
				continue;
			}
			
			// compute every childs
			for (int team: teams)
			{
				for (int index = 0; index < 9; index++)
				{
					if (currentBoard.grid[index] == 0) {  // if the position is free
						
						int[] newGrid = new int[9];
						System.arraycopy(currentBoard.grid, 0, newGrid, 0, 9);
						newGrid[index] = team;
						
						String newHash = MiniBoard.getHashFromArray(newGrid);
						if (!lookupTable.containsKey(newHash)) {
							lookupTable.put(newHash, new MiniBoard(newGrid));
						}
						
						MiniBoard child = lookupTable.get(newHash);
						child.addParent(currentBoard);
						currentBoard.setChild(team, index, child);
						to_process.add(child);
					}
				}
			}
			
			done.add(currentBoard.hash);
		}
		
		return lookupTable;
		// System.out.println(lookupTable.size());
	}
	
}
