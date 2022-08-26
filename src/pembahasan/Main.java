package pembahasan;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



public class Main extends JFrame implements ActionListener{

	JLabel header, name, address, grade, gender, password;
	JTextField nameF, addressF, gradeF;
	JPasswordField passwordF;
	JRadioButton male;
	JRadioButton female;
	ButtonGroup genderG;
	JPanel headerP, centerP, footerP;
	JButton submit, clear;
	database db;
	
	public Main(database db) {
		// TODO Auto-generated constructor stub
		this.db = db;
		setSize(400,400);
		setEnabled(true);
		setVisible(true);
		setTitle("register");
		setLocationRelativeTo(null);
		setResizable(true);
		setLayout(new BorderLayout(10,10));
		
		// header
		header = new JLabel("Welcome to register Page");
		header.setFont(new Font("Sans Serif", Font.BOLD, 25));
		headerP = new  JPanel();
		headerP.add(header);
		
		//Center
		name = new JLabel("Name");
		address = new JLabel("Address");
		grade = new JLabel("Grade");
		gender = new JLabel("Gender");
		password= new JLabel("Password");
		
		nameF = new JTextField();
		addressF = new JTextField();
		gradeF = new JTextField();
		
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		male.setSelected(true);
		
		genderG = new ButtonGroup();
		genderG.add(male);
		genderG.add(female);
		JPanel genderPane = new JPanel();
		genderPane.add(male);
		genderPane.add(female);
		
		passwordF = new JPasswordField();
		passwordF.setEchoChar('*');
		
		centerP = new JPanel(new GridLayout(5,2,0,10));
		
		centerP.add(name);
		centerP.add(nameF);
		centerP.add(address);
		centerP.add(addressF);
		centerP.add(grade);
		centerP.add(gradeF);
		centerP.add(gender);
		centerP.add(genderPane);
		centerP.add(password);
		centerP.add(passwordF);
		
		//footer
		submit = new JButton("Submit");
		submit.addActionListener(this);
		clear = new JButton("Clear");
		clear.addActionListener(this);
		JButton getData = new JButton("Get Data");
		
//		Vector<String> data = new Vector<String>();
		getData.addActionListener(e-> {
			ResultSet rs = db.getData();
			
			try {
				while(rs.next()) {
					System.out.println(rs.getObject("studentid"));
					System.out.println(rs.getObject(2));
					System.out.println(rs.getObject(3));
					System.out.println(rs.getObject(4));
					System.out.println(rs.getObject(5));
					System.out.println(rs.getObject(6));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		
		JButton deletebyID = new JButton("delete -2");
		deletebyID.addActionListener(e -> {
			db.deletebyId("1");
		});
		
		JButton deleteAll = new JButton("delete All");
		deleteAll.addActionListener(e -> {
			db.deleteAll();
		});
		
		footerP = new JPanel();
		footerP.add(submit);
		footerP.add(clear);
		footerP.add(getData);
		footerP.add(deletebyID);
		footerP.add(deleteAll);
		
		headerP.setBorder(new EmptyBorder(10,10,0,10));
		centerP.setBorder(new EmptyBorder(0,10,0,10));
		footerP.setBorder(new EmptyBorder(0,10,10,10));		
		
		this.add(headerP, BorderLayout.NORTH);
		this.add(centerP, BorderLayout.CENTER);
		this.add(footerP, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		database db = new database();
		new Main(db);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == submit) {
			System.out.println("Your data");
			System.out.println(nameF.getText());
			System.out.println(addressF.getText());
			System.out.println(gradeF.getText());
			String gender = (male.isSelected()) ? "Male" : "Female";
			System.out.println(gender);
			System.out.println(String.valueOf(passwordF.getPassword()));
			
			db.insertStudent(new student(nameF.getText(), addressF.getText(), gender, String.valueOf(passwordF.getPassword()), Double.valueOf(gradeF.getText())));
			
			
		} else if(e.getSource() == clear) {
			nameF.setText("");
			addressF.setText("");
			gradeF.setText("");
			male.setSelected(true);
			passwordF.setText("");
			
			System.out.println("Field has been Cleared");
		}
	}

}
