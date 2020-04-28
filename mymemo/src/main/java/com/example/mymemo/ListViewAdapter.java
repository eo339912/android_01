package com.example.mymemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

//모델과  view를 매핑
public class ListViewAdapter extends BaseAdapter {

    //모델
    ArrayList<HashMap<String, String>> list;

    public void setList(ArrayList<HashMap<String, String>> list){
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.textViewTitle);
        //TextView content = (TextView) convertView.findViewById(R.id.textViewContent);
        TextView content = (TextView) convertView.findViewById(R.id.textViewContent);

        name.setText(list.get(pos).get("title"));
        content.setText(list.get(pos).get("time"));
        return convertView;
    }
}
