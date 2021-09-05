package tax;

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private final List<Item> items;
    private double purchaseTotal;
    private double purchaseTotalTax;

    public Purchase() {
        items = new ArrayList<>();
        purchaseTotal = 0;
        purchaseTotalTax = 0;
    }

    public void process(double basicRate, double importRate) {
        items.forEach(item -> {
            item.process(basicRate, importRate);
            purchaseTotalTax += item.tax();
            purchaseTotal += item.total();
        });
        purchaseTotalTax = Precision.round(purchaseTotalTax, 1);
        purchaseTotal = Precision.round(purchaseTotal, 2);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double total() {
        return purchaseTotal;
    }

    public double tax() {
        return purchaseTotalTax;
    }

    public List<Item> getItems() {
        return items;
    }
}
