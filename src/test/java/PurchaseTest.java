import org.junit.Before;
import org.junit.Test;
import tax.Item;
import tax.ItemType;
import tax.Purchase;

import static org.junit.Assert.assertEquals;

public class PurchaseTest {
    public static final double BASIC_RATE = 0.1;
    public static final double IMPORT_RATE = 0.05;
    private Purchase purchase;

    @Before
    public void setUp() throws Exception {
        purchase = new Purchase();
    }

    @Test
    public void noItemNoTax() {
        purchase.process(BASIC_RATE, IMPORT_RATE);
        assertEquals(0, purchase.tax(), 0.0001);
    }

    @Test
    public void oneBookOneCDOneChocolateTax15() {
        purchase.addItem(new Item(1, "book", 124.99, ItemType.BOOK, 0));
        purchase.addItem(new Item(1, "CD", 149.99, ItemType.OTHER, 0));
        purchase.addItem(new Item(1, "CHOCOLATE", 40.85, ItemType.FOOD, 0));
        purchase.process(BASIC_RATE, IMPORT_RATE);
        assertEquals(15, purchase.tax(), 0.0001);
    }

    @Test
    public void oneImportedChocolateOneImportedPerfumeTax75_60() {
        purchase.addItem(new Item(1, "CHOCOLATE", 100, ItemType.FOOD, 1));
        purchase.addItem(new Item(1, "PERFUME", 470.50, ItemType.OTHER, 1));
        purchase.process(BASIC_RATE, IMPORT_RATE);
        assertEquals(75.60, purchase.tax(), 0.0001);
    }

    @Test
    public void oneImportedPerfumeOnePerfumeOnePillOneImportedChocolate() {
        purchase.addItem(new Item(1, "PERFUME", 270.99, ItemType.OTHER, 1));
        purchase.addItem(new Item(1, "PERFUME", 180.99, ItemType.OTHER, 0));
        purchase.addItem(new Item(1, "HEADACHE PILL", 19.75, ItemType.MEDICINE, 0));
        purchase.addItem(new Item(1, "CHOCOLATE", 210.25, ItemType.FOOD, 1));
        purchase.process(BASIC_RATE, IMPORT_RATE);
        assertEquals(69.30, purchase.tax(), 0.0001);
    }
}
