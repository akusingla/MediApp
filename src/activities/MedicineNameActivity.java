package activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import beans.SaltsDataBean;

import com.example.mediapp.R;

import databaseActivities.FetchSaltServices;
import databaseActivities.FetchMedicineServices;

public class MedicineNameActivity extends Activity {
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		getIntent();
		setContentView(R.layout.search_by_medicine_name_layout);
		}
	
// On click listener on the search button to search aliases by Medicine Name
public void searchByMedicineName(View v)
{
	HomeActivity.lstMedicines.clear();
	EditText medicineName=(EditText) findViewById(R.id.enterMedicineName);
	String name=medicineName.getText().toString();
	List<SaltsDataBean> lstPrimarySalts=new ArrayList<SaltsDataBean>();
	List<SaltsDataBean> lstSecondarySalts=new ArrayList<SaltsDataBean>();
	FetchSaltServices objMedicine= new FetchSaltServices(MedicineNameActivity.this);
	List<String> lstPrimary= new ArrayList<String>();
	List<String> lstSecondary=new ArrayList<String>();	
	// Fetching primary salts
	lstPrimarySalts= objMedicine.getPrimarySaltsByMedicineName(name);
	for(SaltsDataBean item:lstPrimarySalts)
	{
		lstPrimary.add(item.getSaltName());
	}
	
	lstSecondarySalts= objMedicine.getSecondarySaltsByMedicineName(name);
	for(SaltsDataBean item:lstSecondarySalts)
	{
		lstSecondary.add(item.getSaltName());
	}
	// fetching secondary salts
	lstSecondarySalts= objMedicine.getSecondarySaltsByMedicineName(name);
	
	FetchMedicineServices objSalt = new FetchMedicineServices(MedicineNameActivity.this);
	HomeActivity.lstMedicines.clear();
		
	HomeActivity.lstMedicines=objSalt.getMedicineBySaltNames(lstPrimary, lstSecondary);
	System.out.println("medicinebean set  for display");
	
	Intent medIntent = new Intent(MedicineNameActivity.this,OutputActivity.class);
	startActivity(medIntent);
	
	
	
}

public void onPause()
{
	super.onPause();
	
}

public void onResume()
{
	super.onResume();
	HomeActivity.lstMedicines.clear();
}

//	@Override
public boolean onCreateOptionsMenu(Menu menu) {

//		 Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.medicine_name, menu);
	return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
