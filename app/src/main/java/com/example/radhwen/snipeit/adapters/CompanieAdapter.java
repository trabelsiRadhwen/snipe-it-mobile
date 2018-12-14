package com.example.radhwen.snipeit.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.CompanieRows;

import java.util.List;

public class CompanieAdapter extends ArrayAdapter<CompanieRows> {

    private Context context;

    private List<CompanieRows> companieRows;

    public CompanieAdapter(@NonNull Context context, @NonNull List<CompanieRows> objects) {
        super(context, R.layout.activity_companie, objects);
        this.context = context;
        this.companieRows = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.companie_item, parent, false);
        }

        CompanieRows rows = companieRows.get(position);

        TextView company = listItem.findViewById(R.id.company_name);

        company.setText(rows.getName());

        return listItem;
    }
}
