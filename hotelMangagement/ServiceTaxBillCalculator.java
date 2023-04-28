package hotelMangagement;

public class ServiceTaxBillCalculator implements BillCalculator {
    private BillCalculator billCalculator;

    public ServiceTaxBillCalculator(BillCalculator billCalculator) {
        this.billCalculator = billCalculator;
    }

    @Override
    public double getTotalPrice() {
        double totalCost = billCalculator.getTotalPrice();
        double tax = 0.10;
        totalCost *= (1 + tax);
        return totalCost;
    }
}
