package com.example.radhwen.snipeit.activities;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.net.ApiConnect;
import com.example.radhwen.snipeit.services.CompanieServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComapniesData extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private static final String TAG_NAME = ComapniesData.class.getSimpleName();

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    private String company_data;

    private int idComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comapnies_data);

        TextView company = (TextView) findViewById(R.id.company_name_data);
        //TextView assetsCount = (TextView) findViewById(R.id.company_assets_count_data);

        sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);

        idComp = sharedPreferences.getInt("id", 0);

        company_data = sharedPreferences.getString("name", "");

        company.setText(company_data);

        /*Intent intent = getIntent();

        company_data = intent.getStringExtra("company");

        id = intent.getExtras().getInt("id");

        //Integer assets_count = intent.getExtras().getInt("assetsCount");

        company.setText(company_data);*/

        //assetsCount.setText(assets_count);

        Log.d(TAG_NAME, "Intent Response: \n"
                +"Id: " +idComp +"\n"
                +"Company Name: " +company_data +"\n"
                //+"Assets Count: " +assets_count +"\n"
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.company_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.edit_company:

                sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);

                idComp = sharedPreferences.getInt("id", 0);

                company_data = sharedPreferences.getString("name", "");

                Intent intent = new Intent(ComapniesData.this, UpdateCompany.class);
                startActivity(intent);
                break;

                default:
                    confirmDelete();
                    break;

        }

        return super.onOptionsItemSelected(item);
    }


    private void confirmDelete() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.delete_company_dialog_message)
                .setTitle(R.string.delete_company_dialog_title)
                .setCancelable(true);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteCompany(idComp);

                Toast.makeText(ComapniesData.this, "Company "+company_data +"Successfully deleted", Toast.LENGTH_SHORT).show();
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

    private void deleteCompany(final int id) {

        CompanieServices services = ApiConnect.getClient().create(CompanieServices.class);

        Call<CompanieRows> call = services.deleteCompany(API_KEY, idComp);

        call.enqueue(new Callback<CompanieRows>() {
            @Override
            public void onResponse(Call<CompanieRows> call, Response<CompanieRows> response) {

                Log.d(TAG_NAME, "Company's id: " +id +"\n"
                        +"Company: " +company_data
                );
            }

            @Override
            public void onFailure(Call<CompanieRows> call, Throwable t) {

                Log.e(TAG_NAME, "Failed to delete company ! ");

                Toast.makeText(ComapniesData.this, "Failed to Connect !", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
