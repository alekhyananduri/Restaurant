package hotelMangagement;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public abstract class Menu {
	protected List<MenuItem> items = new ArrayList<>();
	public void addItem(MenuItem item) {
	    item.setIndex(items.size() + 1);
	    items.add(item);
	}
	public void deleteItem(MenuItem item) {
	    items.remove(item);
	    for (int i = 0; i < items.size(); i++) {
	        items.get(i).setIndex(i + 1);
	    }
	}

    public void displayMenu() {
        System.out.println("=== MENU ===");
        for (MenuItem item : items) {
        	System.out.println(item.getIndex() + ". " + item.getName() + " - Rs. " + item.getPrice());
        }
        System.out.println();
    }
    public MenuItem getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }


    public int size() {
        return items.size();
    }
    public void saveMenuToFile() {
        try (PrintWriter writer = new PrintWriter("menu.txt")) {
            for (MenuItem item : items) {
                writer.println(item.getName() + "," + item.getPrice());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving menu to file: " + e.getMessage());
        }
    }
    public void loadMenuFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("menu.txt"));
            String line = reader.readLine();
            int index = 1;
            while (line != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                items.add(new MenuItem(index, name, price));
                index++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading menu file.");
            e.printStackTrace();
        }
    }


    public abstract void createMenu();
}