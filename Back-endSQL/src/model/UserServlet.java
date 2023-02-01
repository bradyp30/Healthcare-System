package model;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DAO;
// Adding changes to gitlab
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


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO DAO;
	
	public void init() {
		DAO = new DAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		HttpSession session = request.getSession();

		try {
			
			switch (action) {
			case "/Appointmentvisit":
				Appointmentvisit(request,response, session);
				break;
			case "/testing":
				insertvisitstuff(request,response, session);
				break;
			case "/patient":
				showpatientForm(request, response);
				break;
			case "/doctor":
				showdoctorForm(request,response);
			break;
			case "/insertPatient":
				insertPatient(request, response);
				break;
			case "/insertDoctor":
				insertDoctor(request, response);
				break;
			case "/insertCommunityOwner":
				insertCommunityOwner(request, response);
				break;
			case "/insertHospitalOwner":
				insertHospitalOwner(request, response);
				break;
			case "/insertNewTest":
				insertNewTest(request, response);
				break;
			case "/login":
				login(request,response, session);
				break;
			case "/listPatient":
				listPatient(request,response);
				break;
			case "/listDoctor":
				listDoctor(request, response);
				break;
			case "/insertData":
				insertNewData(request, response, session);
				break;
			case "/insertAppointment":
				insertNewAppointment(request, response, session);
				break;
			case "/listTest":
				listTest(request, response);
				break;
			case "/listOpenAppointments":
				listOpenAppointments(request, response, session);
				break;
			case "/listBookedAppointments":
				listBookedAppointments(request, response, session);
				break;
			case "/listDoneAppointments":
				listDoneAppointments(request, response, session);
				break;
			case "/bookAppointment":
				bookAppointment(request, response, session);
				break;
			case "/cancelAppointment":
				cancelAppointment(request, response, session);
				break;
			case "/deletePatient":
				deletePatient(request, response, session);
				break;
			case "/approvePatient":
				approvePatient(request, response, session);
				break;
			case "/approveDoctor":
				approveDoctor(request, response, session);
				break;
			case "/deleteDoctor":
				deleteDoctor(request, response, session);
				break;
			case "/listpatientcommunity":
				listPatientcommunity(request, response, session);
				break;
			case "/listpatienthospital":
				listPatientHospital(request, response, session);
				break;
			case "/listpatientgraph":
				listPatientHospital(request, response, session);
				break;
			case "/patientGraphChoice":
				listPatientgraph(request,response, (String)session.getAttribute("patientSsn"), request.getParameter("testName") );
				break;
			case "/doctorGraphChoice":
				listPatientgraph(request, response, request.getParameter("searchPatientSSN"), request.getParameter("testName"));
				break;

			case "/listAbnormalData":
				listAbnormalData(request, response, request.getParameter("abnormalTestName"));
				break;

			case "/listdoctorhospital":
				listDoctorHospital(request, response, session);
				break;
			case "/getusermedication":
				getprescriptions(request, response, session);
				break;
			case "/listDoctorAppointments":
				listDoctorAppointments(request,response,session);
				break;
			case "/getuserlab":
				getlabs(request, response, session);
			break;
			}
		
		} catch (SQLException | ParseException ex) {
			throw new ServletException(ex);
		}
	}

	private void showpatientForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("patient-registration.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	
	private void showdoctorForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-registration.jsp");
		dispatcher.forward(request, response);
		
	}
	
	

	private void insertPatient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		
		String name = request.getParameter("patientName");
		String email = request.getParameter("patientEmail");
		String Birthdate = request.getParameter("patientBirthday");
		String SSN = request.getParameter("patientSSN");
		String familyid = request.getParameter("patientFamilyID");
		String address = request.getParameter("patientAddress");
		String communityid = request.getParameter("patientCommID");
		String prevfamilyid = request.getParameter("patientPrevFamilyID");
		int doctorid = Integer.parseInt(request.getParameter("patientDocID"));
		String password = request.getParameter("patientPassword");
		Patient newPatient = new Patient(SSN, name, Birthdate, address, email, communityid, familyid, prevfamilyid, doctorid, password, 0);
		DAO.insertPatient(newPatient);
		response.sendRedirect("login-form.jsp");
		
		
	}
	
	private void deletePatient(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		String SSN = request.getParameter("ssn");
		try {
			DAO.deletePatient(SSN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listPatientcommunity(request, response, session);
	}
	private void deleteDoctor(HttpServletRequest request, HttpServletResponse response, HttpSession session ) throws SQLException, IOException, ServletException {
		int SSN = Integer.parseInt(request.getParameter("ssn"));
		System.out.println(SSN);
		try {
			DAO.deleteDoctor(SSN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listDoctorHospital( request,  response, session);
	}
	
	private void approvePatient(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		String ssn = request.getParameter("ssn");
		
		Patient newPatient = new Patient(ssn, 1);
		DAO.updatePatient(newPatient);
		listPatientcommunity(request, response, session);
		//response.sendRedirect("community-data.jsp");
	}
	
	private void approveDoctor(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		String ssn = request.getParameter("ssn");
		
		Doctor newDoctor = new Doctor(ssn, 1);
		DAO.updateDoctor(newDoctor);
		listDoctorHospital( request,  response, session);
	}
	
	private void insertDoctor(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		String ssn = request.getParameter("doctorSSN");
		String name = request.getParameter("doctorName");
		String birthdate = request.getParameter("doctorBirthday");
		String address = request.getParameter("doctorAddress");
		String email = request.getParameter("doctorEmail");
		String communityid = request.getParameter("doctorCommID");
		String hospitalid = request.getParameter("doctorHospID");
		int doctorid = Integer.parseInt(request.getParameter("doctorID"));
		String password = request.getParameter("doctorPassword");
		
		Doctor newDoctor = new Doctor(ssn, name, birthdate, address, email, communityid, hospitalid, doctorid, password, 0);
		DAO.insertDoctor(newDoctor);
		response.sendRedirect("login-form.jsp");
		
		
	}
	
	private void insertCommunityOwner(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int communityid = Integer.parseInt(request.getParameter("commID"));
		String email = request.getParameter("commEmail");
		String password = request.getParameter("commPassword");
		
		CommunityOwner newCommunityOwner = new CommunityOwner(communityid, email, password);
		DAO.insertCommunityOwner(newCommunityOwner);
		System.out.println("Community Owner Added");
		response.sendRedirect("login-form.jsp");
		
	}
	
	private void insertHospitalOwner(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int hospitalid = Integer.parseInt(request.getParameter("ownerHospID"));
		String hospitalname = request.getParameter("ownerHospitalName");
		String hospitaladdress = request.getParameter("ownerHospitalAddress");
		String email = request.getParameter("ownerEmail");
		String password = request.getParameter("ownerPassword");
		
		HospitalOwner newHospitalOwner = new HospitalOwner(hospitalid, hospitalname, hospitaladdress, email, password);
		DAO.insertHospitalOwner(newHospitalOwner);
		System.out.println("Hospital Owner Added");
		response.sendRedirect("login-form.jsp");
		
	}
	
	private void insertNewTest(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {
		
		String name = request.getParameter("testName");
		int upperBound = Integer.parseInt(request.getParameter("upperBound"));
		int lowerBound = Integer.parseInt(request.getParameter("lowerBound"));
		String unit = request.getParameter("testUnits");
		
		NewTest newTest = new NewTest(name, upperBound, lowerBound, unit);
		DAO.insertNewTest(newTest);
		System.out.println("New Test Added");
		response.sendRedirect("create-test.jsp");
		
	}
	
	private void insertNewData(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws SQLException, IOException {
			
			String ssn = session.getAttribute("patientSsn").toString();
			int pressureHigh = Integer.parseInt(request.getParameter("bloodPressureDataUpper"));
			int pressureLow = Integer.parseInt(request.getParameter("bloodPressureDataLower"));
			int pulse = Integer.parseInt(request.getParameter("pulseData"));
			double temperature = Double.parseDouble(request.getParameter("temperatureData"));
			int oxygen = Integer.parseInt(request.getParameter("spo2Data"));
			int glucose = Integer.parseInt(request.getParameter("glucoseData"));
			double weight = Double.parseDouble(request.getParameter("weightData"));
			String date = LocalDate.now().toString();
			
			TestData data = new TestData(ssn, pressureHigh, pressureLow, pulse, temperature, oxygen, glucose, weight, date);
			DAO.insertTestData(data);
			System.out.println("New Data Added");
			response.sendRedirect("patient-testdata.jsp");
			
		}
	
	private void insertNewAppointment(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws SQLException, IOException, ParseException {
		
			int doctorid = Integer.parseInt(session.getAttribute("doctorid").toString());
			//String date = dates.format(availDate.parse(request.getParameter("apptDate")));
			String date = request.getParameter("apptDate");
			String time = request.getParameter("apptTime");
			System.out.println(date);
			Appointment appointment = new Appointment(doctorid, date, time);
			DAO.insertAppointment(appointment);
			System.out.println("New Appointment Added");
			response.sendRedirect("doctor-appointments.jsp");
			
		}
	
	private void listPatient(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		try {
			List<Patient> listPatient = DAO.selectAllPatients();
			request.setAttribute("listPatient", listPatient);
			RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listDoctor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		try {
			List<Doctor> listDoctor = DAO.selectAllDoctors();
			request.setAttribute("listDoctor", listDoctor);
			RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listPatientcommunity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, ServletException, IOException
	{
		try {
			String communityid = (String) session.getAttribute("communityid");
			List<Patient> listPatient = DAO.selectPatientsInCommunity(communityid);
			request.setAttribute("listpatientcommunity", listPatient);
			RequestDispatcher dispatcher = request.getRequestDispatcher("community-data.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void listPatientHospital(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, ServletException, IOException
	{
		try {
			String hospitalid = (String)session.getAttribute("hospitalid");
			List<Patient> listPatient = DAO.selectPatientsInHospital(hospitalid);
			request.setAttribute("listPatient", listPatient);
			RequestDispatcher dispatcher = request.getRequestDispatcher("hospital-data.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void listDoctorHospital(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, ServletException, IOException
	{
		try {
			String hospitalid = (String)session.getAttribute("hospitalid");
			List<Doctor> listDoctor = DAO.selectDoctorsinHospital(hospitalid);
			request.setAttribute("listhospitaldoctor", listDoctor);
			RequestDispatcher dispatcher = request.getRequestDispatcher("hospital-doctors.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listTest(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		try {
			List<NewTest> listTest = DAO.selectAllTests();
			request.setAttribute("listTest", listTest);
			RequestDispatcher dispatcher = request.getRequestDispatcher("patient-testdata.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listPatientgraph(HttpServletRequest request, HttpServletResponse response, String ssn, String testname) 
			throws SQLException, IOException, ServletException {
		String datapoints;
		String unit = DAO.unit_type(testname);
		
		request.setAttribute("testname", testname);
		request.setAttribute("unit", unit);
		if (testname.equals("Blood Pressure")) {
			datapoints = DAO.patientgraphPressure(ssn);
			request.setAttribute("list", datapoints);
			request.setAttribute("chart_type", "rangeColumn");
		} else {
			datapoints = DAO.patientgraph(ssn, testname);
			request.setAttribute("list", datapoints);
			request.setAttribute("chart_type", "line");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Graph.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listAbnormalData(HttpServletRequest request, HttpServletResponse response, String testname) 
			throws SQLException, IOException, ServletException {
		
		try {
			List<AbnormalTest> data = DAO.selectAbnormalTests();
			System.out.println(data);
			request.setAttribute("listAbnormal", data);
			RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-homePage.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listOpenAppointments(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
			throws SQLException, IOException, ServletException {
		SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dates = new SimpleDateFormat("MMM dd yyyy");
		int doctorid = Integer.parseInt(String.valueOf(session.getAttribute("patientDoctorid")));
		String date = String.valueOf(request.getParameter("searchDate"));
		
		try {
			List<Appointment> appointments = DAO.selectOpenAppointments(doctorid, date);
			System.out.println(appointments);
			request.setAttribute("listOpenVisits", appointments);
			RequestDispatcher dispatcher = request.getRequestDispatcher("patient-makeAppointment.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listBookedAppointments(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
			throws SQLException, IOException, ServletException {
		SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dates = new SimpleDateFormat("MMM dd yyyy");
		String ssn = String.valueOf(session.getAttribute("patientSsn"));
		
		try {
			List<Appointment> appointments = DAO.selectBookedAppointments(ssn);
			List<String> uniquedates = new ArrayList<String>();
			for(int i =0; i< appointments.size(); i++) {
				if(!uniquedates.contains(appointments.get(i).getDate())) {
					uniquedates.add(appointments.get(i).getDate());
				}
			}
			
			
			
			System.out.println(appointments);
			request.setAttribute("listBookedVisits", appointments);
			request.setAttribute("uniquedates", uniquedates);
			RequestDispatcher dispatcher = request.getRequestDispatcher("patient-allAppointments.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void listDoctorAppointments(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
			throws SQLException, IOException, ServletException {
		
		int doctorid = Integer.parseInt((String) session.getAttribute("doctorid")) ;
		
		try {
			List<Appointment> appointments = DAO.selectDoctorAppointments(doctorid);
			List<String> uniquedates = new ArrayList<String>();
			for(int i =0; i< appointments.size(); i++) {
				if(!uniquedates.contains(appointments.get(i).getDate())) {
					uniquedates.add(appointments.get(i).getDate());
				}
			}
			
			
			
			System.out.println(appointments);
			request.setAttribute("listBookedVisits", appointments);
			request.setAttribute("uniquedates", uniquedates);
			RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-allAppointments.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listDoneAppointments(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
			throws SQLException, IOException, ServletException {
		int doctorid = Integer.parseInt(String.valueOf(session.getAttribute("doctorid")));
		
		try {
			List<Appointment> appointments = DAO.selectDoneAppointments(doctorid);
			System.out.println(appointments);
			request.setAttribute("listDoneVisits", appointments);
			RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-visit-summary.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void bookAppointment(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		String ssn = String.valueOf(session.getAttribute("patientSsn"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		DAO.bookAppointment(ssn, id);
		listOpenAppointments(request, response, session);
	}
	
	private void cancelAppointment(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String ssn = request.getParameter("name");
		System.out.println(ssn);
		System.out.println("jiocdoi");
		DAO.cancelAppointment(id);
		listBookedAppointments(request, response, session);
	}
	private void insertvisitstuff(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		String[] meds = request.getParameterValues("m");
		String[] labs = request.getParameterValues("l");
		String ssn = (String) session.getAttribute("names");
		String summary = request.getParameter("summary");
		String symptoms = request.getParameter("symptoms");
		int id =Integer.parseInt((String) session.getAttribute("id"));
			DAO.insertmedication(ssn, meds);
		
		
			DAO.insertlab(ssn, labs);
		
			DAO.updateAppointment(symptoms, summary, id);
		
	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-allAppointments.jsp");
		dispatcher.forward(request, response);
		
	}
	private void getprescriptions(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
	List<prescriptions> prescriptions = DAO.retrievemedication((String) session.getAttribute("names"));
	Gson Gson = new Gson();
	String json = Gson.toJson(prescriptions);
	System.out.println(json);
	response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(json);
	
	}
	private void getlabs(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		List<labtests> labtests = DAO.retrievelabs((String) session.getAttribute("names"));
		Gson Gson = new Gson();
		String json = Gson.toJson(labtests);
		System.out.println(json);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
		
		}
	private void Appointmentvisit(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		String ssn = request.getParameter("name");
		session.setAttribute("names", ssn);
		session.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-visit-summary.jsp");
		dispatcher.forward(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws SQLException, IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String list[] = DAO.findperson(email, password);
		if(list[0] != null) {
			request.setAttribute(email, email);
			request.setAttribute(password, password);
			
			if(list[0] == "patient") {
				session.setAttribute("user", "patient");
				session.setAttribute("patientSsn", list[1]);
				session.setAttribute("patientDoctorid", list[2]);
				RequestDispatcher dispatcher = request.getRequestDispatcher("viewPatientTests-hub.jsp");
				dispatcher.forward(request, response);
			}else if(list[0] == "doctor") {
				session.setAttribute("user","doctor");
				session.setAttribute("doctorid", list[1]);
				RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-homePage.jsp");
				dispatcher.forward(request, response);
			}else if(list[0] == "community") {
				session.setAttribute("communityid", list[1]);
				session.setAttribute("user", "community");
				listPatientcommunity(request, response, session);
			}else if(list[0] == "hospital") {
				session.setAttribute("hospitalid", list[1]);
				session.setAttribute("user", "hospital");
				listPatientHospital(request, response, session);
			}else if(list[0] == "admin") {
				session.setAttribute("user", "admin");
				RequestDispatcher dispatcher = request.getRequestDispatcher("create-test.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			System.out.println("Invalid credentials");
			request.setAttribute("error", " invalid login");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login-form.jsp");
            dispatcher.forward(request, response);
		}
		
	}
	}
	

