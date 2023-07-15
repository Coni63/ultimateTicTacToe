package ultimateTicTacToe;

import agents.AbstractAgent;
import game.Board;
import game.Pair;
import renderer.BoardRenderer;

public class Simulator {
	Board board;
	AbstractAgent agent1;
	AbstractAgent agent2;
	BoardRenderer renderer;
	int scoreAgent1 = 0;
	int scoreAgent2 = 0;
	int scoreTie = 0;
	
	public Simulator(Board board, AbstractAgent agent1, AbstractAgent agent2, BoardRenderer renderer)
	{
		this.board = board;
		this.agent1 = agent1;
		this.agent2 = agent2;
		this.renderer = renderer;

		this.renderer.setHeaderInfo(agent1.getName(), agent2.getName(), scoreAgent1, scoreAgent2, scoreTie);		
	}
	
	public void run(int n, int timestepMs)
	{
		// run infinite number of games to evaluate bots in multiple games
		
		for (int i = 0; i < n; i++) {  // on joue des parties à l'infini
			int winner = this.playGame(timestepMs);
			
			if (winner == agent1.team)
			{
				this.scoreAgent1++;
			}
			else if (winner == agent2.team)
			{
				this.scoreAgent2++;
			}
			else if (winner == 0)
			{
				this.scoreTie++;
			}
			this.renderer.setHeaderInfo(agent1.getName(), agent2.getName(), scoreAgent1, scoreAgent2, scoreTie);			
			this.board.reset();
		}
	}
	
	public int playGame(int timestepMs)
	{
		// run 1 game until end of game
		AbstractAgent currentAgent = agent1;
		while (true) {   // on joue tant qu'il y a des moves possibles
			try {
				if (this.board.isOver())
				{
					return this.board.winner();
				}
				
				currentAgent = currentAgent == this.agent1 ? this.agent2 : this.agent1;
				
				Pair<Integer, Integer> chosenPosition = currentAgent.getMove();
				this.board.play(chosenPosition.first(), chosenPosition.second(), currentAgent.team);					
				
				Thread.sleep(timestepMs); // Wait for 1 second (1000 milliseconds)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}
