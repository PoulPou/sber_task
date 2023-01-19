import model.City;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        URL url = new URL("https://drive.google.com/u/0/uc?id=1aZmsPZNHi-yuyOdsnG8w1dA7G4sV6zAD&export=download");
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<City> citiesList = new ArrayList<>();
        while (reader.read() != -1) {
            String str = reader.readLine();
                String[] stringsArray = str.split(";");
                if (stringsArray.length == 6) {
                    citiesList.add(new City(stringsArray[1], stringsArray[2], stringsArray[3], Integer.parseInt(stringsArray[4]), stringsArray[5]));
                }
                if (stringsArray.length == 5) {
                    citiesList.add(new City(stringsArray[1], stringsArray[2], stringsArray[3], Integer.parseInt(stringsArray[4]), null));
                }
        }

        Collections.sort(citiesList, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                boolean bool = o1.getRegion().equals(o2.getRegion());
                if (bool) {
                    return o1.getName().compareTo(o2.getName());
                }
                return 1;
            }
        });

        System.out.println(citiesList.size());
        for (City city : citiesList) {
            System.out.println(city.toString());
        }
        reader.close();
        is.close();

    }
}
