package test;

import static org.junit.Assert.*;

import java.rmi.ConnectIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;


import dao.DAO;
import userclasses.Admin;
import userclasses.Appointment;
import userclasses.CommunityOwner;
import userclasses.Doctor;
import userclasses.HospitalOwner;
import userclasses.Patient;
import java.util.List;

public class DAOTest {
	DAO objDAO;
	private String jdbcURL = "jdbc:mysql://alfred.cs.uwec.edu:3306/TEAM5G5";
	private String jdbcUsername = "TEAM5G5";
	private String jdbcPassword = "m(7yBuc$2&fZ";
	
	

//Connection to the database	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	@Before
	public void setUp() throws Exception {
		 objDAO =new DAO();
		System.out.println("Running before every test case");
	}
	//pass
	@Test
	public void TestInsertPatient() throws SQLException {
	
		
		Patient patient = new Patient("987654321", "Tyler Jeffries","06/04/2000","568 Home Address","tyler@me.com","3","23","12",1,"abc123",0);
		//insert patient object using insert method 
		//create patient object 
		//calling insertPatient
		objDAO.insertPatient(patient);
		
		patient = objDAO.selectPatient("tyler@me.com");
		
		assertEquals("987654321", patient.getSsn());
		assertEquals("Tyler Jeffries", patient.getName());
		assertEquals("06/04/2000", patient.getBirthdate());
		assertEquals("568 Home Address", patient.getAddress());
		assertEquals("tyler@me.com", patient.getEmail());
		assertEquals("3", patient.getCommunityid());
		assertEquals("23", patient.getFamilyid());
		assertEquals("12", patient.getPrevfamilyid());
		assertEquals(1, patient.getDoctorid());
		assertEquals("abc123", patient.getPassword());
		assertEquals(0, patient.getAccepted());

	}
	
	//pass
	@Test
	public void TestSelectPatient() throws SQLException {
		Patient patient;
		patient = objDAO.selectPatient("tyler@me.com");
		//making sure these two are the same
		assertEquals("Tyler Jeffries", patient.getName());
		assertEquals("987654321", patient.getSsn());
		assertEquals("06/04/2000", patient.getBirthdate());
		
	}
	

	@Test 
	
	public void TestInsertDocter() throws SQLException {

		Doctor doctor = new Doctor("854230912", "Aaron Smith","06/06/1987","667 High Ridge Ct","docarron@email.com","1","3",2,"draaronPass",0);
		//insert patient object using insert method 
		//create patient object 
		//calling insertPatient
		
		objDAO.insertDoctor(doctor);
		
		doctor = objDAO.selectDoctor("docaaron@email.com");
		//SNN not passing
		assertEquals("Aaron Smith", doctor.getName());
		assertEquals("06/06/1987", doctor.getBirthdate());
		assertEquals("667 High Ridge Ct", doctor.getAddress());
		assertEquals("docarron@email.com", doctor.getEmail());
		assertEquals("1", doctor.getCommunityid());
		assertEquals("3", doctor.getHospitalid());
		assertEquals(2, doctor.getDoctorid());
		assertEquals("draaronPass", doctor.getPassword());
		assertEquals(0, doctor.getAccepted());
// Pass
	}
	@Test
	public void TestSelectDoctor() throws SQLException {
		Doctor doctor;
		doctor = objDAO.selectDoctor("tyler@me.com");
		//making sure these two are the same
		assertEquals("Tyler Jeffries", doctor.getName());
		assertEquals("06/04/2000", doctor.getBirthdate());
		
	}
	//Pass
	@Test
	public void TestInsertCommunityOwner() throws SQLException {

		// int communityid, String email, String password
		CommunityOwner communityowner = new CommunityOwner("tyler@me.com","abc123");
		//insert patient object using insert method 
		//create patient object 
		//calling insertPatient
		objDAO.insertCommunityOwner(communityowner);;
		communityowner = objDAO.selectCommunityOwner("tyler@me.com");
		assertEquals("tyler@me.com", communityowner.getEmail());
		assertEquals("abc123", communityowner.getPassword());
	

	}
	//pass
	@Test
	public void TestSelectCommunityOwner() throws SQLException {
		CommunityOwner communityowner;
		communityowner = objDAO.selectCommunityOwner("tyler@me.com");
		//making sure these two are the same
		assertEquals("tyler@me.com", communityowner.getEmail());
		assertEquals("abc123", communityowner.getPassword());
	}

	//String hospitalname, String hospitaladdress, String email, String password

	// pass
	@Test
	public void TestInsertHospitalOwner() throws SQLException {
		// int communityid, String email, String password
		HospitalOwner hospital = new HospitalOwner("Garden City Hospital", "6255 Inkster Rd","jbailey@email.com","bailPass");
	
		objDAO.insertHospitalOwner(hospital);
		
		hospital = objDAO.selectHospitalOwner("jbailey@email.com");
		
		assertEquals("Garden City Hospital", hospital.getHospitalname());
		assertEquals("jbailey@email.com", hospital.getEmail());
		assertEquals("bailPass", hospital.getPassword());
		assertEquals("6255 Inkster Rd", hospital.getHospitaladdress());
	}
	//pass!!!
	@Test
	public void TestSlectHospitalOwner() throws SQLException {
		HospitalOwner hospital;
		hospital = objDAO.selectHospitalOwner("clopez@email.com");
		//making sure these two are the same
		assertEquals("Lone Peak Hospital", hospital.getHospitalname());
		assertEquals("clopez@email.com", hospital.getEmail());
		assertEquals("lopezPass", hospital.getPassword());
		assertEquals("11925 State St", hospital.getHospitaladdress());
		
	}
	//public Appointment(int doctorid, String date, String time)
	@Test
	public void TestInsertAppointmentBooked() throws SQLException, ParseException {
		//Make appointment obj
		Appointment appointment = new Appointment(3,3,"2021-05-07","10:30:00");

		List <Appointment> appointments  = objDAO.selectBookedAppointments("129846542");
		int i;
		//System.out.print(appointments.size());
		for (i = 0; i < appointments.size(); i++) 
		{
		Appointment temp = appointments.get(i);
		System.out.print("get..."+appointments.get(i));
		//assertEquals(3, temp.getDoctorid());
		//System.out.print(temp.getDoctorid());
		assertEquals("2021-05-07", temp.getDate());
		assertEquals("10:30:00", temp.getTime());
		}
		//SelectBookedAppintments selects a list 
		//List<Appointment> appointments =  objDAO.selectBookedAppointments("129846542");
		//Grab a object from a list
		//for loop
		//assertEquals("")	
	}	@Test
	public void TestSelectAppointmentBooked() throws SQLException, ParseException {
		Appointment appointment;
		List <Appointment> appointments = objDAO.selectBookedAppointments("129846542");
		int i;
		for (i = 0; i < appointments.size(); i++) 
		{
		Appointment temp = appointments.get(i);
		//assertEquals(3, temp.getDoctorid());
		//System.out.print(temp.getDoctorid());
		assertEquals("2021-05-07", temp.getDate());
		assertEquals("10:30:00", temp.getTime());
		}
		
	}
	
	

	

}





