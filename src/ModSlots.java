/**
 * Created by Yulong on 3/24/2016.
 */
public class ModSlots {
    private Polarity[][] slots;
    private int count; // forma currently installed
    private int stars; // total amount of times with forma

    public static final int MAX_FORMA = 9;
	public static final int MAX_ROWS = 2;
	public static final int MAX_COLS = 4l
	
	// Assumes weapon comes with no polarities
    public ModSlots() {
        this.slots = new Polarity[2][4];
		for (int row = 0; row < MAX_ROWS; row++) {
			for (int col = 0; col < MAX_COLS; col++) {
				this.slots[row][col] = Polarity.NONE;
			}
		}
        this.count = 0;
        this.stars = 0;
    }

    public void polarize(int row, int column, Polarity p) {
        this.checkSlot(row, column);
        this.slots[row][column] = p;
        if (this.count != MAX_FORMA) {
            this.count++;
        }
        this.stars++;
    }

	public void move(int oldRow, int oldCol, int newRow, int newCol) {
		this.checkSlot(oldRow, oldCol);
		this.checkSlot(newRow, new Col);
		Polarity old = this.get(oldRow, oldCol);
		Polarity temp = this.get(newRow, newCol);
		this.slots[newRow][newCol] = old;
		this.slots[oldRow][oldCol] = temp;
	}

	public Polarity get(int row, int col) {
		this.checkSlot(row, col);
		return this.slots[row][col];
	}

    public int formaCount() {
        return this.count;
    }

    public int formaUsed() {
        return this.stars;
    }

    private boolean isLegalSlot(int row, int column) {
        return row > 0 && row < MAX_ROWS && column > 0 && column < MAX_COLS;
    }

	private void checkSlot(int row, int col) {
		if (!this.isLegalSlot(row, col)) {
			throw new IllegalArgumentException();
		}
	}
}
