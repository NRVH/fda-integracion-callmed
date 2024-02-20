package com.fahorro.integracion.exception;

public class CallmedException extends Exception
{
    private final int codeError;
    public CallmedException(String mensaje, int codeError, Throwable e)
    {
        super(mensaje, e);
        this.codeError = codeError;
    }

    public CallmedException(String mensaje, int codeError)
    {
        super(mensaje);
        this.codeError = codeError;
    }

    public int getCodeError() {
        return codeError;
    }

}
