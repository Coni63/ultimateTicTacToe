package game;

public class Converter {
	public static Position to_absolute(Position relativePosition)
	{
		return Converter.convert(relativePosition);
	}
	
	public static Position to_absolute(int subgrid, int index)
	{
		return Converter.convert(subgrid, index);
	}
	
	public static Position to_relative(Position absolutePosition)
	{
		return Converter.convert(absolutePosition);
	}
	
	public static Position to_relative(int row, int col)
	{
		return Converter.convert(row, col);
	}
	
	private static Position convert(Position p)
	{
		int a = 3 * (int)(p.col / 3) + (int)(p.row / 3);
		int b = 3 * (p.col % 3) + (p.col % 3);
		
		return new Position(a, b);
	}
	
	private static Position convert(int b, int a)
	{
		int c = 3 * (int)(b / 3) + (int)(a / 3);
		int d = 3 * (b % 3) + (a % 3);
		
		return new Position(c, d);
	}
}
