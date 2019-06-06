package com.anhanguera.prointerv.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Grupo;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO extends SQLiteOpenHelper {
    public GrupoDAO(Context context) {
        super(context, "Grupo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Grupo (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, representante TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Grupo";
        db.execSQL(sql);
        onCreate(db);
    }

    public List<Grupo> GetGrupo() {
        String sql = "SELECT * FROM Grupo";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Grupo> grupos = new ArrayList<>();

        while(cursor.moveToNext()){
            Grupo grupo = new Grupo();
            grupo.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            grupo.setRepresentante(cursor.getString(cursor.getColumnIndex("representante")));
            grupos.add(grupo);
        }

        cursor.close();

        return grupos;
    }

    public void Delete(Grupo grupo) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {grupo.getId().toString()};
        db.delete("Grupo", "id = ?", params);
    }

    public void EditGrupo(Grupo grupo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetGrupoContentValues(grupo);

        String[] params = {grupo.getId().toString()};

        db.update("Grupo", dados, "id = ?", params);
    }

    public void Insert(Grupo grupo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetGrupoContentValues(grupo);

        db.insert("Grupo", null, dados);
    }

    private ContentValues GetGrupoContentValues(Grupo grupo) {
        ContentValues dados = new ContentValues();
        dados.put("nome", grupo.getNome());
        dados.put("representante", grupo.getRepresentante());
        return dados;
    }
}