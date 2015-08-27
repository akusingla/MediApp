package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SaltsDataBean implements Serializable{
	
	int saltID;
	String saltName,type;
	
	public int getSaltID() {
		return saltID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSaltID(int saltID) {
		this.saltID = saltID;
	}
	public String getSaltName() {
		return saltName;
	}
	public void setSaltName(String saltName) {
		this.saltName = saltName;
	}
	
	

}
