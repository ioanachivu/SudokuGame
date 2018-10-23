package sudokuGame;

public class Board {

	private Cell[][] board = new Cell[9][9];

	public Board() {
	}

	public Cell getCell(int i, int j) {
		return board[i][j];
	}

	public void setCell(int i, int j, Cell c) {
		board[i][j] = c;
	}

	// Set new cell at given position
	public void setCell(int i, int j, int number, boolean fixed) {
		if (board[i][j] == null) {
			board[i][j] = new Cell(number, fixed);
		} else {
			board[i][j].setNumber(number);
			board[i][j].setFixed(fixed);
		}
	}
	
	// Get given row on board 
		public Cell[] getRow(int i) {
			return board[i];
		}

	// Get given column on board
	public Cell[] getColumn(int j) {
		Cell[] column = new Cell[9];

		for (int i = 0; i < 9; i++) {
			column[i] = board[i][j];
		}
		return column;
	}

	// Get the square from board
	public Cell[] getSquare(int i, int j) {
		Cell[] square = new Cell[9];

		i = i - (i % 3);
		j = j - (j % 3);
		int b = 0;

		for (int n = i; n < i + 3; n++) {
			for (int m = j; m < j + 3; m++) {
				square[b] = board[n][m];
				b++;
			}
		}
		return square;
	}
	
	// Check if number is in the array
	private boolean contains(Cell[] c, int n) {
		for (int i = 0; i < c.length; i++) {
			if (c[i] != null && c[i].equals(n)) {
				return true;
			}
		}
		return false;
	}

	// Check if the number is already in row/ column/ square 
	public boolean contains(int i, int j, int n) {
		Cell[] row = getRow(i);
		Cell[] column = getColumn(j);
		Cell[] square = getSquare(i, j);

		if (contains(row, n) || contains(column, n) || contains(square, n)) {
			return true;
		} else {
			return false;
		}
	}

	// Display board
	public void printBoard() {
		System.out.println("-------------------");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == null || board[i][j].equals(0)) {
					System.out.print("*");
				} else {
					System.out.print(board[i][j]);
				}
				if (j % 3 == 2) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
			}
			if (i % 3 == 2) {
				System.out.println("\n-------------------");
			} else {
				System.out.println();
			}
		}
	}

	// Copies cells of a given board
	public void copy(Board board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board.getCell(i, j);
			}
		}
	}

	// Clear the board
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = new Cell(0, false);
			}
		}
	}

	// Removes all user input from the board
	public void reset() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!getCell(i, j).isFixed()) {
					this.board[i][j] = new Cell(0, false);
				}
			}
		}
	}

	// Compares two boards
	public boolean[][] compare(Board board) {
		boolean[][] bool = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getCell(i, j).equals(board.getCell(i, j))) {
					bool[i][j] = true;
				} else {
					bool[i][j] = false;
				}
			}
		}
		return bool;
	}

	// Compares two boards
	public boolean equals(Board board) {
		boolean[][] match = this.compare(board);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!match[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
