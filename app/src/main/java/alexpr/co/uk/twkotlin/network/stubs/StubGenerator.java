package alexpr.co.uk.twkotlin.network.stubs;

import alexpr.co.uk.twkotlin.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static List<PlaceModel> getMockPlaces() {
        List<PlaceModel> list = new ArrayList<>();

        List<ServiceModel>serviceHighlight = new ArrayList<>(3);
        serviceHighlight.add(new ServiceModel("Service1", "12 min", "£145", "save -30%", "£12", false, 1));
        serviceHighlight.add(new ServiceModel("Service2", "1 min", "£45", "", "", false, 1));
        serviceHighlight.add(new ServiceModel("Service3", "20 min", "£4.5", "", "£12", false, 3));
        list.add(new PlaceModel("https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Spacetime_lattice_analogy.svg/1920px-Spacetime_lattice_analogy.svg.png", "Place One", 3.6d, "123 reviews", "NW11", "bestOne", serviceHighlight));
        list.add(new PlaceModel("https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Spacetime_lattice_analogy.svg/1920px-Spacetime_lattice_analogy.svg.png", "Place Two", 3.6d, "123 reviews", "NW11", "bestOne", serviceHighlight));
        list.add(new PlaceModel("https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Spacetime_lattice_analogy.svg/1920px-Spacetime_lattice_analogy.svg.png", "Place Three", 3.6d, "123 reviews", "NW11", "bestOne", serviceHighlight));
        list.add(new PlaceModel("https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Spacetime_lattice_analogy.svg/1920px-Spacetime_lattice_analogy.svg.png", "Place Four", 3.6d, "123 reviews", "NW11", "bestOne", serviceHighlight));
        return list;
    }

    public static PlaceModel getMockPlaceDetails(String placeName) {
        String[] name = placeName.split("-");
        String capitalizedName = "";
        for (String s : name) {
            capitalizedName = capitalizedName.concat(s.substring(0, 1).toUpperCase()).concat(s.substring(1)).concat(" ");
        }
        return new PlaceModel("https://upload.wikimedia.org/wikipedia/commons/d/d9/Mercury_in_color_-_Prockter07-edit1.jpg",
                capitalizedName.trim(),
                new Random().nextInt(50) / 10d,
                new Random().nextInt(1000) + "",
                "Milky Way, Solar System",
                "",
                new ArrayList<ServiceModel>());
    }
    
    public static List<Section> getMockPlaceServices() {
        ArrayList<Section> sections = new ArrayList<>();
        Section section = new Section("January Beauty Sale", new ArrayList<ServiceItem>(), false);
        ServiceItem serviceItem = new ServiceItem(
                new ArrayList<ServiceModel>(),
                new ServiceModel("Service1January", "20 min", "£30", "-25%", "25", false, 111111),
                "details?",
                "Nails",
                false,
                false);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false, 111112));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false, 111113));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false, 111114));
        section.getServiceItem().add(serviceItem);

        serviceItem = new ServiceItem(
                new ArrayList<ServiceModel>(),
                new ServiceModel("Service2January", "20 min", "£30", "-25%", "25", false, 111115),
                "details?",
                "Nails",
                false,
                true);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false, 111116));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false, 111117));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false, 111118));
        section.getServiceItem().add(serviceItem);
        sections.add(section);

        section = new Section("February Beauty Sale", new ArrayList<ServiceItem>(), false);
        serviceItem = new ServiceItem(new ArrayList<ServiceModel>(), new ServiceModel("Service1February", "20 min", "£30", "-25%", "25", false, 111119), "details?", "Nails", false, false);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1February", "21 min", "£31", "-5%", "15", false, 111120));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2February", "22 min", "£32", "-15%", "25", false, 111121));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3February", "23 min", "£33", "-35%", "35", false, 111122));
        section.getServiceItem().add(serviceItem);

        serviceItem = new ServiceItem(new ArrayList<ServiceModel>(), new ServiceModel("Service2February", "20 min", "£30", "-25%", "25", false, 111123), "details?", "Nails", false, false);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1January", "21 min", "£31", "-5%", "15", false, 111124));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2January", "22 min", "£32", "-15%", "25", false, 111125));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3January", "23 min", "£33", "-35%", "35", false, 111126));
        section.getServiceItem().add(serviceItem);
        sections.add(section);

        section = new Section("March Beauty Sale", new ArrayList<ServiceItem>(), false);
        serviceItem = new ServiceItem(new ArrayList<ServiceModel>(), new ServiceModel("Service1March", "20 min", "£30", "-25%", "25", false, 111127), "details?", "Nails", false, true);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1March", "21 min", "£31", "-5%", "15", false, 111128));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2March", "22 min", "£32", "-15%", "25", false, 111129));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3March", "23 min", "£33", "-35%", "35", false, 111130));
        section.getServiceItem().add(serviceItem);

        serviceItem = new ServiceItem(new ArrayList<ServiceModel>(), new ServiceModel("Service2March", "20 min", "£30", "-25%", "25", false, 111131), "details?", "Nails", false, true);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1March", "21 min", "£31", "-5%", "15", false, 111132));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2March", "22 min", "£32", "-15%", "25", false, 111133));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3March", "23 min", "£33", "-35%", "35", false, 111134));
        section.getServiceItem().add(serviceItem);
        sections.add(section);

        section = new Section("April Beauty Sale", new ArrayList<ServiceItem>(), false);
        serviceItem = new ServiceItem(new ArrayList<ServiceModel>(), new ServiceModel("Service1April", "20 min", "£30", "-25%", "25", false, 111135), "details?", "Nails", false, true);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1April", "21 min", "£31", "-5%", "15", false, 111136));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2April", "22 min", "£32", "-15%", "25", false, 111137));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3April", "23 min", "£33", "-35%", "35", false, 111138));
        section.getServiceItem().add(serviceItem);

        serviceItem = new ServiceItem(new ArrayList<ServiceModel>(), new ServiceModel("Service2April", "20 min", "£30", "-25%", "25", false, 111139), "details?", "Nails", false, false);
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService1April", "21 min", "£31", "-5%", "15, false", false, 111140));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService2April", "22 min", "£32", "-15%", "25", false, 111141));
        serviceItem.getServiceSubItem().add(new ServiceModel("SubService3April", "23 min", "£33", "-35%", "35", false, 111142));
        section.getServiceItem().add(serviceItem);
        sections.add(section);
        return sections;
    }                                             ;
}
