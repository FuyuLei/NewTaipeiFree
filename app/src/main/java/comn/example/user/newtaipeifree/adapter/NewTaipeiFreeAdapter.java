package comn.example.user.newtaipeifree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comn.example.user.newtaipeifree.R;
import comn.example.user.newtaipeifree.model.Place;

public class NewTaipeiFreeAdapter extends BaseAdapter {
    private Context context;
    ArrayList<Place> list;

    public NewTaipeiFreeAdapter(Context context, ArrayList<Place> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder h;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            h = new ViewHolder(v);
            v.setTag(h);
        } else {
            h = (ViewHolder) v.getTag();
        }
        final Place item=(Place) getItem(position);
        h.name.setText(item.getSpot_name());
        h.address.setText(item.getAddress());
        return v;
    }

    private class ViewHolder {
        TextView name;
        TextView address;

        ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.tv_list_view_name);
            address = (TextView) v.findViewById(R.id.tv_list_view_address);
        }
    }
}
