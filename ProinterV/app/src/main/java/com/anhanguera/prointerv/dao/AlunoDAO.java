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
        super(context, "Aluno", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, email TEXT, nota REAL, posts INTEGER);";
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
        dados.put("email", aluno.getEmail());
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
            aluno.setPosts(cursor.getLong(cursor.getColumnIndex("posts")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
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
