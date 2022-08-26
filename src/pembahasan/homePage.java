package pembahasan;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class homePage extends JFrame implements ActionListener, Runnable {
	
	database db;
	JLabel header, name, address, grade, id;
	JPanel headerP, centerP, footerP;
	JTextField nameF, addressF, gradeF, idF;
	JScrollPane jsp;
	JTable table;
	DefaultTableModel dm;
	JPanel center;
	
	private void createTable() {
		Vector<Object> columnNames = new Vector<>();
		columnNames.add("id");
		columnNames.add("StudentName");
		columnNames.add("StudentAddress");
		columnNames.add("StudentGrade");
		columnNames.add("Gender");
		columnNames.add("Password");
		
		ResultSet rs = db.getData();
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		try {
			while(rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(rs.getObject(1));
				row.add(rs.getObject(2));
				row.add(rs.getObject(3));
				row.add(rs.getObject(4));
				row.add(rs.getObject(5));
				row.add(rs.getObject(6));
				
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dm = new DefaultTableModel(data, columnNames);
		table = new JTable(dm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		jsp = new JScrollPane(table);
	}
	
	public void initandAddForm() {
		centerP = new JPanel(new GridLayout(5,2,0,10));
		id = new JLabel("ID");
		name = new JLabel("Name");
		address = new JLabel("Address");
		grade = new JLabel("Grade");
		
		idF = new JTextField();
		idF.setEditable(false);
		nameF = new JTextField();
		addressF = new JTextField();
		gradeF = new JTextField();
		
		centerP.add(id);
		centerP.add(idF);
		centerP.add(name);
		centerP.add(nameF);
		centerP.add(address);
		centerP.add(addressF);
		centerP.add(grade);
		centerP.add(gradeF);
	}
	
	public homePage(database db) {
		// TODO Auto-generated constructor stub
		this.db = db;
		
		setSize(400,400);
		setEnabled(true);
		setVisible(true);
		setTitle("register");
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(new BorderLayout(10,10));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// header
		header = new JLabel("Welcome to register Page");
		header.setFont(new Font("Sans Serif", Font.BOLD, 25));
		headerP = new  JPanel();
		headerP.add(header);
		
		// body
		// Create Table
		createTable();
	
		// init and add form
		initandAddForm();
		
		center = new JPanel(new GridLayout(2, 1, 0, 20));
		center.add(jsp, 0);
		center.add(centerP, 1);
		center.setBorder(new EmptyBorder(10,20,10,20));
		
		JButton Update, delete;
		
		Update = new JButton("Update");
		Update.addActionListener(this);
		
		delete = new JButton("Delete");
			
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow() != -1) {
					int idx = table.getSelectedRow();
					idF.setText(String.valueOf(table.getValueAt(idx, 0)));
					nameF.setText(String.valueOf(table.getValueAt(idx, 1)));
					addressF.setText(String.valueOf(table.getValueAt(idx, 2)));
					gradeF.setText(String.valueOf(table.getValueAt(idx, 3)));
				}
			}
		});
		
		footerP = new JPanel();
		footerP.add(Update);
		footerP.add(delete);
		
		
		add(headerP, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(footerP, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		Thread mainthread = new Thread(new homePage(new database()));
		
		Thread counting = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int hour = 0;
				int minute = 0;
				int second = 55;
				
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					second++;
					minute += (second == 60) ? 1 : 0;
					hour += (minute == 60) ? 1 : 0;
					
					System.out.println("thread run time");
					System.out.println("Hours " + hour);
					System.out.println("Minutes " + minute);
					System.out.println("Seconds " + second);
					
					second = (second == 60 ) ? 1 : second;
					minute = (minute == 60) ? 1 : minute;
				}
			}
		});
		counting.setDaemon(true);
		
		mainthread.start();
		counting.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			try {
				db.updateStudent(nameF.getText(), addressF.getText(), gradeF.getText(), idF.getText());
				this.remove(center);
				
				center = new JPanel(new GridLayout(2, 1, 0, 20));
				
				center.setBorder(new EmptyBorder(10,20,10,20));
				
				createTable();
				
				initandAddForm();
				center.add(jsp, 0);
				center.add(centerP, 1);
				
				add(center, BorderLayout.CENTER);
				
				repaint();
				revalidate();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new homePage(new database());
	}
}

