package hotelMangagement;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private final Menu menu;
    private final List<OrderItem> items;

    public OrderBuilder(Menu menu) {
        this.menu = menu;
        this.items = new ArrayList<>();
    }

    public void add(int index, int quantity) {
        MenuItem item = menu.getItem(index);
        if (item == null) {
            System.out.println("Invalid menu item index!");
            return;
        }
        OrderItem orderItem = new OrderItem(item, quantity);
        items.add(orderItem);
        System.out.println(quantity + " x " + item.getName() + " added to order.");
    }

    public void remove(int index, int quantity) {
        if (index < 1 || index > items.size()) {
            System.out.println("Invalid order item index!");
            return;
        }
        OrderItem orderItem = items.get(index - 1);
        if (orderItem.getQuantity() < quantity) {
            System.out.println("Invalid quantity!");
            return;
        } else if (orderItem.getQuantity() == quantity) {
            items.remove(orderItem);
            System.out.println(quantity + " x " + orderItem.getItem().getName() + " removed from order.");
        } else {
            orderItem.setQuantity(orderItem.getQuantity() - quantity);
            System.out.println(quantity + " x " + orderItem.getItem().getName() + " removed from order.");
        }
    }

    public void display() {
        System.out.println("\nOrder:");
        System.out.println("---------------");
        double totalPrice=0;
        for (int i = 0; i < items.size(); i++) {
            OrderItem orderItem = items.get(i);
            double itemPrice = orderItem.getTotalPrice();
            System.out.println((i + 1) + ". " + orderItem.getItem().getName() + " x" + orderItem.getQuantity() + "   -Rs "+ orderItem.getTotalPrice());
            totalPrice+= itemPrice;
        }
        System.out.println("---------------");
        System.out.println("Total: Rs" + totalPrice);
        
    }

    public void cancel() {
        items.clear();
        //System.out.println("Order cancelled!");
    }
    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderItem orderItem : items) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
        
        
    }
}
