package com.anhanguera.prointerv.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PublicacaoDAO extends SQLiteOpenHelper {
    public PublicacaoDAO(Context context) {
        super(context, "Publicacao", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Publicacao (id INTEGER PRIMARY KEY, texto Text NOT NULL, titulo Text NOT NULL, autor Text NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS PublicacaoAluno";
        db.execSQL(sql);
        onCreate(db);
    }

}
