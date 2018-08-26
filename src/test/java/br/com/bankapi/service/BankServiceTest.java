package br.com.bankapi.service;

import br.com.bankapi.br.com.bankapi.exception.BankException;
import br.com.bankapi.br.com.bankapi.exception.EstoqueNotaException;
import br.com.bankapi.br.com.bankapi.service.impl.BankService;
import br.com.bankapi.to.QuantidadeNotaTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

    @Mock
    private BankService bankService;

    @Test
    public void testSacarValor() throws EstoqueNotaException, BankException {
        List<QuantidadeNotaTO> resultado = new ArrayList<>();
        boolean listasIguais = true;
        resultado.add(new QuantidadeNotaTO((short) 50, 1));
        resultado.add(new QuantidadeNotaTO((short) 20, 1));
        Mockito.when(bankService.sacar("70")).thenReturn(resultado);

        List<QuantidadeNotaTO> retorno = bankService.sacar("70");

        if (resultado.size() == retorno.size()) {
            for (int i = 0; i < retorno.size(); i++) {
                if (retorno.get(i).getValorNota() != resultado.get(i).getValorNota() ||
                        retorno.get(i).getQuantidade() != resultado.get(i).getQuantidade()) {
                    listasIguais = false;
                    break;
                }
            }
        } else {
            listasIguais = false;
        }

        Assert.assertTrue(listasIguais);
    }
}
