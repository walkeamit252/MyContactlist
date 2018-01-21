package com.app.mycontactlist.common.utils;
import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyUtil {
    private static VolleyUtil sInstance;
    private RequestQueue mRequestQueue;

    private VolleyUtil(final Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleyUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyUtil(context);
        }
        return sInstance;
    }

    private VolleyUtil() {
    }

    public <T> void addToRequestQueue(Request<T> req) {
        mRequestQueue.add(req);
    }

    public void cancelRequest(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            mRequestQueue.cancelAll(tag);
        }

    }
}
