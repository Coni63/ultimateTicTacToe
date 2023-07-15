package game;

import java.util.Map;

public class BoardEvaluator {
	public static void computeScore(Map<String, MiniBoard> lookupTable)
	{
		for(MiniBoard board: lookupTable.values())
		{
			if (board.isOver)
			{
				backpropagate(board, board.winner);
			}
		}
	}
	
	private static void backpropagate(MiniBoard child, int winner)
	{
		child.setStats(winner);
		for (MiniBoard parent: child.getParents())
		{
			backpropagate(parent, winner);
		}
	}
}
