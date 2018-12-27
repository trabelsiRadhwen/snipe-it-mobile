package com.example.radhwen.snipeit.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.adapters.AssetAdapter;
import com.example.radhwen.snipeit.adapters.UserAssignedAssetsAdapter;
import com.example.radhwen.snipeit.model.Asset;
import com.example.radhwen.snipeit.model.Rows;
import com.example.radhwen.snipeit.model.User;
import com.example.radhwen.snipeit.model.UserRows;
import com.example.radhwen.snipeit.net.ApiConnect;
import com.example.radhwen.snipeit.services.UserServices;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserData extends AppCompatActivity {

    private static final String TAG = UserData.class.getSimpleName();

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    SharedPreferences sharedPreferences;

    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        sharedPreferences = getSharedPreferences("usersPref", MODE_PRIVATE);

        TextView number = (TextView) findViewById(R.id.user_number_data);
        String numberData = sharedPreferences.getString("number", "");
        number.setText(numberData);

        TextView name = (TextView) findViewById(R.id.user_name_data);
        String nameData = sharedPreferences.getString("name", "");
        name.setText(nameData);

        TextView job = (TextView) findViewById(R.id.user_job_data);
        String jobData = sharedPreferences.getString("job", "");
        job.setText(jobData);

        TextView company = (TextView) findViewById(R.id.user_company_data);
        String companyData = sharedPreferences.getString("company", "");
        company.setText(companyData);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.assigned_assets:

                sharedPreferences = getSharedPreferences("usersPref", MODE_PRIVATE);

                idUser = sharedPreferences.getInt("id", 0);
                Intent intent = new Intent(UserData.this, UserAssignedAssets.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
