package com.example.radhwen.snipeit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.Rows;

import java.util.List;

public class AssetAdapter extends ArrayAdapter<Rows> {

    private Context  context;

    private List<Rows> rowsList;

    public AssetAdapter(@NonNull Context context, @NonNull List<Rows> objects) {
        super(context, R.layout.activity_main, objects);
        this.context = context;
        this.rowsList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Rows rows = rowsList.get(position);

        TextView name = listView.findViewById(R.id.asset_name);
        TextView tag = listView.findViewById(R.id.asset_tag);
        //ImageView image = listView.findViewById(R.id.asset_image);

        name.setText(rows.getName());
        tag.setText(rows.getTag());
        //image.setImageResource(rows.getImage());

        return listView;
    }
}
