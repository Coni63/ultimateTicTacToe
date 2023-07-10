package ultimateTicTacToe;

import agents.AbstractAgent;
import game.Board;
import game.GameOverException;
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
	
	public void run()
	{
		// run infinite number of games to evaluate bots in multiple games
		
		while (true) {  // on joue des parties à l'infini
			int winner = this.playGame();
			
			if (winner == 1)
			{
				this.scoreAgent1++;
			}
			else if (winner == 2)
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
	
	public int playGame()
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
				
				Thread.sleep(1); // Wait for 1 second (1000 milliseconds)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}
