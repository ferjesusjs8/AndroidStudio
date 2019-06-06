package com.anhanguera.prointerv.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.prointerv.MODEL.Publicacao;

import java.util.ArrayList;
import java.util.List;

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
        String sql = "DROP TABLE IF EXISTS Publicacao";
        db.execSQL(sql);
        onCreate(db);
    }

    public void Insert(Publicacao publicacao) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetPublicacaoContentValues(publicacao);

        db.insert("Publicacao", null, dados);
    }

    private ContentValues GetPublicacaoContentValues(Publicacao publicacao) {
        ContentValues dados = new ContentValues();
        dados.put("autor", publicacao.getAutor());
        dados.put("texto", publicacao.getTexto());
        dados.put("titulo", publicacao.getTitulo());
        return dados;
    }

    public List<Publicacao> GetPublicacoes() {
        String sql = "SELECT * FROM Publicacao";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Publicacao> publicacoes = new ArrayList<>();

        while(cursor.moveToNext()){
            Publicacao publicacao = new Publicacao();
            publicacao.setTexto(cursor.getString(cursor.getColumnIndex("texto")));
            publicacao.setAutor(cursor.getString(cursor.getColumnIndex("autor")));
            publicacao.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            publicacoes.add(publicacao);
        }

        cursor.close();

        return publicacoes;
    }

    public void Delete(Publicacao publicacao) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {publicacao.getId().toString()};
        db.delete("Publicacao", "id = ?", params);
    }

    public void EditPublicacao(Publicacao publicacao) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetPublicacaoContentValues(publicacao);

        String[] params = {publicacao.getId().toString()};

        db.update("Publicacao", dados, "id = ?", params);
    }
}
