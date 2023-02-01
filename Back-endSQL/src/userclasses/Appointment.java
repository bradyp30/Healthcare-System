package userclasses;

public class Appointment {
	private int id;
	private String ssn;
	private int doctorid;
	private String date;
	private String time;
	private String symptoms;
	private String summary;
	
	public Appointment(String ssn, int doctorid, String date, String time, String symptoms, String summary) {
		this.ssn = ssn;
		this.doctorid = doctorid;
		this.date = date;
		this.time = time;
		this.symptoms = symptoms;
		this.summary = summary;
	}
	
	public Appointment(int doctorid, String date, String time) {
		this.doctorid = doctorid;
		this.date = date;
		this.time = time;
	}
	
	public Appointment(int id, int doctorid, String date, String time) {
		this.id = id;
		this.doctorid = doctorid;
		this.date = date;
		this.time = time;
	}
	
	public Appointment(int id, int doctorid, String date, String symptoms, String summary) {
		this.id = id;
		this.date = date;
		this.symptoms = symptoms;
		this.summary = summary;
	}
	
	public Appointment(int id, String ssn, String date, String time) {
		this.id = id;
		this.ssn = ssn;
		this.date = date;
		this.time = time;
	}
	
	public Appointment(String ssn, int doctorid, String date, String time) {
		this.ssn = ssn;
		this.doctorid = doctorid;
		this.date = date;
		this.time = time;
	}

	public int getId() {
		return id;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
