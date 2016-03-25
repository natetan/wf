/**
 * Created by Yulong on 3/24/2016.
 */
public class ModSlots {
    private Polarity[][] slots;
    private int count; // forma currently installed
    private int stars; // total amount of times with forma

    public static final int MAX_FORMA = 8;

    public ModSlots() {
        this.slots = new Polarity[2][4];
        this.count = 0;
        this.stars = 0;
    }

    public void polarize(int row, int column, Polarity p) {
        if (!isLegalSlot(row, column)) {
            throw new IllegalArgumentException();
        }
        this.slots[row][column] = p;
        if (this.count != MAX_FORMA) {
            this.count++;
        }
        this.stars++;
    }

    public int formaCount() {
        return this.count;
    }

    public int formaUsed() {
        return this.stars;
    }

    private boolean isLegalSlot(int row, int column) {
        return row > 0 && row < 2 && column > 0 && column < 5;
    }
}
