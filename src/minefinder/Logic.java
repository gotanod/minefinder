package minefinder;

import java.util.Random;

public class Logic {

	private LogicState state;

	private Cell[] map;
	private int numberMines;
	private int rows; 
	private int cols; 
	
	private String newLine = System.lineSeparator();
	
	public Logic() {
		//
	}
	
	/**************************
	 * METHODS
	 **************************/
	
	public boolean isGameOver() {
		return this.state==LogicState.LOST;
	}

	public boolean isWin() {
		return this.state==LogicState.WIN;	
	}
	
	public void newMap(int rows, int cols, int numberMines) {
		
		this.state = LogicState.RUNING;
		
		this.rows = rows;
		this.cols = cols;
		this.numberMines = numberMines;
		
		this.map = createEmptyMap(rows, cols);
		
		fillWithMines(this.map, numberMines);
				
	}

	private void fillWithMines(Cell[] map, int numberMines) {
		int size = map.length;
		
		Random rnd = new Random();
		
		int mines =0;
		while (mines<numberMines) {
			int pos = rnd.nextInt(size);
			if ( map[pos].isMine() == false ) {
				map[pos].setMine(true);
				mines++;
				increaseNearbyMines(map, pos);
			}
		}
		
	}

	private void increaseNearbyMines(Cell[] map, int pos) {
		//
		//   *............
		//   ...*.........
		//   .............
		//
		//   11...111......11
		//   *1...1*1......1*
		//   11...111......11
		//
		//   *1...1*1......1*
		

		// is first column
		int col = pos % this.cols;
		boolean isFirstCol = (col == 0);
		boolean isLastCol = ( col == this.cols-1);
		int row = pos / this.cols;
		boolean isFirstRow = (row == 0);
		boolean isLastRow = ( row == this.rows-1);
		
		// LEFT & RIGHT
		if ( !isFirstCol ) { this.map[pos-1].increaseNearbyMines(); }
		if ( !isLastCol ) { this.map[pos+1].increaseNearbyMines(); }

		// UPPER ROW
		if ( !isFirstRow ) {
			this.map[pos-this.cols].increaseNearbyMines();
			if ( !isFirstCol ) { this.map[pos-1-this.cols].increaseNearbyMines(); }
			if ( !isLastCol ) { this.map[pos+1-this.cols].increaseNearbyMines(); }			
		}
		
		// LOWER ROW
		if ( !isLastRow ) {
			this.map[pos+this.cols].increaseNearbyMines();
			if ( !isFirstCol ) { this.map[pos-1+this.cols].increaseNearbyMines(); }
			if ( !isLastCol ) { this.map[pos+1+this.cols].increaseNearbyMines(); }
		}
		
	}

	private Cell[] createEmptyMap(int rows, int cols) {
		Cell[] map = new Cell[rows * cols];
		for (int row=0;row<this.rows; row++) {
			for (int col=0;col<this.cols; col++) {
				map[col + row * this.cols] = new Cell();
			}
		}
		return map;
	}
	
	public void unhide(int row, int col) {
		if ( col >= 0 && col < this.cols &&
				row >= 0 && row < this.rows ) {
			int pos = col + row * this.cols;
			this.map[pos].unhide();	
			if ( this.map[pos].getNearbyMines() == 0 ) {
				clearArea8(pos);
			}
		}
		// Change state
		changeState(row, col);
	}
	
	private void changeState(int row, int col) {
		if ( this.map[col + row * this.cols].isMine() ) {
			this.state = LogicState.LOST;
		} else {
			if ( remainingHiddenCells() == this.numberMines ) {
				this.state = LogicState.WIN;
			}
		}
	}

	private int remainingHiddenCells() {
		int remaining = 0;
		for (int i=0;i<this.map.length;i++) {
			if ( this.map[i].isHidden() ) {
				remaining++;
			}
		}
		return remaining;
	}

	private void unhide(int pos) {
		if ( this.map[pos].getNearbyMines() == 0 
				&& this.map[pos].isHidden() == true ) {
			this.map[pos].unhide(); 		
			clearArea8(pos);
		}
		this.map[pos].unhide(); 		
	}
	
	private void clearArea8(int pos) {
		// is first column
		int col = pos % this.cols;
		boolean isFirstCol = (col == 0);
		boolean isLastCol = ( col == this.cols-1);
		int row = pos / this.cols;
		boolean isFirstRow = (row == 0);
		boolean isLastRow = ( row == this.rows-1);
		
		// LEFT & RIGHT
		if ( !isFirstCol ) { unhide(pos-1); }
		if ( !isLastCol ) { unhide(pos+1); }

		// UPPER ROW
		if ( !isFirstRow ) {
			unhide(pos-this.cols);
			if ( !isFirstCol ) { unhide(pos-1-this.cols); }
			if ( !isLastCol ) { unhide(pos+1-this.cols); }			
		}
		
		// LOWER ROW
		if ( !isLastRow ) {
			unhide(pos+this.cols);
			if ( !isFirstCol ) { unhide(pos-1+this.cols); }
			if ( !isLastCol ) { unhide(pos+1+this.cols); }
		}
		
	}


	/**************************
	 * TEXT DRAWING
	 **************************/
	
	public void drawMap() {
		StringBuilder sb = new StringBuilder();
		
		for (int row=0;row<this.rows; row++) {
			for (int col=0;col<this.cols; col++) {
				Cell cell = this.map[col + row * this.cols];
				sb.append(cell.draw());
			}
			sb.append(newLine);
		}
		// draw the sb
		System.out.println(sb.toString());
	}


}