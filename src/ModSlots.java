/**
 * Yulong Tan
 * 3.26.16
 *
 * Implementation of using Forma from Warframe. This is modding a weapon
 * or frame with different 'polarities' that are specific to the game.
 * Users can add more polarities with 'forma' and can move things around.
 */

public class ModSlots implements Comparable<ModSlots> {
    private Polarity[][] slots;
    private int count; // forma currently installed
    private int stars; // total amount of times with forma
	private int capacity; // number of mod points remaining
	private boolean hasPotato; // checks if item is supercharged

    public static final int MAX_FORMA = 9;
	public static final int MAX_ROWS = 2;
	public static final int MAX_COLS = 4;
	public static final int DEFAULT_CAPACITY = 30;
	public static final int SUPER_CHARGED_CAPACITY = 60;
	
	// Assumes weapon comes with no polarities
    // Places polarity of 'none' in all slots
    public ModSlots(boolean potato) {
        this.slots = new Polarity[2][4];
		for (int row = 0; row < MAX_ROWS; row++) {
			for (int col = 0; col < MAX_COLS; col++) {
				this.slots[row][col] = Polarity.None;
			}
		}
        this.count = 0;
        this.stars = 0;
		this.hasPotato = potato;
		this.capacity = this.setUpCapacity();
    }
	
	// Adds a forma to the weapon, and sets a given slot to the given polarity
    public void polarize(int row, int column, Polarity p) {
        this.checkSlot(row, column);
        this.slots[row][column] = p;
        if (this.count != MAX_FORMA) {
            this.count++;
        }
        this.stars++;
    }
	
	// Polarity slots can be moved around for better elemental modding
	public void move(int oldRow, int oldCol, int newRow, int newCol) {
		this.checkSlot(oldRow, oldCol);
		this.checkSlot(newRow, newCol);
		Polarity old = this.get(oldRow, oldCol);
		Polarity temp = this.get(newRow, newCol);
		this.slots[newRow][newCol] = old;
		this.slots[oldRow][oldCol] = temp;
	}
	
	// Grabs the polarity at a given slot
	public Polarity get(int row, int col) {
		this.checkSlot(row, col);
		return this.slots[row][col];
	}
	
	// Returns the count of how many polarities are on
    public int formaCount() {
        return this.count;
    }
	
	// Returns the number of times an item has been formaed
    public int formaUsed() {
        return this.stars;
    }
	
	// Checks if the given slot is legal
    private boolean isLegalSlot(int row, int column) {
        return row > 0 && row < MAX_ROWS && column > 0 && column < MAX_COLS;
    }
	
	// Factors out the legality check of the slots. Throws an IllegalArgumentException
	// if the slot is illegal.
	private void checkSlot(int row, int col) {
		if (!this.isLegalSlot(row, col)) {
			throw new IllegalArgumentException();
		}
	}
	
	// Compares other ModSlots to this one. The ones with less polarities
	// come before the ones with more. If they're the same, they're compared with total
	// forma. 
    public int compareTo(ModSlots other) {
        if (this.count == other.count) {
            return this.stars - other.stars;
        } else {
            return this.count - other.count;
        }
    }

	public boolean hasPotato() {
		return this.hasPotato;
	}

	private int setUpCapacity() {
		if (this.hasPotato()) {
			return SUPER_CHARGED_CAPACITY;
		} else {
			return DEFAULT_CAPACITY;
		}
	}
}
