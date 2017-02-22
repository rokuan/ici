package com.ideal.apps.ici.api.message;

public class AuthenticationMessage {
    protected String login;
    protected String password;

    public AuthenticationMessage(String l, String p){
        login = l;
        password = p;
    }
}
