package sudokuGame;

public class Cell {

	private int number;
	private boolean fixed;
	
	public Cell () {
		fixed = false;
	}

	public Cell (int number, boolean fixed) {
		this.number = number;
		this.fixed  = fixed;
	}

	public int getNumber () {
		return this.number;
	}

	public void setNumber (int number) {
		this.number = number;
	}

	// Get whether the cell is already set (1-9)
	public boolean isEmpty () {
		return this.number == 0;
	}

	// Get whether the cell is fixed or variable
	public boolean isFixed () {
		return this.fixed;
	}

	// Set the new fixed/variable status of the cell
	public void setFixed (boolean fixed) {
		this.fixed = fixed;
	}
	
	public String toString () {
		return "" + this.number;
	}

	// Compare cell (only verifies number)
	public boolean equals (Cell c) {
		return this.number == c.number;
	}

	// Compare cell with a number
	public boolean equals (int number) {
		return this.number == number;
	}
}