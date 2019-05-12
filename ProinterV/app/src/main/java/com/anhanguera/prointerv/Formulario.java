package com.anhanguera.prointerv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anhanguera.prointerv.dao.AlunoDAO;
import com.anhanguera.prointerv.modelo.Aluno;

public class Formulario extends AppCompatActivity {

    private FormularioActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioActivityHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null){
            helper.PreencherFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

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
                    Toast.makeText(Formulario.this, "Aluno " + aluno.getNome() + " editado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    alunoDAO.Insere(aluno);
                    Toast.makeText(Formulario.this, "Aluno " + aluno.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }

                alunoDAO.close();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
