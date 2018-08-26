package br.com.bankapi.br.com.bankapi.service.impl;

import br.com.bankapi.br.com.bankapi.exception.EstoqueNotaException;
import br.com.bankapi.model.EstoqueNota;
import br.com.bankapi.to.QuantidadeNotaTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface EstoqueService {

    public void salvarEstoqueNota(QuantidadeNotaTO estoqueNota) throws EstoqueNotaException;

    public void alterarEstoqueNota(QuantidadeNotaTO estoqueNota) throws EstoqueNotaException;

    public void excluirEstoqueNota(QuantidadeNotaTO estoqueNota);

    public QuantidadeNotaTO obterEstoqueNota(String valor) throws EstoqueNotaException;

    public List<QuantidadeNotaTO> obterEstoqueNotas() throws EstoqueNotaException;

    public List<QuantidadeNotaTO> obterNotasDisponiveisParaSaque() throws EstoqueNotaException;
}
