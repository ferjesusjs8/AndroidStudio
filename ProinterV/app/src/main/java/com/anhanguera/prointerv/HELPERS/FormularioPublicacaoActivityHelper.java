package com.anhanguera.prointerv.HELPERS;

import android.widget.EditText;
import android.widget.RatingBar;

import com.anhanguera.prointerv.ACTIVITY.FormularioAlunoActivity;
import com.anhanguera.prointerv.ACTIVITY.FormularioPublicacaoActivity;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Publicacao;
import com.anhanguera.prointerv.R;

public class FormularioPublicacaoActivityHelper {
    private EditText titulo;
    private EditText autor;
    private EditText texto;
    private Publicacao publicacao;

    public FormularioPublicacaoActivityHelper(FormularioPublicacaoActivity activity) {
        titulo = activity.findViewById(R.id.publicacao_titulo);
        autor = activity.findViewById(R.id.publicacao_autor);
        texto = activity.findViewById(R.id.publicacao_texto);
        publicacao = new Publicacao();
    }

    public Publicacao GetPublicacao(){
        publicacao.setTitulo(titulo.getText().toString());
        publicacao.setAutor(autor.getText().toString());
        publicacao.setTexto(texto.getText().toString());
        return publicacao;
    }

    public void PreencherFormulario(Publicacao publicacao) {
        titulo.setText(publicacao.getTitulo());
        autor.setText(publicacao.getAutor());
        texto.setText(publicacao.getTexto());
        this.publicacao = publicacao;
    }
}
