package com.anhanguera.DesafioProfissionalV;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.desafiov_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.forumlario_Pessoa:
                Toast.makeText(this, "Adicionar Pessoa", Toast.LENGTH_SHORT).show();
                Intent intentFormularioPessoa = new Intent(MainActivity.this, FormularioPessoaActivity.class);
                startActivity(intentFormularioPessoa);
                return true;

            case R.id.forumlario_Obra:
                Toast.makeText(this, "Clicou no formulario de obras", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.forumlario_Servico:
                Toast.makeText(this, "Clicou no formulario de serviços", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.pessoa_search:
                Toast.makeText(this, "Visualizar Pessoa", Toast.LENGTH_SHORT).show();
                Intent intentEditViewPessoa = new Intent(MainActivity.this, ListaPessoa.class);
                startActivity(intentEditViewPessoa);

            default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
