package br.com.bankapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "ESTOQUE_NOTAS")
@Entity
public class EstoqueNota implements Serializable {

    @Column(name = "VALOR_NOTA")
    @Id
    private Short valorNota;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Short getValorNota() {
        return valorNota;
    }

    public void setValorNota(Short valorNota) {
        this.valorNota = valorNota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueNota that = (EstoqueNota) o;
        return Objects.equals(valorNota, that.valorNota);
    }

    @Override
    public int hashCode() {

        return Objects.hash(valorNota);
    }
}
