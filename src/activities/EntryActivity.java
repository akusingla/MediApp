package activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import beans.MedicineBean;

import com.example.mediapp.R;

import databaseActivities.ConnectDatabase;
import databaseActivities.InsertServices;

public class EntryActivity extends Activity {
	EditText name , price, brand ,unit , stdPack , type ;
	EditText priSalts , secSalts;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.entry_form_layout);
	        
	        checkDatabase();
	        
	        
	 }
	        
	        public void add(View v)
	        {
	        name= (EditText) findViewById(R.id.etName);
	        price=( EditText)findViewById(R.id.etPrice);
	        brand=( EditText)findViewById(R.id.etBrand);
	        unit =( EditText)findViewById(R.id.etUnit);
	        stdPack=( EditText)findViewById(R.id.etPack);
	        type=( EditText)findViewById(R.id.etType);
	        
	        MedicineBean bean= new MedicineBean();
	        bean.setMedicineName(name.getText().toString());
	        if(price.getText().toString()=="")
	        {
	        	bean.setPrice(0.0);
	        }
	        else
	        {
	        bean.setPrice(Double.parseDouble(price.getText().toString()));
	        }
	        bean.setBrandName(brand.getText().toString());
	        bean.setUnit(unit.getText().toString());
	        bean.setStandardPackaging(stdPack.getText().toString());
	        bean.setType(type.getText().toString());
	        
	        // fetching salts
	        
	        priSalts=(EditText) findViewById(R.id.mtxtPrimary);
	        secSalts=(EditText) findViewById(R.id.mtxtSecondary);
	        String primaryText = priSalts.getText().toString();	        
	        String[] pSalts=primaryText.split(",");
	        List<String> lstPSalts= new ArrayList<String>();
	         for(String item:pSalts)
	        {
	        	lstPSalts.add(item);
	        	System.out.println("for loop to enter p salt done");
	        }
	         
	        String secondaryText=secSalts.getText().toString();
	        String[] sSalts=secondaryText.split(",");
	        List<String> lstSSalts = new ArrayList<String>();
	        for(String item:sSalts)
	        {
	        	lstSSalts.add(item);
	        	System.out.println("for loop to enter s salt done");
	        }
	        
	        
	        InsertServices insertObj = new InsertServices(this);
	        insertObj.insertInfo(bean,lstPSalts, lstSSalts);
	        Toast.makeText(EntryActivity.this, "insert complete",Toast.LENGTH_LONG).show();
	        Intent home= new Intent(this, HomeActivity.class);
	        startActivity(home);
	 }
	
	 private void checkDatabase() {
			SharedPreferences one = getSharedPreferences("mDatabase",Context.MODE_PRIVATE);
			Map map = one.getAll();
			if (map.size() == 0) {
				SQLiteDatabase db = ConnectDatabase.connect(this);
				db.execSQL("create table Medicines(medicine_id integer primary key autoincrement,medicine_name  text unique,price double,type text,unit text,std_pack text,brand_name text)");
				db.execSQL("create table Salts(salt_id integer primary key autoincrement, salt_name text unique) " );
				db.execSQL(" create table MedicineSalt(medicine_id integer, salt_id integer, salt_type text, primary key(medicine_id,salt_id))");
				db.close();
				System.out.println("Database Created");
				Editor editor = one.edit();
				editor.putString("DATABASE_EXISTS", "YES");
				editor.commit();
			}
	 }
}
