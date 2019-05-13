package com.anhanguera.DesafioProfissionalV.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.DesafioProfissionalV.Model.Empresa;
import com.anhanguera.DesafioProfissionalV.Model.Endereco;
import com.anhanguera.DesafioProfissionalV.Model.TipoPessoa;

import java.util.ArrayList;
import java.util.List;

public class TipoPessoaDAO extends SQLiteOpenHelper {
    public TipoPessoaDAO(Context context) {
        super(context, "Empresa", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TipoPessoa (id INTEGER PRIMARY KEY, descricao TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TipoPessoa";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public Long InsertTipoPessoa(TipoPessoa tipoPessoa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetTipoPessoaContentValues(tipoPessoa);

        return db.insert("TipoPessoa", null, dados);
    }

    private ContentValues GetTipoPessoaContentValues(TipoPessoa tipoPessoa) {
        ContentValues dados = new ContentValues();
        dados.put("id", tipoPessoa.getId());
        dados.put("descricao", tipoPessoa.getDescricao());
        return dados;
    }

    public void Delete(TipoPessoa tipoPessoa) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[]{tipoPessoa.getId().toString()};
        db.delete("TipoPessoa", "id = ?", params);
    }

    public TipoPessoa GetTipoPessoaById(long idTipoPessoa) {
        String sql = "SELECT * FROM TipoPessoa WHERE id = " + idTipoPessoa;
        SQLiteDatabase db = getReadableDatabase();
        TipoPessoa tipoPessoa = (TipoPessoa) db.rawQuery(sql, null);

        return tipoPessoa;
    }

    public TipoPessoa GetTipoPessoa(String descricao) {
        String sql = "SELECT * FROM TipoPessoa WHERE descricao = " + descricao + "LIMIT 1 ";
        SQLiteDatabase db = getReadableDatabase();
        TipoPessoa tipoPessoa = (TipoPessoa) db.rawQuery(sql, null);

        return tipoPessoa;
    }
}
