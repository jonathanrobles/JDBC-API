import java.sql.*;
import javax.swing.JOptionPane;

public class DeleteItem {
	//Constructor to initialize connection
	DeleteItem(Connection connection) {
		con = connection;
	}
	Connection con;
    PreparedStatement pst;
    // Method to add an item to the database
    public void deleteItem(String id) {
        try {
            pst = con.prepareStatement("delete from items where id = ?");
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item Deleted Successfully");
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Error Deleting an Item");
            ex.printStackTrace();
        }
    }
}