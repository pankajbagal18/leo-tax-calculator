package tax;

public enum ItemType {
    FOOD(0), BOOK(0), MEDICINE(0), OTHER(1);

    private final int excempted;

    ItemType(int excempted) {
        this.excempted = excempted;
    }

    public float excempt() {
        return excempted;
    }
}
