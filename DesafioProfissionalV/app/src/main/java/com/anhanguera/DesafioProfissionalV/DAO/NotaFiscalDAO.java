package com.anhanguera.DesafioProfissionalV.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.DesafioProfissionalV.Model.Empresa;
import com.anhanguera.DesafioProfissionalV.Model.Endereco;
import com.anhanguera.DesafioProfissionalV.Model.NotaFiscal;

import java.util.ArrayList;
import java.util.List;

public class NotaFiscalDAO extends SQLiteOpenHelper {
    private Context context;

    public NotaFiscalDAO(Context context) {
        super(context, "Empresa", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE NotaFiscal (id INTEGER PRIMARY KEY, serie TEXT NOT NULL, numero LONG NOT NULL, comprador TEXT NOT NULL, dataEmissao TEXT NOT NULL, idEmpresa LONG NOT NULL, idPesoa LONG NOT NULL, totalImpostos LONG NOT NULL, valorTotal INTEGER NOT NULL, idEndereco LONG NOT NULL)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS NotaFiscal";
        db.execSQL(sql);
    }

    public void InsertNotafiscal(NotaFiscal notaFiscal) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetNotaFiscalContentValues(notaFiscal);

        db.insert("NotaFiscal", null, dados);
    }

    private ContentValues GetNotaFiscalContentValues(NotaFiscal notaFiscal) {
        ContentValues dados = new ContentValues();
        dados.put("comprador", notaFiscal.getComprador());
        dados.put("id", notaFiscal.getId());
        dados.put("numero", notaFiscal.getNumero());
        dados.put("serie", notaFiscal.getSerie());
        dados.put("totalImpostos", notaFiscal.getTotalImpostos());
        dados.put("valorTotal", notaFiscal.getValorTotal());
        dados.put("idEmpresa", notaFiscal.empresa.getId());
        dados.put("idEndereco", notaFiscal.endereco.getId());
        dados.put("idPessoa", notaFiscal.pessoa.getId());
        return dados;
    }

    public List<NotaFiscal> GetNotas() {
        String sql = "SELECT * FROM NotaFiscal";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        EmpresaDAO empresaDAO = new EmpresaDAO(context);
        EnderecoDAO enderecoDAO = new EnderecoDAO(context);
        PessoaDAO pessoaDAO = new PessoaDAO(context);

        List<NotaFiscal> notas = new ArrayList<NotaFiscal>();

        while (cursor.moveToNext()) {
            NotaFiscal notaFiscal = new NotaFiscal();
            notaFiscal.setComprador(cursor.getString(cursor.getColumnIndex("comprador")));
            notaFiscal.setDataEmissao(cursor.getString(cursor.getColumnIndex("dataEmissao")));
            notaFiscal.setEmpresa(empresaDAO.GetEmpresaById(cursor.getLong(cursor.getColumnIndex("idEmpresa"))));
            notaFiscal.setId(cursor.getLong(cursor.getColumnIndex("id")));
            notaFiscal.setEndereco(enderecoDAO.getEnderecoById(cursor.getLong(cursor.getColumnIndex("idEndereco"))));
            notaFiscal.setNumero(cursor.getLong(cursor.getColumnIndex("Numero")));
            notaFiscal.setSerie(cursor.getLong(cursor.getColumnIndex("Serie")));
            notaFiscal.setTotalImpostos(cursor.getDouble(cursor.getColumnIndex("TotalImpostos")));
            notaFiscal.setValorTotal(cursor.getDouble(cursor.getColumnIndex("ValorTotal")));
            notaFiscal.setPessoa(pessoaDAO.getPessoaById(cursor.getLong(cursor.getColumnIndex("idPessoa"))));
            notas.add(notaFiscal);
        }

        cursor.close();

        return notas;
    }

    public void Delete(NotaFiscal notaFiscal) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[]{notaFiscal.getId().toString()};
        db.delete("NotaFiscal", "id = ?", params);
    }

    public void EditNotaFiscal(NotaFiscal notaFiscal) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetNotaFiscalContentValues(notaFiscal);

        String[] params = {notaFiscal.getId().toString()};

        db.update("NotaFiscal", dados, "id = ?", params);
    }
}