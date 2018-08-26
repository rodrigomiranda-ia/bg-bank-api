package br.com.bankapi.to;

import java.io.Serializable;

public class QuantidadeNotaTO implements Serializable {

    private short valorNota;

    private int quantidade;

    public QuantidadeNotaTO() {

    }

    public QuantidadeNotaTO(short valorNota, int quantidade) {
        this.valorNota = valorNota;
        this.quantidade = quantidade;
    }

    public short getValorNota() {
        return valorNota;
    }

    public void setValorNota(short valorNota) {
        this.valorNota = valorNota;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
