import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {
	int c;
	static int x;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField passfield;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public login() {
		setTitle("Pharmacy Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(361, 69, 121, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel pass = new JLabel("Password:");
		pass.setVerticalAlignment(SwingConstants.TOP);
		pass.setHorizontalAlignment(SwingConstants.RIGHT);
		pass.setFont(new Font("Gabriola", Font.BOLD, 25));
		pass.setBounds(233, 97, 107, 38);
		contentPane.add(pass);
		
		JLabel user = new JLabel("User Name:");
		user.setVerticalAlignment(SwingConstants.TOP);
		user.setHorizontalAlignment(SwingConstants.RIGHT);
		user.setFont(new Font("Gabriola", Font.BOLD, 25));
		user.setBounds(233, 65, 107, 44);
		contentPane.add(user);
		
		passfield = new JPasswordField();
		passfield.setHorizontalAlignment(SwingConstants.LEFT);
		passfield.setBounds(361, 101, 121, 20);
		contentPane.add(passfield);
		
		
		JButton login = new JButton("Log In");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();  
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pm","root","");   
					Statement stmt=con.createStatement();  
					ResultSet rs=stmt.executeQuery("select * from user");  
					while(rs.next())  
						{	
						if(username.getText().equals(rs.getString(4)) && passfield.getText().equals(rs.getString(5)))
							{
								dispose();
								x=rs.getInt(6);
								work wk=new work();
								wk.setVisible(true);
								wk.setLocationRelativeTo(login);
								c=1;
								break;
							}
							else
								c=0;
						}
					if(c==0)
					{JOptionPane.showMessageDialog(login,"WRONG USER NAME OR PASSWORD!!!","Alert",JOptionPane.WARNING_MESSAGE);}
					con.close();  
					}catch(Exception ez){ System.out.println(ez);}
				
			}
		});

		login.setBackground(new Color(0, 191, 255));
		login.setBorder(BorderFactory.createBevelBorder(0));
		login.setFont(new Font("Segoe Print", Font.BOLD, 15));
		login.setIcon(new ImageIcon("E:\\PM\\src\\login.png"));
		login.setBounds(300, 147, 121, 33);
		contentPane.add(login);
		
		JLabel bg = new JLabel("");
		bg.setIcon(new ImageIcon("E:\\PM\\src\\log.jpg"));
		bg.setBounds(0, 0, 735, 393);
		contentPane.add(bg);		  
	}
}
