package com.dlvct.utils.db;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

public class DataHelper {

    //数据库名称

    private static String DB_NAME = "barcode";

    //数据库版本

    private static int DB_VERSION = 2;

    private SQLiteDatabase db;

    private SqliteHelper dbHelper;

    private boolean isClosed;

    public DataHelper(Context context){
    	dbHelper=new SqliteHelper(context,DB_NAME, null, DB_VERSION);
    	try{
	        db= dbHelper.getWritableDatabase();
	        isClosed = false;
    	}catch (SQLiteException e) {
    		dbHelper.close();
    		db= dbHelper.getWritableDatabase();
 	        isClosed = false;
		}
    	
//    	        dbHelper.onUpgrade(db, 1, 2);
    }

    

    public void Close(){
        db.close();
        dbHelper.close();
        isClosed = true;
    }

    public boolean isClosed(){
    	return isClosed;
    }
    
    public long saveSetting(int userid,int status){
    	long r = -1;
    	ContentValues values = new ContentValues();
    	values.put("USERID", userid);
    	values.put("AUTO_LOGIN", status);
    	r = db.update(SqliteHelper.TB_PREFERENCES, values, "USERID=?", new String[]{String.valueOf(userid)});
    	if(r<1){
    		db.delete(SqliteHelper.TB_PREFERENCES, null, null);
    		r = db.insert(SqliteHelper.TB_PREFERENCES, null, values);
    	}
    	return r;
    }
    
    public Map<String,String> getStetting(){
    	Map<String,String> map = new HashMap<String, String>();
    	Cursor cursor = db.query(SqliteHelper.TB_PREFERENCES, null, null, null, null, null, null,null);
    	while(cursor.moveToNext()){
    		map.put("USERID", cursor.getString(1));
        	map.put("AUTO_LOGIN", cursor.getString(2));
    	}
    	cursor.close();
    	return map;
    }
    public Map<String,String> getUser(String userid){
    	Map<String,String> map = new HashMap<String, String>();
    	Cursor cursor = db.query(SqliteHelper.TB_USER, null, "ID=?", new String[]{userid}, null, null, null,null);
    	while(cursor.moveToNext()){
    		map.put("USERNAME", cursor.getString(1));
        	map.put("PASSWORD", cursor.getString(2));
    	}
    	cursor.close();
    	return map;
    }
    
    public int checkLogin(String username,String password){
    	int r = 0;
    	Cursor cursor = db.query(SqliteHelper.TB_USER, null, "USERNAME=? AND PASSWORD=?", new String[]{username,password}, null, null, null);
    	while(cursor.moveToNext()){
    		r = cursor.getInt(0);
    	}
    	return r;
    }
    
    public ArrayList<Map<String,String>> getType(){
    	ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	Cursor cursor = db.query(SqliteHelper.TB_TYPE, null, null, null, null, null, null,null);
    	while(cursor.moveToNext()){
    		HashMap<String,String> map = new HashMap<String, String>();
    		map.put("ID", cursor.getString(0));
        	map.put("TYPE", cursor.getString(1));
        	list.add(map);
    	}
    	cursor.close();
    	return list;
    }
    public long saveType(String[] data){
    	long r = -1;
    	ContentValues values = new ContentValues();
    	for(int i=0;i<data.length;i++){
    		values.put("ID", String.valueOf(i));
        	values.put("TYPE", data[i]);
    	}
		db.delete(SqliteHelper.TB_TYPE, null, null);
		r = db.insert(SqliteHelper.TB_TYPE, null, values);
    	return r;
    }
    public long saveAttribute(String typeId,ArrayList<String> data){
    	long r = -1;
    	ContentValues values = new ContentValues();
    	int size = data.size();
    	db.beginTransaction();	
    	for(int i=0;i<size;i++){
//    		values.put("ID", String.valueOf(i));
        	values.put("TYPEID", typeId);
        	values.put("NAME", data.get(i));
        	db.insert(SqliteHelper.TB_ATTRIBUTE, null, values);
    	}
    	db.setTransactionSuccessful();
    	db.endTransaction();
//		r = db.insert(SqliteHelper.TB_ATTRIBUTE, null, values);
    	return r;
    }
    
//    
//    public int deleteSetting(String id){
//    	if(id!=null){
//    		return db.delete(SqliteHelper.TB_PREFERENCES, "USERID!=?", new String[]{id});
//    	}else{
//    		return db.delete(SqliteHelper.TB_PREFERENCES, null, null);
//    	}
//    	
//    }

    

}