package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MedicineBean implements Serializable{
	
	int MedicineInfoKey;
	String medicineName;
	Double price;
	String type;
	String unit;
	String standardPackaging;
	String brandName;

	public int getMedicineInfoKey() {
		return MedicineInfoKey;
	}
	public void setMedicineInfoKey(int medicineInfoKey) {
		MedicineInfoKey = medicineInfoKey;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStandardPackaging() {
		return standardPackaging;
	}
	public void setStandardPackaging(String standardPackaging) {
		this.standardPackaging = standardPackaging;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	


}
