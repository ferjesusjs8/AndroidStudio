package com.anhanguera.DesafioProfissionalV.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.DesafioProfissionalV.Model.Endereco;

public class EnderecoDAO extends SQLiteOpenHelper {
    public EnderecoDAO(Context context) {
        super(context, "Endereco", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Endereco (id INTEGER PRIMARY KEY, CEP TEXT, Logradouro TEXT, Bairro TEXT, Cidade TEXT, Estado TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Endereco";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public Endereco getEnderecoById(Long idEndereco) {
        String sql = "SELECT * FROM Endereco WHERE id = " + idEndereco;
        SQLiteDatabase db = getReadableDatabase();
        Endereco endereco = (Endereco) db.rawQuery(sql, null);

        return endereco;
    }
}
