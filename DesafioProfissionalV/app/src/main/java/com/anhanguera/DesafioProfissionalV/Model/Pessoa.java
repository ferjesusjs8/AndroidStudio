package com.anhanguera.DesafioProfissionalV.Model;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private Long id;
    private String nome;
    private String CPF;
    public Endereco Endereco;
    private String telefone;
    private String dataNascimento;
    public TipoPessoa TipoPessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Endereco getEnderecoPessoa() {
        return Endereco;
    }

    public void setEnderecoPessoa(Endereco enderecoPessoa) {
        this.Endereco = enderecoPessoa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoPessoa getTipoPessoa() {
        return TipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.TipoPessoa = tipoPessoa;
    }
}
