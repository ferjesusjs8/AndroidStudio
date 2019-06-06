package com.anhanguera.prointerv.HELPERS;

import android.widget.EditText;
import android.widget.RatingBar;

import com.anhanguera.prointerv.ACTIVITY.FormularioAlunoActivity;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.R;

public class FormularioAlunoActivityHelper {
    private RatingBar nota;
    private EditText email;
    private EditText curso;
    private EditText ra;
    private EditText nome;
    private Aluno aluno;

    public FormularioAlunoActivityHelper(FormularioAlunoActivity activity) {
        nome = activity.findViewById(R.id.aluno_nome);
        ra = activity.findViewById(R.id.aluno_ra);
        curso = activity.findViewById(R.id.aluno_curso);
        email = activity.findViewById(R.id.aluno_email);
        nota = activity.findViewById(R.id.aluno_nota);
        aluno = new Aluno();
    }

    public Aluno GetAluno(){
        aluno.setNome(nome.getText().toString());
        aluno.setRa(ra.getText().toString());
        aluno.setCurso(curso.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        return aluno;
    }

    public void PreencherFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        ra.setText(aluno.getRa());
        curso.setText(aluno.getCurso());
        email.setText(aluno.getEmail());
        nota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}