package hotelMangagement;

public class BaseBillCalculator implements BillCalculator {
    private double basePrice;

    public BaseBillCalculator(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public double getTotalPrice() {
        return basePrice;
    }
}
