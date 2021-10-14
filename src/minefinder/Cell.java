package minefinder;

public class Cell {

	private boolean hidden;  	//  '#'
	
	private boolean isMine;  	//  '*'
	
	private char mark;  		// ' ', 'F', '?'
	private char markEmpty = ' ';
	private char markFlag = 'F';
	private char markQuestion = '?';
	private int nearbyMines; 
	
	public Cell() {
		this.isMine = false;
		this.hidden = true;
		this.mark = ' ';
		this.nearbyMines = 0;
	}


	/**************************
	 * GETTERS & SETTERS
	 **************************/
	
	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public int getNearbyMines() {
		return nearbyMines;
	}

	public void setNearbyMines(int nearbyMines) {
		this.nearbyMines = nearbyMines;
	}
	
	public void increaseNearbyMines() {
		this.nearbyMines++;
	}

	public void unhide() {
		this.hidden = false;
	}

	public char getMark() {
		return mark;
	}

	public void setMark(char mark) {
		this.mark = mark;
	}

	/**************************
	 * METHODS
	 **************************/	
	
	public boolean isUnmarked() {		
		return this.mark==this.markEmpty;
	}	
	
	public void switchMark() {
		if ( this.mark == this.markEmpty ) { this.mark = this.markFlag; }
		else if ( this.mark == this.markFlag ) { this.mark = this.markQuestion; }
		else if ( this.mark == this.markQuestion ) { this.mark = this.markEmpty; }
	}

	/**************************
	 * TEXT DRAWING
	 **************************/
	
	public String draw() {
		String out;
	
		if ( this.isHidden() ) {
			if ( this.mark == this.markQuestion ) {
				out = "?";
			} else
			if ( this.mark == this.markFlag ) {
				out = "F";
			} else {
				// default
				out = "#";
			}
		} else {
			if ( this.isMine ) {
				out = "*";
			} else {
				if ( this.nearbyMines == 0 ) {
					out = "0";
				} else {
					out = String.valueOf(this.nearbyMines);
				}
			}
		}
		
		return out;
	}


	
	
}
