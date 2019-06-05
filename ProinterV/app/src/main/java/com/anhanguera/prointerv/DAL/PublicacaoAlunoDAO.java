package com.anhanguera.prointerv.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PublicacaoAlunoDAO extends SQLiteOpenHelper {
    public PublicacaoAlunoDAO(Context context) {
        super(context, "PublicacaoAluno", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE PublicacaoAluno (id INTEGER PRIMARY KEY, idAluno INTEGER NOT NULL, idPublicacao INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS PublicacaoAluno";
        db.execSQL(sql);
        onCreate(db);
    }
}
