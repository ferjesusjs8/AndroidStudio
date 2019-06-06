package com.anhanguera.prointerv.ACTIVITY;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anhanguera.prointerv.DAL.AlunoDAO;
import com.anhanguera.prointerv.DAL.PublicacaoDAO;
import com.anhanguera.prointerv.HELPERS.FormularioAlunoActivityHelper;
import com.anhanguera.prointerv.HELPERS.FormularioGrupoActivityHelper;
import com.anhanguera.prointerv.HELPERS.FormularioPublicacaoActivityHelper;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Publicacao;
import com.anhanguera.prointerv.R;

public class FormularioPublicacaoActivity extends AppCompatActivity {

    private FormularioPublicacaoActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_publicacao);
        helper = new FormularioPublicacaoActivityHelper(this);

        Intent intent = getIntent();
        Publicacao publicacao = (Publicacao) intent.getSerializableExtra("publicacao");
        if (publicacao != null){
            helper.PreencherFormulario(publicacao);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_publicacao, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Publicacao publicacao = helper.GetPublicacao();

                PublicacaoDAO publicacaoDAO = new PublicacaoDAO(this);

                if (publicacao.getId() != null){

                    publicacaoDAO.EditPublicacao(publicacao);
                    Toast.makeText(FormularioPublicacaoActivity.this, "Publicacao " + publicacao.getTitulo() + " editado com sucesso!", Toast.LENGTH_SHORT).show();

                } else {

                    publicacaoDAO.Insert(publicacao);
                    Toast.makeText(FormularioPublicacaoActivity.this, "Publicacao " + publicacao.getTitulo() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();

                }

                publicacaoDAO.close();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
