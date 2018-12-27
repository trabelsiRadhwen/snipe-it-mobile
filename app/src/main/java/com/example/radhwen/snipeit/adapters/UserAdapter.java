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
import com.example.radhwen.snipeit.model.UserRows;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserRows> {

    private Context context;

    private List<UserRows> userRows;

    public UserAdapter(@NonNull Context context, @NonNull List<UserRows> objects) {
        super(context, R.layout.activity_users, objects);
        this.context = context;
        this.userRows = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.user_item, parent, false);
        }

        UserRows rows = userRows.get(position);

        TextView name = listItem.findViewById(R.id.user_name);

        TextView tag = listItem.findViewById(R.id.user_number);

        name.setText(rows.getName());

        tag.setText(rows.getEmployeeNumber());

        return listItem;
    }
}
