package com.anhanguera.prointerv.HELPERS;

import android.widget.EditText;
import android.widget.RatingBar;

import com.anhanguera.prointerv.ACTIVITY.FormularioAlunoActivity;
import com.anhanguera.prointerv.ACTIVITY.FormularioGrupoActivity;
import com.anhanguera.prointerv.MODEL.Aluno;
import com.anhanguera.prointerv.MODEL.Grupo;
import com.anhanguera.prointerv.R;

public class FormularioGrupoActivityHelper {
    private EditText nome;
    private EditText representante;
    private Grupo grupo;

    public FormularioGrupoActivityHelper(FormularioGrupoActivity activity) {
        nome = activity.findViewById(R.id.grupo_nome);
        representante = activity.findViewById(R.id.grupo_representante);
        grupo = new Grupo();
    }

    public Grupo GetGrupo(){
        grupo.setNome(nome.getText().toString());
        grupo.setRepresentante(representante.getText().toString());
        return grupo;
    }

    public void PreencherFormulario(Grupo grupo) {
        nome.setText(grupo.getNome());
        representante.setText(grupo.getRepresentante());
        this.grupo = grupo;
    }
}
