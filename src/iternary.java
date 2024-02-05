import java.io.File;
import java.util.Scanner;
import java.util.HashMap;

public class iternary extends test{

    public void getplace() throws Exception {
        File i_file = new File("D://PROGRAMS//Flight_Reservation -sql//lib//flight itinerary.txt");
        HashMap<String, String> map = new HashMap<>();
        Scanner isc = new Scanner(i_file);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the destination");
        String search = sc.next();
        isc.useDelimiter("$");
        while(isc.hasNext())
        {
            String a=isc.nextLine();
            String place[]=a.split("#");
            map.put(place[0].replace("$", ""), place[1]);
        }
        System.out.println(map.get(search));
    }
}