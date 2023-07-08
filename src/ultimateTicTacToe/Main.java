package ultimateTicTacToe;

import game.Board;
import game.MemoryTable;
import game.MiniBoard;
import renderer.BoardRenderer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main");
		
		MiniBoard root = new MiniBoard();
		
		long start1 = System.nanoTime();
		MemoryTable.computeChilds(root);
		long end1 = System.nanoTime();      
        System.out.println("Generated the mapping in "+ (end1-start1)/1000000 + "ms.");      
		
		
		
		Board board = new Board(root);
		
		board.play(0, 0, 1);
		board.play(4, 0, 2);
		board.play(8, 8, 2);
		board.play(7, 2, 2);
		
		BoardRenderer renderer = new BoardRenderer(board);
		
		while (true) {
			try {
		        Thread.sleep(1000); // Wait for 1 second (1000 milliseconds)
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
		
	}

}
