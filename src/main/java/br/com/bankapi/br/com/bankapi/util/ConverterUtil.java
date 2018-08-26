package br.com.bankapi.br.com.bankapi.util;

import br.com.bankapi.model.EstoqueNota;
import br.com.bankapi.to.QuantidadeNotaTO;

public final class ConverterUtil {

    public static EstoqueNota toEstoqueNota(QuantidadeNotaTO quantidadeNotaTO) {
        EstoqueNota estoqueNota = new EstoqueNota();
        estoqueNota.setValorNota(quantidadeNotaTO.getValorNota());
        estoqueNota.setQuantidade(quantidadeNotaTO.getQuantidade());

        return estoqueNota;
    }

    public static QuantidadeNotaTO toQuantidadeNotaTO(EstoqueNota estoqueNota) {
        QuantidadeNotaTO quantidadeNotaTO = new QuantidadeNotaTO();
        quantidadeNotaTO.setValorNota(estoqueNota.getValorNota());
        quantidadeNotaTO.setQuantidade(estoqueNota.getQuantidade());

        return quantidadeNotaTO;
    }
}
