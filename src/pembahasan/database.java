package pembahasan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {

		// untuk membuat sebuah database awalnya harus membuat sebuah koneksi kedatabase		
		// import jdbc driver
	Connection con;
	PreparedStatement ps;
	Statement s;
	ResultSet rs;
	
	public database() {		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pert6_javaprog","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("kalo gaad error maka success");
		}
		
		try {
			s = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// setelah di construct kita bisa melakukan CRUD
	
	public void insertStudent(student st) {
		String qry = String.format("INSERT INTO `student` (`studentId`, `StudentName`, `StudentAddress`, `StudentGrade`, `gender`, `password`) VALUES (NULL,'%s', '%S', '%s', '%s', '%s'); ", st.getName(), st.getAddress(), st.getGrade(), st.getGender(), st.getPassword());
		System.out.println(qry);
		try {
			s.execute(qry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public ResultSet getData() {
		try {
			rs = s.executeQuery("Select *  from student");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateStudent(String name, String Address, String Grade, String id) throws SQLException {
		ps = con.prepareStatement("UPDATE `student` SET `StudentName` = ?, `StudentAddress` = ?, `StudentGrade` = ? WHERE `student`.`studentId` = ? ");
		ps.setString(1, name);
		ps.setString(2, Address);
		ps.setString(3, Grade);		
		ps.setString(4, id);
		ps.execute();
	}
	
	public void updateElvinjdiFabian(String namaFabian, String id) throws SQLException {
		ps = con.prepareStatement("UPDATE `student` SET `StudentName` = ? WHERE `student`.`studentId` = ? ");
		ps.setString(1, namaFabian);
		ps.setString(2, id);
		ps.execute();
	}
	
	public void deleteAll() {
		try {
			s.execute("Delete from student");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deletebyId(String id) {
		try {
			ps = con.prepareStatement("Delete from student where studentId = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
