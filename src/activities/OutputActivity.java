package activities;

import adapter.MedicineAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mediapp.R;


public class OutputActivity extends Activity {
	ListView listView;
	String activity;
	MedicineAdapter adapter;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.output_screen_layout);
	        
	        listView=(ListView) findViewById(R.id.outputListView);	        
	        setAdapter();
	        
	        
	 } 
	 
	 private void setAdapter(){
		 
	          
	    	  adapter = new MedicineAdapter(OutputActivity.this, R.layout.output_screen_adapter, HomeActivity.lstMedicines);
	    	  listView.setAdapter(adapter);
	    	  
	    
	 }
	 
}
