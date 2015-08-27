package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MedicineSaltsBean implements Serializable {

	int medicineId;
	int saltId;
	String type;

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public int getSaltId() {
		return saltId;
	}

	public void setSaltId(int saltId) {
		this.saltId = saltId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
