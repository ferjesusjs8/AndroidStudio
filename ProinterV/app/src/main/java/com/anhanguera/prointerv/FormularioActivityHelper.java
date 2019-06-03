package com.anhanguera.prointerv;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.anhanguera.prointerv.modelo.Aluno;

public class FormularioActivityHelper {
    private RatingBar rating;
    private EditText email;
    private EditText telefone;
    private EditText endereco;
    private EditText nome;
    private EditText posts;
    private Aluno aluno;

    public FormularioActivityHelper(Formulario activity) {
        nome = activity.findViewById(R.id.formulario_nome);
        endereco = activity.findViewById(R.id.formulario_endereco);
        telefone = activity.findViewById(R.id.formulario_telefone);
        email = activity.findViewById(R.id.formulario_email);
        rating = activity.findViewById(R.id.formulario_ratingBar);
        posts = activity.findViewById(R.id.formulario_posts);
        aluno = new Aluno();
    }

    public Aluno GetAluno(){
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota(Double.valueOf(rating.getProgress()));
        aluno.setPosts(Long.parseLong(posts.getText().toString()));
        return aluno;
    }


    public void PreencherFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        email.setText(aluno.getEmail());
        rating.setProgress(aluno.getNota().intValue());
        posts.setText(aluno.getPosts().toString());
        this.aluno = aluno;
    }
}
