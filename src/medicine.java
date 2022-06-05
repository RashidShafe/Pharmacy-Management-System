import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;

public class medicine extends JFrame {
	String typ,date,a;
	int x=0;
	Connection con=null;
	Statement stmt=null;  
	ResultSet rs=null;
	private JPanel contentPane;
	private JTextField nmfld;
	private JTextField manfld;
	private JTextField pricefld;
	private JTextField expfld;
	private JTextField pfld;
	private JTextField stockfld;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					medicine frame = new medicine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public medicine() {
		setTitle("Manage medicine");
		con=Connect.connect();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 482);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton extbtn = new JButton("EXIT");
		sl_contentPane.putConstraint(SpringLayout.NORTH, extbtn, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, extbtn, 373, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, extbtn, -10, SpringLayout.EAST, contentPane);
		extbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		extbtn.setFont(new Font("Miriam Libre", Font.BOLD, 13));
		extbtn.setBackground(new Color(220, 20, 60));
		contentPane.add(extbtn);
		
		JLabel lblName = new JLabel("Name:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblName, 65, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblName, 75, SpringLayout.WEST, contentPane);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblName);
		
		JLabel lblManufacturer = new JLabel("Manufacturer:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblManufacturer, 16, SpringLayout.SOUTH, lblName);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblManufacturer, 0, SpringLayout.EAST, lblName);
		lblManufacturer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManufacturer.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblManufacturer);
		
		JLabel lblUnitPrice = new JLabel("Unit price:");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUnitPrice, 0, SpringLayout.EAST, lblName);
		lblUnitPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnitPrice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblUnitPrice);
		
		JLabel lblExpireDate = new JLabel("Expire date:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblExpireDate, 19, SpringLayout.SOUTH, lblUnitPrice);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblExpireDate, 0, SpringLayout.EAST, lblName);
		lblExpireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExpireDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblExpireDate);
		
		nmfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, nmfld, -1, SpringLayout.NORTH, lblName);
		sl_contentPane.putConstraint(SpringLayout.WEST, nmfld, 6, SpringLayout.EAST, lblName);
		sl_contentPane.putConstraint(SpringLayout.EAST, nmfld, 195, SpringLayout.EAST, lblName);
		contentPane.add(nmfld);
		nmfld.setColumns(10);
		
		manfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, manfld, -1, SpringLayout.NORTH, lblManufacturer);
		sl_contentPane.putConstraint(SpringLayout.WEST, manfld, 6, SpringLayout.EAST, lblManufacturer);
		sl_contentPane.putConstraint(SpringLayout.EAST, manfld, 0, SpringLayout.EAST, nmfld);
		manfld.setColumns(10);
		contentPane.add(manfld);
		
		JComboBox tcb = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tcb, -5, SpringLayout.NORTH, lblName);
		sl_contentPane.putConstraint(SpringLayout.WEST, tcb, 9, SpringLayout.EAST, nmfld);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tcb, -346, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tcb, -10, SpringLayout.EAST, contentPane);
		tcb.setBackground(SystemColor.info);
		tcb.setFont(new Font("Segoe Print", Font.PLAIN, 13));
		tcb.setModel(new DefaultComboBoxModel(new String[] {"Tablet", "Syrup", "Injection", "Capsule", "suppositories", "Drops", "Inhaler"}));
		contentPane.add(tcb);
		
		pricefld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUnitPrice, 1, SpringLayout.NORTH, pricefld);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pricefld, 13, SpringLayout.SOUTH, manfld);
		sl_contentPane.putConstraint(SpringLayout.WEST, pricefld, 117, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pricefld, -212, SpringLayout.EAST, contentPane);
		pricefld.setColumns(10);
		contentPane.add(pricefld);
		
		expfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, expfld, -2, SpringLayout.NORTH, lblExpireDate);
		sl_contentPane.putConstraint(SpringLayout.WEST, expfld, 6, SpringLayout.EAST, lblExpireDate);
		sl_contentPane.putConstraint(SpringLayout.EAST, expfld, 0, SpringLayout.EAST, nmfld);
		expfld.setColumns(10);
		contentPane.add(expfld);
		
		JLabel lblNewLabel = new JLabel("(TK)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, -14, SpringLayout.SOUTH, lblUnitPrice);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 11, SpringLayout.EAST, pricefld);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, lblUnitPrice);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 37, SpringLayout.EAST, pricefld);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblInStock = new JLabel("In Stock:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblInStock, 0, SpringLayout.WEST, lblUnitPrice);
		lblInStock.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInStock.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblInStock);
		
		
		
		JLabel lblbox = new JLabel("(Box)");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblbox, 1, SpringLayout.NORTH, lblInStock);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblbox, 0, SpringLayout.EAST, lblNewLabel);
		lblbox.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		contentPane.add(lblbox);
		
		JLabel lblPosition = new JLabel("Position:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPosition, 6, SpringLayout.SOUTH, lblInStock);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPosition, 10, SpringLayout.WEST, lblInStock);
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblPosition);
		
		pfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pfld, 6, SpringLayout.SOUTH, lblbox);
		sl_contentPane.putConstraint(SpringLayout.WEST, pfld, 0, SpringLayout.WEST, nmfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, pfld, 0, SpringLayout.EAST, nmfld);
		pfld.setColumns(10);
		contentPane.add(pfld);
		
		JLabel lblexShelfRow = new JLabel("(ex: shelf-1 Row-2 Right side)");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblexShelfRow, 2, SpringLayout.SOUTH, pfld);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblexShelfRow, 0, SpringLayout.WEST, nmfld);
		lblexShelfRow.setBackground(SystemColor.activeCaption);
		lblexShelfRow.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		contentPane.add(lblexShelfRow);
		
		stockfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblInStock, 1, SpringLayout.NORTH, stockfld);
		sl_contentPane.putConstraint(SpringLayout.WEST, stockfld, 0, SpringLayout.WEST, nmfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, stockfld, 0, SpringLayout.EAST, pricefld);
		stockfld.setColumns(10);
		contentPane.add(stockfld);
		
		JButton btnAdd = new JButton("ADD");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, 21, SpringLayout.SOUTH, lblexShelfRow);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 0, SpringLayout.WEST, nmfld);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, -116, SpringLayout.SOUTH, contentPane);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					typ=""+tcb.getItemAt(tcb.getSelectedIndex());
					date=expfld.getText();
				stmt=con.createStatement();  
				stmt.executeUpdate("INSERT INTO `medicine`(`name`, `manufacturer`, `type`, `unit price`, `expire date`, `stock`, `position`) VALUES "
						+ "('"+nmfld.getText()+"', '"+manfld.getText()+"', '"+typ+"', '"+pricefld.getText()+"', '"+date+"', '"+stockfld.getText()+"', '"+pfld.getText()+"');"); 
				JOptionPane.showMessageDialog(btnAdd,"Added successfully","Alert",JOptionPane.WARNING_MESSAGE);
				} catch(Exception ez){ JOptionPane.showMessageDialog(btnAdd,"Error in input","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
		btnAdd.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnAdd.setBackground(new Color(0, 128, 128));
		contentPane.add(btnAdd);
		
		JLabel lblyyyymmdd = new JLabel("(YYYY-MM-DD)");
		sl_contentPane.putConstraint(SpringLayout.NORTH, stockfld, 6, SpringLayout.SOUTH, lblyyyymmdd);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblyyyymmdd, 0, SpringLayout.SOUTH, expfld);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblyyyymmdd, 0, SpringLayout.WEST, nmfld);
		lblyyyymmdd.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblyyyymmdd.setBackground(SystemColor.activeCaption);
		contentPane.add(lblyyyymmdd);
		
		JButton btnDelete = new JButton("Delete");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDelete, -229, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnDelete, 0, SpringLayout.EAST, nmfld);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nmfld.getText().equals(""))
						JOptionPane.showMessageDialog(btnDelete,"No data selected","Alert",JOptionPane.WARNING_MESSAGE);
					else {
						typ=""+tcb.getItemAt(tcb.getSelectedIndex());
					stmt=con.createStatement();  
					stmt.executeUpdate("delete from `medicine` where `name`='"+nmfld.getText()+"' and `type`='"+typ+"';");
					nmfld.setText("");
					manfld.setText("");
					pricefld.setText("");
					expfld.setText("");
					stockfld.setText("");
					pfld.setText("");
					x=0;
					JOptionPane.showMessageDialog(btnDelete,"Deleted!","Alert",JOptionPane.YES_OPTION);}
					} catch(Exception ez){ }
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		btnDelete.setBackground(Color.RED);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDelete, 0, SpringLayout.NORTH, btnClear);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnClear, 16, SpringLayout.SOUTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnClear, 0, SpringLayout.WEST, nmfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnClear, 0, SpringLayout.EAST, btnAdd);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nmfld.setText("");
				manfld.setText("");
				pricefld.setText("");
				expfld.setText("");
				stockfld.setText("");
				pfld.setText("");
				x=0;
			}
		});
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnClear.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		btnClear.setBackground(Color.CYAN);
		contentPane.add(btnClear);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nmfld.getText().equals(""))
						JOptionPane.showMessageDialog(btnUpdate,"No data selected","Alert",JOptionPane.WARNING_MESSAGE);
					else {
						typ=""+tcb.getItemAt(tcb.getSelectedIndex());
					stmt=con.createStatement();  
					stmt.executeUpdate("UPDATE `medicine` SET `name`='"+nmfld.getText()+"',`manufacturer`='"+manfld.getText()+"',`type`='"+tcb.getItemAt(tcb.getSelectedIndex())+"',`unit price`='"+pricefld.getText()+"',"
							+ "`expire date`='"+expfld.getText()+"',`stock`='"+stockfld.getText()+"',`position`='"+pfld.getText()+"' WHERE `name`= '"+a+"' and `type` = '"+typ+"';");
					
					JOptionPane.showMessageDialog(btnUpdate,"Updated","Alert",JOptionPane.WARNING_MESSAGE);}
					} catch(Exception ez){ }
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, btnDelete);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnUpdate, 0, SpringLayout.SOUTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnUpdate, 0, SpringLayout.EAST, nmfld);
		btnUpdate.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnUpdate.setBorder(BorderFactory.createBevelBorder(0));
		btnUpdate.setBackground(new Color(135, 206, 250));
		contentPane.add(btnUpdate);
		JButton Search = new JButton("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					typ=""+tcb.getItemAt(tcb.getSelectedIndex());
					stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from `medicine` where `name`= '"+nmfld.getText()+"' and `type` = '"+typ+"';");
					while(rs.next())
					{
						if((nmfld.getText().equals(rs.getString(1)) && (typ.equals(rs.getString(3))) ))
						{
							manfld.setText(rs.getString(2));
							pricefld.setText(rs.getString(4));
							expfld.setText(rs.getString(5));
							stockfld.setText(rs.getString(6));
							pfld.setText(rs.getString(7));
							a=nmfld.getText();
							x=1;
							break;
						}
					}
					if(x==0)
					{
						JOptionPane.showMessageDialog(Search,"No data Found","Alert",JOptionPane.WARNING_MESSAGE);
					}
					} catch(Exception ez){ }
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, Search, -6, SpringLayout.NORTH, lblUnitPrice);
		sl_contentPane.putConstraint(SpringLayout.WEST, Search, 41, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, Search, -10, SpringLayout.EAST, contentPane);
		Search.setFont(new Font("Segoe Print", Font.BOLD, 13));
		Search.setBorder(BorderFactory.createBevelBorder(0));
		Search.setBackground(new Color(135, 206, 250));
		contentPane.add(Search);
		
		
	}
}
