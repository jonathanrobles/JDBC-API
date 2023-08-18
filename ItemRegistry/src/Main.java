import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

	private JFrame frame;
	private JTextField txtItemName;
	private JTextField txtCode;
	private JTextField txtPrice;
	private JTextField txtSearch;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		load.loadData(table);
		
		JLabel lblItemList = new JLabel("Item List");
		lblItemList.setBounds(761, 54, 137, 49);
		frame.getContentPane().add(lblItemList);
		lblItemList.setFont(new Font("Tahoma", Font.BOLD, 30));
	}
	
	Database database = new Database();//Database Connection
	Add_Item add = new Add_Item(database.con);//Add an Item Class/Object
	Update_Item update = new Update_Item(database.con);//Update an Item Class/Object
	Delete_Item delete = new Delete_Item(database.con); //Delete an Item Class/Object
	Load_Data load = new Load_Data(database.con);//Load Items Class/Object
	Item_Search search = new Item_Search(database.con);//Search an Item by id Class/Object

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBackground(new Color(192, 192, 192));
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setBounds(100, 100, 1148, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register an Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(62, 186, 465, 345);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Item Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(63, 72, 115, 24);
		panel.add(lblNewLabel_1);
		
		txtItemName = new JTextField();
		txtItemName.setBounds(188, 75, 248, 24);
		panel.add(txtItemName);
		txtItemName.setColumns(10);
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(188, 129, 248, 24);
		panel.add(txtCode);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(188, 180, 248, 24);
		panel.add(txtPrice);
		
		JLabel lblNewLabel_1_2 = new JLabel("Code:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(63, 129, 115, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Price:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(63, 177, 115, 24);
		panel.add(lblNewLabel_1_3);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = txtItemName.getText();
                String itemCode = txtCode.getText();
                String itemPrice = txtPrice.getText();

                add.addItem(itemName, itemCode, itemPrice);
                //reset text fields
                txtItemName.setText("");
                txtCode.setText("");
                txtPrice.setText("");
                txtItemName.requestFocus();
                load.loadData(table);
                
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setForeground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(74, 239, 137, 39);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//reset fields
                //reset text fields
                txtItemName.setText("");
                txtCode.setText("");
                txtPrice.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(261, 239, 137, 39);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(62, 104, 465, 71);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Item Code:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(45, 23, 73, 26);
		panel_1.add(lblNewLabel_2);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String id = txtSearch.getText();
				search.searchItem(id, txtItemName, txtCode, txtPrice);
			}
		});
		txtSearch.setBounds(128, 27, 247, 20);
		panel_1.add(txtSearch);
		txtSearch.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_2.setBounds(62, 22, 266, 71);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ITEM-Shop");
		lblNewLabel.setBounds(21, 11, 223, 49);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		
		JScrollPane itemTable = new JScrollPane();
		itemTable.setBounds(574, 104, 507, 284);
		frame.getContentPane().add(itemTable);
		
		table = new JTable();
		itemTable.setViewportView(table);
		
		
		JButton btnUpdate = new JButton("Update\r\n");
		btnUpdate.setForeground(new Color(0, 128, 0));
		btnUpdate.setBackground(new Color(192, 192, 192));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String itemName = txtItemName.getText();
                String itemCode = txtCode.getText();
                String itemPrice = txtPrice.getText();
                String id = txtSearch.getText();
                
				update.updateItem(itemName, itemCode, itemPrice, id);
				load.loadData(table);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(651, 424, 137, 39);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton_2_1 = new JButton("Delete");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtSearch.getText();
				
				delete.deleteItem(id);
                //reset text fields
				txtSearch.setText("");
                txtItemName.setText("");
                txtCode.setText("");
                txtPrice.setText("");
				load.loadData(table);
			}
		});
		btnNewButton_2_1.setBackground(new Color(192, 192, 192));
		btnNewButton_2_1.setForeground(new Color(255, 0, 0));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2_1.setBounds(866, 424, 137, 39);
		frame.getContentPane().add(btnNewButton_2_1);
	}
}