import java.sql.*;
import javax.swing.JOptionPane;

public class AddItem {
	//Constructor to initialize connection
	AddItem(Connection connection) {
		con = connection;
	}
	Connection con;
    PreparedStatement pst;
    // Method to add an item to the database
    public void addItem(String itemName, String itemCode, String itemPrice) {
        try {
            pst = con.prepareStatement("insert into items(item_name, item_code, price) values(?,?,?)");
            pst.setString(1, itemName);
            pst.setString(2, itemCode);
            pst.setString(3, itemPrice);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item Added Successfully");
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Error Adding an Item");
            ex.printStackTrace();
        }
    }
}
