package comn.example.user.newtaipeifree.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comn.example.user.newtaipeifree.R;
import comn.example.user.newtaipeifree.adapter.NewTaipeiFreeAdapter;
import comn.example.user.newtaipeifree.model.Place;
import comn.example.user.newtaipeifree.retrofit.NewTaipeiFreeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends CommonActivity implements TextWatcher, View.OnClickListener {

    private ListView lv;
    private ArrayList<Place> list_place;
    private ArrayList<Place> list_place_show;
    private ImageView search;
    private EditText word;
    private NewTaipeiFreeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_taipei_free);

        search = (ImageView) findViewById(R.id.iv_search);
        word = (EditText) findViewById(R.id.edt_word);
        lv = (ListView) findViewById(R.id.lv_new_taipei_free);
        list_place = new ArrayList<>();
        list_place_show = new ArrayList<>();

        adapter = new NewTaipeiFreeAdapter(this, list_place_show);
        lv.setAdapter(adapter);

        getNewTaipeiFree();

        lv.setTextFilterEnabled(true);
        search.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        MainActivity.this.adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void updateAdapter() {
        ((NewTaipeiFreeAdapter) lv.getAdapter()).notifyDataSetChanged();
    }

    private void getNewTaipeiFree() {
        NewTaipeiFreeService service = NewTaipeiFreeService.retrofit.create(NewTaipeiFreeService.class);
        Call<String> call = service.getData();
        showLoadingDialog();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dismissLoadingDialog();
                Log.d("test", response.body());
                try {
                    JSONObject j = new JSONObject(response.body());
                    JSONArray array = j.getJSONObject("result").getJSONArray("records");
                    list_place.clear();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        Place pla = new Place();

                        pla.setId(o.getString("id"));
                        pla.setSpot_name(o.getString("spot_name"));
                        pla.setType(o.getString("type"));
                        pla.setCompany(o.getString("company"));
                        pla.setDistrict(o.getString("district"));
                        pla.setAddress(o.getString("address"));
                        pla.setApparatus_name(o.getString("apparatus_name"));
                        pla.setLatitude(o.getString("latitude"));
                        pla.setLongitude(o.getString("longitude"));
                        pla.setTwd97X(o.getString("twd97X"));
                        pla.setTwd97Y(o.getString("twd97Y"));
                        pla.setWgs84aX(o.getString("wgs84aX"));
                        pla.setWgs84aY(o.getString("wgs84aY"));
                        list_place.add(pla);
                    }
                    list_place_show.clear();
                    list_place_show.addAll(list_place);
                    updateAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                try {
//                    JSONObject j = new JSONObject(response.body()).getJSONObject("retVal");
//                    Iterator<String> keys = j.keys();
//                    list_place.clear();
//                    while (keys.hasNext()) {
//                        String key = keys.next();
//                        if (j.get(key) instanceof JSONObject) {
//                            JSONObject j_sta = (JSONObject) j.get(key);
//                            Place pla = new Place();
//                            pla.setId(j_sta.getString("id"));
//                            pla.setSpot_name(j_sta.getString("spot_name"));
//                            pla.setType(j_sta.getString("type"));
//                            pla.setCompany(j_sta.getString("company"));
//                            pla.setDistrict(j_sta.getString("district"));
//                            pla.setAddress(j_sta.getString("address"));
//                            pla.setApparatus_name(j_sta.getString("apparatus_name"));
//                            pla.setLatitude(j_sta.getString("latitude"));
//                            pla.setLongitude(j_sta.getString("longitude"));
//                            pla.setTwd97X(j_sta.getString("twd97X"));
//                            pla.setTwd97Y(j_sta.getString("twd97Y"));
//                            pla.setWgs84aX(j_sta.getString("wgs84aX"));
//                            pla.setWgs84aY(j_sta.getString("wgs84aY"));
//                            list_place.add(pla);
//                        }
//                    }
//                    updateAdapter();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dismissLoadingDialog();
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("test", t.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        String keyword = word.getText().toString();
        list_place_show.clear();
        for (Place place:list_place){
            if(place.getAddress().contains(keyword)){
                list_place_show.add(place);
            }
        }
        updateAdapter();
    }
}
