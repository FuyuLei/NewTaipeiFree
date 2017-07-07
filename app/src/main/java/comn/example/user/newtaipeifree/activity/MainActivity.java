package comn.example.user.newtaipeifree.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import java.util.ArrayList;

import comn.example.user.newtaipeifree.R;
import comn.example.user.newtaipeifree.model.Place;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<Place> list_place;
    private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_taipei_free);

        lv = (ListView) findViewById(R.id.lv_new_taipei_free);
        list_place = new ArrayList<>();

    }
}
