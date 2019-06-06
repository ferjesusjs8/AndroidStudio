package com.anhanguera.prointerv.ACTIVITY;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.anhanguera.prointerv.DAL.AlunoDAO;
import com.anhanguera.prointerv.DAL.GrupoDAO;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Grupo;
import com.anhanguera.prointerv.R;

import java.io.Serializable;
import java.util.List;

public class GrupoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listaGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);
        Toolbar toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);

        listaGrupo = findViewById(R.id.lista_grupo);

        Button novoAluno = findViewById(R.id.novo_grupo);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNovoGrupo = new Intent(GrupoActivity.this, FormularioGrupoActivity.class);
                startActivity(intentNovoGrupo);
            }
        });

        listaGrupo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Grupo grupo = (Grupo) listaGrupo.getItemAtPosition(posicao);
                Toast.makeText(GrupoActivity.this, "Grupo " + grupo.getNome() + "clicado!", Toast.LENGTH_SHORT).show();
                Intent editGrupo = new Intent(GrupoActivity.this, FormularioGrupoActivity.class);
                editGrupo.putExtra("grupo", (Serializable) grupo);
                startActivity(editGrupo);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        registerForContextMenu(listaGrupo);
    }

    private void carregaLista() {
        GrupoDAO grupoDAO = new GrupoDAO(this);
        List<Grupo> grupos = grupoDAO.GetGrupo();
        grupoDAO.close();

        listaGrupo = findViewById(R.id.lista_grupo);
        ArrayAdapter<Grupo> adapter = new ArrayAdapter<Grupo>(this, android.R.layout.simple_list_item_1, grupos);
        listaGrupo.setAdapter(adapter);
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
                Grupo grupo = (Grupo) listaGrupo.getItemAtPosition((info.position));
                GrupoDAO dao = new GrupoDAO(GrupoActivity.this);
                dao.Delete(grupo);
                dao.close();
                Toast.makeText(GrupoActivity.this, "Grupo " + grupo.getNome() + " deletado com sucesso!", Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_alunos) {
            Intent intentNovoAluno = new Intent(GrupoActivity.this, AlunoActivity.class);
            startActivity(intentNovoAluno);
        } else if (id == R.id.nav_grupos) {
            Intent grupos = new Intent(GrupoActivity.this, GrupoActivity.class);
            startActivity(grupos);
        } else if (id == R.id.nav_publicacoes) {
            Intent publicacoes = new Intent(GrupoActivity.this, PublicacaoActivity.class);
            startActivity(publicacoes);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(GrupoActivity.this, MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
