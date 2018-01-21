package com.app.mycontactlist.common.net;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.app.mycontactlist.common.utils.Constants;
import com.app.mycontactlist.common.utils.DialogUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class APIRequest<T> extends JsonRequest<T> implements Constants.HeaderKeys, Constants.AppConstants {

    private static final String TAG = "APIRequest";
    private final ObjectMapper mMapper = new ObjectMapper();
    private Class<T> mClazz;
    private Map<String, String> mHeaders;
    private Response.Listener<T> mListener;
    private Context mContext;


    private APIRequest(Builder builder) {
        super(builder.mMethod, builder.mUrl, builder.mRequestBody, builder.mListener, builder.mErrorListener);
        mClazz = builder.mClazz;
        mHeaders = builder.mHeaders;
        mListener = builder.mListener;
        mContext = builder.mContext;
        setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        mHeaders.put(X_CLIENT, PLATFORM);
        mHeaders.put(CONTENT_TYPE, CONTENT_FORMAT);
        mHeaders.put(CACHE_CONTROL, NO_CACHE);
        Log.i(TAG, "\nHEADER INFORMATION =>"+mHeaders);
        return mHeaders;
    }

    @Override
    public String getBodyContentType() {
        return CONTENT_FORMAT;
    }

    @Override
    protected void deliverResponse(T response) {

        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        if (error.networkResponse != null){
            DialogUtil.showDebugToast(mContext, error.networkResponse.statusCode + error.getMessage());
            Log.i(TAG, "\nDELIVERY VOLLEY RESPONSE ERROR ==>"+error.toString());
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.i(TAG, "\nJSON RESPONSE==> "+json+"\n");
            return Response.success(mMapper.readValue(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonMappingException e) {
            Log.i(TAG, "parseNetworkResponse: Exception==> "+e.getMessage());
            DialogUtil.showDebugToast(mContext, e.getMessage());
            return Response.error(new ParseError(e));
        } catch (UnsupportedEncodingException e) {
            DialogUtil.showDebugToast(mContext, e.getMessage());
            return Response.error(new ParseError(e));
        } catch (JsonParseException e) {
            DialogUtil.showDebugToast(mContext, e.getMessage());
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            DialogUtil.showDebugToast(mContext, e.getMessage());
            return Response.error(new ParseError(e));
        }
    }

    public static class Builder<T> {
        private final Class<T> mClazz;
        private Map<String, String> mHeaders = new HashMap<>();
        private final Response.Listener<T> mListener;
        private final Context mContext;
        private final String mUrl;
        private final Response.ErrorListener mErrorListener;
        private final int mMethod;
        private String mRequestBody;

        public Builder(Context ctx, int method, Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
            mContext = ctx;
            mMethod = method;
            mClazz = clazz;
            mUrl = url;
            mListener = listener;
            mErrorListener = errorListener;
        }
        public Builder setHeaders(Map<String, String> headers) {
            mHeaders = headers;
            return this;
        }
        public Builder setRequestBody(String requestBody) {
            mRequestBody = requestBody;
            return this;
        }
        public APIRequest<T> build() {
            return new APIRequest<>(this);
        }
    }

}
