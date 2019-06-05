package com.anhanguera.prointerv.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GrupoAlunoDAO extends SQLiteOpenHelper {
    public GrupoAlunoDAO(Context context) {
        super(context, "GrupoAluno", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE GrupoAluno (id INTEGER PRIMARY KEY, idGrupo INTEGER NOT NULL, idAluno INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS GrupoAluno";
        db.execSQL(sql);
        onCreate(db);
    }
}
