package com.anhanguera.prointerv.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Publicacao;
import com.anhanguera.prointerv.MODEL.PublicacaoAluno;

import java.util.ArrayList;
import java.util.List;

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
    public void Insert(PublicacaoAluno publicacaoAluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetPublicacaoAlunoContentValues(publicacaoAluno);

        db.insert("PublicacaoAluno", null, dados);
    }

    private ContentValues GetPublicacaoAlunoContentValues(PublicacaoAluno publicacaoAluno) {
        ContentValues dados = new ContentValues();
        dados.put("idAluno", publicacaoAluno.getIdAluno());
        dados.put("idPublicacao", publicacaoAluno.getIdPublicacao());
        return dados;
    }

    public List<PublicacaoAluno> GetPublicacoesAlunos() {
        String sql = "SELECT * FROM PublicacaoAluno";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<PublicacaoAluno> publicacoesAlunos = new ArrayList<PublicacaoAluno>();

        while(cursor.moveToNext()){
            PublicacaoAluno publicacaoAluno = new PublicacaoAluno();
            publicacaoAluno.setIdAluno(cursor.getLong(cursor.getColumnIndex("idAluno")));
            publicacaoAluno.setIdPublicacao(cursor.getLong(cursor.getColumnIndex("IdPublicacao")));
            publicacoesAlunos.add(publicacaoAluno);
        }

        cursor.close();

        return publicacoesAlunos;
    }

    public void Delete(PublicacaoAluno publicacaoAluno) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {publicacaoAluno.getId().toString()};
        db.delete("PublicacaoAluno", "id = ?", params);
    }

    public void EditPublicacaoAluno(PublicacaoAluno publicacaoAluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetPublicacaoAlunoContentValues(publicacaoAluno);

        String[] params = {publicacaoAluno.getId().toString()};

        db.update("PublicacaoAluno", dados, "id = ?", params);
    }
}
