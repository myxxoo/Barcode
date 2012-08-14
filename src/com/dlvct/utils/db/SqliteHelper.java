package com.dlvct.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper{

    //用来保存UserID、Access Token、Access Secret的表名

    public static final String TB_USER="user";
    public static final String TB_PREFERENCES="preferences";
    public SqliteHelper(Context context, String name, CursorFactory factory, int version) {

        super(context, name, factory, version);

    }

    //创建表

    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "+
				TB_USER+"("+
                "ID"+" integer AUTO_INCREMENT primary key,"+
                "USERNAME"+" varchar,"+
                "PASSWORD"+" varchar"+
                ")"
                );
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
				TB_PREFERENCES+"("+
                "ID"+" integer AUTO_INCREMENT primary key,"+
                "USERID"+" varchar,"+
                "AUTO_LOGIN"+" varchar"+ 		//0:正常  1:自动登陆
                ")"
                );
        Log.i("Database","onCreate");

    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
      db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
      onCreate(db);
      Log.e("Database","onUpgrade");
	}

}
