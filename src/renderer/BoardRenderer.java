package renderer;

import game.Board;
import game.Position;
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
		
		for(Position p: this.refBoard.getPositionsWithState(2)) {
			make_cross(p.row, p.col);
		}
		
		
		for(Position p: this.refBoard.getPositionsWithState(1)) {
			make_circle(p.row, p.col);
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
	
	private void make_cross(int row, int col) {
		int offset = 10;
		
		stroke(161, 161, 14);
		strokeWeight(4);
		
		int top = this.width * (row+1) + offset;
		int left =  this.width * (col+1) + offset;
		
		line(left, top, left + this.width - (2 * offset), top + this.width - (2 * offset));
		line(left, top + this.width - (2 * offset), left + this.width - (2 * offset), top);
	}
	
	private void make_circle(int row, int col) {
		int offset = 10;
		
		stroke(14, 161, 161);
		strokeWeight(4);
		noFill();
		
		int center_y = (int) (this.width * (row + 1.5));
		int center_x =  (int) (this.width * (col + 1.5));
		
		ellipse(center_x, center_y, this.width - (2 * offset), this.width - (2 * offset));
	}
	
	public void keyPressed() {
	    if (keyCode == ESC) {
	    	exit(); // Terminate the sketch and stop the program
	    }
	}
}