package alexpr.co.uk.twkotlin.network.stubs;

import alexpr.co.uk.twkotlin.models.MainMenu;
import alexpr.co.uk.twkotlin.models.MenuItem;
import alexpr.co.uk.twkotlin.models.MenuSection;

import java.util.ArrayList;
import java.util.List;

public class StubGenerator {

    public static MainMenu getMockMainMenu() {
        List<MenuSection> list = new ArrayList<>();
        MenuSection menuSection = new MenuSection("https://upload.wikimedia.org/wikipedia/commons/a/a1/Khajuraho-landscape.jpg", "Hair Removal", new ArrayList<MenuItem>(0));
        menuSection.getMenuItems().add(new MenuItem("Ladies' Waxing", "Ladies' Waxing QUERY"));
        menuSection.getMenuItems().add(new MenuItem("Bikini Waxing", "Bikini Waxing QUERY"));
        menuSection.getMenuItems().add(new MenuItem("Hollywood Waxing", "Hollywood Waxing QUERY"));
        menuSection.getMenuItems().add(new MenuItem("Brazilian Waxing", "Ladies' Waxing QUERY"));
        menuSection.getMenuItems().add(new MenuItem("Ladies' Leg Waxing", "Ladies' Waxing QUERY"));
        menuSection.getMenuItems().add(new MenuItem("Ladies' Arm & Underarm Waxing", "Bikini Waxing QUERY"));
        
        list.add(menuSection);

        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Nails", new ArrayList<MenuItem>()));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Nails", new ArrayList<MenuItem>(0)));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Nails", new ArrayList<MenuItem>(0)));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Nails", new ArrayList<MenuItem>(0)));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Nails", new ArrayList<MenuItem>(0)));
        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Nails", new ArrayList<MenuItem>(0)));

        return new MainMenu(list);
    }
}
