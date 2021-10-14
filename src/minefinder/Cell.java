package minefinder;

public class Cell {

	private boolean hidden;  //  '#'
	
	private boolean isMine;  //  '*'
	private int nearbyMines; 
	
	public Cell() {
		this.isMine = false;
		this.hidden = true;
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
	
	
	
	/**************************
	 * TEXT DRAWING
	 **************************/
	
	public String draw() {
		String out;
	
		if ( this.isHidden() ) {
			out = "#"; 
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
