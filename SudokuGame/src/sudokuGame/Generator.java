package sudokuGame;

import java.util.Random;

public class Generator {

	private Board board = new Board();
	private Board backupBoard = new Board(); // a board to backtrack, if something goes wrong

	public Generator() {
	}

	// Get the board
	public Board getBoard() {
		return this.board;
	}
	
	// Get random number(0-8)
	private int random() {
		Random rand = new Random();
		return rand.nextInt(9);
	}

	// Check if board is solvable
	private boolean solvable() {
		Solution solution = new Solution(board, true);
		return solution.solve();
	}
	
	// Initializing board, fill randomly with numbers,solves it to create a puzzle
	private void initalizeBoard() {
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
			int i, j, k, hide = 0;
			while (hide < n) {
				i = random();
				j = random();
				if (board.getCell(i, j) == null || !board.getCell(i, j).equals(0)) {
					if (board.getCell(i, j) == null) {
						k = 0;
					} else {
						k = board.getCell(i, j).getNumber();
					}
					board.setCell(i, j, 0, false);
					if (!multipleSolutions()) {
						hide++;
					} else {
						board.setCell(i, j, k, true);
					}
				}
			}
		}

	// Generate a new solvable board, diff - difficulty level (1-3)
	public void generateBoard(int diff) {
		initalizeBoard();
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
	private boolean multipleSolutions() {
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
