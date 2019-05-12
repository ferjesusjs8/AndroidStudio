package com.anhanguera.DesafioProfissionalV.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.DesafioProfissionalV.Model.Empresa;
import com.anhanguera.DesafioProfissionalV.Model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class ServicoDAO extends SQLiteOpenHelper {
    public ServicoDAO(Context context) {
        super(context, "Servico", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Servico (Id INTEGER PRIMARY KEY, Descricao TEXT NOT NULL, Valor LONG)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Servico";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
