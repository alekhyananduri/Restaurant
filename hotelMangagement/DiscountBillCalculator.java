package hotelMangagement;

public class DiscountBillCalculator implements BillCalculator {
    private BillCalculator billCalculator;

    public DiscountBillCalculator(BillCalculator billCalculator) {
        this.billCalculator = billCalculator;
    }

    @Override
    public double getTotalPrice() {
        double totalCost = billCalculator.getTotalPrice();
        double discount = 0;
        if (totalCost > 3000) {
            discount = 0.1;
        } else if (totalCost > 2000) {
            discount = 0.05;
        }
        totalCost *= (1 - discount);
        return totalCost;
    }
}
