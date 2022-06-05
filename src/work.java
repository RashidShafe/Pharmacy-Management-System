import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;

public class work extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					work frame = new work();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public work() {
		setTitle("Pharmacy Management System");
		
		Connection con=null;
		Statement stmt=null;  
		ResultSet rs=null;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sidepanel = new JPanel();
		sidepanel.setBackground(new Color(100, 149, 237));
		sidepanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sidepanel.setBounds(0, 0, 245, 595);
		contentPane.add(sidepanel);
		sidepanel.setLayout(null);
		
		JPanel wmp = new JPanel();
		wmp.setBounds(244, 0, 780, 595);
		contentPane.add(wmp);
		wmp.setLayout(new CardLayout(0, 0));
		
		checkpanel chkp=new checkpanel();
		wmp.add(chkp);
		
		JButton sell = new JButton("Sell");
		sell.setIcon(new ImageIcon("E:\\PM\\src\\sell.png"));
		sell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wmp.removeAll();
				wmp.revalidate();
				checkpanel chkp=new checkpanel();
				wmp.add(chkp);
			}
		});
		sell.setFont(new Font("Segoe Print", Font.BOLD, 15));
		sell.setBorder(BorderFactory.createBevelBorder(0));
		sell.setBackground(new Color(0, 139, 139));
		sell.setBounds(48, 233, 136, 33);
		sidepanel.add(sell);
		
		JButton Customer = new JButton("Customer");
		Customer.setIcon(new ImageIcon("E:\\PM\\src\\c.png"));
		Customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wmp.removeAll();
				wmp.revalidate();
				customerpanel cp=new customerpanel();
				wmp.add(cp);
			}
		});
		Customer.setFont(new Font("Segoe Print", Font.BOLD, 15));
		Customer.setBorder(BorderFactory.createBevelBorder(0));
		Customer.setBackground(new Color(0, 139, 139));
		Customer.setBounds(48, 277, 136, 33);
		sidepanel.add(Customer);
		
		login lg=new login();
		//lg.login();
		//System.out.println(lg.x+"rj");
		
		JButton edit = new JButton("Edit");
		edit.setIcon(new ImageIcon("E:\\PM\\src\\edit.png"));
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wmp.removeAll();
				wmp.revalidate();
				editpanel ed=new editpanel();
				wmp.add(ed);
			}
		});
		edit.setFont(new Font("Segoe Print", Font.BOLD, 15));
		edit.setBorder(BorderFactory.createBevelBorder(0));
		edit.setBackground(new Color(135, 206, 250));
		edit.setBounds(48, 321, 136, 33);
		sidepanel.add(edit);
		
		if(lg.x==0)
		{
			edit.setEnabled(false);
		}
		
		JButton logout = new JButton("Log Out");
		logout.setIcon(new ImageIcon("E:\\PM\\src\\logout.png"));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				lg.setVisible(true);
				lg.setLocationRelativeTo(null);
			}
		});
		logout.setForeground(new Color(255, 255, 255));
		logout.setFont(new Font("Segoe Print", Font.BOLD, 15));
		logout.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		logout.setBackground(SystemColor.textHighlight);
		logout.setBounds(48, 536, 136, 33);
		sidepanel.add(logout);
	}
}
