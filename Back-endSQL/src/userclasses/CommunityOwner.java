package userclasses;

public class CommunityOwner {
	private int communityid;
	private String email;
	private String password;
	
	public CommunityOwner(int communityid, String email, String password) {
		super();
		this.communityid = communityid;
		this.email = email;
		this.password = password;
	}
	
	public CommunityOwner(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getCommunityid() {
		return communityid;
	}
	public void setCommunityid(int communityid) {
		this.communityid = communityid;
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
