package com.anhanguera.prointerv.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.prointerv.MODEL.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "Aluno", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Aluno (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, curso TEXT, ra TEXT, email TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Aluno";
        db.execSQL(sql);
        onCreate(db);
    }

    public void Insert(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetAlunoContentValues(aluno);

        db.insert("Aluno", null, dados);
    }

    private ContentValues GetAlunoContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("ra", aluno.getRa());
        dados.put("curso", aluno.getCurso());
        dados.put("email", aluno.getEmail());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    public List<Aluno> GetAlunos() {
        String sql = "SELECT * FROM Aluno";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();

        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setRa(cursor.getString(cursor.getColumnIndex("ra")));
            aluno.setCurso(cursor.getString(cursor.getColumnIndex("curso")));
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
        db.delete("Aluno", "id = ?", params);
    }

    public void EditAluno(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetAlunoContentValues(aluno);

        String[] params = {aluno.getId().toString()};

        db.update("Aluno", dados, "id = ?", params);
    }

    public Aluno GetAlunoByNome(String nome){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Grupo", new String[] {"rowid","*"},"nome LIKE '?'", new String[]{nome+"%"}, null, null, null);

        Aluno aluno = new Aluno();

        while(cursor.moveToNext()){
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setRa(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setCurso(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
        }

        cursor.close();

        return aluno;
    }
}
