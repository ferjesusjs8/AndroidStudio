package com.anhanguera.DesafioProfissionalV.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.DesafioProfissionalV.Model.Empresa;
import com.anhanguera.DesafioProfissionalV.Model.Endereco;
import com.anhanguera.DesafioProfissionalV.Model.Obra;

import java.util.ArrayList;
import java.util.List;

public class ObraDAO extends SQLiteOpenHelper {
    private Context context;

    public ObraDAO(Context context) {
        super(context, "Empresa", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Obra (id INTEGER PRIMARY KEY, Descricao TEXT NOT NULL, IdEndereco LONG NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Obra";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public void InsertObra(Obra obra){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetObraContentValues(obra);

        db.insert("Obra", null, dados);
    }

    private ContentValues GetObraContentValues(Obra obra) {
        ContentValues dados = new ContentValues();
        dados.put("Id", obra.getId());
        dados.put("Descricao", obra.getDescricao());
        dados.put("IdEndereco", obra.enderecoObra.getId());
        return dados;
    }

    public List<Obra> GetObras() {
        String sql = "SELECT * FROM Obra";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        EnderecoDAO enderecoDAO = new EnderecoDAO(context);
        List<Obra> obras = new ArrayList<Obra>();

        while(cursor.moveToNext()){
            Obra obra = new Obra();
            obra.setId(cursor.getLong(cursor.getColumnIndex("id")));
            obra.setDescricao(cursor.getString(cursor.getColumnIndex("Descricao")));
            obra.setEnderecoObra(enderecoDAO.getEnderecoById(cursor.getLong(cursor.getColumnIndex("idEndereco"))));
            obras.add(obra);
        }

        cursor.close();

        return obras;
    }

    public void Delete(Obra obra) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[]{obra.getId().toString()};
        db.delete("Obra", "id = ?", params);
    }

    public void EditObra(Obra obra) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetObraContentValues(obra);

        String[] params = {obra.getId().toString()};

        db.update("Obras", dados, "id = ?", params);

    }
}