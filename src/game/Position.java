package game;

public class Position {
	public int row;
	public int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public static Position decode(int position) {
		return new Position((int)(position/10), position % 10);
	}
	
	public static int encode(Position p) {
		return 10 * p.row + p.col;
	}
}
