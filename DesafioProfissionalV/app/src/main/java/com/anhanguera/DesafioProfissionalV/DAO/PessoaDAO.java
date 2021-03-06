package com.anhanguera.DesafioProfissionalV.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anhanguera.DesafioProfissionalV.Model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO extends SQLiteOpenHelper {
    private Context context;

    public PessoaDAO(Context context) {
        super(context, "Empresa", null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Pessoa (Id INTEGER PRIMARY KEY, Nome TEXT NOT NULL, CPF TEXT, TipoPessoa TEXT NOT NULL, Telefone TEXT, DataNascimento TEXT, CEP TEXT NOT NULL, Bairro TEXT, Cidade TEXT, Logradouro TEXT, Estado TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Pessoa";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public void InsertPessoa(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = GetPessoaContentValues(pessoa);

        db.insert("Pessoa", null, dados);
    }

    private ContentValues GetPessoaContentValues(Pessoa pessoa) {
        ContentValues dados = new ContentValues();
        dados.put("CPF", pessoa.getCPF());
        dados.put("dataNascimento", pessoa.getDataNascimento());
        dados.put("nome", pessoa.getNome());
        dados.put("telefone", pessoa.getTelefone());
        dados.put("tipoPessoa", pessoa.getTipoPessoa());
        dados.put("bairro", pessoa.getBairro());
        dados.put("CEP", pessoa.getCEP());
        dados.put("cidade", pessoa.getCidade());
        dados.put("estado", pessoa.getEstado());
        dados.put("logradouro", pessoa.getLogradouro());
        return dados;
    }

    public List<Pessoa> GetPessoas() {
        String sql = "SELECT * FROM Pessoa";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Pessoa> pessoas = new ArrayList<>();

        while (cursor.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setCPF(cursor.getString(cursor.getColumnIndex("CPF")));
            pessoa.setId(cursor.getLong(cursor.getColumnIndex("Id")));
            pessoa.setDataNascimento(cursor.getString(cursor.getColumnIndex("DataNascimento")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
            pessoa.setTelefone(cursor.getString(cursor.getColumnIndex("Telefone")));
            pessoa.setBairro(cursor.getString(cursor.getColumnIndex("Bairro")));
            pessoa.setCEP(cursor.getString(cursor.getColumnIndex("CEP")));
            pessoa.setCidade(cursor.getString(cursor.getColumnIndex("Cidade")));
            pessoa.setEstado(cursor.getString(cursor.getColumnIndex("Estado")));
            pessoa.setLogradouro(cursor.getString(cursor.getColumnIndex("Logradouro")));
            pessoa.setTipoPessoa(cursor.getString(cursor.getColumnIndex("TipoPessoa")));
            pessoas.add(pessoa);
        }

        cursor.close();

        return pessoas;
    }

    public void Delete(Pessoa pessoa) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[]{pessoa.getId().toString()};
        db.delete("Pessoa", "id = ?", params);
    }

    public void EditPessoa(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = GetPessoaContentValues(pessoa);

        String[] params = {pessoa.getId().toString()};

        db.update("Pessoa", dados, "id = ?", params);

    }

    public Pessoa getPessoaById(long idPessoa) {
        String sql = "SELECT * FROM Pessoa WHERE id = " + idPessoa;
        SQLiteDatabase db = getReadableDatabase();
        Pessoa pessoa = (Pessoa) db.rawQuery(sql, null);

        return pessoa;
    }
}