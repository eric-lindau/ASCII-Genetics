package entity;

public class Position {

	int col, row;

	/**
	 * Constructor for Position
	 * 
	 * @param c
	 *            column index
	 * @param r
	 *            row index
	 */
	public Position(int c, int r) {
		col = c;
		row = r;
	}

	/**
	 * Gets col index
	 * 
	 * @return col index
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Sets col index
	 * 
	 * @param col
	 *            new col index
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Gets row index
	 * 
	 * @return new row index
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets row index
	 * 
	 * @param row
	 *            new row index
	 */
	public void setRow(int row) {
		this.row = row;
	}

}