import java.sql.*;

public class test {
    public static void main(String[] args) throws Exception {
        try {
            String url = "jdbc:mysql://localhost:3306/?user=root";
            String user = "root";
            String password = "tarun@sql";
            Connection connect = DriverManager.getConnection(url, user, password);
            Statement stm = connect.createStatement();
            String query = "use flights";
            
            boolean b = stm.execute(query);
            System.out.println("CONNECTION SUCESSFUL  " + b);
        } catch (Exception e) {
            System.out.println("could not connect");
        }
    }
}