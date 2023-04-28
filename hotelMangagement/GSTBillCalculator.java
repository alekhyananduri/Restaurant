package hotelMangagement;

public class GSTBillCalculator implements BillCalculator {
    private BillCalculator billCalculator;

    public GSTBillCalculator(BillCalculator billCalculator) {
        this.billCalculator = billCalculator;
    }

    @Override
    public double getTotalPrice() {
        double totalCost = billCalculator.getTotalPrice();
        double tax = 0.18;
        totalCost *= (1 + tax);
        return totalCost;
    }
}
