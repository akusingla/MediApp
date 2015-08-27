package databaseActivities;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;
import beans.MedicineBean;

public class InsertServices {
	Context ctx;
	public InsertServices(Context ctx) {
		// TODO Auto-generated constructor stub
		this.ctx=ctx;
	}
	
	public void insertInfo(MedicineBean bean, List<String>lstPSalts , List<String>lstSSalts)
	{
		SQLiteDatabase db =null;
		Cursor cur= null;
		
		
		try{
			db = ConnectDatabase.connect(ctx);
			ContentValues values = new ContentValues();
			String medName=bean.getMedicineName();
			values.put("medicine_name",medName);
			values.put("price" , bean.getPrice());
			values.put("type", bean.getType());
			values.put("unit", bean.getUnit());
			values.put("std_pack",bean.getStandardPackaging());
			values.put("brand_name",bean.getBrandName());
			
			     long id =db.insert("Medicines", null, values);
			     if(id>0)
			      {
			             	System.out.println(" Medicine entered");
				
			       }
		
		
			for(String item:lstPSalts)
				{
					ContentValues sValue = new ContentValues();
					sValue.put("salt_name", item);
					long saltId =	db.insert("Salts",null,sValue);
					if(saltId>0)
					{
						System.out.println(" Primary Salt entered");
					}
					String selectionArgs[] ={medName,item};
				cur =db.rawQuery("insert into MedicineSalt(medicine_id , salt_id, salt_type)" +
						"  values(  (select medicine_id from  Medicines where lower(medicine_name) = ?), " +
						" ( select salt_id from  Salts where lower(salt_name)= ?)," +
						" 'primary'  )", selectionArgs);
				if(cur.moveToFirst())
				{
						db.execSQL("commit");
						
						System.out.println(" Entry done in MedicineSalt for primary salt");	
				}
				else 
				{
					System.out.println("insert into medicne salt did not return value");
				}
				
			}
			
		
	        
		    
		    for(String item:lstSSalts)
			{
				ContentValues sValue = new ContentValues();
				sValue.put("salt_name", item);
				 long saltId =	db.insert("Salts",null,sValue);
				if(saltId>0){
					System.out.println(" secondary Salt entered");
				}
				String selectionArgs[] = { medName, item};
				cur= db.rawQuery("insert into MedicineSalt(medicine_id ,salt_id,salt_type)" +
						" values(select medicine_id from  Medicines where medicine_name = ?) ," +
						" (select salt_id from  Salts where salt_name= ?)," +
						" 'secondary'  )", selectionArgs);
				
				if(cur.moveToFirst())
				{
					db.execSQL("commit");
					System.out.println(" entry done in MedicineSalt for secondary salt");
				}
			}
			Toast.makeText(ctx,"Info added",Toast.LENGTH_LONG).show();
			
		}
	    catch(SQLiteConstraintException e){
	    	System.out.println("constraint violation exception "+e);
	    }
		catch(SQLiteDatabaseCorruptException e){
			System.out.println("the data base was corrupted leading to error -" + e);
		}
		catch(SQLiteException e){
			System.out.println("error in database entry :"+e);
		}
		catch(Exception e){
			System.out.println("error"+e+"in insertservice");
		}
		 finally {
			 if (db != null)
				db.close();
			 if(cur !=null)
				cur.close();
		 }
	}
	    
	}
	

