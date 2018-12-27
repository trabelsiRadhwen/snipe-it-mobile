package com.example.radhwen.snipeit.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.Companies;
import com.example.radhwen.snipeit.model.Model;
import com.example.radhwen.snipeit.model.ModelRows;
import com.example.radhwen.snipeit.model.Rows;
import com.example.radhwen.snipeit.model.StatusLabel;
import com.example.radhwen.snipeit.model.StatusLabelRows;
import com.example.radhwen.snipeit.net.ApiConnect;
import com.example.radhwen.snipeit.services.AssetServices;
import com.example.radhwen.snipeit.services.CompanieServices;
import com.example.radhwen.snipeit.services.ModelServices;
import com.example.radhwen.snipeit.services.StatusLabelServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAsset extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    private static final String TAG_NAME = UpdateAsset.class.getSimpleName();

    private int id;

    private String name;

    private ModelRows modelRows;
    private CompanieRows companieRows;
    private StatusLabelRows statusLabelRows;

    private EditText assetName;

    private Spinner companySpinner, modelSpinner, statusSpinner;

    private Button editBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_asset);

        assetName = findViewById(R.id.edit_asset_name);

        editBtn = findViewById(R.id.edit_asset_button);

        companySpinner = findViewById(R.id.asset_company_spinner_edit);
        modelSpinner = findViewById(R.id.asset_model_spinner_edit);
        statusSpinner = findViewById(R.id.asset_status_spinner_edit);

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

                name = assetName.getText().toString();
                modelRows = (ModelRows) modelSpinner.getSelectedItem();
                companieRows = (CompanieRows) companySpinner.getSelectedItem();
                statusLabelRows = (StatusLabelRows) statusSpinner.getSelectedItem();

                updateAsset(id, name, modelRows, companieRows, statusLabelRows);
            }
        });


        //Load data to spinners

        ModelServices services = ApiConnect.getClient().create(ModelServices.class);

        Call<Model> call = services.getModels(API_KEY);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {


                final List<ModelRows> models = response.body().getModelRows();

                final ArrayAdapter<ModelRows> modelAdapter = new ArrayAdapter<ModelRows>(UpdateAsset.this, android.R.layout.simple_list_item_1, models);

                modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                modelSpinner.setAdapter(modelAdapter);

                modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ModelRows modelRows = (ModelRows) parent.getSelectedItem();
                        displayModel(modelRows);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                for (int i = 0; i < models.size(); i++) {
                    Log.d(TAG_NAME, "Models Data Spinner Size: " + models.size());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e(TAG_NAME, "Failed to connect !");

                Toast.makeText(UpdateAsset.this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        });

        CompanieServices companieServices = ApiConnect.getClient().create(CompanieServices.class);

        Call<Companies> companiesCall = companieServices.getCompanies(API_KEY);

        companiesCall.enqueue(new Callback<Companies>() {
            @Override
            public void onResponse(Call<Companies> call, Response<Companies> response) {
                final List<CompanieRows> companieRows = response.body().getCompanieRows();

                final ArrayAdapter<CompanieRows> companiesAdapter = new ArrayAdapter<CompanieRows>(UpdateAsset.this, android.R.layout.simple_list_item_1, companieRows);
                companiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                companySpinner.setAdapter(companiesAdapter);

                companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CompanieRows companyRows = (CompanieRows) parent.getSelectedItem();
                        displayCompany(companyRows);

                        Log.d(TAG_NAME, "Company item position " + companyRows);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                for (int i = 0; i < companieRows.size(); i++) {
                    Log.d(TAG_NAME, "Companies Data Spinner Size: " + companieRows.size());
                }
            }

            @Override
            public void onFailure(Call<Companies> call, Throwable t) {
                Log.e(TAG_NAME, "Failed to connect !");

                Toast.makeText(UpdateAsset.this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        });

        StatusLabelServices labelServices = ApiConnect.getClient().create(StatusLabelServices.class);

        Call<StatusLabel> statusCall = labelServices.getStatus(API_KEY);

        statusCall.enqueue(new Callback<StatusLabel>() {
            @Override
            public void onResponse(Call<StatusLabel> call, Response<StatusLabel> response) {

                List<StatusLabelRows> statusRows = response.body().getStatusLabelRows();

                final ArrayAdapter<StatusLabelRows> statusAdapter = new ArrayAdapter<StatusLabelRows>(UpdateAsset.this, android.R.layout.simple_spinner_dropdown_item, statusRows);
                statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                statusSpinner.setAdapter(statusAdapter);

                statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        StatusLabelRows statusRows = (StatusLabelRows) parent.getSelectedItem();
                        displayStatus(statusRows);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                for (int i = 0; i < statusRows.size(); i++) {
                    Log.d(TAG_NAME, "Status Data Spinner Size: " + statusRows.size());
                }
            }

            @Override
            public void onFailure(Call<StatusLabel> call, Throwable t) {
                Log.e(TAG_NAME, "Failed to connect !");

                Toast.makeText(UpdateAsset.this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSelectedCompany(View view) {
        CompanieRows companyRows = (CompanieRows) companySpinner.getSelectedItem();
        displayCompany(companyRows);
    }

    private void displayCompany(CompanieRows companyRows) {
        String company = companyRows.getName();
    }

    private void getSelectedModel(View view) {
        ModelRows model = (ModelRows) modelSpinner.getSelectedItem();
        displayModel(model);
    }


    private void displayModel(ModelRows modelRows) {
        String model = modelRows.getName();
    }

    private void getSelectedStatus(View view) {
        StatusLabelRows statusLabelRows = (StatusLabelRows) statusSpinner.getSelectedItem();
        displayStatus(statusLabelRows);
    }

    private void displayStatus(StatusLabelRows statusLabel) {
        String status = statusLabel.getName();
    }

        //End load data to spinners


    private void updateAsset(int id, final String name, ModelRows modelRows, CompanieRows companieRows, StatusLabelRows statusLabelRows) {
        AssetServices services = ApiConnect.getClient().create(AssetServices.class);

        Call<Rows> call = services.updateAsset(API_KEY, id, name, modelRows.getId(), companieRows.getId(), statusLabelRows.getId());

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
