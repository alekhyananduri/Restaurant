package hotelMangagement;

public class MenuFactory {
    public static Menu createMenu(String menuType) {
        if (menuType.equalsIgnoreCase("VEG")) {
            return new VegMenu();
        } else if (menuType.equalsIgnoreCase("NONVEG")) {
            return new NonVegMenu();
        } else {
            return null;
        }
    }
}