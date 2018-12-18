package com.example.radhwen.snipeit.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.Rows;
import com.example.radhwen.snipeit.net.ApiConnect;
import com.example.radhwen.snipeit.services.AssetServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAsset extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    private static final String TAG_NAME = UpdateAsset.class.getSimpleName();

    private int id;

    private String name;

    private EditText assetName;

    private Button editBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_asset);

        assetName = findViewById(R.id.edit_asset_name);

        editBtn = findViewById(R.id.edit_asset_button);

        sharedPreferences = getSharedPreferences("assetsPref", MODE_PRIVATE);

        id = sharedPreferences.getInt("id", 0);

        name = sharedPreferences.getString("name", "");

        assetName.setText(name);

        Log.d(TAG_NAME, "Intent Response: \n "
                +"Id Response: " +id +"\n"
                +"Name Response: " +name
        );

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAsset(id, assetName.getText().toString());
            }
        });
    }


    private void updateAsset(int id, final String name) {
        AssetServices services = ApiConnect.getClient().create(AssetServices.class);

        Call<Rows> call = services.updateAsset(API_KEY, id, name);

        call.enqueue(new Callback<Rows>() {
            @Override
            public void onResponse(Call<Rows> call, Response<Rows> response) {
                Log.d(TAG_NAME, "Updated Successfully !");

                Toast.makeText(UpdateAsset.this, "Asset "+name +"updated successfully ! :)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Rows> call, Throwable t) {
                Log.e(TAG_NAME, "Failed to update ! :(");

                Toast.makeText(UpdateAsset.this, "Failed to connect!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
