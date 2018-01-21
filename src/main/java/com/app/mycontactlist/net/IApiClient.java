package com.app.mycontactlist.net;


import java.util.List;

import com.app.mycontactlist.model.ContactListParent;

import retrofit.Call;
import retrofit.http.GET;

public interface IApiClient
{
	@GET("/user")
	public Call<List<ContactListParent>> getContactList();

}
