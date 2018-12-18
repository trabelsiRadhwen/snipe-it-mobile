package com.example.radhwen.snipeit.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.Category;
import com.example.radhwen.snipeit.model.CategoryRows;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.Model;
import com.example.radhwen.snipeit.model.ModelRows;
import com.example.radhwen.snipeit.model.Rows;
import com.example.radhwen.snipeit.model.StatusLabel;
import com.example.radhwen.snipeit.model.StatusLabelRows;
import com.example.radhwen.snipeit.net.ApiConnect;
import com.example.radhwen.snipeit.services.AssetServices;

import java.io.Serializable;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetsData extends AppCompatActivity {

    private static final String TAG_NAME = AssetsData.class.getSimpleName();

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    SharedPreferences sharedPreferences;

    private int idAsset;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_data);

        sharedPreferences = getSharedPreferences("assetsPref", MODE_PRIVATE);

        TextView name = (TextView) findViewById(R.id.asset_name_data);

        TextView tag = (TextView) findViewById(R.id.asset_tag_data);

        TextView model = (TextView) findViewById(R.id.asset_model_data);

        TextView category = (TextView) findViewById(R.id.asset_category_data);

        TextView status = (TextView) findViewById(R.id.asset_status_data);

        TextView company = (TextView) findViewById(R.id.asset_company_data);

        idAsset = sharedPreferences.getInt("id", 0);

        String asset = sharedPreferences.getString("name", "");

        String tag_data = sharedPreferences.getString("tag", "");

        String model_data = sharedPreferences.getString("model","");

        String category_data = sharedPreferences.getString("category", "");

        String status_data = sharedPreferences.getString("status", "");

        String company_data = sharedPreferences.getString("company", "");

        name.setText(asset);

        tag.setText(tag_data);

        model.setText(model_data);

        category.setText(category_data);

        status.setText(status_data);

        company.setText(company_data);

        /*Intent intent = getIntent();

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

        company.setText(company_data.getName());*/

        Log.d(TAG_NAME, "Intent Response: \n" +
                "Asset Name: " +asset +"\n"
                +"Model Name: " +model_data +"\n"
                +"Category Name: " +category_data +"\n"
                +"Status: " +status_data +"\n"
                +"Company Name: " +company_data
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.assets_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.edit_asset:

                sharedPreferences = getSharedPreferences("assetsPref", MODE_PRIVATE);

                idAsset = sharedPreferences.getInt("id", 0);

                name = sharedPreferences.getString("name", "");

                Intent intent = new Intent(AssetsData.this, UpdateAsset.class);
                startActivity(intent);
                break;

                default:

                    confirmDeleteAsset();

                    Log.d(TAG_NAME, "Asset Info delete: \n"
                            +"Id: " +id +"\n"
                            +"Name:" +name
                    );

                    break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void deleteAsset(final int id) {

        AssetServices services = ApiConnect.getClient().create(AssetServices.class);

        Call<Rows> call = services.deleteAsset(API_KEY, idAsset);

        call.enqueue(new Callback<Rows>() {
            @Override
            public void onResponse(Call<Rows> call, Response<Rows> response) {
                Log.d(TAG_NAME, "Asset's id: " +id + "\n"
                        +"Asset's Name:" +name
                );
            }

            @Override
            public void onFailure(Call<Rows> call, Throwable t) {
                Log.d(TAG_NAME, "Failed to delete / to connect ! ");

                Toast.makeText(AssetsData.this, "Failed to delete !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void confirmDeleteAsset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.delete_asset_dialog_message)
                .setTitle(R.string.delete_asset_dialog_title)
                .setCancelable(true);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAsset(idAsset);

                Toast.makeText(AssetsData.this, "Asset deleted successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
