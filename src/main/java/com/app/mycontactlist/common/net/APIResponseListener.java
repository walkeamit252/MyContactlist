package com.app.mycontactlist.common.net;

public interface APIResponseListener<T> {
    void onSuccessResponse(T response);
    void onResponseError(String message);
}
