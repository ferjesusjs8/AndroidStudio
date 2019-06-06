package com.anhanguera.prointerv.ACTIVITY;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import com.anhanguera.prointerv.DAL.PublicacaoDAO;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Grupo;
import com.anhanguera.prointerv.MODEL.Publicacao;
import com.anhanguera.prointerv.R;

import java.io.Serializable;
import java.util.List;

public class PublicacaoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listaPublicacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacao);

        listaPublicacao = findViewById(R.id.lista_publicacao);

        Button novoPublicacao = findViewById(R.id.novo_publicacao);
        novoPublicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNovoPublicacao = new Intent(PublicacaoActivity.this, FormularioPublicacaoActivity.class);
                startActivity(intentNovoPublicacao);
            }
        });

        listaPublicacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Publicacao publicacao = (Publicacao) listaPublicacao.getItemAtPosition(posicao);
                Toast.makeText(PublicacaoActivity.this, "Publicacao " + publicacao.getTitulo() + " clicado!", Toast.LENGTH_SHORT).show();
                Intent editPublicacao = new Intent(PublicacaoActivity.this, FormularioPublicacaoActivity.class);
                editPublicacao.putExtra("publicacao", (Serializable) publicacao);
                startActivity(editPublicacao);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        registerForContextMenu(listaPublicacao);
    }

    private void carregaLista() {
        PublicacaoDAO publicacaoDAO = new PublicacaoDAO(this);
        List<Publicacao> publicacoes = publicacaoDAO.GetPublicacoes();
        publicacaoDAO.close();

        listaPublicacao = findViewById(R.id.lista_publicacao);
        ArrayAdapter<Publicacao> adapter = new ArrayAdapter<Publicacao>(this, android.R.layout.simple_list_item_1, publicacoes);
        listaPublicacao.setAdapter(adapter);
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
                Publicacao publicacao = (Publicacao) listaPublicacao.getItemAtPosition((info.position));
                PublicacaoDAO dao = new PublicacaoDAO(PublicacaoActivity.this);
                dao.Delete(publicacao);
                dao.close();
                Toast.makeText(PublicacaoActivity.this, "Publicacao " + publicacao.getTitulo() + " deletado com sucesso!", Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_alunos) {
            Intent intentNovoAluno = new Intent(PublicacaoActivity.this, AlunoActivity.class);
            startActivity(intentNovoAluno);
        } else if (id == R.id.nav_grupos) {
            Intent grupos = new Intent(PublicacaoActivity.this, GrupoActivity.class);
            startActivity(grupos);
        } else if (id == R.id.nav_publicacoes) {
            Intent publicacoes = new Intent(PublicacaoActivity.this, PublicacaoActivity.class);
            startActivity(publicacoes);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(PublicacaoActivity.this, MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
