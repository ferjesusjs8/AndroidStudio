package com.anhanguera.prointerv.ACTIVITY;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

import com.anhanguera.prointerv.DAL.AlunoDAO;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.R;

import java.util.List;

public class AlunoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ListView listaAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        listaAluno = findViewById(R.id.lista_alunos);

        Button novoAluno = findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNovoAluno = new Intent(AlunoActivity.this, FormularioAlunoActivity.class);
                startActivity(intentNovoAluno);
            }
        });

        listaAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Aluno aluno = (Aluno) listaAluno.getItemAtPosition(posicao);
                Toast.makeText(AlunoActivity.this, "Aluno" + aluno.getNome() + "clicado!", Toast.LENGTH_SHORT).show();
                Intent editAluno = new Intent(AlunoActivity.this, FormularioAlunoActivity.class);
                editAluno.putExtra("aluno", aluno);
                startActivity(editAluno);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                AlunoDAO dao = new AlunoDAO(AlunoActivity.this);
                dao.Delete(aluno);
                dao.close();
                    Toast.makeText(AlunoActivity.this, "Aluno" + aluno.getNome() + "deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    carregaLista();
                return false;
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_alunos) {
            Intent intentNovoAluno = new Intent(AlunoActivity.this, AlunoActivity.class);
            startActivity(intentNovoAluno);
        } else if (id == R.id.nav_grupos) {
            Intent grupos = new Intent(AlunoActivity.this, GruposActivity.class);
            startActivity(grupos);
        } else if (id == R.id.nav_publicacoes) {
            Intent publicacoes = new Intent(AlunoActivity.this, PublicacoesActivity.class);
            startActivity(publicacoes);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(AlunoActivity.this, MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
