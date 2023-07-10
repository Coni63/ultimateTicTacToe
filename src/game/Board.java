package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Board {
	private MiniBoard refRoot;        // reference to the root of the state tree (empty Board) -- required for reset
	
	private MiniBoard[] subBoard;     // array of TicTacToe Board
	MiniBoard mainBoard;                  // TicTacToe of the main board
	Pair<Integer, Integer> lastMove;  // store the last move in relative state (subboard, index played)
	int boardCompleted = 0;
	
	
	public Board(MiniBoard emptyBoard)
	{
		/*
		 * Initialize an empty board game that refer the root of the board state tree
		 * */
		this.refRoot = emptyBoard;
		this.subBoard = new MiniBoard[9];
		
		reset();
	}
	
	public void play(int subgrid, int index, int team)
	{
		/*
		 * Update the state of the played miniboard
		 * Update the main board if the subboard is won
		 * However there is a bug for the main board. The state of the subboard can be "no winner" = -1.
		 * And there is no state for the MiniBoard in that case
		 * This is handled with a custom Exception in getAvailablePosition
		 * */
		this.subBoard[subgrid] = this.subBoard[subgrid].getChild(team, index);
		
		if (this.subBoard[subgrid].isOver)
		{
			// update the mainboard
			this.mainBoard = this.mainBoard.getChild(team, subgrid);
			this.boardCompleted++;
		}
		
		lastMove = new Pair<Integer, Integer>(subgrid, index);
	}
	
	public int winner()
	{
		return this.mainBoard.winner;
	}
	
	public boolean isOver()
	{
		// the main board can be mentionned as not over even if there is no move. 
		// this is because the related subgrid can be over but not won
		return this.mainBoard.isOver || this.boardCompleted == 9;
	}
	
	public void reset()
	{		
		/*
		 * Set or reset the board to a new state
		 * */
		for (int i = 0; i < 9; i++)
		{
			this.subBoard[i] = this.refRoot;
		}
		
		this.mainBoard = this.refRoot;
		this.lastMove = new Pair<Integer, Integer>(-1, -1);
		this.boardCompleted = 0;
	}
	
	public List<Pair<Integer, Integer>> getAvailablePosition()
	{
		int previousIndex = lastMove.second();
		List<Pair<Integer, Integer>> positions = new LinkedList<Pair<Integer, Integer>>();

		if (previousIndex == -1 || this.subBoard[previousIndex].isOver)
		{
			for (int subboard = 0; subboard < 9; subboard++)
			{				
				for (int index: this.subBoard[subboard].getIndexWithStates(0))
				{
					positions.add(new Pair(subboard, index));
				}
			}			
		}
		else
		{
			for (int index: this.subBoard[previousIndex].getIndexWithStates(0))
			{
				positions.add(new Pair(previousIndex, index));
			}
		}
		
		return positions;
	}
	
	public List<Pair<Integer, Integer>> getPositionsWithState(int state) {
		/*
		 * Return a list of relative positions of a given state
		 * Required for the renderer
		 * */
		List<Pair<Integer, Integer>> positions = new ArrayList<Pair<Integer, Integer>>();
		for (int subgrid = 0; subgrid < 9; subgrid++)
		{
			for (int index: this.subBoard[subgrid].getIndexWithStates(state))
			{
				positions.add(Converter.toAbsolute(subgrid, index));
			}
		}
		
		return positions;
	}
	
	public List<Integer> getMajorPositionsWithState(int state) {
		/*
		 * Return a list of relative positions of a given state
		 * Required for the renderer
		 * */
		return this.mainBoard.getIndexWithStates(state);
	}
}
