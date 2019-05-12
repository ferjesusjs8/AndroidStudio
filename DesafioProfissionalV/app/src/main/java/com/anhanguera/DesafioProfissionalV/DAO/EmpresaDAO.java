package com.anhanguera.DesafioProfissionalV.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.DesafioProfissionalV.Model.Empresa;
import com.anhanguera.DesafioProfissionalV.Model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO extends SQLiteOpenHelper {
    private Context context;

    public EmpresaDAO(Context context) {
        super(context, "Empresa", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Empresa (id INTEGER PRIMARY KEY, RazaoSocial TEXT NOT NULL, CNPJ TEXT NOT NULL, Telefone TEXT, IdEndereco INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Empresa";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public void InsertEmpresa(Empresa empresa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetEmpresaContentValues(empresa);

        db.insert("Empresa", null, dados);
    }

    private ContentValues GetEmpresaContentValues(Empresa empresa) {
        ContentValues dados = new ContentValues();
        dados.put("razaoSocial", empresa.getRazaoSocial());
        dados.put("CNPJ", empresa.getCNPJ());
        dados.put("telefone", empresa.getTelefone());
        dados.put("idEndereco", empresa.endereco.getId());
        return dados;
    }

    public List<Empresa> GetEmpresas() {
        String sql = "SELECT * FROM Empresa";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        EnderecoDAO enderecoDAO = new EnderecoDAO(context);
        List<Empresa> empresas = new ArrayList<Empresa>();

        while(cursor.moveToNext()){
            Empresa empresa = new Empresa();
            Endereco endereco = enderecoDAO.getEnderecoById(cursor.getLong(cursor.getColumnIndex("idEndereco")));
            empresa.setCNPJ(cursor.getString(cursor.getColumnIndex("CNPJ")));
            empresa.setId(cursor.getLong(cursor.getColumnIndex("id")));
            empresa.setRazaoSocial(cursor.getString(cursor.getColumnIndex("razaoSocial")));
            empresa.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            empresa.setEndereco(endereco);
            empresas.add(empresa);
        }

        cursor.close();

        return empresas;
    }

    public void Delete(Empresa empresa) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[]{empresa.getId().toString()};
        db.delete("Empresa", "id = ?", params);
    }

    public void EditEmpresa(Empresa empresa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetEmpresaContentValues(empresa);

        String[] params = {empresa.getId().toString()};

        db.update("Empresa", dados, "id = ?", params);
    }

    public Empresa GetEmpresaById(long idEmpresa) {
        String sql = "SELECT * FROM Empresa WHERE id = " + idEmpresa;
        SQLiteDatabase db = getReadableDatabase();
        Empresa empresa = (Empresa) db.rawQuery(sql, null);

        return empresa;
    }
}
