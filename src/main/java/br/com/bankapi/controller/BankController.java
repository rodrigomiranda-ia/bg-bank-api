package br.com.bankapi.controller;

import br.com.bankapi.br.com.bankapi.exception.BankException;
import br.com.bankapi.br.com.bankapi.exception.EstoqueNotaException;
import br.com.bankapi.br.com.bankapi.service.impl.BankService;
import br.com.bankapi.br.com.bankapi.service.impl.EstoqueService;
import br.com.bankapi.br.com.bankapi.util.Response;
import br.com.bankapi.to.QuantidadeNotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BankController {

    private static final String URL_BASE = "bank/api";

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private BankService bankService;


    @PostMapping(URL_BASE + "/saque")
    public ResponseEntity<Response> sacar(@RequestParam("valor") String valor) {
        List<QuantidadeNotaTO> resumoSaque = new ArrayList<>();

        try {
            resumoSaque = bankService.sacar(valor);
        } catch (BankException e) {
            return new ResponseEntity<Response>(Response.montarResposta(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (EstoqueNotaException e) {
            return new ResponseEntity<Response>(Response.montarResposta(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Response>(Response.montarResposta(resumoSaque, null), HttpStatus.OK);
    }
}
