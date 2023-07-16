package renderer;

import game.Board;
import game.Pair;
import processing.core.PApplet;

public class BoardRenderer extends PApplet {
	final int width = 100;
	final int header = 150;
	String name1 = "Agent1";
	String name2 = "Agent2";
	int score1;
	int score2;
	int scoreTie;
	
	Board refBoard;
	
	public BoardRenderer(Board board) {		
		String[] processingArgs = {"MySketch"};
		PApplet.runSketch(processingArgs, this);
		
		this.refBoard = board;
	}
	
	public void settings() {
		// Configure your sketch's settings here
		size(11 * this.width, 11 * this.width + header);
	}
	
	public void setup() {
		frameRate(10);
		rectMode(CENTER);
	}
	
	public void draw() {
		// Render your sketch's visuals here
		background(0);
		
		this.makeGrid();
		this.makeHeader();

		for(Pair<Integer, Integer> p: this.refBoard.getPositionsWithState(-1)) {
			this.makeCross(p.first(), p.second());
		}
		
		for(Pair<Integer, Integer> p: this.refBoard.getPositionsWithState(1)) {
			this.makeCircle(p.first(), p.second());
		}
		
		for(Integer p: this.refBoard.getMajorPositionsWithState(-1)) {
			this.makeLargeCross((int)(p/3), p%3);
		}
		
		for(Integer p: this.refBoard.getMajorPositionsWithState(1)) {
			this.makeLargeCircle((int)(p/3), p%3);
		}
	}
	
	public void setHeaderInfo(String name1, String name2, int score1, int score2, int tie)
	{
		this.name1 = name1;
		this.name2 = name2;
		this.score1 = score1;
		this.score2 = score2;
		this.scoreTie = tie;
	}

	private void makeHeader()
	{
		fill(255);
		textSize(48);
		
		textAlign(RIGHT);
		text(this.name2, 10 * this.width, this.width); 
		text(this.score2, 10 * this.width, this.width + 48);
		_drawCross(10 * this.width - 100, this.width + 32, 12, 4);
		
		textAlign(CENTER);
		text("Tie", (int)(5.5 * this.width), this.width); 
		text(this.scoreTie, (int)(5.5 * this.width), this.width + 48); 
		
		textAlign(LEFT);
		text(this.name1, width, width);
		text(this.score1, width, width + 48); 
		_drawCircle(this.width + 100, this.width + 32, 12, 4);
	}
	
	private void makeGrid() {				
		stroke(255);
		
		// horizontal main lines
		for (int i = 0; i < 10; i++) {
			strokeWeight(i % 3 == 0 ? 8 : 4);
			line(this.width, this.width * (i+1) + header, this.width * 10,  this.width * (i + 1) + header);
			line(this.width * (i+1), this.width + header, this.width * (i + 1),  this.width * 10 + header);
		}
	}
	
	private void makeCross(int row, int col) {
		int offset = 10;		
		int center_y = (int)(this.width * (row + 1.5)) + header;
		int center_x = (int)(this.width * (col + 1.5));
		int width = (this.width - 2 * offset) / 2;
		
		_drawCross(center_x, center_y, width, 4);
	}
	
	private void makeCircle(int row, int col) {
		int offset = 10;		
		int center_y = (int) (this.width * (row + 1.5)) + header;
		int center_x =  (int) (this.width * (col + 1.5));
		int radius = (this.width - (2 * offset)) / 2;

		_drawCircle(center_x, center_y, radius, 4);
	}
	
	private void makeLargeCross(int row, int col) {
		this.addOpacity(row, col);
		
		int offset = 20;			
		int center_y = (int)(this.width * (3*row + 2.5)) + header;
		int center_x = (int)(this.width * (3*col + 2.5));
		int width = (3 * this.width - 2 * offset) / 2;
		
		_drawCross(center_x, center_y, width, 8);
	}
	
	private void makeLargeCircle(int row, int col) {
		this.addOpacity(row, col);

		int offset = 20;
		int center_y = (int) (this.width * (3*row + 2.5)) + header;
		int center_x =  (int) (this.width * (3*col + 2.5));
		int radius = (this.width * 3 - (2 * offset)) / 2;

		_drawCircle(center_x, center_y, radius, 8);
	}
	
	private void addOpacity(int row, int col)
	{
		fill(0, 0, 0, 192);
		noStroke();
		
		int center_y = (int) (this.width * (3*row + 2.5)) + header;
		int center_x =  (int) (this.width * (3*col + 2.5));
		
		rect(center_x, center_y, 3*this.width - 8, 3*this.width - 8);
	}
	
	public void keyPressed() {
	    if (keyCode == ESC) {
	    	exit(); // Terminate the sketch and stop the program
	    }
	}
	
	private void _drawCross(int centerX, int centerY, int halfSize, int weight)
	{
		stroke(161, 161, 14);
		strokeWeight(weight);
		
		line(centerX - halfSize, centerY - halfSize, centerX + halfSize, centerY + halfSize);
		line(centerX - halfSize, centerY + halfSize, centerX + halfSize, centerY - halfSize);
	}
	
	private void _drawCircle(int centerX, int centerY, int radius, int weight)
	{
		stroke(14, 161, 161);
		strokeWeight(weight);
		noFill();
		
		ellipse(centerX, centerY, 2 * radius, 2 * radius);
	}
}