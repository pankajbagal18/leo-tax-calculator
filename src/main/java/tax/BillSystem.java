package tax;

public class BillSystem {
    public static final String HEADING = "LEO BILLING SYSTEM";
    private final double basicRate;
    private final double importRate;

    public BillSystem(double basicRate, double importRate) {
        this.basicRate = basicRate;
        this.importRate = importRate;
    }

    public String generateReceipt(Purchase purchase) {
        purchase.process(basicRate, importRate);
        return buildReceipt(purchase);
    }

    private String buildReceipt(Purchase purchase) {
        StringBuilder builder = new StringBuilder(HEADING);
        for (Item item : purchase.getItems()) {
            builder.append("\n").append(item.quantity()).append(" ")
                    .append("Imported==").append(item.isImported()).append(" ")
                    .append(item.name()).append(": ").append(item.total());
        }
        builder.append("\n").append("Tax: ").append(purchase.tax());
        builder.append("\n").append("Total: ").append(purchase.total());
        return builder.toString();
    }
}
