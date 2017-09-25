package org.tugva.basaksehir.tugvabasaksehir.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.tugva.basaksehir.tugvabasaksehir.Models.Icerik;

import java.util.ArrayList;

/**
 * Created by cdirman on 29.7.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int databaseVersion = 1;
    private static final String databaseName = "IcerikDB";
    private static final String tableIcerik = "icerikTable";

    //--------Table HomeActivity Icerik
    private static final String clmnId = "columnID";
    private static final String clmnIcerik = "columnIcerik";
    private static final String clmnIcerikBaslik ="columnIcerikBaslik";
    private static final String clmnIcerikUrl = "columnIcerikUrl";

    //----------Table Kategori

    private static final String createTableIcerikSql = "CREATE TABLE " + tableIcerik + "(" + clmnId + " INTEGER PRIMARY KEY,"
            +  clmnIcerik +" TEXT," + clmnIcerikBaslik + " TEXT,"  +clmnIcerikUrl + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(createTableIcerikSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + createTableIcerikSql);
        onCreate(db);
    }

    public void insertIcerikFromJson(Icerik icerik){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(clmnId, icerik.getId());
        values.put(clmnIcerik, icerik.getIcerik());
        values.put(clmnIcerikBaslik, icerik.getIcerikBaslik());
        values.put(clmnIcerikUrl, icerik.getIcerikUrl());
        db.insert(tableIcerik,null,values);
    }
    public ArrayList<Icerik> getAllIcerik(){
        ArrayList<Icerik> icerikList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + tableIcerik;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Icerik icerik = new Icerik();
            icerik.setId(cursor.getInt(cursor.getColumnIndex(clmnId)));
            icerik.setIcerik(cursor.getString(cursor.getColumnIndex(clmnIcerik)));
            icerik.setIcerikBaslik(cursor.getString(cursor.getColumnIndex(clmnIcerikBaslik)));
            icerik.setIcerikUrl(cursor.getString(cursor.getColumnIndex(clmnIcerikUrl)));
            icerikList.add(icerik);
            cursor.moveToNext();
        }
        return icerikList;
    }
}
