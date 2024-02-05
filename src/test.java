import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
public class test {
    static String url = "jdbc:mysql://localhost:3306/";
    static String user = "root";
    static String password = "tarun@sql";
    static String db = "flights";
    public void read() throws Exception {
        try {
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
    public void write(String query,ArrayList values)throws Exception
    {
        try{
            Connection connect =DriverManager.getConnection(url+db, user, password);
            PreparedStatement stm= connect.prepareStatement(query);
            for(int i=0;i<values.size();i++)
            {
                stm.setString(i+1, values.get(i).toString());
            }
            stm.executeUpdate();
            System.out.println("VALUES ENTERED SUCCESFULLY");
            connect.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void delete(String query,String value)throws Exception
    {
        Connection connect =DriverManager.getConnection(url+db, user, password);
        PreparedStatement stm= connect.prepareStatement(query);
        stm.setString(1, value);
        stm.executeUpdate();
    }
}