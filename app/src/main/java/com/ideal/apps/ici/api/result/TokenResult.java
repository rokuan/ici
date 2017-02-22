package com.ideal.apps.ici.api.result;


public class TokenResult extends ApiResult<String> {
    protected String token;

    @Override
    public String getResult() {
        return token;
    }
}
