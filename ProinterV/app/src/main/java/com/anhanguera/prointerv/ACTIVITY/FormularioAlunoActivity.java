package com.anhanguera.prointerv.ACTIVITY;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anhanguera.prointerv.DAL.AlunoDAO;
import com.anhanguera.prointerv.HELPERS.FormularioAlunoActivityHelper;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    private FormularioAlunoActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        helper = new FormularioAlunoActivityHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null){
            helper.PreencherFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_aluno, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.GetAluno();

                AlunoDAO alunoDAO = new AlunoDAO(this);

                if (aluno.getId() != null){

                    alunoDAO.EditAluno(aluno);
                    Toast.makeText(FormularioAlunoActivity.this, "Aluno " + aluno.getNome() + " editado com sucesso!", Toast.LENGTH_SHORT).show();

                } else {

                    alunoDAO.Insert(aluno);
                    Toast.makeText(FormularioAlunoActivity.this, "Aluno " + aluno.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();

                }

                alunoDAO.close();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
