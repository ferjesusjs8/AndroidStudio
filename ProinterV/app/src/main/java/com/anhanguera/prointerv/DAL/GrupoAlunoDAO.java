package com.anhanguera.prointerv.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.GrupoAluno;

import java.util.ArrayList;
import java.util.List;

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
    public void Insert(GrupoAluno grupoAluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetGrupoAlunoContentValues(grupoAluno);

        db.insert("GrupoAluno", null, dados);
    }

    private ContentValues GetGrupoAlunoContentValues(GrupoAluno grupoAluno) {
        ContentValues dados = new ContentValues();
        dados.put("idAluno", grupoAluno.getIdAluno());
        dados.put("idGrupo", grupoAluno.getIdGrupo());
        return dados;
    }

    public List<GrupoAluno> GetGrupoAluno() {
        String sql = "SELECT * FROM GrupoAluno";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<GrupoAluno> grupoAlunos = new ArrayList<>();

        while(cursor.moveToNext()){
            GrupoAluno grupoAluno = new GrupoAluno();
            grupoAluno.setIdAluno(cursor.getLong(cursor.getColumnIndex("idAluno")));
            grupoAluno.setIdGrupo(cursor.getLong(cursor.getColumnIndex("idGrupo")));
            grupoAlunos.add(grupoAluno);
        }

        cursor.close();

        return grupoAlunos;
    }

    public void Delete(GrupoAluno grupoAluno) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {grupoAluno.getId().toString()};
        db.delete("GrupoAluno", "id = ?", params);
    }

    public void EditGrupoAluno(GrupoAluno grupoAluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetGrupoAlunoContentValues(grupoAluno);

        String[] params = {grupoAluno.getId().toString()};

        db.update("GrupoAluno", dados, "id = ?", params);
    }
}
