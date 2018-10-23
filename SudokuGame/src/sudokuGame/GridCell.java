package sudokuGame;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class GridCell extends JTextField {

	private Grid grid;
	private int row;
	private int column;

	public GridCell(Grid grid, int row, int column, Cell cell) {
		this.grid = grid;
		this.row = row;
		this.column = column;
		this.drawCell(cell);
	}

	private void drawCell(Cell cell) {
		if (!cell.isEmpty()) {
			this.setText(Integer.toString(cell.getNumber()));
		}
		this.setEditable(!cell.isFixed());
		if (cell.isFixed()) {
			this.setFont(new Font("Arial", Font.BOLD, 20));
		} else {
			this.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setHorizontalAlignment(JTextField.CENTER);
		this.getDocument().addDocumentListener(new CellDocumentListener());
	}

	// Highlight the cell
	public void highlightCell(boolean valid) {
		if (valid) {
			this.setForeground(Color.WHITE);
		} else {
			this.setForeground(Color.RED);
		}
	}

	// Assign customized document for appropriate cell behaviour
	protected Document createDefaultModel() {
		return new CellDocument();
	}

	// Customized cell document
	private class CellDocument extends PlainDocument {

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= 1) {
				try {
					Integer.parseInt(str);
					super.insertString(offset, str, attr);
				} catch (NumberFormatException e) {
				}
			}
		}
	}

	// Customized document listener
	private class CellDocumentListener implements DocumentListener {

		public void changedUpdate(DocumentEvent arg0) {
			this.updateCell();
		}

		public void insertUpdate(DocumentEvent arg0) {
			this.updateCell();
		}

		public void removeUpdate(DocumentEvent arg0) {
			this.updateCell();
		}

		private void updateCell() {
			highlightCell(true);
			try {
				grid.updateBoardCell(row, column, Integer.parseInt(getText()));
			} catch (NumberFormatException e) {
				grid.updateBoardCell(row, column, 0);
			}
		}
	}
}
