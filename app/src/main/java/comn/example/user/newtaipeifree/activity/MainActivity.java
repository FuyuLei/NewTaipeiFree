package comn.example.user.newtaipeifree.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import comn.example.user.newtaipeifree.R;
import comn.example.user.newtaipeifree.adapter.NewTaipeiFreeAdapter;
import comn.example.user.newtaipeifree.model.Place;
import comn.example.user.newtaipeifree.retrofit.NewTaipeiFreeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends CommonActivity {

    private ListView lv;
    private ArrayList<Place> list_place;
    private ImageView search;
    private EditText word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_taipei_free);

        search= (ImageView) findViewById(R.id.iv_search);
        word= (EditText) findViewById(R.id.edt_word);
        lv = (ListView) findViewById(R.id.lv_new_taipei_free);
        list_place = new ArrayList<>();

        lv.setAdapter(new NewTaipeiFreeAdapter(this, list_place));
        getNewTaipeiFree();
    }

    private void updateAdapter() {
        ((NewTaipeiFreeAdapter) lv.getAdapter()).notifyDataSetChanged();
    }

    private void getNewTaipeiFree() {
        NewTaipeiFreeService service = NewTaipeiFreeService.retrofit.create(NewTaipeiFreeService.class);
        Call<String> call = service.getData();
//        showLoadingDialog();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dismissLoadingDialog();
                try {
                    JSONObject j = new JSONObject(response.body()).getJSONObject("retVal");
                    Iterator<String> keys = j.keys();
                    list_place.clear();
                    while (keys.hasNext()){
                        String key = keys.next();
                        if(j.get(key) instanceof JSONObject){
                            JSONObject j_pla = (JSONObject)j.get(key);
                            Place pla = new Place();
                            pla.setId(j_pla.getString("id"));
                            pla.setSpot_name(j_pla.getString("spot_name"));
                            pla.setType(j_pla.getString("type"));
                            pla.setCompany(j_pla.getString("company"));
                            pla.setDistrict(j_pla.getString("district"));
                            pla.setAddress(j_pla.getString("address"));
                            pla.setApparatus_name(j_pla.getString("apparatus_name"));
                            pla.setLatitude(j_pla.getString("latitude"));
                            pla.setLongitude(j_pla.getString("longitude"));
                            pla.setTwd97X(j_pla.getString("twd97X"));
                            pla.setTwd97Y(j_pla.getString("twd97Y"));
                            pla.setWgs84aX(j_pla.getString("wgs84aX"));
                            pla.setWgs84aY(j_pla.getString("wgs84aY"));
                            list_place.add(pla);
                        }
                    }
                    updateAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dismissLoadingDialog();
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
