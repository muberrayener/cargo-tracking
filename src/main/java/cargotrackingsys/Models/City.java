package cargotrackingsys.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City {
    int cityId;
    String cityName;
    ArrayList<City> children;

    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.children = new ArrayList<City>();
    }

    public City(String cityName) {
        this.cityName = cityName;
        this.children = new ArrayList<City>();
    }

    public void addChild(City city) {
        this.children.add(city);
    }

    public void ShowCity() {
        System.out.println("City ID: " + cityId + ", City Name: " + cityName);
    }

    public void generateDOT(StringBuilder dotBuilder, Set<City> visited) {
        // Skip if the city has already been visited
        if (visited.contains(this)) {
            return;
        }

        // Mark this city as visited
        visited.add(this);

        // Add the city node to the DOT file
        dotBuilder.append("  \"" + this.cityName + "\";\n");

        // Add edges (connections) between this city and its neighbors
        for (City neighbor : children) {
            dotBuilder.append("  \"" + this.cityName + "\" -> \"" + neighbor.cityName + "\";\n");
            neighbor.generateDOT(dotBuilder, visited); // Recurse for neighbors
        }
    }

    public ArrayList<City> getChildren() {

        return this.children;
    }

    public ArrayList<City> setChildren() {
        City root= GetTree();
        return this.children = findCity(root, this.cityName).getChildren();
    }

    public static City findCity(City root, String targetCityName) {
        if (root.getCityName().equalsIgnoreCase(targetCityName)) {
            return root;
        }

        for (City child : root.getChildren()) {
            City result = findCity(child, targetCityName);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public String getCityName() {
        return this.cityName;
    }

    public static City GetTree() {
        City adana = new City(1, "Adana");
        City adiyaman = new City(2, "Adıyaman");
        City afyonkarahisar = new City(3, "Afyonkarahisar");
        City agri = new City(4, "Ağrı");
        City aksaray = new City(5, "Aksaray");
        City amasya = new City(6, "Amasya");
        City ankara = new City(7, "Ankara");
        City antalya = new City(8, "Antalya");
        City ardahan = new City(9, "Ardahan");
        City artvin = new City(10, "Artvin");
        City aydın = new City(11, "Aydın");
        City balikesir = new City(12, "Balıkesir");
        City bartin = new City(13, "Bartın");
        City batman = new City(14, "Batman");
        City bayburt = new City(15, "Bayburt");
        City bilecik = new City(16, "Bilecik");
        City bingol = new City(17, "Bingöl");
        City bitlis = new City(18, "Bitlis");
        City bolu = new City(19, "Bolu");
        City burdur = new City(20, "Burdur");
        City bursa = new City(21, "Bursa");
        City canakkale = new City(22, "Çanakkale");
        City cankiri = new City(23, "Çankırı");
        City corum = new City(24, "Çorum");
        City denizli = new City(25, "Denizli");
        City diyarbakir = new City(26, "Diyarbakır");
        City duzce = new City(27, "Düzce");
        City edirne = new City(28, "Edirne");
        City elazig = new City(29, "Elazığ");
        City erzincan = new City(30, "Erzincan");
        City erzurum = new City(31, "Erzurum");
        City eskisehir = new City(32, "Eskişehir");
        City gaziantep = new City(33, "Gaziantep");
        City giresun = new City(34, "Giresun");
        City gumushane = new City(35, "Gümüşhane");
        City hakkari = new City(36, "Hakkâri");
        City hatay = new City(37, "Hatay");
        City igdir = new City(38, "Iğdır");
        City isparta = new City(39, "Isparta");
        City istanbul = new City(40, "İstanbul");
        City izmir = new City(41, "İzmir");
        City kahramanmaras = new City(42, "Kahramanmaraş");
        City karabuk = new City(43, "Karabük");
        City karaman = new City(44, "Karaman");
        City kars = new City(45, "Kars");
        City kastamonu = new City(46, "Kastamonu");
        City kayseri = new City(47, "Kayseri");
        City kilis = new City(48, "Kilis");
        City kirikkale = new City(49, "Kırıkkale");
        City kirklareli = new City(50, "Kırklareli");
        City kirsehir = new City(51, "Kırşehir");
        City kocaeli = new City(52, "Kocaeli");
        City konya = new City(53, "Konya");
        City kutahya = new City(54, "Kütahya");
        City malatya = new City(55, "Malatya");
        City manisa = new City(56, "Manisa");
        City mardin = new City(57, "Mardin");
        City mersin = new City(58, "Mersin");
        City mugla = new City(59, "Muğla");
        City mus = new City(60, "Muş");
        City nevsehir = new City(61, "Nevşehir");
        City nigde = new City(62, "Niğde");
        City ordu = new City(63, "Ordu");
        City osmaniye = new City(64, "Osmaniye");
        City rize = new City(65, "Rize");
        City sakarya = new City(66, "Sakarya");
        City samsun = new City(67, "Samsun");
        City sanliurfa = new City(68, "Şanlıurfa");
        City siirt = new City(69, "Siirt");
        City sinop = new City(70, "Sinop");
        City sivas = new City(71, "Sivas");
        City sirnak = new City(72, "Şırnak");
        City tekirdag = new City(73, "Tekirdağ");
        City tokat = new City(74, "Tokat");
        City trabzon = new City(75, "Trabzon");
        City tunceli = new City(76, "Tunceli");
        City usak = new City(77, "Uşak");
        City van = new City(78, "Van");
        City yalova = new City(79, "Yalova");
        City yozgat = new City(80, "Yozgat");
        City zonguldak = new City(81, "Zonguldak");

        City center = new City(100, "Cargo Center");

        center.addChild(edirne);
        edirne.addChild(istanbul);
        istanbul.addChild(kocaeli);
        istanbul.addChild(tekirdag);
        istanbul.addChild(bursa);
        istanbul.addChild(sakarya);

        kocaeli.addChild(istanbul);
        kocaeli.addChild(sakarya);

        bursa.addChild(balikesir);
        bursa.addChild(yalova);
        yalova.addChild(sakarya);

        sakarya.addChild(duzce);
        sakarya.addChild(bolu);

        duzce.addChild(bolu);

        bolu.addChild(ankara);

        ankara.addChild(konya);
        ankara.addChild(kayseri);
        ankara.addChild(eskisehir);
        ankara.addChild(kirikkale);
        konya.addChild(mersin);
        mersin.addChild(adana);
        mersin.addChild(hatay);
        mersin.addChild(gaziantep);

        adana.addChild(hatay);
        adana.addChild(gaziantep);

        hatay.addChild(gaziantep);

        gaziantep.addChild(sanliurfa);
        gaziantep.addChild(kilis);
        gaziantep.addChild(mardin);

        sanliurfa.addChild(gaziantep);
        sanliurfa.addChild(kilis);
        sanliurfa.addChild(mardin);

        kilis.addChild(mardin);


        antalya.addChild(burdur);
        antalya.addChild(isparta);
        antalya.addChild(mugla);

        burdur.addChild(isparta);

        return center;
    }

    public static String DrawTree(){

        City center = GetTree();
        System.out.println("Geographically Connected Tree of Turkish Cities:");

        StringBuilder dotBuilder = new StringBuilder();
        dotBuilder.append("TurkishCities Tree {\n");

        Set<City> visited = new HashSet<>();
        center.generateDOT(dotBuilder, visited);

        dotBuilder.append("}\n");

        return dotBuilder.toString();
    }

    public static String findRoute(City currentCity, City destinationCity, Set<City> visited, List<String> path) {
        // Mark the current city as visited
        visited.add(currentCity);
        path.add(currentCity.getCityName());

        // If we've reached the destination city, return the path as a string
        if (currentCity == destinationCity) {
            return String.join(" -> ", path);
        }

        // Recursively visit each child city (child nodes in the tree)
        for (City child : currentCity.getChildren()) {
            if (!visited.contains(child)) {
                String result = findRoute(child, destinationCity, visited, path);
                if (result != null) {
                    return result; // If route is found, return the result
                }
            }
        }

        // Backtrack: remove the current city from the path if not found
        path.remove(path.size() - 1);
        return null; // No route found through this path
    }

    private static boolean findPathDFS(City currentCity, String targetCityName, List<String> path, Set<City> visited) {
        // Mark the current city as visited
        if (visited.contains(currentCity)) {
            return false;  // Prevent revisiting the city (to avoid infinite recursion)
        }
        visited.add(currentCity);

        // Add the current city to the path
        path.add(currentCity.getCityName());

        // If we've found the target city, return true
        if (currentCity.getCityName().equals(targetCityName)) {
            return true;
        }

        // Recursively search each child
        for (City child : currentCity.getChildren()) {
            if (findPathDFS(child, targetCityName, path, visited)) {
                return true;  // Path found in the child
            }
        }

        // Backtrack: remove the current city from the path if the target is not found
        path.remove(path.size() - 1);
        return false;
    }

    public static int findDistance(City source, City destination) {
        if (source == destination) {
            return 0; // Distance to the same city is 0
        }
        Set<City> visited = new HashSet<>();
        return dfs(source, destination, visited, 0);
    }

    // DFS recursive method to find the distance
    private static int dfs(City current, City destination, Set<City> visited, int depth) {
        // If we've already visited this city, skip it (to avoid cycles, though there should not be any in a tree)
        if (visited.contains(current)) {
            return -1; // No path found
        }

        // Mark the current city as visited
        visited.add(current);

        // If we reached the destination, return the depth (distance)
        if (current == destination) {
            return depth;
        }

        // Explore all the child cities (neighboring cities) of the current city
        for (City child : current.getChildren()) {
            int result = dfs(child, destination, visited, depth + 1); // Recurse with incremented depth
            if (result != -1) {
                return result; // If we found the destination, return the distance
            }
        }

        return -1; // If no path was found in the current branch, return -1
    }

    public static String listRoute ( String targetCityName){
        City root = GetTree();
        List<String> path = findPath(root, targetCityName);
        String route;

        if (path != null) {
            route = "Path to" + targetCityName + ": " + String.join(" -> ", path);
        } else {
            route = "City not found.";
        }
        return route;
    }

    public static List<String> findPath(City root, String targetCityName) {
        // List to hold the path
        List<String> path = new ArrayList<>();
        Set<City> visited = new HashSet<>();
        // Start DFS from the root
        if (findPathDFS(root, targetCityName, path,visited)) {
            return path;  // Path found, return it
        } else {
            return null;  // No path found
        }
    }

    public static int countArrowOccurrences(String input) {
        // Split the input string by the "->" delimiter and count the occurrences
        int count = 0;
        int index = 0;

        // Keep looking for "->" in the string
        while ((index = input.indexOf("->", index)) != -1) {
            count++;  // Increment the counter each time "->" is found
            index += 2; // Move past the found "->"
        }

        return count;
    }
}