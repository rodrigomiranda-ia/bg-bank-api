package br.com.bankapi.br.com.bankapi.exception;

public class BankException extends Exception {
    public BankException() {
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }
}
