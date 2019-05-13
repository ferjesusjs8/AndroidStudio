package com.anhanguera.DesafioProfissionalV;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.anhanguera.DesafioProfissionalV.DAO.PessoaDAO;
import com.anhanguera.DesafioProfissionalV.Model.Pessoa;

import java.util.List;

public class ListaPessoa extends AppCompatActivity {

    private ListView listaPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);

        listaPessoas = findViewById(R.id.lista_pessoas);

        listaPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Pessoa pessoa = (Pessoa) listaPessoas.getItemAtPosition(posicao);
                Toast.makeText(ListaPessoa.this, "Pessoa" + pessoa.getNome() + "clicado(a)!", Toast.LENGTH_SHORT).show();
                Intent editPessoa = new Intent(ListaPessoa.this, FormularioPessoaActivity.class);
                editPessoa.putExtra("pessoa", pessoa);
                startActivity(editPessoa);
            }
        });

        registerForContextMenu(listaPessoas);
    }

    private void carregaLista() {
        PessoaDAO pessoaDAO = new PessoaDAO(this);
        List<Pessoa> pessoas = pessoaDAO.GetPessoas();
        pessoaDAO.close();

        listaPessoas = findViewById(R.id.lista_pessoas);
        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
        listaPessoas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Pessoa pessoa = (Pessoa) listaPessoas.getItemAtPosition((info.position));
                PessoaDAO dao = new PessoaDAO(ListaPessoa.this);
                dao.Delete(pessoa);
                dao.close();
                Toast.makeText(ListaPessoa.this, pessoa.getNome() + "deletado(a) com sucesso!", Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });
    }
}
