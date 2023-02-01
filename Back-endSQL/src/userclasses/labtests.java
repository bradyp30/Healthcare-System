package userclasses;

public class labtests {
	private String ssn;
	private String lab;
	
	public labtests(String ssn, String lab) {
		this.ssn = ssn;
		this.lab = lab;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}
	
}
