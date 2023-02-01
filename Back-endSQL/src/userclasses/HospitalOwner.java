package userclasses;

public class HospitalOwner {
	private int hospitalid;
	private String hospitalname;
	private String hospitaladdress;
	private String email;
	private String password;
	
	
	public HospitalOwner(int hospitalid, String hospitalname, String hospitaladdress, String email, String password) {
		this.hospitalid = hospitalid;
		this.hospitalname = hospitalname;
		this.hospitaladdress = hospitaladdress;
		this.email = email;
		this.password = password;
	}
	
	public HospitalOwner(String hospitalname, String hospitaladdress, String email, String password) {
		this.hospitalname = hospitalname;
		this.hospitaladdress = hospitaladdress;
		this.email = email;
		this.password = password;
	}

	public int getHospitalid() {
		return hospitalid;
	}
	public void setHospitalid(int hospitalid) {
		this.hospitalid = hospitalid;
	}
	public String getHospitalname() {
		return hospitalname;
	}
	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	public String getHospitaladdress() {
		return hospitaladdress;
	}
	public void setHospitaladdress(String hospitaladdress) {
		this.hospitaladdress = hospitaladdress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
