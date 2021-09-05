import org.junit.Test;
import tax.BillSystem;
import tax.Item;
import tax.ItemType;
import tax.Purchase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BillingSystemTest {

    private BillSystem billSystem = new BillSystem(0.1,0.05);
    private final Purchase purchase = new Purchase();

    @Test
    public void zeroItemReceipt(){
        String receipt = billSystem.generateReceipt(new Purchase());
        assertEquals("LEO BILLING SYSTEM\nTax: 0.0\nTotal: 0.0", receipt);
    }

    @Test
    public void oneBookOneCDOneChocolateTax15(){
        purchase.addItem(new Item(1, "book", 124.99, ItemType.BOOK, 0));
        purchase.addItem(new Item(1, "CD", 149.99, ItemType.OTHER, 0));
        purchase.addItem(new Item(1, "CHOCOLATE", 40.85, ItemType.FOOD, 0));
        String expected = "LEO BILLING SYSTEM\n1 Imported==0 book: 124.99\n1 Imported==0 CD: 164.99\n1 Imported==0 CHOCOLATE: 40.85\nTax: 15.0\nTotal: 330.83";
        String receipt = billSystem.generateReceipt(purchase);
        assertEquals(expected, receipt);
    }

    @Test
    public void oneImportedChocolateOneImportedPerfumeTax75_60() {
        purchase.addItem(new Item(1, "CHOCOLATE", 100, ItemType.FOOD, 1));
        purchase.addItem(new Item(1, "PERFUME", 470.50, ItemType.OTHER, 1));
        String expected = "LEO BILLING SYSTEM\n" +
                "1 Imported==1 CHOCOLATE: 105.0\n" +
                "1 Imported==1 PERFUME: 541.08\n" +
                "Tax: 75.6\n" +
                "Total: 646.08";
        String receipt = billSystem.generateReceipt(purchase);
        assertEquals(expected, receipt);
    }

    @Test
    public void oneImportedPerfumeOnePerfumeOnePillOneImportedChocolate() {
        purchase.addItem(new Item(1, "PERFUME", 270.99, ItemType.OTHER, 1));
        purchase.addItem(new Item(1, "PERFUME", 180.99, ItemType.OTHER, 0));
        purchase.addItem(new Item(1, "HEADACHE PILL", 19.75, ItemType.MEDICINE, 0));
        purchase.addItem(new Item(1, "CHOCOLATE", 210.25, ItemType.FOOD, 1));
        String expected = "LEO BILLING SYSTEM\n" +
                "1 Imported==1 PERFUME: 311.64\n" +
                "1 Imported==0 PERFUME: 199.09\n" +
                "1 Imported==0 HEADACHE PILL: 19.75\n" +
                "1 Imported==1 CHOCOLATE: 220.76\n" +
                "Tax: 69.3\n" +
                "Total: 751.24";
        String receipt = billSystem.generateReceipt(purchase);
        assertEquals(expected, receipt);
    }
}
