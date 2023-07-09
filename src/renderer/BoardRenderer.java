package renderer;

import game.Board;
import game.Pair;
import processing.core.PApplet;

public class BoardRenderer extends PApplet {
	int width = 100;
	Board refBoard;
	
	public BoardRenderer(Board board) {		
		String[] processingArgs = {"MySketch"};
		PApplet.runSketch(processingArgs, this);
		
		this.refBoard = board;
	}
	
	public void setup() {
		frameRate(10);
		rectMode(CENTER);
	}
	
	public void settings() {
		// Configure your sketch's settings here
		size(11 * width, 11 * width);
	}
	
	public void draw() {
		// Render your sketch's visuals here
		background(0);
		
		makeGrid();
		
		
		for(Pair<Integer, Integer> p: this.refBoard.getPositionsWithState(2)) {
			makeCross(p.first(), p.second());
		}
		
		for(Pair<Integer, Integer> p: this.refBoard.getPositionsWithState(1)) {
			makeCircle(p.first(), p.second());
		}
		
		
		for(Integer p: this.refBoard.getMajorPositionsWithState(2)) {
			makeLargeCross((int)(p/3), p%3);
		}
		
		for(Integer p: this.refBoard.getMajorPositionsWithState(1)) {
			makeLargeCircle((int)(p/3), p%3);
		}
		
	}
	
	private void makeGrid() {		
		background(0);
		
		stroke(255);
		
		// horizontal main lines
		for (int i = 0; i < 10; i++) {
			strokeWeight(i % 3 == 0 ? 8 : 4);
			line(this.width, this.width * (i+1), this.width * 10,  this.width * (i + 1));
			line(this.width * (i+1), this.width, this.width * (i + 1),  this.width * 10);
		}
	}
	
	private void makeCross(int row, int col) {
		int offset = 10;
		
		stroke(161, 161, 14);
		strokeWeight(4);
		
		int top = this.width * (row+1) + offset;
		int left =  this.width * (col+1) + offset;
		
		line(left, top, left + this.width - (2 * offset), top + this.width - (2 * offset));
		line(left, top + this.width - (2 * offset), left + this.width - (2 * offset), top);
	}
	
	private void makeCircle(int row, int col) {
		int offset = 10;
		
		stroke(14, 161, 161);
		strokeWeight(4);
		noFill();
		
		int center_y = (int) (this.width * (row + 1.5));
		int center_x =  (int) (this.width * (col + 1.5));
		
		ellipse(center_x, center_y, this.width - (2 * offset), this.width - (2 * offset));
	}
	
	private void makeLargeCross(int row, int col) {
		int offset = 20;
		
		this.addOpacity(row, col);
		
		stroke(161, 161, 14);
		strokeWeight(8);
		
		int top = this.width * (3*row+1) + offset;
		int left =  this.width * (3*col+1) + offset;
		
		line(left, top, left + this.width * 3 - (2 * offset), top + this.width * 3 - (2 * offset));
		line(left, top + this.width * 3 - (2 * offset), left + this.width * 3 - (2 * offset), top);
	}
	
	private void makeLargeCircle(int row, int col) {
		int offset = 20;
		
		this.addOpacity(row, col);

		stroke(14, 161, 161);
		strokeWeight(8);
		noFill();
		
		
		int center_y = (int) (this.width * (3*row + 2.5));
		int center_x =  (int) (this.width * (3*col + 2.5));
		
		ellipse(center_x, center_y, this.width * 3 - (2 * offset), this.width * 3 - (2 * offset));
	}
	
	private void addOpacity(int row, int col)
	{
		fill(0, 0, 0, 192);
		noStroke();
		
		int center_y = (int) (this.width * (3*row + 2.5));
		int center_x =  (int) (this.width * (3*col + 2.5));
		
		rect(center_x, center_y, 3*this.width - 8, 3*this.width - 8);
	}
	
	public void keyPressed() {
	    if (keyCode == ESC) {
	    	exit(); // Terminate the sketch and stop the program
	    }
	}
}