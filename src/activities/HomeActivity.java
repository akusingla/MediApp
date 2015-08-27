package activities;

import java.util.ArrayList;
import java.util.List;

import beans.MedicineBean;

import com.example.mediapp.R;

import databaseActivities.ConnectDatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends ActionBarActivity {
	public static List<MedicineBean> lstMedicines= new ArrayList<MedicineBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        System.out.println("hello home activity fine");
        lstMedicines.clear();
        check();
        
    }

    public void byMedicineName(View v)
    {
    	Intent byMedicineName= new Intent(HomeActivity.this,MedicineNameActivity.class);
    	startActivity(byMedicineName);
    }
    
    public void bySalts(View v)

    {
    	Intent bySalts=new Intent(HomeActivity.this,SaltsActivity.class);
    	startActivity(bySalts);
    }
    
    public void Entry(View v)
    {
    	Intent entry= new Intent(HomeActivity.this,EntryActivity.class);
    	startActivity(entry);
    }
  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

  
    
   void check()
   {
		SQLiteDatabase db =null;
		Cursor cur= null;
		
		
		try
		{
			db = ConnectDatabase.connect(this);
			cur=db.rawQuery("Select * from Medicines",null);
					if(cur.moveToFirst())
					{
						do
						{
							System.out.println(cur.getString(cur.getColumnIndex("medicine_name")));
						}while(cur.moveToNext());
					}
		}
		catch(Exception e)
		{
			System.out.println(e+ "in insert");
		}
		 finally
		 {
			 if (db != null)
					db.close();
			 if(cur !=null)
				 cur.close();
		 }
		}
					
}
