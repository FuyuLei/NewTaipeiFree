package comn.example.user.newtaipeifree.retrofit;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface NewTaipeiFreeService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://data.ntpc.gov.tw/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    NewTaipeiFreeService service = NewTaipeiFreeService.retrofit.create(NewTaipeiFreeService.class);

    @GET("v1/rest/datastore/382000000A-000009-002")
    Call<String> getElevations(@QueryMap Map<String, String> options);

}
