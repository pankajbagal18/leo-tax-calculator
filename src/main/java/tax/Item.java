package tax;

import org.apache.commons.math3.util.Precision;

public class Item {
    private int quantity;
    private String name;
    private ItemType itemType;
    private double price;
    private int isImported;
    private double total;
    private double tax;

    public int isImported(){
        return isImported;
    }

    public int quantity(){
        return quantity;
    }

    public double total(){
        return total;
    }
    public double tax(){
        return tax;
    }
    public String name(){
        return name;
    }

    public Item(int quantity, String name, double price, ItemType itemType, int isImported) {
        this.quantity = quantity;
        this.name = name;
        this.itemType = itemType;
        this.price = price;
        this.isImported = isImported;
    }

    private double tax(final double taxable, double basicRate, double importRate) {
        return  taxable * basicRate * itemType.excempt() + taxable * importRate * isImported;
    }

    public void process(double basicRate, double importRate) {
        final double taxable = quantity * price;
        tax = Precision.round(tax(taxable, basicRate, importRate), 2);
        total = Precision.round(taxable + tax, 2);
    }
}
