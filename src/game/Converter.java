package game;

public class Converter {	
	public static Pair<Integer, Integer> toAbsolute(int subgrid, int index)
	{
		// Convert a position from (subgrid, index) to (row, col)
		return Converter.convert(subgrid, index);
	}
	
	public static Pair<Integer, Integer> toRelative(int row, int col)
	{
		// Convert a position from (row, col) to (subgrid, index)
		return Converter.convert(row, col);
	}
	
	private static Pair<Integer, Integer> convert(int b, int a)
	{
		int c = 3 * (int)(b / 3) + (int)(a / 3);
		int d = 3 * (b % 3) + (a % 3);
		
		return new Pair<Integer, Integer>(c, d);
	}
}
