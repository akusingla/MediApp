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

import com.example.mediapp.R;

import databaseActivities.FetchMedicineServices;

public class SaltsActivity extends Activity {
	List<String> secondarySaltsList=new ArrayList<String>();  // list having values of secondary salts
	List<String> primarySaltsList=new ArrayList<String>(); // list having values of primary salts
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_by_salt_layout);
	}
	
// On click listener on the search button that searches aliases by salts entered
	public void searchBySalts(View v)
	  {
		HomeActivity.lstMedicines.clear();
		  EditText primarySaltnames=(EditText) findViewById(R.id.enterPrimarySalts);
		  EditText secondarySaltNames=(EditText) findViewById(R.id.enterSecondrySalts);
		  String primaryText=primarySaltnames.getText().toString();
		  if(primaryText!="")
		  {
			  String[] psalts=primaryText.split(",");
			  
			  for(String salt:psalts)
			  {
				  primarySaltsList.add(salt);     // list instantiated
			  }  
		  }
		  
		  String secondaryText= secondarySaltNames.getText().toString();
		 if(secondaryText!="") 
		 {
			 String[] ssalts=secondaryText.split(",");
			 
			 for(String salt:ssalts)
			 {
				 secondarySaltsList.add(salt);   // list instantiated
			 }
		 } 
		  FetchMedicineServices objSalt= new FetchMedicineServices(this);
		  HomeActivity.lstMedicines.clear();
		  HomeActivity.lstMedicines= objSalt.getMedicineBySaltNames(primarySaltsList, secondarySaltsList);
		  Intent saltIntent = new Intent(SaltsActivity.this,OutputActivity.class);
		  startActivity(saltIntent);
		  
	  }
	
// TO CREATE OPTIONS MENU CREATE LATER
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.salts, menu);
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
	
	public void onPause()
	{
		super.onPause();
		
	}

	public void onResume()
	{
		super.onResume();
		HomeActivity.lstMedicines.clear();
	}

	
}
