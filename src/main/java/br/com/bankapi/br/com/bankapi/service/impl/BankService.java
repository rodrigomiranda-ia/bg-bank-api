package br.com.bankapi.br.com.bankapi.service.impl;

import br.com.bankapi.br.com.bankapi.exception.BankException;
import br.com.bankapi.br.com.bankapi.exception.EstoqueNotaException;
import br.com.bankapi.to.QuantidadeNotaTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    public List<QuantidadeNotaTO> sacar(String valor) throws BankException, EstoqueNotaException;

}
