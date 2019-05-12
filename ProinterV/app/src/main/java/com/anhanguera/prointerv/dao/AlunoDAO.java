package com.anhanguera.prointerv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.anhanguera.prointerv.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void Insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetAlunoContentValues(aluno);

        db.insert("Alunos", null, dados);
    }

    private ContentValues GetAlunoContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    public List<Aluno> GetAlunos() {
        String sql = "SELECT * FROM Alunos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();

        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            alunos.add(aluno);
        }

        cursor.close();

        return alunos;
    }

    public void Delete(Aluno aluno) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params);
    }

    public void EditAluno(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetAlunoContentValues(aluno);

        String[] params = {aluno.getId().toString()};

        db.update("Alunos", dados, "id = ?", params);

    }
}
