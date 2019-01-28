package alexpr.co.uk.twkotlin.network.stubs;

import java.util.ArrayList;
import java.util.List;

import alexpr.co.uk.twkotlin.models.MainMenu;
import alexpr.co.uk.twkotlin.models.MenuItem;
import alexpr.co.uk.twkotlin.models.MenuSection;

public class StubGenerator {

    public static MainMenu getMockMainMenu() {
        List<MenuSection> list = new ArrayList<>();

        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/a/a1/Khajuraho-landscape.jpg", "Hair Removal", new ArrayList<MenuItem>()));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladies' Waxing", "Ladies' Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Bikini Waxing", "Bikini Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Hollywood Waxing", "Hollywood Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Brazilian Waxing", "Ladies' Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladies' Leg Waxing", "Ladies' Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladies' Arm & Underarm Waxing", "Bikini Waxing QUERY"));

        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Nails", new ArrayList<MenuItem>()));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Gel nails manicure", "Gel nails manicure QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Manicure", "Manicure QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Nail or Gel Polish Removal", "Nail or Gel Polish Removal QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Pedicure", "Pedicure QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Acrylic, Hard Gel & Nail Extensions", "Acrylic, Hard Gel & Nail Extensions QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Gel Nails Pedicure", "Gel Nails Pedicure QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Callus Peel", "Callus Peel QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Nail Art", "Nail Art QUERY"));

        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Hair", new ArrayList<MenuItem>()));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Haircuts and Hairdressing", "Haircuts and Hairdressing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Blow Dry", "Blow Dry QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladies' Hair Colouring & Highlights", "Ladies' Hair Colouring & Highlights QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Men's Haircut", "Men's Haircut QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Balayage & Ombre", "Balayage & Ombre QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Hair Conditioning & Scalp Treatments", "Hair Conditioning & Scalp Treatments QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Chidren's Haircuts", "Chidren's Haircuts QUERY"));

        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Face", new ArrayList<MenuItem>()));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));

        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Massage", new ArrayList<MenuItem>()));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));
        list.get(list.size() - 1).getMenuItems().add(new MenuItem("Ladie's Waxing", "Ladie's Waxing QUERY"));

        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Spa", new ArrayList<MenuItem>()));
        list.add(new MenuSection("https://upload.wikimedia.org/wikipedia/commons/1/1e/Computer_server_rack.jpg", "Body", new ArrayList<MenuItem>()));

        return new MainMenu(list);
    }
}
