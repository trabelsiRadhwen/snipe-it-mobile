package com.example.radhwen.snipeit.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.Rows;
import com.squareup.picasso.Picasso;

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

        Rows rows = getItem(position);

        TextView name = (TextView) listView.findViewById(R.id.asset_name);
        name.setText(rows.getName());

        TextView tag = (TextView) listView.findViewById(R.id.asset_tag);
        tag.setText(rows.getTag());

        ImageView imageStatus = (ImageView) listView.findViewById(R.id.image_status);

        TextView status = (TextView) listView.findViewById(R.id.asset_status);
        status.setText(rows.getStatusLabel().getStatusMeta());

        if (status.getText().toString().equals("deployable")) {
            imageStatus.setImageResource(R.drawable.deployable2);
        }else if (status.getText().toString().equals("pending")){
            imageStatus.setImageResource(R.drawable.pending);
        }else if (status.getText().toString().equals("deployed")) {
            imageStatus.setImageResource(R.drawable.deployed);
        } else if (status.getText().toString().equals("archived")){
            imageStatus.setImageResource(R.drawable.archived);
        }

        ImageView image = (ImageView) listView.findViewById(R.id.asset_image);
        String imageVal = rows.getImage();
        Picasso.get().load(imageVal).into(image);
        Log.d("Asset Adapter", "ImageVal Response: " +imageVal);


        return listView;
    }
}
