package com.example.radhwen.snipeit.adapters;

import android.content.Context;
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

public class UserAssignedAssetsAdapter extends ArrayAdapter<Rows> {

    private Context context;

    private List<Rows> rows;


    public UserAssignedAssetsAdapter(@NonNull Context context, @NonNull List<Rows> objects) {
        super(context, R.layout.activity_user_assigned_assets, objects);
        this.context = context;
        this.rows = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.user_assets_item, parent, false);
        }

        Rows rows = getItem(position);

        if (rows != null) {

            TextView name = (TextView) listView.findViewById(R.id.assigned_asset_name);
            name.setText(rows.getName());

            TextView tag = (TextView) listView.findViewById(R.id.assigned_asset_tag);
            tag.setText(rows.getTag());

            ImageView image = (ImageView) listView.findViewById(R.id.assigned_asset_image);

            String imageVal = rows.getImage();
            Log.d("Asset Adapter", "ImageVal Response: " + imageVal);
            Picasso.get().load(imageVal).into(image);
        }

        return listView;
    }
}
