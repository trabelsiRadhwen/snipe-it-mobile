package com.example.radhwen.snipeit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.radhwen.snipeit.activities.AssetsData;
import com.example.radhwen.snipeit.activities.CompanieActivity;
import com.example.radhwen.snipeit.activities.NewAsset;
import com.example.radhwen.snipeit.adapters.AssetAdapter;
import com.example.radhwen.snipeit.model.Asset;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "assetsPref";

    SharedPreferences preferences;

    private DrawerLayout drawer;

    private ActionBarDrawerToggle toggle;

    private static final String TAG_NAME = MainActivity.class.getSimpleName();

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    private AssetAdapter adapter;

    private FloatingActionButton fabAddAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddAsset = (FloatingActionButton) findViewById(R.id.fab_add_asset);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.Open, R.string.Close);

        toggle.setDrawerIndicatorEnabled(true);

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.companies_item) {
                    Intent companyIntent = new Intent(MainActivity.this, CompanieActivity.class);
                    startActivity(companyIntent);
                }

                return true;
            }
        });

        fabAddAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewAsset.class);
                startActivity(intent);
            }
        });

        final ListView listView = findViewById(R.id.assets_result);

        preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        AssetServices services = ApiConnect.getClient().create(AssetServices.class);

        Call<Asset> call = services.getAssets(API_KEY);

        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {

                final List<Rows> list = response.body().getRows();

                listView.setAdapter(new AssetAdapter(MainActivity.this, list));

                final SharedPreferences.Editor editor = preferences.edit();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int idAsset = list.get(position).getId();
                        String name = list.get(position).getName();
                        String tag = list.get(position).getTag();
                        ModelRows model = list.get(position).getModel();
                        CategoryRows category = list.get(position).getCategory();
                        StatusLabelRows status = list.get(position).getStatusLabel();
                        CompanieRows company = list.get(position).getCompany();

                        editor.putInt("id", idAsset);
                        editor.putString("name", name);
                        editor.putString("tag", tag);
                        editor.putString("model", String.valueOf(model));
                        editor.putString("category", String.valueOf(category));
                        editor.putString("status", String.valueOf(status));
                        editor.putString("company", String.valueOf(company));

                        editor.apply();

                        Intent intent = new Intent(MainActivity.this, AssetsData.class);
                        startActivity(intent);

                        /*Intent intent = new Intent(getApplication(), AssetsData.class);

                        intent.putExtra("name", name);
                        intent.putExtra("tag", tag);
                        intent.putExtra("model", model);
                        intent.putExtra("category", category);
                        intent.putExtra("status", status);
                        intent.putExtra("company", company);
                        startActivity(intent);*/
                    }
                });

                for (int i=0; i < list.size(); i++) {
                    Log.d(TAG_NAME, "OnResponse: \n" +
                            "Id: " +list.get(i).getId() +"\n"
                            +"Name: " +list.get(i).getName() +"\n"
                            +"Tag: " +list.get(i).getTag() +"\n"
                            +"Model Name: " +list.get(i).getModel() +"\n"
                            +"Category Name: " +list.get(i).getCategory()
                            +"--------------------------\n"
                    );
                }

                Log.d(TAG_NAME, "OnResponse: " +list.size());
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to connect !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
