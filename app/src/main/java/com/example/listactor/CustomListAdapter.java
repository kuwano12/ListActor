package com.example.listactor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<Actor> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context context, List<Actor> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.photoView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.nameView = (TextView) convertView.findViewById(R.id.textViewName);
            holder.ageView = (TextView) convertView.findViewById(R.id.textViewAge);
            holder.countryView = (TextView) convertView.findViewById(R.id.textCountry);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Actor a = this.listData.get(position);
        holder.nameView.setText(a.getName());
        holder.ageView.setText(Integer.toString(a.getAge()));
        holder.countryView.setText(a.getCountry());
        int imageId = this.getMipmapResIdByName(a.getImageName());
        holder.photoView.setImageResource(imageId);
        return convertView;
    }

    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomList", "Res Name : " + resName + " ==> Res ID = " + resID);
        return resID;
    }

    static class ViewHolder {
        ImageView photoView;
        TextView nameView;
        TextView ageView;
        TextView countryView;
    }
}
