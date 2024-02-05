import java.sql.*;

public class test {
    public static void main(String[] args) throws Exception {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "tarun@sql";
            String db = "flights";
            Connection connect = DriverManager.getConnection(url + db, user, password);
            String query = "select * from flight";
            Statement stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
            System.out.println("CONNECTION SUCCESFUL");
            connect.close();
        } catch (Exception e) {
            System.out.println("could not connect");
            System.out.println(e);
        }
    }
}