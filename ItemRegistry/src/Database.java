import java.sql.*;
import java.sql.Connection;

public class Database {
    Connection con;
    PreparedStatement pst;
    ResultSet result;
    // Constructor to establish a database connection
    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/item_shop", "root", "");
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error connecting to the database.");
            ex.printStackTrace();
        }
    }
}