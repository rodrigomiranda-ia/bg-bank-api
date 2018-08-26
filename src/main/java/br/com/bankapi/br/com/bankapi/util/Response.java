package br.com.bankapi.br.com.bankapi.util;

public final class Response {

    private Object data;
    private Object errors;

    private Response() {}

    /**
     * @param data - objeto criado ou consutado retornado pelo WebService
     * @param errors - objeto com os erros do WebService
     * @return
     */
    public static Response montarResposta(Object data, Object errors) {
        Response r = new Response();
        if (data != null) {
            r.setData(data);
        }

        if (errors != null) {
            r.setErrors(errors);
        }
        return r;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
