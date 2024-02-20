package com.fahorro.integracion.dto.response;

public class TokenResponse
{
    public TokenResponse(String token) {
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
