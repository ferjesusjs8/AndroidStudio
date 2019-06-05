package com.anhanguera.prointerv.ACTIVITY;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.anhanguera.prointerv.R;

public class PublicacoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacoes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_alunos) {
            Intent intentNovoAluno = new Intent(PublicacoesActivity.this, AlunoActivity.class);
            startActivity(intentNovoAluno);
        } else if (id == R.id.nav_grupos) {
            Intent grupos = new Intent(PublicacoesActivity.this, GruposActivity.class);
            startActivity(grupos);
        } else if (id == R.id.nav_publicacoes) {
            Intent publicacoes = new Intent(PublicacoesActivity.this, PublicacoesActivity.class);
            startActivity(publicacoes);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(PublicacoesActivity.this, MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
