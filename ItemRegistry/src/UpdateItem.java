import java.sql.*;
import javax.swing.JOptionPane;

public class UpdateItem {
	//Constructor to initialize connection
	UpdateItem(Connection connection) {
		con = connection;
	}
	Connection con;
    PreparedStatement pst;
    // Method to add an item to the database
    public void updateItem(String itemName, String itemCode, String itemPrice, String id) {
        try {
            pst = con.prepareStatement("update items set item_name = ?, item_code = ?, price = ? where id = ?");
            pst.setString(1, itemName);
            pst.setString(2, itemCode);
            pst.setString(3, itemPrice);
            pst.setString(4, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item Updated Successfully");
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Error Updating an Item");
            ex.printStackTrace();
        }
    }
}