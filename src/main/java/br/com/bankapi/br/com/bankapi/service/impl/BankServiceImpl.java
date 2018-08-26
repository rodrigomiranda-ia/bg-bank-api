package br.com.bankapi.br.com.bankapi.service.impl;

import br.com.bankapi.br.com.bankapi.exception.BankException;
import br.com.bankapi.br.com.bankapi.exception.EstoqueNotaException;
import br.com.bankapi.to.QuantidadeNotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private EstoqueService estoqueService;

    @Override
    public List<QuantidadeNotaTO> sacar(String valor) throws BankException, EstoqueNotaException {
        List<QuantidadeNotaTO> resumoSaque = new ArrayList<>();
        int valorObtido = 0;
        int valorSaque = converterValorSaque(valor);
        if(valorSaque == 0) {
            throw new BankException("Valor do saque não atendeu aos requisitos");
        }

        List<QuantidadeNotaTO> notasDisponiveis = estoqueService.obterNotasDisponiveisParaSaque();

        int indexNotas = 0;

        while (valorObtido < valorSaque) {
            if(indexNotas == notasDisponiveis.size()) {
                break;
            }

            QuantidadeNotaTO notaTO = notasDisponiveis.get(indexNotas++);

            boolean notaImpar = notaTO.getValorNota() % 2 != 0;

            int qtdNotaUsada = 0;

            for (int i = 0; i < notaTO.getQuantidade(); i++) {
                valorObtido += notaTO.getValorNota();
                qtdNotaUsada++;

                if ((valorSaque - valorObtido > 0) &&
                        (valorSaque - valorObtido <= 10) && (valorSaque % 2 == 0 ) && notaImpar ) {
                    valorObtido -= notaTO.getValorNota();
                    qtdNotaUsada--;
                    break;
                }

                if ((valorSaque - valorObtido > 0) &&
                        (valorSaque - valorObtido <= 10) && (valorSaque % 2 != 0 ) && !notaImpar && notaTO.getValorNota() > 2 ) {
                    valorObtido -= notaTO.getValorNota();
                    qtdNotaUsada--;
                    break;
                }

                if ((valorSaque - valorObtido > 0) && (valorSaque - valorObtido <= 5) && notaTO.getValorNota() >= 5) {
                    valorObtido -= notaTO.getValorNota();
                    qtdNotaUsada--;
                    break;
                }

                if(valorObtido >= valorSaque) {
                    if (valorObtido > valorSaque) {
                        valorObtido -= notaTO.getValorNota();
                        qtdNotaUsada--;
                    }
                    break;
                }
            }

            if(qtdNotaUsada > 0) {
                resumoSaque.add(new QuantidadeNotaTO(notaTO.getValorNota(), qtdNotaUsada));
            }

        }

        if (valorObtido < valorSaque) {
            throw new BankException("Não foi possivel realizar o saque");
        }

        calcularUsoEstoqueNotas(notasDisponiveis, resumoSaque);

        return resumoSaque;
    }

    private void calcularUsoEstoqueNotas(List<QuantidadeNotaTO> notasDisponiveis, List<QuantidadeNotaTO> resumoSaque) throws EstoqueNotaException {
        for (QuantidadeNotaTO itemResumo : resumoSaque) {
            for (QuantidadeNotaTO itemDisponivel : notasDisponiveis) {
                if(itemResumo.getValorNota() == itemDisponivel.getValorNota()) {
                    int totalRestante = itemDisponivel.getQuantidade() - itemResumo.getQuantidade();
                    itemDisponivel.setQuantidade(totalRestante);
                    estoqueService.alterarEstoqueNota(itemDisponivel);
                }
            }
        }
    }

    private Integer converterValorSaque(String valor) {
        Integer valorSaque = 0;
        try {
            valorSaque = Integer.parseInt(valor);
        } catch (Exception e ) {
            return 0;
        }

        if(valorSaque == null || valorSaque <= 0 || valorSaque > 10000) {
            return 0;
        }

        return valorSaque;
    }
}
