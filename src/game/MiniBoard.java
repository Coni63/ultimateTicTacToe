package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class MiniBoard {
	private Map<Integer, List<Integer>> states; // list of index with every states
	private Map<Integer, MiniBoard> childs;     // Dictionary of moves ->  Board state
	private List<MiniBoard> parents;            // list 
	public boolean isOver;
	public int winner; 
	public int[] grid;                          // values of the grid flatten
	public String hash;                         // simple hash used to handle duplicated State
	public int balanceEndstate;                 // sum of winner - if balance 0, 
	public int totalEndstate;                   // total of possible endstates from this states
	
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
		this.hash = getHashFromArray(this.grid);
		this.initBoard();
	}
	
	private void initBoard()
	{
		this.childs = new HashMap<Integer, MiniBoard>();
		this.parents = new ArrayList<MiniBoard>();
		this.computeStates();
		this.computeWinner();
		
		this.balanceEndstate = 0;
		this.totalEndstate = 0;
	}
	
	public static String getHashFromArray(int[] grid)
	{
		return Arrays.stream(grid)
        .mapToObj(x -> x == 1 ? "X" : x == -1 ? "O" : "-")
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
	
	public Collection<MiniBoard> getChilds()
	{
		// get the next state knowing the move done
		return this.childs.values();
	}
	
	public void addParent(MiniBoard parent)
	{
		this.parents.add(parent);
	}
	
	public List<MiniBoard> getParents()
	{
		return this.parents;
	}
	
	public void setStats(int winner)
	{
		this.balanceEndstate += winner;
		this.totalEndstate++;
	}
	
	public float getStats()
	{
		return (float)this.balanceEndstate / (float)this.totalEndstate;
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
		this.states = new HashMap<Integer, List<Integer>>();
		this.states.put(0, new ArrayList<Integer>());   // placeholder for free cells index
		this.states.put(1, new ArrayList<Integer>());   // placeholder for team 1
		this.states.put(-1, new ArrayList<Integer>());   // placeholder for team 2
		
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
        else if (this.isWinner(-1)) 
        {
        	this.winner = -1;
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
