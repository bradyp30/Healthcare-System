package userclasses;
// adding changes to patient
public class Patient {
	private String ssn;
	private String name;
	private String birthdate;
	private String address;
	private String email;
	private String communityid;
	private String familyid;
	private String prevfamilyid;
	private int doctorid;
	private String password;
	private int accepted;
	
	
	//all values
	public Patient(String ssn, String name, String birthdate, String address, String email, String communityid,
			String familyid, String prevfamilyid, int doctorid, String password, int accepted) {
		this.ssn = ssn;
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.communityid = communityid;
		this.familyid = familyid;
		this.prevfamilyid = prevfamilyid;
		this.doctorid = doctorid;
		this.password = password;
		this.accepted = accepted;
	}
	
	//no password
	public Patient(String ssn, String name, String birthdate, String address, String email, String communityid,
			String familyid, String prevfamilyid, int doctorid, int accepted) {
		this.ssn = ssn;
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.communityid = communityid;
		this.familyid = familyid;
		this.prevfamilyid = prevfamilyid;
		this.doctorid = doctorid;
		this.accepted = accepted;
	}
	
	//just ssn and accepted
	public Patient(String ssn, int accepted) {
		this.ssn = ssn;
		this.accepted = accepted;
	}
	
	//no ssn
	public Patient(String name, String birthdate, String address, String email, String communityid, String familyid,
			String prevfamilyid, int doctorid, String password) {
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.communityid = communityid;
		this.familyid = familyid;
		this.prevfamilyid = prevfamilyid;
		this.doctorid = doctorid;
		this.password = password;
	}
	
	//no accepted
	public Patient(String ssn, String name, String birthdate, String address, String email, String communityid, String familyid,
			String prevfamilyid, int doctorid, String password) {
		this.ssn = ssn;
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.communityid = communityid;
		this.familyid = familyid;
		this.prevfamilyid = prevfamilyid;
		this.doctorid = doctorid;
		this.password = password;
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCommunityid() {
		return communityid;
	}
	public void setCommunityid(String communityid) {
		this.communityid = communityid;
	}
	public String getFamilyid() {
		return familyid;
	}
	public void setFamilyid(String familyid) {
		this.familyid = familyid;
	}
	public String getPrevfamilyid() {
		return prevfamilyid;
	}
	public void setPrevfamilyid(String prevfamilyid) {
		this.prevfamilyid = prevfamilyid;
	}
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccepted() {
		return accepted;
	}
	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}
	
}
