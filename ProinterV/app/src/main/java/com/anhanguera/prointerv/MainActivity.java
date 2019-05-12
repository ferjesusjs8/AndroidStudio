package com.anhanguera.prointerv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.anhanguera.prointerv.dao.AlunoDAO;
import com.anhanguera.prointerv.modelo.Aluno;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAluno = findViewById(R.id.lista_alunos);

        Button novoAluno = findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNovoAluno = new Intent(MainActivity.this, Formulario.class);
                startActivity(intentNovoAluno);
            }
        });

        listaAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Aluno aluno = (Aluno) listaAluno.getItemAtPosition(posicao);
                Toast.makeText(MainActivity.this, "Aluno" + aluno.getNome() + "clicado!", Toast.LENGTH_SHORT).show();
                Intent editAluno = new Intent(MainActivity.this, Formulario.class);
                editAluno.putExtra("aluno", aluno);
                startActivity(editAluno);
            }
        });

        registerForContextMenu(listaAluno);
    }

    private void carregaLista() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.GetAlunos();
        alunoDAO.close();

        listaAluno = findViewById(R.id.lista_alunos);
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        listaAluno.setAdapter(adapter);
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
                Aluno aluno = (Aluno) listaAluno.getItemAtPosition((info.position));
                AlunoDAO dao = new AlunoDAO(MainActivity.this);
                dao.Delete(aluno);
                dao.close();
                    Toast.makeText(MainActivity.this, "Aluno" + aluno.getNome() + "deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    carregaLista();
                return false;
            }
        });
    }
}
