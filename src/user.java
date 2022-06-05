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
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;

public class user extends JFrame {
	int chk=0,x=0;
	String phn;
	Connection con=null;
	Statement stmt=null;  
	ResultSet rs=null;

	private JPanel contentPane;
	private JTextField usrnmfld;
	private JPasswordField pfld;
	private JTextField adrsfld;
	private JTextField nmfld;
	private JTextField phnfld;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user frame = new user();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public user() {
		setTitle("Manage user");
		con=Connect.connect();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 412);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton extbtn = new JButton("EXIT");
		sl_contentPane.putConstraint(SpringLayout.NORTH, extbtn, 0, SpringLayout.NORTH, contentPane);
		extbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		extbtn.setFont(new Font("Miriam Libre", Font.BOLD, 13));
		extbtn.setBackground(new Color(220, 20, 60));
		contentPane.add(extbtn);
		
		JLabel namelbl = new JLabel("User name:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, namelbl, 169, SpringLayout.NORTH, contentPane);
		namelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		namelbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(namelbl);
		
		usrnmfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, usrnmfld, -174, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, namelbl, -6, SpringLayout.WEST, usrnmfld);
		sl_contentPane.putConstraint(SpringLayout.WEST, usrnmfld, 138, SpringLayout.WEST, contentPane);
		usrnmfld.setBackground(SystemColor.inactiveCaptionBorder);
		usrnmfld.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(usrnmfld);
		usrnmfld.setColumns(10);
		
		JLabel passlbl = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, passlbl, 197, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, namelbl, 0, SpringLayout.WEST, passlbl);
		sl_contentPane.putConstraint(SpringLayout.WEST, passlbl, 62, SpringLayout.WEST, contentPane);
		passlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		passlbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(passlbl);
		
		JLabel adas = new JLabel("Add as:");
		sl_contentPane.putConstraint(SpringLayout.EAST, passlbl, 0, SpringLayout.EAST, adas);
		sl_contentPane.putConstraint(SpringLayout.WEST, adas, 62, SpringLayout.WEST, contentPane);
		adas.setHorizontalAlignment(SwingConstants.RIGHT);
		adas.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(adas);
		JButton btnAdd = new JButton("ADD");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 0, SpringLayout.WEST, usrnmfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, -222, SpringLayout.EAST, contentPane);
		btnAdd.setFont(new Font("Segoe Print", Font.BOLD, 15));
		String to[]={"Admin","Employee"};
		JComboBox cbox = new JComboBox(to);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, 16, SpringLayout.SOUTH, cbox);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 43, SpringLayout.SOUTH, cbox);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, cbox, -116, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, cbox, 136, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, cbox, -106, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, usrnmfld, 0, SpringLayout.EAST, cbox);
		sl_contentPane.putConstraint(SpringLayout.NORTH, adas, 2, SpringLayout.NORTH, cbox);
		sl_contentPane.putConstraint(SpringLayout.EAST, adas, -6, SpringLayout.WEST, cbox);
		cbox.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		cbox.setMaximumRowCount(2);
		contentPane.add(cbox);
		
		//
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String addas=""+cbox.getItemAt(cbox.getSelectedIndex());
					if(addas.equals("Admin"))
						chk=1;
					else
						chk=0;
				stmt=con.createStatement();  
				stmt.executeUpdate("INSERT INTO `user`(`phone`, `name`, `address`, `user name`, `password`, `value`) VALUES "
						+ "("+"'"+phnfld.getText()+"', '"+nmfld.getText()+"', '"+adrsfld.getText()+"', '"+usrnmfld.getText()+"','"+pfld.getText()+"','"+chk+"')"); 
				JOptionPane.showMessageDialog(btnAdd,"User added successfully","Alert",JOptionPane.DEFAULT_OPTION);
				nmfld.setText("");
				adrsfld.setText("");
				usrnmfld.setText("");
				pfld.setText("");

				} catch(Exception ez){ JOptionPane.showMessageDialog(btnAdd,"Error in input","Alert",JOptionPane.WARNING_MESSAGE);}
			}
		});
		btnAdd.setBackground(new Color(0, 128, 128));
		contentPane.add(btnAdd);
		
		
		pfld = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, cbox, 9, SpringLayout.SOUTH, pfld);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pfld, -2, SpringLayout.NORTH, passlbl);
		sl_contentPane.putConstraint(SpringLayout.WEST, pfld, 0, SpringLayout.WEST, usrnmfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, pfld, -106, SpringLayout.EAST, contentPane);
		contentPane.add(pfld);
		
		adrsfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, adrsfld, 0, SpringLayout.WEST, usrnmfld);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, adrsfld, -6, SpringLayout.NORTH, usrnmfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, adrsfld, 0, SpringLayout.EAST, usrnmfld);
		adrsfld.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		adrsfld.setColumns(10);
		adrsfld.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(adrsfld);
		
		JLabel lblAddress = new JLabel("Address:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAddress, 3, SpringLayout.NORTH, adrsfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAddress, 0, SpringLayout.EAST, namelbl);
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblAddress);
		
		JLabel lblName = new JLabel("Name:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblName, 94, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblName, -11, SpringLayout.NORTH, lblAddress);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblName, -2, SpringLayout.EAST, namelbl);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblName);
		
		nmfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, nmfld, -1, SpringLayout.NORTH, lblName);
		sl_contentPane.putConstraint(SpringLayout.WEST, nmfld, 8, SpringLayout.EAST, lblName);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, nmfld, -7, SpringLayout.NORTH, adrsfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, nmfld, 0, SpringLayout.EAST, usrnmfld);
		nmfld.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nmfld.setColumns(10);
		nmfld.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(nmfld);
		
		JLabel lblPhoneNo = new JLabel("Phone no:");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblPhoneNo, -6, SpringLayout.NORTH, lblName);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPhoneNo, 0, SpringLayout.EAST, namelbl);
		lblPhoneNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblPhoneNo);
		
		phnfld = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, phnfld, 0, SpringLayout.NORTH, lblPhoneNo);
		sl_contentPane.putConstraint(SpringLayout.WEST, phnfld, 6, SpringLayout.EAST, lblPhoneNo);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, phnfld, -5, SpringLayout.NORTH, nmfld);
		phnfld.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		phnfld.setColumns(10);
		phnfld.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(phnfld);
		
		JButton Searchbtn = new JButton("Search");
		sl_contentPane.putConstraint(SpringLayout.EAST, phnfld, -47, SpringLayout.WEST, Searchbtn);
		sl_contentPane.putConstraint(SpringLayout.NORTH, Searchbtn, 50, SpringLayout.SOUTH, extbtn);
		sl_contentPane.putConstraint(SpringLayout.WEST, Searchbtn, 325, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, Searchbtn, -257, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Searchbtn, 0, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, extbtn, 0, SpringLayout.EAST, Searchbtn);
		Searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from `user` where `phone`= '"+phnfld.getText()+"';");
					while(rs.next())
					{
						if(phnfld.getText().equals(rs.getString(1)))
						{
							nmfld.setText(rs.getString(2));
							adrsfld.setText(rs.getString(3));
							usrnmfld.setText(rs.getString(4));
							pfld.setText(rs.getString(5));
							phn=phnfld.getText();
							if(rs.getInt(6)==1)
								cbox.setSelectedItem(to[0]);
							else
								cbox.setSelectedItem(to[1]);
							x=1;
							break;
						}
					}
					if(x==0)
					{
						JOptionPane.showMessageDialog(Searchbtn,"No data Found","Alert",JOptionPane.WARNING_MESSAGE);
					}
					} catch(Exception ez){ }
			}
		});
		Searchbtn.setFont(new Font("Segoe Print", Font.BOLD, 13));
		Searchbtn.setBorder(BorderFactory.createBevelBorder(0));
		Searchbtn.setBackground(new Color(135, 206, 250));
		contentPane.add(Searchbtn);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(phnfld.getText().equals(""))
						JOptionPane.showMessageDialog(btnDelete,"No data selected","Alert",JOptionPane.WARNING_MESSAGE);
					else {
					stmt=con.createStatement();  
					stmt.executeUpdate("delete from `user` where `phone`='"+phnfld.getText()+"';");
					phnfld.setText("");
					nmfld.setText("");
					usrnmfld.setText("");
					adrsfld.setText("");
					pfld.setText("");
					JOptionPane.showMessageDialog(Searchbtn,"Deleted!","Alert",JOptionPane.YES_OPTION);x=0;}
					} catch(Exception ez){ }
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDelete, 16, SpringLayout.SOUTH, cbox);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDelete, 42, SpringLayout.EAST, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnDelete, 0, SpringLayout.SOUTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnDelete, 0, SpringLayout.EAST, usrnmfld);
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		btnDelete.setBackground(Color.RED);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(phnfld.getText().equals(""))
						JOptionPane.showMessageDialog(btnUpdate,"No data selected","Alert",JOptionPane.WARNING_MESSAGE);
					else {
					stmt=con.createStatement();  
					stmt.executeUpdate("UPDATE `user` SET `phone`='"+phnfld.getText()+"', `name`='"+nmfld.getText()+"',"
							+ "`address`='"+adrsfld.getText()+"',`user name`='"+usrnmfld.getText()+"', `password`='"+pfld.getText()+"' WHERE `phone`='"+phn+"';");
					
					nmfld.setText("");
					adrsfld.setText("");
					usrnmfld.setText("");
					pfld.setText("");
					x=0;
					JOptionPane.showMessageDialog(Searchbtn,"Updated","Alert",JOptionPane.WARNING_MESSAGE);}
					} catch(Exception ez){JOptionPane.showMessageDialog(Searchbtn,"ERROR!","Alert",JOptionPane.WARNING_MESSAGE);} 
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnUpdate, 6, SpringLayout.SOUTH, btnAdd);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, usrnmfld);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnUpdate, 70, SpringLayout.WEST, usrnmfld);
		btnUpdate.setFont(new Font("Segoe Print", Font.BOLD, 13));
		btnUpdate.setBorder(BorderFactory.createBevelBorder(0));
		btnUpdate.setBackground(new Color(135, 206, 250));
		contentPane.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phnfld.setText("");
				nmfld.setText("");
				adrsfld.setText("");
				usrnmfld.setText("");
				pfld.setText("");
				x=0;
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnClear, 6, SpringLayout.SOUTH, btnDelete);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnClear, 0, SpringLayout.WEST, btnDelete);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnClear, 0, SpringLayout.EAST, usrnmfld);
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Segoe Print", Font.BOLD, 13));
		btnClear.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		btnClear.setBackground(Color.CYAN);
		contentPane.add(btnClear);
	}
}
