import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ItemSearch {
	//Constructor to initialize connection
	ItemSearch(Connection connection) {
		con = connection;
	}
	Connection con;
    PreparedStatement pst;
    ResultSet result;
	//Method for item search by id
	void searchItem(String id, JTextField txtItemName, JTextField txtCode, JTextField txtPrice) {
		try {
			pst = con.prepareStatement("select item_name, item_code, price from items where item_code = ?");
			pst.setString(1, id);
			result = pst.executeQuery();
			
			if(result.next() == true) {
				String item_name = result.getString(1);
				String item_code = result.getString(2);
				String price = result.getString(3);
				
				txtItemName.setText(item_name);
				txtCode.setText(item_code);
				txtPrice.setText(price);
				
			}else {
				txtItemName.setText("");
				txtCode.setText("");
				txtPrice.setText("");
			}
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Encountered an error");
            ex.printStackTrace();
		}
	}
}