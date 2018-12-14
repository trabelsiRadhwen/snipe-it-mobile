package com.example.radhwen.snipeit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.Category;
import com.example.radhwen.snipeit.model.CategoryRows;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.Model;
import com.example.radhwen.snipeit.model.ModelRows;
import com.example.radhwen.snipeit.model.StatusLabel;
import com.example.radhwen.snipeit.model.StatusLabelRows;

import java.io.Serializable;

public class AssetsData extends AppCompatActivity {

    private static final String TAG_NAME = AssetsData.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_data);

        TextView name = (TextView) findViewById(R.id.asset_name_data);

        TextView tag = (TextView) findViewById(R.id.asset_tag_data);

        TextView model = (TextView) findViewById(R.id.asset_model_data);

        TextView category = (TextView) findViewById(R.id.asset_category_data);

        TextView status = (TextView) findViewById(R.id.asset_status_data);

        TextView company = (TextView) findViewById(R.id.asset_company_data);

        Intent intent = getIntent();

        String asset = intent.getStringExtra("name");

        String tag_data = intent.getStringExtra("tag");

        ModelRows model_data = (ModelRows) intent.getSerializableExtra("model");

        CategoryRows category_data = (CategoryRows) intent.getSerializableExtra("category");

        StatusLabelRows status_data = (StatusLabelRows) intent.getSerializableExtra("status");

        CompanieRows company_data = (CompanieRows) intent.getSerializableExtra("company");

        name.setText(asset);

        tag.setText(tag_data);

        model.setText(model_data.getName());

        category.setText(category_data.getName());

        status.setText(status_data.getName());

        company.setText(company_data.getName());

        Log.d(TAG_NAME, "Intent Response: \n" +
                "Asset Name: " +asset +"\n"
                +"Model Name: " +model_data +"\n"
                +"Company Name: " +company_data
        );
    }
}
