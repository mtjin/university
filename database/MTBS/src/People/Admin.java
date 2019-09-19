package People;

public class Admin {
	private String ID;
	private String PW;
	
	public Admin() {
		setID("admin");
		setPW("admin");
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}

}
