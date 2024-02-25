import java.sql.*;
import java.util.ArrayList;

/*
 * executeUpdate() is used for DML language returns the count of the affected Row
 * excuteQuery() is used for getting the data from the database 
 */
public class database {
    static String url = "jdbc:mysql://localhost:3306/";
    static String user = "root";
    static String password = "tarun@sql";
    static String db = "flights";

    // creates new records in database
    public void create(String query, ArrayList values) throws Exception {
        try {
            Connection connect = DriverManager.getConnection(url + db, user, password);
            PreparedStatement stm = connect.prepareStatement(query);
            for (int i = 0; i < values.size(); i++) {
                stm.setString(i + 1, values.get(i).toString());
            }
            stm.executeUpdate();
            System.out.println("VALUES ENTERED SUCCESFULLY");
            connect.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // deletes records from the database
    public void delete(String query, String value) throws Exception {
        Connection connect = DriverManager.getConnection(url + db, user, password);
        PreparedStatement stm = connect.prepareStatement(query);
        stm.setString(1, value);
        stm.executeUpdate();// used for DML language returns count of rows affected
    }

    // displays contents of the whole table
    public void display(String query, String details[]) throws Exception {
        Connection connect = DriverManager.getConnection(url + db, user, password);
        PreparedStatement stm = connect.prepareStatement(query);
        ResultSet rs = stm.executeQuery();
        for (String string : details) {
            System.out.print(string + "\t");
        }
        printdb(rs, details);
    }

    public void search(String id, String query, String details[]) throws Exception {
        Connection connect = DriverManager.getConnection(url + db, user, password);
        PreparedStatement stm = connect.prepareStatement(query);
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        for (String string : details) {
            System.out.print(string + "\t");
        }
        printdb(rs, details);
    }

    // prints the details of the received result set from the database
    public static void printdb(ResultSet rs, String details[]) throws Exception {
        while (rs.next()) {
            int i = 1;
            System.out.println();
            for (String string : details) {
                System.out.print(rs.getString(i) + "\t \t");
                i++;
            }
        }
    }
}