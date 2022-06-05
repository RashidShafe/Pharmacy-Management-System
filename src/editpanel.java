import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class editpanel extends JPanel {

	public editpanel() {
		setBackground(SystemColor.activeCaption);
		setBorder(new LineBorder(Color.BLUE, 1, true));
		setBounds(244, 0, 780, 595);
		setLayout(null);
		
		JButton mngUser = new JButton("Manage User");
		mngUser.setIcon(new ImageIcon("E:\\PM\\src\\user.png"));
		mngUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user au=new user();
				au.setVisible(true);
				au.setLocationRelativeTo(mngUser);
			}
		});
		mngUser.setBounds(135, 253, 227, 51);
		mngUser.setFont(new Font("Segoe Print", Font.BOLD, 15));
		mngUser.setBorder(BorderFactory.createBevelBorder(0));
		mngUser.setBackground(new Color(135, 206, 250));
		add(mngUser);
		
		JButton mnginv = new JButton("Manage Inventory");
		mnginv.setIcon(new ImageIcon("E:\\PM\\src\\inventory.png"));
		mnginv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicine md=new medicine();
				md.setVisible(true);
				md.setLocationRelativeTo(mnginv);
			}
		});
		mnginv.setFont(new Font("Segoe Print", Font.BOLD, 15));
		mnginv.setBorder(BorderFactory.createBevelBorder(0));
		mnginv.setBackground(new Color(135, 206, 250));
		mnginv.setBounds(410, 253, 227, 51);
		add(mnginv);
	}
}
