package sudokuGame;

public class Board {

	Cell[][] cells; 
	
	public Board(Cell[][] cells) {
		this.cells = cells;
	}

	public Cell getCell(int i, int j) {
		return cells[i][j];
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	
	// display the board method
	public void display() {
		System.out.println("    0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 ");
		System.out.println("-----------------------------------");
		for (int i = 0; i <= 8; i++) {
			System.out.print(i + " | ");
			for (int j = 0; j <= 2; j++) {
				System.out.print(getCell(i, j).getDigit() + " | ");
			}
			System.out.println();
			System.out.println("---------------");
		}
	}
}
