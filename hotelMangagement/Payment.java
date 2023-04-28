package hotelMangagement;

import java.util.Scanner;

public class Payment {

    
    private static Payment payment;
    
    private Payment() {}

    public static Payment getInstance() {
        if (payment == null) {
            payment = new Payment();
        }
        return payment;
    }

    public void makePayment(PaymentOption paymentOption, double amount) {
        try (Scanner scanner = new Scanner(System.in)) {
            switch (paymentOption) {
                case CASH:
                    payWithCash(amount);
                    break;
                case CARD:
                    System.out.print("Enter card number: ");
                    String cardNumber = scanner.nextLine();
                    System.out.print("Enter expiry date (MM/YY): ");
                    String expiryDate = scanner.nextLine();
                    System.out.print("Enter CVV: ");
                    int cvv = scanner.nextInt();
                    payWithCard(amount, cardNumber, expiryDate, cvv);
                    break;
                case NET_BANKING:
                    System.out.print("Enter bank name: ");
                    String bankName = scanner.nextLine();
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter IFSC code: ");
                    String ifscCode = scanner.nextLine();
                    payWithNetBanking(amount, bankName, accountNumber, ifscCode);
                    break;
                case GIFT_CARD:
                    System.out.print("Enter gift card number: ");
                    String giftCardNumber = scanner.nextLine();
                    System.out.print("Enter pin: ");
                    int pin = scanner.nextInt();
                    payWithGiftCard(amount, giftCardNumber, pin);
                    break;
                default:
                    System.out.println("Invalid payment option selected");
            }
        }
    }

    private void payWithCash(double amount) {
        // Payment logic for cash
        System.out.println("Payment made with cash: " + amount);
    }

    private void payWithCard(double amount, String cardNumber, String expiryDate, int cvv) {
        // Payment logic for card
        System.out.println("Payment made with card: " + amount + ", Card Number: " + cardNumber + ", Expiry Date: " + expiryDate + ", CVV: " + cvv);
    }

    private void payWithNetBanking(double amount, String bankName, String accountNumber, String ifscCode) {
        // Payment logic for net banking
        System.out.println("Payment made with net banking: " + amount + ", Bank Name: " + bankName + ", Account Number: " + accountNumber + ", IFSC Code: " + ifscCode);
    }

    private void payWithGiftCard(double amount, String giftCardNumber, int pin) {
        // Payment logic for gift card
        System.out.println("Payment made with gift card: " + amount + ", Gift Card Number: " + giftCardNumber + ", Pin: " + pin);
    }
}

