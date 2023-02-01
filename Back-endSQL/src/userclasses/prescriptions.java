package userclasses;

public class prescriptions {
	private String ssn;
	private String prescription;
	
	public prescriptions(String ssn, String prescription) {
		this.ssn = ssn;
		this.prescription = prescription;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

}
