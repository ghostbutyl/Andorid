package com.example.shujucunchu;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class mydb extends SQLiteOpenHelper {

	private static String name = "mydb.db";
	private static mydb busDB;
	
	public mydb(Context context) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}
	
	//获取数据库实例化
	public static mydb getInstance(Context context){
		if(busDB == null){
			busDB = new mydb(context);
			return busDB;
		}else{
			return busDB;
		}
	}

	public int getID(Cursor c){
		return c.getInt(0);
	}
	
	public String getName(Cursor c){
		return c.getString(1);
	}
	
	public int getAge(Cursor c){
		return c.getInt(2);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table user(id integer primary key autoincrement,name char(10),age integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
