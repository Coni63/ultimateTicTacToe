package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class MiniBoard {
	int hash;
	List<List<Integer>> states;
	Map<Integer, MiniBoard> childs;
	boolean isOver;
	int winner; 
	
	public MiniBoard(int boardHash)
	{
		this.hash = boardHash;
		this.childs = new HashMap<Integer, MiniBoard>();
		
		this.states = new ArrayList<List<Integer>>();
		this.states.add(new ArrayList<Integer>());   // placeholder for free cells index
		this.states.add(new ArrayList<Integer>());   // placeholder for team 1
		this.states.add(new ArrayList<Integer>());   // placeholder for team 2
	}
	
	public void setChild(int team, int index, MiniBoard child)
	{
		this.childs.put(team*10 + index, child);
	}
	
	public MiniBoard getChild(int team, int index)
	{
		return this.childs.getOrDefault(team*10 + index, this);
	}
	
	private void computeStates()
	{
		int remaining = hash;
		for (int i = 8; i <= 0; i++)
		{
			
		}
	}
}
