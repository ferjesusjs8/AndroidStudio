package com.anhanguera.DesafioProfissionalV.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.anhanguera.DesafioProfissionalV.DAO.EnderecoDAO;
import com.anhanguera.DesafioProfissionalV.DAO.TipoPessoaDAO;
import com.anhanguera.DesafioProfissionalV.FormularioPessoaActivity;
import com.anhanguera.DesafioProfissionalV.Model.Endereco;
import com.anhanguera.DesafioProfissionalV.Model.Pessoa;
import com.anhanguera.DesafioProfissionalV.Model.TipoPessoa;
import com.anhanguera.DesafioProfissionalV.R;

import java.util.EmptyStackException;

public class FormularioPessoaActivityHelper {
        private Pessoa pessoa;
        private EditText descricaoView;
        private EditText nomePessoaView;
        private EditText CPFView;
        private EditText telefoneView;
        private EditText dataNascimentoView;
        private EditText CEPView;
        private EditText logradouroView;
        private EditText bairroView;
        private EditText cidadeView;
        private EditText estadoView;

        public FormularioPessoaActivityHelper(FormularioPessoaActivity activity){
            descricaoView = activity.findViewById(R.id.formulario_tipoPessoa);
            nomePessoaView = activity.findViewById(R.id.formulario_nome);
            CPFView = activity.findViewById(R.id.formulario_cpf);
            telefoneView = activity.findViewById(R.id.formulario_telefone);
            dataNascimentoView = activity.findViewById(R.id.formulario_dataNascimento);
            CEPView = activity.findViewById(R.id.formulario_cep);
            logradouroView = activity.findViewById(R.id.formulario_logradouro);
            bairroView = activity.findViewById(R.id.formulario_bairro);
            cidadeView = activity.findViewById(R.id.formulario_cidade);
            estadoView = activity.findViewById(R.id.formulario_estado);
        }

    public Pessoa GetPessoa(Context context) {
            pessoa = new Pessoa();

            pessoa.setNome(nomePessoaView.getText().toString());
            pessoa.setDataNascimento(dataNascimentoView.getText().toString());
            pessoa.setCPF(CPFView.getText().toString());
            pessoa.setTelefone(telefoneView.getText().toString());
            pessoa.setTipoPessoa(descricaoView.getText().toString());
            pessoa.setLogradouro(logradouroView.getText().toString());
            pessoa.setCidade(cidadeView.getText().toString());
            pessoa.setCEP(CEPView.getText().toString());
            pessoa.setEstado(estadoView.getText().toString());

            return pessoa;
    }

    public void PreencherFormulario(Pessoa pessoa) {
        bairroView.setText(pessoa.getBairro());
        cidadeView.setText(pessoa.getCidade());
        dataNascimentoView.setText(pessoa.getDataNascimento());
        descricaoView.setText(pessoa.getTipoPessoa());
        estadoView.setText(pessoa.getEstado());
        logradouroView.setText(pessoa.getLogradouro());
        nomePessoaView.setText(pessoa.getNome());
        telefoneView.setText(pessoa.getTelefone());
        CEPView.setText(pessoa.getCEP());
        CPFView.setText(pessoa.getCPF());
    }
}
