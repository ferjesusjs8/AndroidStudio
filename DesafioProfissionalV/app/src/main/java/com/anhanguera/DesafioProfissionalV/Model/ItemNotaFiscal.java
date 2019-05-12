package com.anhanguera.DesafioProfissionalV.Model;

import java.io.Serializable;

public class ItemNotaFiscal implements Serializable {
    private Long id;
    private Long quantidade;
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
