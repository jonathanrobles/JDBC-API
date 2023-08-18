import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LoadData {
	//Constructor to initialize connection
	LoadData(Connection connection) {
		con = connection;
	}
    Connection con;
    PreparedStatement pst;
    ResultSet result;
    //Method to load data
    public void loadData(JTable table) {
    	try {
    		pst = con.prepareStatement("select * from items");
    		result = pst.executeQuery();
    		
            // Build the table model with column names and data rows
            DefaultTableModel tableModel = buildTableModel(result);

            // Set the table model to the JTable
            table.setModel(tableModel);
    	}catch(SQLException ex) {
    		JOptionPane.showMessageDialog(null, "Error Loading Data");
            ex.printStackTrace();
    	}
    }
    // Method to convert a ResultSet to a DefaultTableModel
    private DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Get column names
        Vector<String> columnNames = new Vector<>();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // Get data rows
        Vector<Vector<Object>> dataVector = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(resultSet.getObject(columnIndex));
            }
            dataVector.add(row);
        }
        return new DefaultTableModel(dataVector, columnNames);
    }
}