import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class reservation extends passenger {
    static Scanner sc = new Scanner(System.in);
    static File r_file = new File("Reservations.txt");
    static String r_details[] = { "TICKET NUMBER", "SOURCE", "DESTINATION", "ADHAR NUMBER", "PASSPORT NUMBER", "DATE",
            "DEPARTURE TIME", "CLASS(First/Economy)", "TYPE OF TRAVEL(International/Domestic)", "STATUS" };
    static String reservation[] = new String[10];// stores resrvation information
    static reservation r = new reservation();

    // books tickets
    public void Book_ticket() throws FileNotFoundException {
        String details[] = new String[10];
        PrintWriter pw = new PrintWriter(r_file);
        System.out.println("Enter the number of reservations");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String temp = "";
            for (String d : r_details) {
                System.out.println("Enter " + d);
                temp = temp + sc.next() + ",";
            }
            details[i] = temp;
        }
        for (String d : details) {
            if (d == null)
                break;
            pw.write(d + '#');
        }
        pw.close();
    }

    // cancels tickets
    public void Cancel_ticket(String tn) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(r_file);
        boolean find = true;
        for (int i = 0; i < reservation.length; i++) {
            if (reservation[i] == null)
                break;
            if (reservation[i].startsWith(tn)) {
                reservation[i] = "del";
                find = false;
                break;
            }
        }
        if (find) {
            System.out.println("TICKET NUMBER NOT FOUND");
        } else {
            for (String d : reservation) {
                if (d == null) {
                    break;
                } else if (d.equals("del")) {
                    continue;
                } else {
                    pw.write(d + "#");
                }
            }
        }
        pw.close();
    }

    // reads the reservation file
    public void read_reservation() throws FileNotFoundException {
        int n = 0;
        Arrays.fill(reservation, null);
        Scanner f = new Scanner(r_file);
        f.useDelimiter("#");
        while (f.hasNext()) {
            reservation[n] = f.next();
            n++;
        }
    }

    // calls all the functions under the reservation module
    public void reservationcall() throws FileNotFoundException {
        boolean control = true;
        while (control) {
            System.out.println("Enter 1 to book a ticket");
            System.out.println("Enter 2 to cancel a ticket");
            System.out.println("Enter 0 to exit reservation module");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    r.Book_ticket();
                    break;
                case 2:
                    r.read_reservation();
                    System.out.println("Enter the ticket number to cancel the ticket");
                    r.Cancel_ticket(sc.next());
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

    // main function to call the modules
    public static void main(String args[]) throws Exception {
        while (true) {
            System.out.println("Enter 1 to access passenger details");
            System.out.println("Enter 2 to access flight details");
            System.out.println("Enter 3 to reserve the tickets");
            System.out.println("Enter 4 to get the intenaries");
            System.out.println("Enter 0 to exit");
            int module_choice = sc.nextInt();
            if (module_choice == 1) {
                r.passengercall();
            } else if (module_choice == 2) {
                r.flightcall();
            } else if (module_choice == 3) {
                r.reservationcall();
            } else if (module_choice == 4) {
                r.getplace();
            } else if (module_choice == 0) {
                break;
            } else {
                System.out.println("Wrong choice\nTRY AGAIN");
            }
        }
    }
}