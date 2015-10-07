public class Life {

	private int[][] current;
	private int[][] next;

	private final int ROWS;
	private final int COLS;

	public Life(int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		current = new int[ROWS][COLS];
	}

	private int neighborsAlive(int row, int col) {
		int sum = 0;

		int currentRow;
		int currentCol;
		int currentNum;

		for (int i = 1; i >= -1; i--) {
			for (int j = 1; j >= -1; j--) {

				currentRow = row - i;
				currentCol = col - j;

				if (row == currentRow && col == currentCol) {
					// do not check current cell
				} else {
					// for checked cells. separate IFs allow all bidirectional wrapping
					if (currentRow < 0) {
						// wrap around to bottom row
						currentRow = ROWS - 1;
					}

					if (currentCol < 0) {
						// wrap around to rightmost row
						currentCol = COLS - 1;
					}

					if (currentRow >= ROWS) {
						// wrap around to top row
						currentRow = 0;
					}

					if (currentCol >= COLS) {
						// wrap around to leftmost row
						currentCol = 0;
					}

					currentNum = current[currentRow][currentCol];

					if (currentNum == 1) {
						sum++;
					}
				}
			}
		}

		return sum;
	}

	public void calcNext() {
		int neighbors;

		next = new int[ROWS][COLS];

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				neighbors = neighborsAlive(i, j);

				if (neighbors == 3) {
					next[i][j] = 1;
				} else if (neighbors == 2) {
					next[i][j] = current[i][j];
				} else {
					next[i][j] = 0;
				}
			}
		}

		// set current equal to next for next iteration
		current = next;
	}

	public void toggleAlive(int row, int col) {
		if (current[row][col] == 0) {
			current[row][col] = 1;
		} else if (current[row][col] == 1) {
			current[row][col] = 0;
		}
	}

	public int[][] getCurrentState() {
		return current;
	}

}
