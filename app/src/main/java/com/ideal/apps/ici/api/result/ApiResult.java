package com.ideal.apps.ici.api.result;


public abstract class ApiResult<T> extends SimpleResult {
    public abstract T getResult();
}
