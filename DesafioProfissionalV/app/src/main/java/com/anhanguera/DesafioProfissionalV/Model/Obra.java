package com.anhanguera.DesafioProfissionalV.Model;

import java.io.Serializable;

public class Obra implements Serializable {
    private Long id;
    private String descricao;
    public Endereco enderecoObra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Endereco getEnderecoObra() {
        return enderecoObra;
    }

    public void setEnderecoObra(Endereco enderecoObra) {
        this.enderecoObra = enderecoObra;
    }
}
