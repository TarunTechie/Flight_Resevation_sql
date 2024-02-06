import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class Flight extends iternary {
    static Scanner sc = new Scanner(System.in);
    static String f_details[] = { "FLIGHT NUMBER", "FLIGHT NAME", "SOURCE", "DESTINATION", "DEPARTURE TIMING" };
    static String flight[] = new String[5];// contains the details from the file
    static Flight f = new Flight();

    // add flight
    public void Add_Flight() throws Exception {
        System.out.println("Enter the number of flights");
        int n = sc.nextInt();
        ArrayList<String> values = new ArrayList<String>();
        String query = "insert into flight (flno,flname,source,destination,detime) values(?,?,?,?,?);";
        for (int i = 0; i < n; i++) {
            System.out.println("FLIGHT " + (i + 1));
            for (String d : f_details) {
                System.out.println("Enter " + d);
                values.add(sc.next());
            }
            f.write(query, values);
            values.clear();
        }
    }

    // delete flight
    public void Delete_Flight(String fn) throws Exception {
        String query="delete from flight where flno=(?)";
        f.delete(query, fn);
    }

    // searchs for flight
    public void Search_Flight(String fn) {
        boolean f = true;
        for (String d : flight) {
            if (d == null)
                break;
            if (d.startsWith(fn)) {
                System.out.println(Arrays.toString(f_details).replace(",", '\t' + "|"));
                System.out.println(d.replace(",", '\t' + "|"));
                f = false;
                break;
            }
        }
        if (f)
            System.out.println("FLIGHT NOT FOUND");
    }

    // display flight
    public void Display_Flight()throws Exception {
        f.search("select * from flight",f_details);
    }
    // calls all the functions under the flight module
    public void flightcall() throws Exception {
        Flight f = new Flight();
        boolean control = true;
        while (control) {
            System.out.println("\n Enter 1 to Add A Flight");
            System.out.println("Enter 2 to Delete A Flight");
            System.out.println("Enter 3 to Search A Flight");
            System.out.println("Enter 4 to Display A Flight");
            System.out.println("Enter 0 to exit Flight detials module");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    f.Add_Flight();
                    break;
                case 2:
                    f.read();
                    System.out.println("Enter the flight number to delete");
                    f.Delete_Flight(sc.next());
                    break;
                case 3:
                    System.out.println("Enter the flight number to search");
                    f.Search_Flight(sc.next());
                    break;
                case 4:
                    f.Display_Flight();
                    break;
                case 0:
                    control = false;
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }
    }
}