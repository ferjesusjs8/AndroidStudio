package com.anhanguera.prointerv;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.anhanguera.prointerv.modelo.Aluno;

public class FormularioActivityHelper {
    private RatingBar rating;
    private EditText site;
    private EditText telefone;
    private EditText endereco;
    private EditText nome;

    public FormularioActivityHelper(Formulario activity) {
        nome = activity.findViewById(R.id.formulario_nome);
        endereco = activity.findViewById(R.id.formulario_endereco);
        telefone = activity.findViewById(R.id.formulario_telefone);
        site = activity.findViewById(R.id.formulario_site);
        rating = activity.findViewById(R.id.formulario_ratingBar);
    }

    public Aluno GetAluno(){
        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(rating.getProgress()));
        return aluno;
    }


}
