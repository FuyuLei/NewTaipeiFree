package comn.example.user.newtaipeifree.adapter;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

//    public Filter getFilter() {
//        Filter filter = new Filter() {
//
//            @SuppressWarnings("unchecked")
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                list= (ArrayList<Place>) results.values;
//                notifyDataSetChanged();
//            }
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
//                ArrayList<String> FilteredList= new ArrayList<String>();
//                if (constraint == null || constraint.length() == 0) {
//                    // No filter implemented we return all the list
//                    results.values = list;
//                    results.count = list.size();
//                }
//                else {
//                    for (int i = 0; i < list.size(); i++) {
//                        String data = String.valueOf(list.get(i));
//                        if (data.toLowerCase().contains(constraint.toString()))  {
//                            FilteredList.add(data);
//                        }
//                    }
//                    results.values = FilteredList;
//                    results.count = FilteredList.size();
//                }
//                return results;
//            }
//        };
//        return filter;
//    }


}
