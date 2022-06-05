import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

public class checkpanel extends JPanel {
	private JTable table;
	int sum;
	Connection con=null;
	Statement stmt=null;  
	ResultSet rs=null;
	private JTable tab1;
	private JTextField nmfld;
	private JTextField nmfld2;
	private JTextField tfld;
	private JTextField typfld;
	private JTextField cnam;
	public checkpanel() {
		con=Connect.connect();
		setBackground(SystemColor.inactiveCaption);
		setBorder(new LineBorder(Color.BLUE, 1, true));
		setBounds(244, 0, 780, 595);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 760, 92);
		add(scrollPane);
		
		tab1 = new JTable();
		tab1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int n= tab1.getSelectedRow();
				nmfld2.setText(tab1.getValueAt(n, 0).toString());
				typfld.setText(tab1.getValueAt(n, 2).toString());
			}
		});
		scrollPane.setViewportView(tab1);
		tab1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"name", "manufacturer", "type", "unit price", "stock", "position"
			}
		));
		
		JButton Search = new JButton("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	
					stmt=con.createStatement();  
					rs=stmt.executeQuery("select `name`,`manufacturer`,`type`,`unit price`,`stock`,`position` from `medicine` where `name` LIKE '%"+nmfld.getText()+"%';");
					tab1.setModel(DbUtils.resultSetToTableModel(rs));
					
					} catch(Exception ez){ }
			}
		});
		Search.setFont(new Font("Segoe Print", Font.BOLD, 13));
		Search.setBorder(BorderFactory.createBevelBorder(0));
		Search.setBackground(new Color(135, 206, 250));
		Search.setBounds(434, 37, 82, 29);
		add(Search);
		
		JLabel lblNewLabel = new JLabel("Search medicine by name:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblNewLabel.setBounds(137, 23, 158, 14);
		add(lblNewLabel);
		
		nmfld = new JTextField();
		nmfld.setBackground(Color.YELLOW);
		nmfld.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nmfld.setBounds(131, 37, 240, 29);
		add(nmfld);
		nmfld.setColumns(10);
		
		JLabel lblDrugsName = new JLabel("Drug's name:");
		lblDrugsName.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblDrugsName.setBounds(98, 175, 82, 29);
		add(lblDrugsName);
		
		nmfld2 = new JTextField();
		nmfld2.setBackground(SystemColor.info);
		nmfld2.setBounds(178, 180, 158, 20);
		add(nmfld2);
		nmfld2.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblQuantity.setBounds(364, 185, 59, 29);
		add(lblQuantity);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(434, 190, 82, 20);
		add(spinner);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 259, 435, 325);
		add(scrollPane_1);
		sum=0;
		JTextArea txtarea = new JTextArea();
		txtarea.setBackground(new Color(230, 230, 250));
		scrollPane_1.setViewportView(txtarea);
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				stmt=con.createStatement();  
				rs=stmt.executeQuery("select * from `medicine` where `name` = '"+nmfld2.getText()+"' and `type`='"+typfld.getText()+"';");
				while(rs.next())
					{
						float p=rs.getFloat(4);
						Integer sp=(Integer)spinner.getValue();
						p=sp.floatValue()*p;
						sum+=p;
						txtarea.append(rs.getString(1)+"\t"+rs.getString(3)+"\t"+sp+"\t"+p+"\n");
						tfld.setText(Float.toString(sum));
					}
				}catch(Exception ez){ }
			}
		});
		add.setFont(new Font("Segoe Print", Font.BOLD, 13));
		add.setBorder(BorderFactory.createBevelBorder(0));
		add.setBackground(new Color(135, 206, 250));
		add.setBounds(583, 185, 88, 29);
		add(add);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblTotal.setBounds(532, 271, 43, 29);
		add(lblTotal);
		
		tfld = new JTextField(0);
		tfld.setEditable(false);
		tfld.setBounds(585, 276, 86, 20);
		add(tfld);
		tfld.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("(TK)");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(679, 279, 26, 14);
		add(lblNewLabel_1);
		
		JLabel lblDrugsType = new JLabel("Drug's Type:");
		lblDrugsType.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblDrugsType.setBounds(98, 200, 82, 29);
		add(lblDrugsType);
		
		typfld = new JTextField();
		typfld.setColumns(10);
		typfld.setBackground(SystemColor.info);
		typfld.setBounds(178, 205, 158, 20);
		add(typfld);
		
		JButton newbill = new JButton("New Bill");
		newbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtarea.setText("");
				sum=0;
				tfld.setText("0");
			}
		});
		newbill.setForeground(Color.BLACK);
		newbill.setFont(new Font("Segoe Print", Font.BOLD, 15));
		newbill.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		newbill.setBackground(Color.CYAN);
		newbill.setBounds(583, 416, 88, 29);
		add(newbill);
		
		JButton printbill = new JButton("Print Bill");
		printbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fn="bill.txt";
				try {
					FileWriter fw = new FileWriter(fn);
					BufferedWriter bw=new BufferedWriter(fw);
					//bw.write(");
					bw.write(""
							+ "		\tM&M Pharma Ltd.\n"
							+ "		 \t    Sylhet\n"
							+"==============================\n"
							+ "Customer name:"+cnam.getText()+"\n------------------------------\n"
							+"Name\t\t Type\t\tQty.\tPrice\n");
					bw.write(txtarea.getText()+"\n------------------------------\n");
					bw.write("\t\t\t\tTotal: "+tfld.getText());
					bw.close();
					File f=new File("bill.txt");
					Desktop desktop = Desktop.getDesktop();
			        if(f.exists()) desktop.open(f);
				}catch(IOException ex) {}
			}
		});
		printbill.setForeground(Color.BLACK);
		printbill.setFont(new Font("Segoe Print", Font.BOLD, 15));
		printbill.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 51, 0)));
		printbill.setBackground(Color.CYAN);
		printbill.setBounds(583, 366, 88, 29);
		add(printbill);
		
		cnam = new JTextField();
		cnam.setColumns(10);
		cnam.setBackground(SystemColor.info);
		cnam.setBounds(585, 320, 158, 20);
		add(cnam);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		lblCustomerName.setBounds(481, 319, 94, 20);
		add(lblCustomerName);
		
	}
}
