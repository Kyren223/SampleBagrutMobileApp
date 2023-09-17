package me.kyren223.samplebagrutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.regButtonReg);
        registerButton.setOnClickListener(this::onRegisterButtonClicked);
    }

    private void onRegisterButtonClicked(View view) {
        String login = ((EditText) findViewById(R.id.regLogin)).getText().toString();
        String pass = ((EditText) findViewById(R.id.regPass)).getText().toString();
        String first = ((EditText) findViewById(R.id.regFirst)).getText().toString();
        String last = ((EditText) findViewById(R.id.regLast)).getText().toString();

        DBHelper db = new DBHelper(this);

        if (db.InsertOneUser(login, pass, first, last)) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}