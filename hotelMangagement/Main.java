package hotelMangagement;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Menu menu = new VegMenu(); // default menu type
        //menu.loadMenuFromFile(); // load menu data from file
        System.out.println("Enter menu type (VEG or NONVEG): ");
        String menuType = scanner.nextLine();
        Menu menu = MenuFactory.createMenu(menuType);
        menu.loadMenuFromFile(); // load menu data from file
        if (menu != null) {
            menu.createMenu();
            int choice;
            do {
                System.out.println("\nEnter your choice:");
                System.out.println("1. Add Item");
                System.out.println("2. Delete Item");
                System.out.println("3. Display Menu");
                System.out.println("4. Quit");
                try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                switch (choice) {
                    case 1:
                        System.out.println("\nEnter item to add: ");
                        String addItem = scanner.nextLine();
                        System.out.println("\nEnter price of the item: ");
                        double addPrice = scanner.nextDouble();
                        scanner.nextLine(); // consume newline character
                        menu.addItem(new MenuItem(choice, addItem, addPrice));
                        menu.saveMenuToFile(); // save changes to file
                        break;
                    case 2:
                        System.out.println("\nEnter item to delete: ");
                        String deleteItem = scanner.nextLine();
                        for (MenuItem item : menu.items) {
                            if (item.getName().equalsIgnoreCase(deleteItem)) {
                                menu.deleteItem(item);
                                menu.saveMenuToFile(); // save changes to file
                                break;
                            }
                        }
                        break;
                    case 3:
                        menu.displayMenu();
                        break;
                    case 4:
                        System.out.println("\nExiting menu management...\n\n");
                        break;
                    default:
                        System.out.println("\nInvalid choice.");
                }
                } catch (Exception e) {
                    System.out.println("\nPlease choose a valid option first.");
                    scanner.nextLine(); // consume newline character
                    choice = 0; // reset choice to an invalid value
                }

            } while (choice != 4);
            OrderBuilder orderBuilder = new OrderBuilder(menu);

            boolean done = false;
            while (!done) {
                // Display menu
                menu.displayMenu();

                // Ask for user input
                System.out.println("\n1. Add item to order");
                System.out.println("2. Remove item from order");
                System.out.println("3. Display order");
                System.out.println("4. Cancel order");
                System.out.println("5. Exit");
                int choice2 = scanner.nextInt();

                if (choice2 == 1) {
                    // Ask for item index
                    System.out.print("Enter item index: ");
                    int itemIndex = scanner.nextInt();

                    // Ask for quantity
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    // Add the item to the order
                    orderBuilder.add(itemIndex-1, quantity);
                } else if (choice2 == 2) {
                    // Ask for item index to remove
                    System.out.print("Enter item index to remove: ");
                    int itemIndex = scanner.nextInt();

                    // Ask for quantity to remove
                    System.out.print("Enter quantity to remove: ");
                    int quantity = scanner.nextInt();

                    // Remove the item from the order
                    orderBuilder.remove(itemIndex, quantity);
                } else if (choice2 == 3) {
                    // Display the order
                    orderBuilder.display();
                } else if (choice2 == 4) {
                    // Cancel the order
                    orderBuilder.cancel();
                    System.out.println("Order cancelled!");
                    //done = true;
                }else if (choice2 == 5) {
                	done= true;
                	System.out.println("Order Placed");
                	System.out.println("\n\n======= BILL ========");
                	
                	//BillCalculator billCalculator = new DiscountBillCalculator(new SimpleBillCalculator(order));
                	BillCalculator billCalculator = new DiscountBillCalculator(new GSTBillCalculator(new ServiceTaxBillCalculator(new BaseBillCalculator(orderBuilder.getTotalPrice()))));
                	double totalPrice = billCalculator.getTotalPrice();
                    // print total price of order
                    //System.out.println("Total price of order: Rs " + totalPrice);
                	System.out.printf("Total cost (including GST and Service Tax): Rs. %.2f\n", totalPrice);   
                	
                	Payment payment = Payment.getInstance();
                	// Display payment options to user
                    System.out.println("\n\nSelect a payment option:");
                    System.out.println("1. Cash");
                    System.out.println("2. Card");
                    System.out.println("3. Net Banking");
                    System.out.println("4. Gift Card");
                    int option = scanner.nextInt();

                    // Get amount from totalPrice of order
                    double amount = totalPrice;

                    // Make payment based on user's choice
                    switch (option) {
                        case 1:
                            payment.makePayment(PaymentOption.CASH, amount);
                            System.out.println("Thank you!!");
                            break;
                        case 2:
                            payment.makePayment(PaymentOption.CARD, amount);
                            System.out.println("Thank you!!");
                            break;
                        case 3:
                            payment.makePayment(PaymentOption.NET_BANKING, amount);
                            System.out.println("Thank you!!");
                            break;
                        case 4:
                            payment.makePayment(PaymentOption.GIFT_CARD, amount);
                            System.out.println("Thank you!!");
                            break;
                        default:
                            System.out.println("Invalid option selected");
                            break;
                            
                }
                
            }

        } 
        scanner.close();
    }
}}