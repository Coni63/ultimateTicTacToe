package game;

public class Converter {	
	public static Pair<Integer, Integer> to_absolute(int subgrid, int index)
	{
		return Converter.convert(subgrid, index);
	}
	
	public static Pair<Integer, Integer> to_relative(int row, int col)
	{
		return Converter.convert(row, col);
	}
	
	private static Pair<Integer, Integer> convert(int b, int a)
	{
		int c = 3 * (int)(b / 3) + (int)(a / 3);
		int d = 3 * (b % 3) + (a % 3);
		
		return new Pair<Integer, Integer>(c, d);
	}
}
