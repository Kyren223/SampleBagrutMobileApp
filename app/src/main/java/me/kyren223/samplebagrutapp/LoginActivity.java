package me.kyren223.samplebagrutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        Button loginButton = findViewById(R.id.mainButtonLogin);
        loginButton.setOnClickListener(this::onLoginButtonClicked);

        Button registerButton = findViewById(R.id.mainButtonGoToReg);
        registerButton.setOnClickListener((v) -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        Button showButton = findViewById(R.id.mainButtonShowDB);
        showButton.setOnClickListener((v) -> db.ShowTable("users"));
    }

    private void onLoginButtonClicked(View view) {
        String login = ((EditText)findViewById(R.id.mainLogin)).getText().toString();
        String pass = ((EditText)findViewById(R.id.mainPass)).getText().toString();

        if(db.TryLogin(login, pass)) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}