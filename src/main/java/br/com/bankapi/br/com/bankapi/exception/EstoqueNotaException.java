package br.com.bankapi.br.com.bankapi.exception;

public class EstoqueNotaException extends Exception {

    public EstoqueNotaException() {
    }

    public EstoqueNotaException(String message) {
        super(message);
    }

    public EstoqueNotaException(String message, Throwable cause) {
        super(message, cause);
    }
}
