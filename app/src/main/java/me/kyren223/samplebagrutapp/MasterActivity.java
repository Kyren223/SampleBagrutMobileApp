package me.kyren223.samplebagrutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Class<?> cls = null;

        int id = item.getItemId();
        if (id == R.id.itemMain) cls = MainActivity.class;
        else if (id == R.id.itemSMS) cls = SMSActivity.class;

        if (cls != null) startActivity(new Intent(this, cls));
        return super.onOptionsItemSelected(item);
    }

}