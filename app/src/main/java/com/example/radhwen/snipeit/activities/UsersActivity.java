package com.example.radhwen.snipeit.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.radhwen.snipeit.R;
import com.example.radhwen.snipeit.adapters.UserAdapter;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.User;
import com.example.radhwen.snipeit.model.UserRows;
import com.example.radhwen.snipeit.net.ApiConnect;
import com.example.radhwen.snipeit.services.UserServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "usersPref";

    SharedPreferences preferences;

    private static final String TAG_NAME = UsersActivity.class.getSimpleName();

    private static final String API_KEY = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImM5NGJkMTY1MWIyNDkzY2U2NTAyOTc0MDNjMjFkOTM1MTk5NTc1ZGM2N2IzYTNiYzgyODkzNzVjNDRlYWE1Yjk3YTQ2YWE4MGQ0YTgwYjFiIn0.eyJhdWQiOiIxIiwianRpIjoiYzk0YmQxNjUxYjI0OTNjZTY1MDI5NzQwM2MyMWQ5MzUxOTk1NzVkYzY3YjNhM2JjODI4OTM3NWM0NGVhYTViOTdhNDZhYTgwZDRhODBiMWIiLCJpYXQiOjE1NDM4NTA4MDgsIm5iZiI6MTU0Mzg1MDgwOCwiZXhwIjoxNTc1Mzg2ODA4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.JijbSmjDvW6ieK73Y6kMzgIoDR3Xc8f_cz7QaNS-d--_4phSIvnaFa6Drtt7Jce7QaHLUZwR4PEN4El1KpCvohuVTXwX-rDx4x7Qh_68N34mjQoGgu25e6-zPSKymqtXzAHAVznsImTIzKaC3FYIJxNZzmENZQ2MBTUXIm_3C7d5WIc_nzn3kl3J4NQDclAjCdLQ2wByE_ecKM5mYf-vVu524mXXExoEIr4XGo4_YP0RVILu2zrnXW3pvWabRokkEjpHoyczleRLAWUR6eqTXY8unSfO7nK8GIftSxVeVvnPha8Im4ONb9fZP4VCYnlxvuZUalY1yRMtHrhFahc9A_QJY5de04ZUuVEJXPaNTX4asbK8XWQmAke2iwU72hCX146IkpALJ5Xz-UM_V-0IuAgYwMFG_RtsPI4OCo2RDNWDvqanWbTc_Rux9pXDIACe1UpJL3uFd15uZA1iLuzkGgSQU_iqOlBhfIzaLHU8gDCSxsu3ejwkViCNH39kfQxxR6pOFx0bR9ZdErQHXktrHfFBW2AqtjjpCFsqn8aYtlFICMCjphKhGxUTapOwQBrDHMOril1_sJG6_DH46-GSpujrFfMVl7RzPVnY5XALzDQMpm7J64SzihnLCzLWAhChmxAW05mqP79wQF3DnR-l07QCqwXKPQkFg3quAG2AI-s";

    private ListView users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        users = findViewById(R.id.users_list);

        getUsers();
    }

    private void getUsers() {

        UserServices services = ApiConnect.getClient().create(UserServices.class);

        Call<User> call = services.getUsers(API_KEY);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                final List<UserRows> list = response.body().getUserRows();

                users.setAdapter(new UserAdapter(UsersActivity.this, list));

                preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

                users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        final SharedPreferences.Editor editor = preferences.edit();

                        int idUser = list.get(position).getId();
                        String name = list.get(position).getName();
                        String job = list.get(position).getJobTitle();
                        String number = list.get(position).getEmployeeNumber();
                        CompanieRows company = list.get(position).getCompany();

                        editor.putInt("id", idUser);
                        editor.putString("name", name);
                        editor.putString("job", job);
                        editor.putString("number", number);
                        editor.putString("company", String.valueOf(company));

                        editor.apply();

                        Intent intent = new Intent(UsersActivity.this, UserData.class);
                        startActivity(intent);
                    }
                });

                for (UserRows userRows : list) {
                    Log.d(TAG_NAME, "Users response: \n" + userRows.getName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG_NAME, "Failed !");

                Toast.makeText(UsersActivity.this, "Failed to connect !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
