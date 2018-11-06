package sudokuGame;

import java.util.Random;

public class Generator {

	private Board board = new Board();
	private Board backupBoard = new Board(); // srcBoard is a board to backtrack to if something goes wrong

	public Board getBoard() {
		return this.board;
	}

	// Get random number from (0-8)
	private int random() {
		Random rand = new Random();
		return rand.nextInt(9);
	}

	// Check if the board is solvable
	private boolean solvable() {
		Solution solver = new Solution(board, true);
		return solver.solveBoard();
	}
	
	// Initialize board: fills the board up randomly with numbers, then attempts to solve it to create
	// a finished puzzle
	private void initBoard() {
		int i, j, n;
		do {
			board.clear();
			for (int k = 0; k < 80; k++) {
				i = random();
				j = random();
				n = random() + 1;

				if (!board.contains(i, j, n)) {
					board.setCell(i, j, n, true);
				}
			}
		} while (!solvable());
		backupBoard.copy(board);
	}
	
	// Clear n randomly placed cells
		private void hideCells(int n) {
			int i, j, k, dug = 0;
			while (dug < n) {
				i = random();
				j = random();
				if (board.getCell(i, j) == null || !board.getCell(i, j).equals(0)) {
					if (board.getCell(i, j) == null) {
						k = 0;
					} else {
						k = board.getCell(i, j).getNumber();
					}
					board.setCell(i, j, 0, false);
					if (!multSol()) {
						dug++;
					} else {
						board.setCell(i, j, k, true);
					}
				}
			}
		}
	
	// Generate a new solvable board, diff - difficulty level (1-3)
	public void generateBoard(int diff) {
		initBoard();
		Random random = new Random();
		if (diff == 1) {
			hideCells(30 + random.nextInt(10));
		} else if (diff == 2) {
			hideCells(40 + random.nextInt(10));
		} else {
			hideCells(50 + random.nextInt(10));
		}
	}

	// Check for multiple solutions by brute force
	private boolean multSol() {
		int n = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (backupBoard.getCell(i, j) != null && backupBoard.getCell(i, j).equals(0)) {
					// if a cell in the original puzzle is empty
					for (int k = 1; k < 10; k++) {
						// loop through all possible numbers for this cell
						board.copy(backupBoard);
						board.setCell(i, j, k, true);
						if (!board.contains(i, j, k) && solvable()) {
							// count all possible solutions for numbers in this cell
							n++;
						}
					}

					if (n > 1) {
						// if there is more than one possible solution, return true
						return true;
					}
					board.setCell(i, j, 0, false);
				}
			}
		}
		// if after testing all possible numbers for all empty cells there is not more
		// than one solution, return false
		return false;
	}

	
}