package com.ideal.apps.ici.api;

import com.ideal.apps.ici.api.result.ApiResult;

public abstract class SimpleCallback<T> {
    public final void process(ApiResult<T> result) {
        if(result.isSuccess()){
            onSuccess(result.getResult());
        } else {
            onFailure(result.getMessage());
        }
    }

    public final void onFailure(Throwable t){
        onFailure(t.toString());
    }

    public abstract void onSuccess(T result);
    public abstract void onFailure(String error);
}
