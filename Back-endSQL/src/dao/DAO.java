package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;

import userclasses.AbnormalTest;
import userclasses.Admin;
import userclasses.Appointment;
import userclasses.CommunityOwner;
import userclasses.Doctor;
import userclasses.HospitalOwner;
import userclasses.NewTest;
import userclasses.Patient;
import userclasses.TestData;
import userclasses.labtests;
import userclasses.prescriptions;

import java.util.Map;
import java.util.HashMap;
// adding changes to delete patient
/**
 
 */
public class DAO {
	private String jdbcURL = "jdbc:mysql://alfred.cs.uwec.edu:3306/TEAM5G5";
	private String jdbcUsername = "TEAM5G5";
	private String jdbcPassword = "m(7yBuc$2&fZ";

	private static final String INSERT_PATIENT_SQL = "INSERT INTO patients" + "  (ssn, name, birthdate, address, email, communityid, familyid, prevfamilyid, doctorid, password) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_PATIENT_BY_EMAIL = "select ssn, name, birthdate, address, email, communityid, familyid, prevfamilyid, doctorid, password from patients where email = ?";
	private static final String SELECT_ALL_PATIENTS = "select * from patients";
	private static final String SELECT_NOT_ACCEPTED_PATIENTS = "select * from patients where accepted = 0";
	private static final String SELECT_ALL_ACCEPTED_PATIENTS = "select * from patients where accepted = 1";
	private static final String DELETE_PATIENTS_SQL = "delete from patients where ssn = ?;";
	private static final String UPDATE_PATIENTS_SQL = "update patients set accepted=? where ssn=?";
	
	private static final String SELECT_PATIENT_SSN = "select ssn from patients where email = ?";
	private static final String SELECT_PATIENT_BY_COMMUNITY = "select * from patients where communityid=?";
	private static final String SELECT_PATIENT_BY_HOSPITALID = "select * from patients where communityid in ( select * from doctors where hospitalid=?";
	private static final String INSERT_DOCTOR_SQL = "INSERT INTO doctors" + "  (ssn, name, birthdate, address, email, communityid, hospitalid, doctorid, password) VALUES " 
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_DOCTOR_BY_EMAIL = "select ssn, name, birthdate, address, email, communityid, hospitalid, doctorid, password from doctors where email=?";
	private static final String SELECT_ALL_DOCTORS = "select * from doctors";
	private static final String SELECT_DOCTOR_BY_HOSPITALID = "select * from doctors where hospitalid = ?";
	private static final String SELECT_NOT_ACCEPTED_DOCTORS = "select * from doctors where accepted = 0";
	private static final String SELECT_ALL_ACCEPTED_DOCTORS = "select * from doctors where accepted = 1";
	private static final String DELETE_DOCTORS_SQL = "delete from doctors where ssn = ?;";
	private static final String UPDATE_DOCTORS_SQL = "update doctors set accepted = ? where ssn = ?";
	
	private static final String SELECT_HOSPITALOWNER_BY_EMAIL = "select hospitalid, hospitalname, hospitaladdress, email, password from hospitalowners where email=?";
	private static final String INSERT_HOSPITALOWNER_SQL = "INSERT INTO hospitalowners" + "  (hospitalid, hospitalname, hospitaladdress, email, password) VALUES "
			+ " (?, ?, ?, ?, ?);";
	private static final String SELECT_ALL_HOSPITALOWNERS = "select * from hospitalowners";
	private static final String DELETE_HOSPITALOWNER_SQL = "delete from hospitalowners where email = ?;";
	private static final String UPDATE_HOSPITALOWNER_SQL = "update hospitalowners set hospitalid=?, hospitalname=?, hospitaladdress=?, email=?, password=? where email=?";
	
	private static final String SELECT_COMMUNITYOWNER_BY_EMAIL = "select communityid, email, password from communityowners where email=?";
	private static final String INSERT_COMMUNITYOWNER_SQL = "INSERT INTO communityowners" + "  (communityid, email, password) VALUES "	+ " (?, ?, ?);";
	private static final String SELECT_ALL_COMMUNITYOWNERS = "select * from communityowners";
	private static final String DELETE_COMMUNITYOWNER_SQL = "delete from communityowners where email = ?;";
	private static final String UPDATE_COMMUNITYOWNER_SQL = "update communityowners set communityid=?, email=?, password=? where email=?";
	
	private static final String SELECT_ADMIN_BY_EMAIL = "select * from admins where email = ?";
	
	private static final String INSERT_NEWTEST_SQL = "insert into tests" + "(name, upperbound, lowerbound, unit) values" + "(?, ?, ?, ?)";
	
	private static final String SELECT_ALL_TESTS = "select * from tests";
	
	private static final String INSERT_DATA_SQL = "insert into testdata" + " (ssn, pressurehigh, pressurelow, pulse, temperature, oxygen, glucose, weight, date) values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_PRESSURE = "SELECT <replace>, date from testdata where ssn = ?;";

	
	private static final String SELECT_BOUNDS_BY_NAME = "select upperbound, lowerbound from tests where name = ?";
	private static final String SELECT_ABNORMAL_TESTS = "select patients.name, patients.ssn, testdata.date from patients JOIN testdata ON patients.ssn = testdata.ssn where <replace> > ? or <replace2> < ?";
	

	private static final String SELECT_TYPE = "SELECT unit from tests where name = ?";
	private static final String SELECT_DOCTORS_IN_HOSPITAL = "SELECT * from doctors where hospitalid = ?";
	private static final String SELECT_ABNORMAL = "SELECT patients.name, tests.name, testdata.date, patients.ssn FROM patients JOIN testdata ON testdata.ssn = patients.ssn JOIN tests ON tests.name = (SELECT column_name from information_schema.columns where column_name = ?) where <replace1> > ? OR <replace> < ?";
	private static final String SELECT_BOUNDARY = "SELECT name, upperbound, lowerbound from tests";
	
	private static final String INSERT_NEW_VISIT = "insert into visits" + " (doctorid, date, time) " + "values (?, ?, ?)";
	private static final String BOOK_VISIT_SQL = "update visits set ssn=?, status=? where id=?";
	private static final String CANCEL_VISIT_SQL = "update visits set ssn=NULL, status=? where id=?";
	private static final String SELECT_VISITS_BY_STATUS = "select * from visits where doctorid = ? and date = ? and status = ? order by time";
	private static final String SELECT_BOOKED_VISITS = "select * from visits where ssn = ? and status = ?";
	private static final String INSERT_MEDICATION = "insert into medications" + "(ssn, name)" + "values (?, ?)";
	private static final String INSERT_LAB = "insert into lab_tests" + "(ssn, name)" + "values (?, ?)";
	private static final String UPDATE_APPOINTMENTS = "update visits set symptoms = ?, summary = ? where id=?";
	private static final String SELECT_MEDICATIONS = "select * from medications where ssn = ?";
	private static final String DELETE_MEDICATIONS = "delete from medications where ssn = ?";
	private static final String SELECT_DOCTOR_VISITS = "select * from visits where doctorid = ? and status = ?";
	private static final String DELETE_LABS = "delete from lab_tests where ssn = ?";
	private static final String SELECT_LABS = "select * from lab_tests where ssn = ?";
	private static final String UPDATE_VISIT = "update visits set symptoms = ?, summary = ?, status = 'done' where id = ?";
	public DAO() {
	}

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
	public String unit_type(String testname) throws SQLException {
		String unit = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TYPE)) {
			
			preparedStatement.setString(1, testname);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			 unit = rs.getString("unit");
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return unit;
	}
	public void insertPatient(Patient patient) throws SQLException {
		System.out.println(INSERT_PATIENT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT_SQL)) {
			
			preparedStatement.setString(1, patient.getSsn());
			preparedStatement.setString(2, patient.getName());
			preparedStatement.setString(3, patient.getBirthdate());
			preparedStatement.setString(4, patient.getAddress());
			preparedStatement.setString(5, patient.getEmail());
			preparedStatement.setString(6, patient.getCommunityid());
			preparedStatement.setString(7, patient.getFamilyid());
			preparedStatement.setString(8, patient.getPrevfamilyid());
			preparedStatement.setInt(9, patient.getDoctorid());
			preparedStatement.setString(10, patient.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	
	public String patientgraph(String ssn, String testname) throws SQLException {
		System.out.println(INSERT_COMMUNITYOWNER_SQL);
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		String dataPoints = null;
		String yVal;
	
		
		Date date;
		
		Gson gsonObj = new Gson();
		
		
			String statement = SELECT_PRESSURE.replace("<replace>",testname);
		
	

		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
			
			preparedStatement.setString(1, ssn);
			System.out.println(preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
		
			
			while(rs.next()){
				yVal = rs.getString(testname);
				date = rs.getDate("date");
				map = new HashMap<Object,Object>();
				map.put("x", date.getTime());
				map.put("y", Double.parseDouble(yVal));
				list.add(map);
			}
			System.out.println(list);
			dataPoints = gsonObj.toJson(list);
			System.out.println(dataPoints);
		} catch (SQLException e) {
			printSQLException(e);
		}
		return dataPoints;
	}
	public String patientgraphPressure(String ssn) throws SQLException {
		System.out.println(INSERT_COMMUNITYOWNER_SQL);
		Map<Object,Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		String dataPoints = null;
		String yVal;
		String yVal2;
		int[] result;
		result = new int[2];
		Date date;
		
		Gson gsonObj = new Gson();
		
		
			String statement = SELECT_PRESSURE.replace("<replace>", "pressurehigh, pressurelow");
	
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
			
			preparedStatement.setString(1, ssn);
			System.out.println(preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
		
			
			while(rs.next()){
				yVal = rs.getString("pressurehigh");
				yVal2 = rs.getString("pressurelow");
				date = rs.getDate("date");

				int low = Integer.parseInt(yVal);
				int high = Integer.parseInt(yVal2);

				map = new HashMap<Object,Object>();
				map.put("x", date.getTime());

				map.put("y", new Integer[] {low,high});

				list.add(map);
		
			}
			System.out.println(list);
			dataPoints = gsonObj.toJson(list);
			dataPoints = dataPoints.replace("\"","");
			System.out.println(dataPoints);
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return dataPoints;
	}
	//select patient by ssn
	public Patient selectPatient(String email) {
		Patient patient = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_EMAIL);){
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				String ssn = rs.getString("ssn");
				String name = rs.getString("name");
				String birthdate = rs.getString("birthdate");
				String address = rs.getString("address");
				String communityid = rs.getString("communityid");
				String familyid = rs.getString("familyid");
				String prevfamilyid = rs.getString("prevfamilyid");
				int doctorid = rs.getInt("doctorid");
				String password = rs.getString("password");
				patient = new Patient(ssn, name, birthdate, address, email, communityid, familyid, prevfamilyid, doctorid, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return patient;
	}
	
	public String selectPatientSsn(String email) {
		Patient patient = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_EMAIL);){
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String ssn = rs.getString("ssn");
				String name = rs.getString("name");
				String birthdate = rs.getString("birthdate");
				String address = rs.getString("address");
				String familyid = rs.getString("familyid");
				String prevfamilyid = rs.getString("prevfamilyid");
				String communityid = rs.getString("communityid");
				int doctorid = rs.getInt("doctorid");
				String password = rs.getString("password");
				patient = new Patient(ssn, name, birthdate, address, email, familyid, prevfamilyid, communityid, doctorid, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return patient.getSsn();
	}
	
	// select all patients
		public List<Patient> selectAllPatients(){
			
			// using try-with-resources to avoid closing resources
			List<Patient> patients = new ArrayList<>();
			// Step 1: Establishing a connection
			try (Connection connection = getConnection();
					
					// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PATIENTS);){
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				
				// Step 4: Process the ResultSet
				while (rs.next()) {
					String ssn = rs.getString("ssn");
					String name = rs.getString("name");
					String birthdate = rs.getString("birthdate");
					String address = rs.getString("address");
					String email = rs.getString("email");
					String familyid = rs.getString("familyid");
					String prevfamilyid = rs.getString("prevfamilyid");
					String communityid = rs.getString("communityid");
					int doctorid = rs.getInt("doctorid");
					int accepted = rs.getInt("accepted");
					patients.add(new Patient(ssn, name, birthdate, address, email, familyid, prevfamilyid, communityid, doctorid, accepted));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return patients;
		}
	
public List<Patient> selectPatientsInCommunity(String communityid){
			
			// using try-with-resources to avoid closing resources
			List<Patient> patients = new ArrayList<>();
			// Step 1: Establishing a connection
			try (Connection connection = getConnection();
					
					// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_COMMUNITY);){
				preparedStatement.setString(1, communityid);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				
				// Step 4: Process the ResultSet
				while (rs.next()) {
					String ssn = rs.getString("ssn");
					String name = rs.getString("name");
					String birthdate = rs.getString("birthdate");
					String address = rs.getString("address");
					String email = rs.getString("email");
					String communityID = rs.getString("communityid");
					String familyid = rs.getString("familyid");
					String prevfamilyid = rs.getString("prevfamilyid");
					int doctorid = rs.getInt("doctorid");
					int accepted = rs.getInt("accepted");
					patients.add(new Patient(ssn, name, birthdate, address, email, communityID, familyid, prevfamilyid, doctorid, accepted));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return patients;
		}
public List<Patient> selectPatientsInHospital(String hospitalid){
	
	// using try-with-resources to avoid closing resources
	List<Patient> patients = new ArrayList<>();
	// Step 1: Establishing a connection
	try (Connection connection = getConnection();
			
			// Step 2: Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_COMMUNITY);){
		preparedStatement.setString(1, hospitalid);
		System.out.println(preparedStatement);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();
		
		// Step 4: Process the ResultSet
		while (rs.next()) {
			String ssn = rs.getString("ssn");
			String name = rs.getString("name");
			String birthdate = rs.getString("birthdate");
			String address = rs.getString("address");
			String email = rs.getString("email");
			String communityID = rs.getString("communityid");
			String familyid = rs.getString("familyid");
			String prevfamilyid = rs.getString("prevfamilyid");
			int doctorid = rs.getInt("doctorid");
			int accepted = rs.getInt("accepted");
			patients.add(new Patient(ssn, name, birthdate, address, email, communityID, familyid, prevfamilyid, doctorid, accepted));
		}
	} catch (SQLException e) {
		printSQLException(e);
	}
	return patients;
}

	
	public boolean deletePatient(String ssn) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PATIENTS_SQL);) {
			statement.setString(1, ssn);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updatePatient(Patient patient) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENTS_SQL);) {
			System.out.println("updated Patient:"+ preparedStatement);
			preparedStatement.setInt(1, patient.getAccepted());
			preparedStatement.setString(2, patient.getSsn());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	public void insertDoctor(Doctor doctor) throws SQLException {
		System.out.println(INSERT_DOCTOR_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR_SQL)) {
			
			preparedStatement.setString(1, doctor.getSsn());
			preparedStatement.setString(2, doctor.getName());
			preparedStatement.setString(3, doctor.getBirthdate());
			preparedStatement.setString(4, doctor.getAddress());
			preparedStatement.setString(5, doctor.getEmail());
			preparedStatement.setString(6, doctor.getCommunityid());
			preparedStatement.setString(7, doctor.getHospitalid());
			preparedStatement.setInt(8, doctor.getDoctorid());
			preparedStatement.setString(9, doctor.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	//select doctor by doctorid
		public Doctor selectDoctor(String email) {
			Doctor doctor = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2: Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_EMAIL);){
				preparedStatement.setString(1, email);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				
				// Step 4: Process the ResultSet object
				while (rs.next()) {
					String name = rs.getString("name");
					String address = rs.getString("address");
					String birthdate = rs.getString("birthdate");
					int doctorid = Integer.parseInt(rs.getString("doctorid"));
					String communityid = rs.getString("communityid");
					String hospitalid = rs.getString("hospitalid");
					String password = rs.getString("password");
					doctor = new Doctor(name, birthdate, address, email, communityid, hospitalid, doctorid, password);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return doctor;
		}
		
		// select all doctors
				public List<Doctor> selectAllDoctors(){
					
					// using try-with-resources to avoid closing resources
					List<Doctor> doctors = new ArrayList<>();
					// Step 1: Establishing a connection
					try (Connection connection = getConnection();
							
							// Step 2: Create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DOCTORS);){
						System.out.println(preparedStatement);
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						
						// Step 4: Process the ResultSet
						while (rs.next()) {
							String name = rs.getString("name");
							String address = rs.getString("address");
							String email = rs.getString("email");
							String birthdate = rs.getString("birthdate");
							int doctorid = Integer.parseInt(rs.getString("doctorid"));
							String communityid = rs.getString("communityid");
							String hospitalid = rs.getString("hospitalid");
							String password = rs.getString("password");
							String ssn = rs.getString("ssn");
							int accepted = Integer.parseInt(rs.getString("accepted"));
							doctors.add(new Doctor(ssn, name, birthdate, address, email, communityid, hospitalid, doctorid, password, accepted));
						}
					} catch (SQLException e) {
						printSQLException(e);
					}
					return doctors;
				}
				
				
					public List<Doctor> selectDoctorsinHospital(String hospitalid){
					
					// using try-with-resources to avoid closing resources
					List<Doctor> doctors = new ArrayList<>();
					// Step 1: Establishing a connection
					try (Connection connection = getConnection();
							
							// Step 2: Create a statement using connection object
						PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTORS_IN_HOSPITAL);){
						preparedStatement.setString(1, hospitalid);
						System.out.println(preparedStatement);
						// Step 3: Execute the query or update query
						ResultSet rs = preparedStatement.executeQuery();
						
						// Step 4: Process the ResultSet
						while (rs.next()) {
							String name = rs.getString("name");
							String address = rs.getString("address");
							String email = rs.getString("email");
							String birthdate = rs.getString("birthdate");
							int doctorid = Integer.parseInt(rs.getString("doctorid"));
							String communityid = rs.getString("communityid");
							String hospitalID = rs.getString("hospitalid");
							String password = rs.getString("password");
							String ssn = rs.getString("ssn");
							int accepted = Integer.parseInt(rs.getString("accepted"));
							doctors.add(new Doctor(ssn, name, birthdate, address, email, communityid, hospitalID, doctorid, password, accepted));
						}
					} catch (SQLException e) {
						printSQLException(e);
					}
					return doctors;
				}
	
	
	public boolean deleteDoctor(int ssn) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DOCTORS_SQL);) {
			statement.setInt(1, ssn);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}


	public boolean updateDoctor(Doctor doctor) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTORS_SQL);) {
			System.out.println("updated Doctor:"+ preparedStatement);
			preparedStatement.setInt(1, doctor.getAccepted());
			preparedStatement.setString(2, doctor.getSsn());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	public void updateAppointment(String symptoms, String summary, int id) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VISIT);) {
		
			preparedStatement.setString(1, symptoms);
			preparedStatement.setString(2, summary);
			preparedStatement.setInt(3, id  );

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		
	}
	
	
	
	public void insertCommunityOwner(CommunityOwner community) throws SQLException {
		System.out.println(INSERT_COMMUNITYOWNER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMUNITYOWNER_SQL)) {
			
			preparedStatement.setInt(1, community.getCommunityid());
			preparedStatement.setString(2, community.getEmail());
			preparedStatement.setString(3, community.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public CommunityOwner selectCommunityOwner(String email) throws SQLException {
		CommunityOwner community = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMUNITYOWNER_BY_EMAIL);){
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				int communityid = Integer.parseInt(rs.getString("communityid"));
				String password = rs.getString("password");
				community = new CommunityOwner(communityid, email, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return community;
	}
	
	public void insertHospitalOwner(HospitalOwner hospital) throws SQLException {
		System.out.println(INSERT_HOSPITALOWNER_SQL);
		
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOSPITALOWNER_SQL)) {
				
			preparedStatement.setInt(1, hospital.getHospitalid());
			preparedStatement.setString(2, hospital.getHospitalname());
			preparedStatement.setString(3, hospital.getHospitaladdress());
			preparedStatement.setString(4, hospital.getEmail());
			preparedStatement.setString(5, hospital.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public HospitalOwner selectHospitalOwner(String email) throws SQLException {
		HospitalOwner hospital = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSPITALOWNER_BY_EMAIL);){
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				int hospitalid = Integer.parseInt(rs.getString("hospitalid"));
				String hospitalname = rs.getString("hospitalname");
				String hospitaladdress = rs.getString("hospitaladdress");
				String password = rs.getString("password");
				hospital = new HospitalOwner(hospitalid, hospitalname, hospitaladdress, email, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return hospital;
	}
	
	public void insertNewTest(NewTest newTest) throws SQLException {
		System.out.println(INSERT_NEWTEST_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWTEST_SQL)) {
			
			preparedStatement.setString(1, newTest.getName());
			preparedStatement.setDouble(2, newTest.getUpperBound());
			preparedStatement.setDouble(3, newTest.getLowerBound());
			preparedStatement.setString(4, newTest.getUnit());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Admin selectAdmin(String email) throws SQLException {
		Admin admin = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_EMAIL);){
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String password = rs.getString("password");
				admin = new Admin(id, email, password);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return admin;
	}
	
	public NewTest selectTestBounds(String name) throws SQLException {
		NewTest test = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOUNDS_BY_NAME);){
			preparedStatement.setString(1, name);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				double upperBound = Double.parseDouble(rs.getString("upperbound"));
				double lowerBound = Double.parseDouble(rs.getString("lowerbound"));
				test = new NewTest(upperBound, lowerBound);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return test;
	}
	
	public List<AbnormalTest> selectAbnormalTests() throws SQLException{
		List<AbnormalTest> data = new ArrayList<>();
		String query = null;	
		try (Connection connection = getConnection();
				// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOUNDARY);
				){
			
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet
		
			while (rs.next()) {
				String name = rs.getString("name");
				int upperbound = rs.getInt("upperbound");
				int lowerbound = rs.getInt("lowerbound");
				if (name.equals("Blood Pressure")) {
					query = SELECT_ABNORMAL_TESTS.replace("<replace>", "pressurehigh");
					query = query.replace("<replace2>", "pressurelow");
					
				} else {
				query = SELECT_ABNORMAL.replace("<replace1>", name);
				query = query.replace("<replace>", name);
				
				}
				
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				if (name.equals("Blood Pressure")) {
					preparedStatement2.setInt(1, upperbound);
					
					preparedStatement2.setInt(2, lowerbound);
				} else {
				preparedStatement2.setString(1, name);
				
				preparedStatement2.setInt(2, upperbound);
				
				preparedStatement2.setInt(3, lowerbound);
				}
				ResultSet rs2 = preparedStatement2.executeQuery();
				while(rs2.next()) {
					
					if (name.equals("Blood Pressure")) {
						String patient_name = rs2.getString("patients.name");
						
						String test_date = rs2.getDate("testdata.date").toString();
						String patient_ssn = rs2.getString("patients.ssn");
						AbnormalTest Abnormaltest = new AbnormalTest(patient_ssn, patient_name, "Blood Pressure",test_date);
						data.add(Abnormaltest);
						
					} else {
						String patient_name = rs2.getString("patients.name");
						String test_name = rs2.getString("tests.name");
						String test_date = rs2.getDate("testdata.date").toString();
						String patient_ssn = rs2.getString("patients.ssn");
						AbnormalTest Abnormaltest = new AbnormalTest(patient_ssn, patient_name, test_name,test_date);
						data.add(Abnormaltest);
					}
					
					
					
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return data;
	}
	
	public List<NewTest> selectAllTests(){
		
		// using try-with-resources to avoid closing resources
		List<NewTest> tests = new ArrayList<>();
		// Step 1: Establishing a connection
		try (Connection connection = getConnection();
				
				// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TESTS);){
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet
			while (rs.next()) {
				String name = rs.getString("name");
				int upperBound = Integer.parseInt(rs.getString("upperBound"));
				int lowerBound = Integer.parseInt(rs.getString("lowerBound"));
				String unit = rs.getString("unit");
				
				tests.add(new NewTest(name, upperBound, lowerBound, unit));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tests;
	}
	
	public void insertTestData(TestData data) throws SQLException {
		System.out.println(INSERT_DATA_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA_SQL)) {
			
			preparedStatement.setString(1, data.getSsn());
			preparedStatement.setInt(2, data.getPressureHigh());
			preparedStatement.setInt(3, data.getPressureLow());
			preparedStatement.setInt(4, data.getPulse());
			preparedStatement.setDouble(5, data.getTemperature());
			preparedStatement.setInt(6, data.getOxygen());
			preparedStatement.setInt(7, data.getGlucose());
			preparedStatement.setDouble(8, data.getWeight());
			preparedStatement.setString(9, data.getDate());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void insertAppointment(Appointment appointment) throws SQLException {
		System.out.println(INSERT_NEW_VISIT);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_VISIT)) {
			
			preparedStatement.setInt(1, appointment.getDoctorid());
			preparedStatement.setString(2, appointment.getDate());
			preparedStatement.setString(3, appointment.getTime());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void insertmedication(String ssn, String[] med) throws SQLException {
		int i = 0;
		try (Connection connection = getConnection();
				) {
			
			PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MEDICATIONS);
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEDICATION);
			preparedStatement1.setString(1,  ssn);
			preparedStatement1.executeUpdate();
			if(med == null) {
				
			} else {
			while(i < med.length) {
			preparedStatement.setString(1, ssn);
			preparedStatement.setString(2, med[i]);
			preparedStatement.addBatch();
			i++;
			}
			
			}
			preparedStatement.executeBatch();
		}
	}
	public List<prescriptions> retrievemedication(String ssn) throws SQLException {
		List<prescriptions> prescriptions = new ArrayList<prescriptions>();
		
		int i = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEDICATIONS)) {
		
			preparedStatement.setString(1, ssn);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				prescriptions.add(new prescriptions(ssn, name));
				
			}
			
		}
		return prescriptions;
	}
	
	
	public List<labtests> retrievelabs(String ssn) throws SQLException {
		List<labtests> labtests = new ArrayList<labtests>();
		
		int i = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LABS)) {
		
			preparedStatement.setString(1, ssn);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				labtests.add(new labtests(ssn, name));
				
			}
			
		}
		return labtests;
	}
	public void insertlab(String ssn, String[] lab) throws SQLException {
		int i = 0;
		try (Connection connection = getConnection();
				) {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LAB);
			PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_LABS);
			preparedStatement1.setString(1, ssn);
			preparedStatement1.executeUpdate();
			if( lab == null) {
			} else {
			
			while(i < lab.length) {
			preparedStatement.setString(1, ssn);
			preparedStatement.setString(2, lab[i]);
			preparedStatement.addBatch();
			i++;
			
			}
			}
			preparedStatement.executeBatch();
		}
	}
	public List<Appointment> selectOpenAppointments(int doctorid, String date) throws ParseException{
		SimpleDateFormat availtime = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat times = new SimpleDateFormat("hh:mm a");
		// using try-with-resources to avoid closing resources
		List<Appointment> appointments = new ArrayList<>();
		// Step 1: Establishing a connection
		try (Connection connection = getConnection();
				
				// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VISITS_BY_STATUS);){
			preparedStatement.setInt(1, doctorid);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, "available");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String time = times.format(availtime.parse(rs.getString("time")));
				
				appointments.add(new Appointment(id, doctorid, date, time));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return appointments;
	}
	
public List<Appointment> selectBookedAppointments(String ssn) throws ParseException{
	SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dates = new SimpleDateFormat("MMM dd, yyyy");
	SimpleDateFormat availtime = new SimpleDateFormat("hh:mm:ss");
	SimpleDateFormat times = new SimpleDateFormat("hh:mm a");
		// using try-with-resources to avoid closing resources
		List<Appointment> appointments = new ArrayList<>();
		// Step 1: Establishing a connection
		try (Connection connection = getConnection();
				
				// Step 2: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKED_VISITS);){
			preparedStatement.setString(1, ssn);
			preparedStatement.setString(2, "booked");
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Step 4: Process the ResultSet
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String date = dates.format(availDate.parse(rs.getString("date")));
				String time = times.format(availtime.parse(rs.getString("time")));
				
				appointments.add(new Appointment(id, ssn, date, time));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return appointments;
	}
public List<Appointment> selectDoctorAppointments(int doctorid) throws ParseException{
	SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dates = new SimpleDateFormat("MMM dd, yyyy");
	SimpleDateFormat availtime = new SimpleDateFormat("hh:mm:ss");
	SimpleDateFormat times = new SimpleDateFormat("hh:mm a");
	// using try-with-resources to avoid closing resources
	List<Appointment> appointments = new ArrayList<>();
	// Step 1: Establishing a connection
	try (Connection connection = getConnection();
			
			// Step 2: Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_VISITS);){
		preparedStatement.setInt(1, doctorid);
		preparedStatement.setString(2, "booked");
		System.out.println(preparedStatement);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();
		
		// Step 4: Process the ResultSet
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("id"));
			String date =  dates.format(availDate.parse(rs.getString("date")));
			String time = times.format(availtime.parse(rs.getString("time")));
			String ssn = rs.getString("ssn");
			appointments.add(new Appointment(id, ssn, date, time));
		}
	} catch (SQLException e) {
		printSQLException(e);
	}
	return appointments;
}

public List<Appointment> selectDoneAppointments(int doctorid){
	
	// using try-with-resources to avoid closing resources
	List<Appointment> appointments = new ArrayList<>();
	// Step 1: Establishing a connection
	try (Connection connection = getConnection();
			
			// Step 2: Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_VISITS);){
		preparedStatement.setInt(1, doctorid);
		preparedStatement.setString(2, "done");
		System.out.println(preparedStatement);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();
		
		// Step 4: Process the ResultSet
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString("id"));
			String date = rs.getString("date");
			String symptoms = rs.getString("symptoms");
			String summary = rs.getString("summary");
			appointments.add(new Appointment(id, doctorid, date, symptoms, summary));
		}
	} catch (SQLException e) {
		printSQLException(e);
	}
	return appointments;
}
	
	public boolean bookAppointment(String ssn, int id) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(BOOK_VISIT_SQL);) {
			preparedStatement.setString(1, ssn);
			preparedStatement.setString(2, "booked");
			preparedStatement.setInt(3, id);

			System.out.println("updated Visit:"+ preparedStatement);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	public boolean cancelAppointment(int id) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_VISIT_SQL);) {
			preparedStatement.setString(1, "available");
			preparedStatement.setInt(2, id);

			System.out.println("updated Visit:"+ preparedStatement);
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public String[] findperson(String email, String password) throws SQLException {
		Doctor doctor = selectDoctor(email);
		Patient patient = selectPatient(email);
		CommunityOwner community = selectCommunityOwner(email);
		HospitalOwner hospital = selectHospitalOwner(email);
		Admin admin = selectAdmin(email);
		String list[] = new String[3];
		if(doctor != null) {
			if(doctor.getPassword().equals(password)) {
				System.out.println("is doctor");
				list[0] = "doctor";
				list[1] = String.valueOf(doctor.getDoctorid());
				return list;
			}
		} else if(patient != null) {
			if(patient.getPassword().equals(password)) {
				System.out.println("is patient");
				list[0] = "patient";
				list[1] = patient.getSsn();
				list[2] = String.valueOf(patient.getDoctorid());
				System.out.println(list[1]);
				System.out.println(list[2]);
				return list;
			}
		} else if(community != null) {
			if(community.getPassword().equals(password)) {
				list[0] = "community";
				list[1] = String.valueOf(community.getCommunityid());
				return list;
			}
		} else if(hospital != null) {
			if(hospital.getPassword().equals(password)) {
				list[0] = "hospital";
				list[1] = String.valueOf(hospital.getHospitalid());
				return list;
			}
		} else if(admin != null) {
			if(admin.getPassword().equals(password)) {
				list[0] = "admin";
				return list;
			}
		}
		return list;	
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}