package userclasses;

public class AbnormalTest {
	private String ssn;
	private String name;
	private String test;
	private String date;
	public AbnormalTest(String ssn,String name, String test,  String date) {
		this.ssn = ssn;
		this.name = name;
		this.test = test;
		this.date = date;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
