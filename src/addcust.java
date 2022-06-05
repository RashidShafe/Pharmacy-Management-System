import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class addcust extends JFrame {

	Connection con=null;
	Statement stmt=null;  
	ResultSet rs=null;

	private JPanel contentPane;
	private JTextField pfield;
	private JTextField nfield;
	private JTextField afield;
	private JTextField dfield;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addcust frame = new addcust();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public addcust() {
		setTitle("Add customer");
		con=Connect.connect();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 442);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton extbtn = new JButton("EXIT");
		extbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, extbtn, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, extbtn, -27, SpringLayout.EAST, contentPane);
		extbtn.setFont(new Font("Miriam Libre", Font.BOLD, 13));
		extbtn.setBackground(new Color(220, 20, 60));
		contentPane.add(extbtn);
		
		JLabel plbl = new JLabel("Phone NO:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, plbl, 73, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, plbl, 61, SpringLayout.WEST, contentPane);
		plbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(plbl);
		
		pfield = new JTextField();
		pfield.setBackground(SystemColor.info);
		sl_contentPane.putConstraint(SpringLayout.EAST, plbl, -11, SpringLayout.WEST, pfield);
		sl_contentPane.putConstraint(SpringLayout.EAST, pfield, -108, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, pfield, 136, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pfield, 70, SpringLayout.NORTH, contentPane);
		pfield.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(pfield);
		pfield.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, plbl, -8, SpringLayout.NORTH, lblName);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, plbl);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAddress, 0, SpringLayout.EAST, plbl);
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblAddress);
		
		JLabel lblDue = new JLabel("Due:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDue, 99, SpringLayout.WEST, contentPane);
		lblDue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblDue);
		
		nfield = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblName, 2, SpringLayout.NORTH, nfield);
		sl_contentPane.putConstraint(SpringLayout.NORTH, nfield, 6, SpringLayout.SOUTH, pfield);
		sl_contentPane.putConstraint(SpringLayout.WEST, nfield, 0, SpringLayout.WEST, pfield);
		sl_contentPane.putConstraint(SpringLayout.EAST, nfield, 0, SpringLayout.EAST, pfield);
		nfield.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nfield.setColumns(10);
		contentPane.add(nfield);
		
		afield = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAddress, 2, SpringLayout.NORTH, afield);
		sl_contentPane.putConstraint(SpringLayout.NORTH, afield, 6, SpringLayout.SOUTH, nfield);
		sl_contentPane.putConstraint(SpringLayout.WEST, afield, 0, SpringLayout.WEST, pfield);
		sl_contentPane.putConstraint(SpringLayout.EAST, afield, -108, SpringLayout.EAST, contentPane);
		afield.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		afield.setColumns(10);
		contentPane.add(afield);
		
		dfield = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDue, 2, SpringLayout.NORTH, dfield);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblDue, -6, SpringLayout.WEST, dfield);
		sl_contentPane.putConstraint(SpringLayout.NORTH, dfield, 6, SpringLayout.SOUTH, afield);
		sl_contentPane.putConstraint(SpringLayout.WEST, dfield, 0, SpringLayout.WEST, pfield);
		sl_contentPane.putConstraint(SpringLayout.EAST, dfield, 0, SpringLayout.EAST, pfield);
		dfield.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		dfield.setColumns(10);
		contentPane.add(dfield);
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				stmt=con.createStatement();  
				stmt.executeUpdate("INSERT INTO `customer`(`phone no`, `name`, `address`, `due`) VALUES ("+"'"+pfield.getText()+"','"+nfield.getText()+"','"+afield.getText()+"','"+dfield.getText()+"')"); 
				JOptionPane.showMessageDialog(btnAdd,"Customer added successfully","Alert",JOptionPane.WARNING_MESSAGE);
				pfield.setText("");
				nfield.setText("");
				afield.setText("");
				dfield.setText("");
				} catch(Exception ez){ JOptionPane.showMessageDialog(btnAdd,"Error in input","Alert",JOptionPane.DEFAULT_OPTION);}
			}
		});
		btnAdd.setBackground(new Color(0, 128, 128));
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, 54, SpringLayout.SOUTH, dfield);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 166, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 250, SpringLayout.WEST, contentPane);
		contentPane.add(btnAdd);
	}

}
