package com.anhanguera.prointerv.HELPERS;

import android.widget.EditText;
import android.widget.RatingBar;

import com.anhanguera.prointerv.ACTIVITY.FormularioAlunoActivity;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.R;

public class FormularioAlunoActivityHelper {
    private RatingBar rating;
    private EditText email;
    private EditText telefone;
    private EditText endereco;
    private EditText nome;
    private EditText posts;
    private Aluno aluno;

    public FormularioAlunoActivityHelper(FormularioAlunoActivity activity) {
        nome = activity.findViewById(R.id.aluno_nome);
        endereco = activity.findViewById(R.id.aluno_endereco);
        telefone = activity.findViewById(R.id.aluno_telefone);
        email = activity.findViewById(R.id.aluno_email);
        rating = activity.findViewById(R.id.aluno_ratingBar);
        aluno = new Aluno();
    }

    public Aluno GetAluno(){
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota(Double.valueOf(rating.getProgress()));
        return aluno;
    }

    public void PreencherFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        email.setText(aluno.getEmail());
        rating.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}
