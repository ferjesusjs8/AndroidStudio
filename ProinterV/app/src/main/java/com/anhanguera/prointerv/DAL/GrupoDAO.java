package com.anhanguera.prointerv.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GrupoDAO extends SQLiteOpenHelper {
    public GrupoDAO(Context context) {
        super(context, "Grupo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Grupo (id INTEGER PRIMARY KEY, nome TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Grupo";
        db.execSQL(sql);
        onCreate(db);
    }
}
