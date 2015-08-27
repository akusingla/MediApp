package databaseActivities;

import java.util.ArrayList;
import java.util.List;

import activities.MedicineNameActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import beans.SaltsDataBean;

public class FetchSaltServices {
	Context ctx = null;

	public FetchSaltServices(Context ctx) {
		// TODO Auto-generated constructor stub
		this.ctx = ctx;
	}

	public List<SaltsDataBean> getPrimarySaltsByMedicineName(String medicineName) {
		SQLiteDatabase db = null;
		Cursor cur = null;
		List<SaltsDataBean> lstPrimarySalts = new ArrayList<SaltsDataBean>();
		try {
			db = ConnectDatabase.connect(ctx);
			cur = db.rawQuery(
					"SELECT * from MedicineSalt ms inner join Medicines mm on ms.medicine_id=mm.medicine_id inner join Salts sm on ms.salt_id=sm.salt_id where medicine_name like ? and type='primary'",
					new String[] { "" + medicineName + "" });
			if (cur.moveToFirst()) {
				do {
					SaltsDataBean obj = new SaltsDataBean();
					obj.setSaltID(cur.getInt(cur.getColumnIndex("salt_id")));
					obj.setSaltName(cur.getString(cur.getColumnIndex("salt_name")));
					lstPrimarySalts.add(obj);
				} while (cur.moveToNext());
			}
			
		/*	else
				{
					Toast.makeText(this, "The medicine does not exist in the database",Toast.LENGTH_LONG).show();
				}  */
			
		} catch (Exception e) {
			System.out.println("error"+ e + " in fetching primay salts from medicine");
		} finally {
			if (cur != null)
				cur.close();
			if (db != null)
				db.close();
		}
		return lstPrimarySalts;
	}

	public List<SaltsDataBean> getSecondarySaltsByMedicineName(
			String medicineName) {
		SQLiteDatabase db = null;
		Cursor cur = null;
		List<SaltsDataBean> lstSecondarySalts = new ArrayList<SaltsDataBean>();
		try {
			db = ConnectDatabase.connect(ctx);
			cur = db.rawQuery("SELECT * from MedicineSalt ms inner join Medicines mm on ms.medicine_id=mm.medicine_id inner join Salts sm on ms.salt_id=sm.salt_id where medicine_name like ? and type= 'secondary' ",
					new String[] { "%" + medicineName + "%" });
			if (cur.moveToFirst()) {
				do {
					SaltsDataBean obj = new SaltsDataBean();
					obj.setSaltID(cur.getInt(cur.getColumnIndex("salt_id")));
					obj.setSaltName(cur.getString(cur.getColumnIndex("salt_name")));
					lstSecondarySalts.add(obj);
					System.out.println("salt bean set in medicineservices");
				} while (cur.moveToNext());
			}
		} catch (Exception e) {
			System.out.println("error"+e + " in fetching salsts from medicine name");
		} finally {
			if (cur != null)
				cur.close();
			if (db != null)
				db.close();
		}
		return lstSecondarySalts;
	}

	
	
}
