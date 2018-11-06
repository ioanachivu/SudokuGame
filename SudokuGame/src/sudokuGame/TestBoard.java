package sudokuGame;

import junit.framework.*;
import junit.textui.TestRunner;

public class TestBoard extends TestCase {
	
	public TestBoard () {
		super();
	}
	
	// Test method Board.setCell
	public void testSetCell () {
		Game sudoku = new Game(1);
		sudoku.getBoard().setCell(0, 0, 7, false);
		assertEquals(sudoku.getBoard().getCell(0,0).getNumber(), 7);
		assertEquals(sudoku.getBoard().getCell(0,0).isFixed(), false);
	}	
	
	// Test method Board.contains 
	public void testCopy () {
		Game sudoku1 = new Game(1);
		Game sudoku2 = new Game(1);
		sudoku2.getBoard().copy(sudoku1.getBoard());
		assertTrue(sudoku2.getBoard().equals(sudoku1.getBoard()));
	}	
	
	// Test method Board.copy
	public void testContains () {
		Game sudoku = new Game(1);
		sudoku.getBoard().setCell(0, 0, 7, false);
		assertTrue(sudoku.getBoard().contains(0, 8, 7));
		assertTrue(sudoku.getBoard().contains(8, 0, 7));
		assertTrue(sudoku.getBoard().contains(1, 1, 7));
	}	

	// Test method Board.equals
	public void testEquals () {
		Game sudoku = new Game(1);
		assertTrue(sudoku.getBoard().equals(sudoku.getBoard()));
	}

	// Test method Board.clear,set all user cell numbers to 0
	public void testClear () {
		Board board = new Board();
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				board.setCell(i, j, 0, false);
			}
		}
		Game sudoku = new Game(1);
		sudoku.getBoard().clear();
		assertTrue(board.equals(sudoku.getBoard()));
	}

	// Test method Board.reset, set all user cell numbers to 0
	public void testReset () {
		Game sudoku = new Game(1);
		Board initial = sudoku.getBoard();
		sudoku.solveBoard();
		sudoku.getBoard().reset();
		assertTrue(initial.equals(sudoku.getBoard()));
	}
	
	// Test row/column/block sums of solved board, sum is expected to be 45
	public void testSubTotals () {
		Game sudoku = new Game(1);
		sudoku.solveBoard();
		
		for(int i = 0; i < 9; i++) {
			assertEquals (sum(sudoku.getBoard().getRow(i)), 45);
			assertEquals (sum(sudoku.getBoard().getCol(i)), 45);
			for(int j = 0; j < 3; j++) {
				assertEquals (sum(sudoku.getBoard().getBlock(i, j*3)), 45);
			}
		}
	}	

	private int sum (Cell[] array) {
		int sum = 0;
		for (int i=0; i<array.length; i++) {
			sum += array[i].getNumber();
		}
		return sum;
	}
	
	public static void main(String[] args) {
	    TestRunner.run(TestBoard.class);
	  }
}