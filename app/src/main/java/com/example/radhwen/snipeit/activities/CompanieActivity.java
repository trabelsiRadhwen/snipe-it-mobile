package com.example.radhwen.snipeit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.adapters.CompanieAdapter;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.Companies;
import com.example.radhwen.snipeit.net.ApiConnect;
import com.example.radhwen.snipeit.services.CompanieServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanieActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "preferences";

    SharedPreferences preferences;

    private static final String TAG_NAME = CompanieActivity.class.getSimpleName();

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    private CompanieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companie);

        final ListView listCompany = (ListView) findViewById(R.id.companie_list);

        FloatingActionButton fabCompany = (FloatingActionButton) findViewById(R.id.fab_add_company);

        fabCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanieActivity.this, NewCompany.class);
                startActivity(intent);
            }
        });

        preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        CompanieServices services = ApiConnect.getClient().create(CompanieServices.class);

        Call<Companies> call = services.getCompanies(API_KEY);

        call.enqueue(new Callback<Companies>() {
            @Override
            public void onResponse(Call<Companies> call, Response<Companies> response) {

                final List<CompanieRows> list = response.body().getCompanieRows();

                listCompany.setAdapter(new CompanieAdapter(CompanieActivity.this, list));

                //final Intent intent = new Intent(getApplication(), ComapniesData.class);

                final SharedPreferences.Editor editor = preferences.edit();

                // pass company's data to be show in CompaniesData
                listCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String company = list.get(position).getName();

                        int assets = list.get(position).getAssetsCount();

                        int licences = list.get(position).getLicencesCount();

                        int accessories = list.get(position).getAccessoriesCount();

                        int idComp = list.get(position).getId();

                        //Integer assetsCount = list.get(position).getAssetsCount();

                        editor.putInt("id", idComp);

                        editor.putString("name", company);

                        editor.putInt("assets", assets);

                        editor.putInt("licences", licences);

                        editor.putInt("accessories", accessories);

                        editor.apply();

                        Intent intent = new Intent(CompanieActivity.this, ComapniesData.class);
                        startActivity(intent);


                        /*intent.putExtra("company", company);

                        intent.putExtra("id", idComp);
                        //intent.putExtra("assetsCount", assetsCount);

                        startActivity(intent);*/
                    }
                });

                for (int i=0; i < list.size(); i++) {
                    Log.d(TAG_NAME, "Companies Response: \n" +
                            "Id: " +list.get(i).getId() +"\n"
                            +"Company name: " +list.get(i).getName() +"\n"
                            +"--------------------------\n"
                    );
                }

                Log.d(TAG_NAME, "Companies List Size: " +list.size());
            }

            @Override
            public void onFailure(Call<Companies> call, Throwable t) {
                Toast.makeText(CompanieActivity.this, "Failed to connect !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}