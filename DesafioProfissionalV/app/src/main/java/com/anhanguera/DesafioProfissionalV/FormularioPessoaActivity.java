package com.anhanguera.DesafioProfissionalV;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anhanguera.DesafioProfissionalV.DAO.PessoaDAO;
import com.anhanguera.DesafioProfissionalV.Helper.FormularioPessoaActivityHelper;
import com.anhanguera.DesafioProfissionalV.Model.Pessoa;

public class FormularioPessoaActivity extends AppCompatActivity{

    private FormularioPessoaActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);
        helper = new FormularioPessoaActivityHelper(this);

        Intent intent = getIntent();
        Pessoa pessoa = (Pessoa) intent.getSerializableExtra("pessoa");
        if (pessoa != null){
            helper.PreencherFormulario(pessoa);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario_pessoa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.formulario_pessoa_ok:
                Pessoa pessoa = helper.GetPessoa(FormularioPessoaActivity.this);

                PessoaDAO pessoaDAO = new PessoaDAO(this);

                if (pessoa.getId() != null){
                    pessoaDAO.EditPessoa(pessoa);
                    Toast.makeText(FormularioPessoaActivity.this, pessoa.getNome() + " editado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    pessoaDAO.InsertPessoa(pessoa);
                    Toast.makeText(FormularioPessoaActivity.this, pessoa.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }

                pessoaDAO.close();

                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}