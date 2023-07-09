package ultimateTicTacToe;

import java.util.List;
import java.util.Random;

import game.Board;
import game.MemoryTable;
import game.MiniBoard;
import game.Pair;
import renderer.BoardRenderer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		long start1 = System.nanoTime();
		MiniBoard root = new MiniBoard();
		MemoryTable.computeChilds(root);
		long end1 = System.nanoTime();      
        System.out.println("Tree generated in "+ (end1-start1)/1000000 + "ms.");      
		
		Board board = new Board(root);
		BoardRenderer renderer = new BoardRenderer(board);
		
		Random rand = new Random();
		int team = 1;
		int lastIndex = -1;
		while (true) {
			team = 3 - team;
			try {
				if (board.isOver())
				{
					break;
				}
				
				List<Pair<Integer, Integer>> positions = board.getAvailablePosition(lastIndex);
				
				if (positions.size() == 0)
				{
					break;
				}
				
				Pair<Integer, Integer> chosenPosition = positions.get(rand.nextInt(positions.size()));
				
			    board.play(chosenPosition.first(), chosenPosition.second(), team);
			    lastIndex = chosenPosition.second();
		        Thread.sleep(500); // Wait for 1 second (1000 milliseconds)
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
		
	}

}
