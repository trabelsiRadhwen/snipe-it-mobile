package com.example.radhwen.snipeit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.radhwen.snipeit.MainActivity;
import com.example.radhwen.snipeit.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private AppCompatButton login;

    private String usernameVal, passwordVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin();
            }
        });
    }

    private void setLogin() {

        usernameVal = username.getText().toString().trim();
        passwordVal = password.getText().toString().trim();

        if (usernameVal.isEmpty()) {
            username.setError("Username required !");
        }else if (password == null) {
            password.setError("Username required !");
        }else if (usernameVal.isEmpty() || passwordVal.isEmpty()) {
            username.setError("Username required !");
            password.setError("Password required !");
        }else if (usernameVal.equals("admin") && passwordVal.equals("password")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else if ((!usernameVal.equals("admin")) && (!passwordVal.equals("password"))){
            Toast.makeText(this, "Invalid Credential", Toast.LENGTH_SHORT).show();
        }
    }
}
