package com.anhanguera.prointerv.ACTIVITY;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anhanguera.prointerv.DAL.AlunoDAO;
import com.anhanguera.prointerv.DAL.GrupoDAO;
import com.anhanguera.prointerv.HELPERS.FormularioAlunoActivityHelper;
import com.anhanguera.prointerv.HELPERS.FormularioGrupoActivityHelper;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Grupo;
import com.anhanguera.prointerv.R;

public class FormularioGrupoActivity extends AppCompatActivity {

    private FormularioGrupoActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_grupo);

        helper = new FormularioGrupoActivityHelper(this);

        Intent intent = getIntent();
        Grupo grupo = (Grupo) intent.getSerializableExtra("grupo");
        if (grupo != null){
            helper.PreencherFormulario(grupo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_grupo, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Grupo grupo = helper.GetGrupo();

                GrupoDAO grupoDAO = new GrupoDAO(this);

                if (grupo.getId() != null){

                    grupoDAO.EditGrupo(grupo);
                    Toast.makeText(FormularioGrupoActivity.this, "Grupo " + grupo.getNome() + " editado com sucesso!", Toast.LENGTH_SHORT).show();

                } else {

                    grupoDAO.Insert(grupo);
                    Toast.makeText(FormularioGrupoActivity.this, "Grupo " + grupo.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();

                }

                grupoDAO.close();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
