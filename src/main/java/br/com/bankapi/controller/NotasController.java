package br.com.bankapi.controller;

import br.com.bankapi.br.com.bankapi.exception.EstoqueNotaException;
import br.com.bankapi.br.com.bankapi.service.impl.EstoqueService;
import br.com.bankapi.to.QuantidadeNotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotasController {

    @Autowired
    private EstoqueService estoqueService;

    private static final String URL_BASE = "bank/api/estoque";

    @GetMapping(URL_BASE)
    public ResponseEntity<List<QuantidadeNotaTO>> getNotas() {
        List<QuantidadeNotaTO> estoqueNotas = new ArrayList<>();
        try {
            estoqueNotas = estoqueService.obterEstoqueNotas();
        } catch (EstoqueNotaException e) {
            return new ResponseEntity<List<QuantidadeNotaTO>>(estoqueNotas, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<QuantidadeNotaTO>>(estoqueNotas, HttpStatus.OK);
    }

    @PostMapping(URL_BASE)
    public ResponseEntity<String> salvarNota(@RequestBody QuantidadeNotaTO quantidadeNotaTO) {
        try {
            estoqueService.salvarEstoqueNota(quantidadeNotaTO);
        } catch (EstoqueNotaException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Nota salva com sucesso", HttpStatus.CREATED);
    }
}
