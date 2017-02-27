package com.ideal.apps.ici.api.result;

public class SimpleResult {
    private boolean success = false;
    private String message = "";

    public boolean isSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }
}
