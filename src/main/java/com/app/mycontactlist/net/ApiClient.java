package com.app.mycontactlist.net;
import java.io.IOException;
import java.util.List;
import com.app.mycontactlist.model.ContactListParent;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ApiClient
{
	private static Retrofit mRetrofit;
	static String BASE_URL = "http://jsonplaceholder.typicode.com/";
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private static Retrofit getRetrofitInstance()
	{
		if (null == mRetrofit)
		{
			OkHttpClient client = new OkHttpClient();
			client.interceptors().add(new ApiLogIntercepter());
			Retrofit.Builder builder = new Retrofit.Builder();
			builder.baseUrl(BASE_URL);
			builder.client(client);
			mRetrofit = builder.build();
		}
		return mRetrofit;
	}

	public static retrofit.Response<List<ContactListParent>> getContactList() throws IOException
	{
		return getRetrofitInstance().create(IApiClient.class).getContactList().execute();
	}
}
