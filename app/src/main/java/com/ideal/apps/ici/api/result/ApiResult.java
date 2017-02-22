package com.ideal.apps.ici.api.result;


public abstract class ApiResult<T> {
    private boolean success = false;
    private String message = "";

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public abstract T getResult();
}
