package minefinder;

import java.util.Scanner;

public class MineFinder {

	public static void main(String[] args) {
		
		System.out.println("Mine Finder");
		System.out.println("===========");
		
		Logic logic = new Logic();
		
		logic.newMap(7, 20, 5);
		
		Scanner in = new Scanner(System.in);
		
		boolean isRunning = true;
		while ( isRunning ) {
			
			// DRAW
			logic.drawMap();
			
			// USER INPUT
			System.out.println(">>Enter coordinates (row,col):");
			String input = in.nextLine();
			String[] coords = input.split(",");
			if ( coords.length < 2 ) {
				isRunning = false;
			} else {
				
				// TURN
				int row = Integer.parseInt(coords[0]);
				int col = Integer.parseInt(coords[1]);
				logic.unhide(row, col);
				
				// CHECK WIN / LOSE
				if ( logic.isGameOver() ) {
					System.out.println("GAME OVER");
					logic.drawMap();
					System.exit(1);
				}
				if ( logic.isWin() ) {
					System.out.println("YOU WIN!");
					logic.drawMap();
					System.exit(0);
				}
			}
			
		}
		in.close();
		
		System.out.println("BYE BYE");
	}
}
