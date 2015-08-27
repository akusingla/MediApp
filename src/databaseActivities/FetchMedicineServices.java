package databaseActivities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import beans.MedicineBean;

public class FetchMedicineServices {
	Context ctx;

	public FetchMedicineServices(Context ctx) {
		// TODO Auto-generated constructor stub
		this.ctx = ctx;
	}

	// returns list of medicines by taking list of salts
	
	
	public List<MedicineBean> getMedicineBySaltNames(List<String> lstPrimarySalts, List<String> lstSecondarySalts) {
		SQLiteDatabase db = null;
		Cursor cur = null;
		
		List<MedicineBean> medicinesList = new ArrayList<MedicineBean>();

		String query = "SELECT  * from MedicineSalt ms inner join Medicines mm on ms.medicine_id=mm.medicine_id inner join Salts sm on ms.salt_id=sm.salt_id";
		if (lstPrimarySalts.size() > 0 || lstSecondarySalts.size() > 0) {
			query = query + " where ";
		}
		if (lstPrimarySalts.size() > 0) {
			query = query + " salt_name in(";
			for (int i = 0; i < lstPrimarySalts.size(); i++) {
				if (i < lstPrimarySalts.size() - 1) {
					query = query +"'"+ lstPrimarySalts.get(i) + "' ,";
				} else {
					query = query + "'"+lstPrimarySalts.get(i) + "'";
				}
			}
			query = query + ")";
		}
		String andKeyWord = "";
		if (lstSecondarySalts.size() > 0)
			andKeyWord = " and ";

		if (lstSecondarySalts.size() > 0 && lstSecondarySalts.get(0)!="") {
			query = query + andKeyWord + " salt_name in(";
			for (int i = 0; i < lstSecondarySalts.size(); i++) {
				if (i < lstSecondarySalts.size() - 1) {
					query = query + "'" +lstSecondarySalts.get(i) + "' ,";
				} else {
					query = query +"'"+ lstSecondarySalts.get(i) + "'";
				}
			}
			query = query + ")";
		}
		try {
			db = ConnectDatabase.connect(ctx);
			MedicineBean obj = new MedicineBean();
			System.out.println(query);
			cur = db.rawQuery(query, null);
			if (cur.moveToFirst()) {
				do {
					System.out.println("Query successful");
					System.out.println(cur.getCount());
					obj.setMedicineName(cur.getString(cur.getColumnIndex("medicine_name")));
					obj.setMedicineInfoKey(cur.getInt(cur.getColumnIndex("medicine_id")));
					obj.setBrandName(cur.getString(cur.getColumnIndex("brand_name")));
					obj.setPrice(cur.getDouble(cur.getColumnIndex("price")));
					obj.setType(cur.getString(cur.getColumnIndex("type")));
					obj.setStandardPackaging(cur.getString(cur.getColumnIndex("std_pack")));
					obj.setUnit(cur.getString(cur.getColumnIndex("unit")));
					medicinesList.add(obj);
				} while (cur.moveToNext());
				
				
			}
			else
			{
				System.out.println("Query did not return a result");
				cur=db.rawQuery(" Select *  from Salts", null);
				if(cur.moveToFirst())
				{
					System.out.println(cur.getString(cur.getColumnIndex("salt_name")));
				}
				cur=db.rawQuery("Select * from MedicineSalt where medicine_id=1",null);
				if(cur.moveToFirst())
				{
					System.out.println(cur.getString(cur.getColumnIndex("type")));
				}
			}
			
			
		}

		catch (Exception e) {
			System.out.println(e + " in fetching medicines");
		}

		finally {
			if (db != null)
				db.close();
			if (cur != null)
				cur.close();

		}
		return medicinesList;
	}

}
