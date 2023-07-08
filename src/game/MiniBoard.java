package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class MiniBoard {
	List<List<Integer>> states;
	Map<Integer, MiniBoard> childs;
	boolean isOver;
	int winner; 
	int[] grid;
	public String hash;
	
	public MiniBoard(int[] grid)
	{
		this.grid = grid;
		this.hash = MiniBoard.getHashFromArray(grid);
		
		this.childs = new HashMap<Integer, MiniBoard>();
		
		this.computeStates();
		this.computeWinner();
	}
	
	public MiniBoard()
	{
		this.grid = new int[9];
		this.childs = new HashMap<Integer, MiniBoard>();
				
		this.computeStates();
		this.computeWinner();
	}
	
	public static String getHashFromArray(int[] grid)
	{
		return Arrays.stream(grid)
        .mapToObj(String::valueOf)
        .reduce((a, b) -> a.concat(b))
        .get();
	}
	
	public void setChild(int team, int index, MiniBoard child)
	{
		this.childs.put(team*10 + index, child);
	}
	
	public MiniBoard getChild(int team, int index)
	{
		return this.childs.getOrDefault(team*10 + index, this);
	}
	
	public List<Integer> getIndexWithStates(int team)
	{
		return states.get(team);
	}
		
	private void computeStates()
	{
		this.states = new ArrayList<List<Integer>>();
		this.states.add(new ArrayList<Integer>());   // placeholder for free cells index
		this.states.add(new ArrayList<Integer>());   // placeholder for team 1
		this.states.add(new ArrayList<Integer>());   // placeholder for team 2
		
		for (int i = 0; i < 9; i++)
		{
			this.states.get(this.grid[i]).add(i);
		}
	}
	
	private void computeWinner()
	{
		// if there is no remaining spaces
		if (this.states.get(0).size() == 0)
		{
			this.isOver = true;
		}
		
		this.winner = -1; // no winner
		this.isOver = false;
		// check rows
        if (this.isWinner(1)) 
        {
        	this.winner = 1;
        	this.isOver = true;
        }
        else if (this.isWinner(2)) 
        {
        	this.winner = 2;
        	this.isOver = true;
        }
        else if (this.states.get(0).size() == 0)
		{
			this.isOver = true;
			this.winner = 0;
		}
        
	}
	
	private boolean isWinner(int player) {
		// check rows
        if (this.grid[0] == player && this.grid[1] == player && this.grid[2] == player) { return true; }
        if (this.grid[3] == player && this.grid[4] == player && this.grid[5] == player) { return true; }
        if (this.grid[6] == player && this.grid[7] == player && this.grid[8] == player) { return true; }

        // check columns
        if (this.grid[0] == player && this.grid[3] == player && this.grid[6] == player) { return true; }
        if (this.grid[1] == player && this.grid[4] == player && this.grid[7] == player) { return true; }
        if (this.grid[2] == player && this.grid[5] == player && this.grid[8] == player) { return true; }

        // check diags
        if (this.grid[0] == player && this.grid[4] == player && this.grid[8] == player) { return true; }
        if (this.grid[6] == player && this.grid[4] == player && this.grid[2] == player) { return true; }

        return false;
	}
}
