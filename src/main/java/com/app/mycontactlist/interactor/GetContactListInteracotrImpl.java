package com.app.mycontactlist.interactor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.mycontactlist.app.AppController;
import com.app.mycontactlist.common.net.APIResponseListener;
import com.app.mycontactlist.common.utils.DialogUtil;
import com.app.mycontactlist.model.ContactListParent;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GetContactListInteracotrImpl implements GetContactListInteractor{
    private static final String TAG = "UserPetDetailFragmentIn";
    Context mContext;
    public GetContactListInteracotrImpl(Context mContext){
        this.mContext=mContext;
    }

    @Override
    public void getContactList(final APIResponseListener<List<ContactListParent>> listener) {
        DialogUtil.showProgress(mContext,"Please Wait");
        String  tag_string_req = "string_req";
        String url = "http://jsonplaceholder.typicode.com/users";
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtil.cancelPrgoress();
                List<ContactListParent> parentList=new ArrayList<>();
                try {
                    JSONArray array = new JSONArray(response);
                    ContactListParent contactListParent;
                    ObjectMapper mMapper = new ObjectMapper();
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object = (JSONObject)array.get(i);
                        contactListParent=mMapper.readValue(object.toString(), ContactListParent.class);
                        parentList.add(contactListParent);
                    }
                }catch (Exception e)
                {
                 e.printStackTrace();
                }
                Collections.sort(parentList, new Comparator<ContactListParent>(){
                    public int compare(ContactListParent obj1, ContactListParent obj2) {
                        return obj1.getName().compareToIgnoreCase(obj2.getName()); // To compare string values
                    }
                });

                listener.onSuccessResponse(parentList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.cancelPrgoress();
                listener.onResponseError(error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}

