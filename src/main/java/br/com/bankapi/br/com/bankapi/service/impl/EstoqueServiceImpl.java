package br.com.bankapi.br.com.bankapi.service.impl;

import br.com.bankapi.br.com.bankapi.exception.EstoqueNotaException;
import br.com.bankapi.br.com.bankapi.util.ConverterUtil;
import br.com.bankapi.model.EstoqueNota;
import br.com.bankapi.repository.EstoqueNotaRepository;
import br.com.bankapi.to.QuantidadeNotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    @Autowired
    private EstoqueNotaRepository estoqueNotaRepository;

    @Override
    public void salvarEstoqueNota(QuantidadeNotaTO estoqueNota) throws EstoqueNotaException {
        if (estoqueNota == null) {
            throw new EstoqueNotaException("Campo inválido");
        }

        if (estoqueNota.getQuantidade() < 0 || estoqueNota.getValorNota() < 0) {
            throw new EstoqueNotaException("Campos inválidos");
        }

        estoqueNotaRepository.save(ConverterUtil.toEstoqueNota(estoqueNota));
    }

    @Override
    public void alterarEstoqueNota(QuantidadeNotaTO estoqueNota) throws EstoqueNotaException {
        estoqueNotaRepository.save(ConverterUtil.toEstoqueNota(estoqueNota));
    }

    @Override
    public void excluirEstoqueNota(QuantidadeNotaTO estoqueNota) {
        estoqueNotaRepository.delete(ConverterUtil.toEstoqueNota(estoqueNota));
    }

    @Override
    public List<QuantidadeNotaTO> obterEstoqueNotas() throws EstoqueNotaException {
        List<QuantidadeNotaTO> retorno = new ArrayList<>();

        List<EstoqueNota> estoqueNotasBanco = estoqueNotaRepository.findAll();

        if (estoqueNotasBanco == null) {
            throw new EstoqueNotaException("Nenhuma nota encontrada na base");
        }

        for (EstoqueNota item: estoqueNotasBanco) {
            retorno.add(ConverterUtil.toQuantidadeNotaTO(item));
        }

        return retorno;
    }

    @Override
    public List<QuantidadeNotaTO> obterNotasDisponiveisParaSaque() throws EstoqueNotaException {
        List<QuantidadeNotaTO> retorno = new ArrayList<>();
        List<EstoqueNota> notasDisponiveisBanco = estoqueNotaRepository.obterNotasDisponiveisParaSaque();

        if (notasDisponiveisBanco == null) {
            throw new EstoqueNotaException("Nenhuma nota está disponivel para saque");
        }

        for (EstoqueNota item : notasDisponiveisBanco) {
            retorno.add(ConverterUtil.toQuantidadeNotaTO(item));
        }

        return retorno;
    }
}
