package comn.example.user.newtaipeifree.retrofit;

import comn.example.user.newtaipeifree.model.Data;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public interface NewTaipeiFreeService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://data.ntpc.gov.tw/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    NewTaipeiFreeService service = NewTaipeiFreeService.retrofit.create(NewTaipeiFreeService.class);

    @GET("v1/rest/datastore/382000000A-000009-002")
    Call<Data> getData();

}
