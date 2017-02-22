package com.ideal.apps.ici.api.message;


public class SignUpMessage {
    protected String login;
    protected String email;
    protected String password;
    protected String confirmPassword;

    public SignUpMessage(String l, String e, String p, String c){
        login = l;
        email = e;
        password = p;
        confirmPassword = c;
    }
}
