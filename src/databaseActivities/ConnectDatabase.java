package databaseActivities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ConnectDatabase {

	public static SQLiteDatabase connect(Context ctx) {
		// TODO Auto-generated constructor stub
		SQLiteDatabase database = ctx.openOrCreateDatabase("mDatabase.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
		return database;
	}
}
