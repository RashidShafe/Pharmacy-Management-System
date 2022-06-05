import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class customerpanel extends JPanel {
	int x=0;
	String phn;
	Connection con=null;
	Statement stmt=null;  
	ResultSet rs=null;
	
	private JTextField srchfld;
	private JTextField namefld;
	private JTextField addrsfld;
	private JTextField duefld;

	public customerpanel() {
		setBackground(SystemColor.scrollbar);
		con=Connect.connect();
		setBorder(new LineBorder(Color.BLUE, 1, true));
		setBounds(244, 0, 780, 595);
		setLayout(null);
		
		JButton addc = new JButton("Add Customer");
		addc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcust ac=new addcust();
				ac.setVisible(true);
				ac.setLocationRelativeTo(null);
			}
		});
		addc.setBounds(625, 11, 145, 31);
		addc.setFont(new Font("Segoe Print", Font.BOLD, 15));
		addc.setBorder(BorderFactory.createBevelBorder(0));
		addc.setBackground(new Color(135, 206, 250));
		add(addc);
		
		JLabel srchlbl = new JLabel("Search customer by contact no.");
		srchlbl.setFont(new Font("Source Sans Pro Semibold", Font.ITALIC, 13));
		srchlbl.setBackground(Color.YELLOW);
		srchlbl.setBounds(265, 147, 188, 31);
		add(srchlbl);
		
		srchfld = new JTextField();
		srchfld.setBounds(265, 175, 188, 20);
		add(srchfld);
		srchfld.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Source Sans Pro Semibold", Font.ITALIC, 13));
		lblName.setBackground(Color.YELLOW);
		lblName.setBounds(126, 250, 129, 31);
		add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Source Sans Pro Semibold", Font.ITALIC, 13));
		lblAddress.setBackground(Color.YELLOW);
		lblAddress.setBounds(126, 282, 129, 31);
		add(lblAddress);
		
		JLabel lblDue = new JLabel("Due:");
		lblDue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDue.setFont(new Font("Source Sans Pro Semibold", Font.ITALIC, 13));
		lblDue.setBackground(Color.YELLOW);
		lblDue.setBounds(126, 313, 129, 31);
		add(lblDue);
		
		namefld = new JTextField();
		namefld.setColumns(10);
		namefld.setBounds(265, 256, 302, 20);
		add(namefld);
		
		addrsfld = new JTextField();
		addrsfld.setColumns(10);
		addrsfld.setBounds(265, 288, 302, 20);
		add(addrsfld);
		
		duefld = new JTextField();
		duefld.setBackground(Color.YELLOW);
		duefld.setColumns(10);
		duefld.setBounds(265, 319, 302, 20);
		add(duefld);
		
		JButton Searchbtn = new JButton("Search");
		Searchbtn.setFont(new Font("Segoe Print", Font.BOLD, 13));
		Searchbtn.setBorder(BorderFactory.createBevelBorder(0));
		Searchbtn.setBackground(new Color(135, 206, 250));
		Searchbtn.setBounds(469, 174, 98, 21);
		add(Searchbtn);
		
		
		Searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from `customer` where `phone no`= '"+srchfld.getText()+"';");
				while(rs.next())
				{
					if(srchfld.getText().equals(rs.getString(1)))
					{
						namefld.setText(rs.getString(2));
						addrsfld.setText(rs.getString(3));
						duefld.setText(rs.getString(4));
						x=1;
						phn=srchfld.getText();
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
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(srchfld.getText().equals(""))
						JOptionPane.showMessageDialog(Searchbtn,"No data selected","Alert",JOptionPane.WARNING_MESSAGE);
					else {
					stmt=con.createStatement();  
					stmt.executeUpdate("UPDATE `customer` SET `phone no`= '"+srchfld.getText()+"',`name`='"+namefld.getText()+"',"
							+ "`address`='"+addrsfld.getText()+"',`due`='"+duefld.getText()+"' WHERE `phone no`='"+phn+"';");
					srchfld.setText("");
					namefld.setText("");
					addrsfld.setText("");
					duefld.setText("");
					x=0;
					JOptionPane.showMessageDialog(Searchbtn,"Updated","Alert",JOptionPane.WARNING_MESSAGE);}
					} catch(Exception ez){ }
			}
		});
		btnUpdate.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnUpdate.setBorder(BorderFactory.createBevelBorder(0));
		btnUpdate.setBackground(new Color(135, 206, 250));
		btnUpdate.setBounds(265, 382, 105, 31);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(srchfld.getText().equals(""))
						JOptionPane.showMessageDialog(btnDelete,"No data selected","Alert",JOptionPane.WARNING_MESSAGE);
					else {
					stmt=con.createStatement();  
					stmt.executeUpdate("delete from `customer` where `phone no`='"+srchfld.getText()+"';");
					srchfld.setText("");
					namefld.setText("");
					addrsfld.setText("");
					duefld.setText("");
					JOptionPane.showMessageDialog(Searchbtn,"Deleted!","Alert",JOptionPane.YES_OPTION);x=0;}
					} catch(Exception ez){ }
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(462, 383, 105, 31);
		add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchfld.setText("");
				namefld.setText("");
				addrsfld.setText("");
				duefld.setText("");
				x=0;
			}
		});
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Segoe Print", Font.BOLD, 15));
		btnClear.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		btnClear.setBackground(Color.CYAN);
		btnClear.setBounds(362, 454, 105, 31);
		add(btnClear);
	}
}
