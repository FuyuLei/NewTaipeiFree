package comn.example.user.newtaipeifree.retrofit;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public interface NewTaipeiFreeService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://data.ntpc.gov.tw/api/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    NewTaipeiFreeService service = NewTaipeiFreeService.retrofit.create(NewTaipeiFreeService.class);

    @GET("v1/rest/datastore/382000000A-000009-002")
    Call<String> getData();

}
