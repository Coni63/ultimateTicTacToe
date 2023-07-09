package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class MiniBoard {
	List<List<Integer>> states;      // list of index with every states
	Map<Integer, MiniBoard> childs;  // Dictionary of moves ->  Board state
	boolean isOver;
	int winner; 
	int[] grid;                      // values of the grid flatten
	public String hash;              // simple hash used to handle duplicated State
	
	public MiniBoard(int[] grid)
	{
		// Class representing a TicTacToe Board
		// This class holds childs states depending on the move done
		this.grid = grid;
		this.hash = MiniBoard.getHashFromArray(grid);
		this.initBoard();
	}
	
	public MiniBoard()
	{
		this.grid = new int[9];
		this.hash = "000000000";
		this.initBoard();
	}
	
	private void initBoard()
	{
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
		// save the next state knowing the move done
		this.childs.put(team*10 + index, child);
	}
	
	public MiniBoard getChild(int team, int index)
	{
		// get the next state knowing the move done
		return this.childs.getOrDefault(team*10 + index, this);
	}
	
	public List<Integer> getIndexWithStates(int team)
	{
		// return all the index having a given value
		return states.get(team);
	}
		
	private void computeStates()
	{
		// compute the index of every values in the array
		// faster for the bot later on
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
		// check if the board in a leaf state or not
        if (this.isWinner(1)) 
        {
        	this.winner = 1;
        	this.isOver = true;
        	this.states.get(0).clear();
        }
        else if (this.isWinner(2)) 
        {
        	this.winner = 2;
        	this.isOver = true;
        	this.states.get(0).clear();
        }
        else if (this.states.get(0).size() == 0)  // board full with no winner
		{
        	this.winner = 0;
			this.isOver = true;
		}
        else  // board still playable
        {        	
        	this.winner = 0; 
        	this.isOver = false;
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
