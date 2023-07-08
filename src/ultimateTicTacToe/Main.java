package ultimateTicTacToe;

import game.Board;
import game.MemoryTable;
import renderer.BoardRenderer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main");
		
		MemoryTable table = new MemoryTable();
		
		
		System.out.println(table.lookupTable.size());
		
		System.out.println(table.getGrid(0, 8, 2));
		System.out.println(table.getGrid(120022111, 8, 2));
		System.out.println(table.getGrid(120022111, 2, 1));
		System.out.println(table.getGrid(0, 0, 1));
		
		
		Board board = new Board(table);
		
		board.setState(0, 0, 1);
		board.setState(4, 0, 2);
		board.setState(8, 8, 2);
		board.setState(7, 2, 2);
		
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
