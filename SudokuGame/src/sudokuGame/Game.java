package sudokuGame;

public class Game {

	private Board board;
	private Board solution;
	private int level;

	public Game(int level) {
		this.level = level;
		generateBoard();
		getSolution();
	}

	public Board getBoard() {
		return this.board;
	}

	public void generateBoard() {
		Generator generator = new Generator();
		generator.generateBoard(this.level);
		this.board = generator.getBoard();
	}

	// Create a solution
	private void getSolution() {
		Board solveBoard = new Board();
		solveBoard.copy(this.board);
		solveBoard.reset();
		Solution solution = new Solution(solveBoard);
		solution.solve();
		this.solution = solution.getBoard();
	}

	// Check board
	public boolean[][] checkBoard() {
		Board checkBoard = new Board();
		checkBoard.copy(this.board);
		checkBoard.reset();

		Solution solver = new Solution(checkBoard);
		solver.solve();
		checkBoard = solver.getBoard();

		return checkBoard.compare(this.board);
	}

	// Solve board.
	public void solveBoard() {
		this.board = this.solution;
	}
	
	// verifying if the board is successfully solved
	public boolean isSolved() {
		return this.board.equals(this.solution);
	}

}
