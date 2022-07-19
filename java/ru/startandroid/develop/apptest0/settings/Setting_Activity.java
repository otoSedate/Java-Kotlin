package ru.startandroid.develop.apptest0.settings;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import ru.startandroid.develop.apptest0.R;

public class Setting_Activity extends AppCompatActivity {
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.action_settings));
        }

        //getSupportFragmentManager().beginTransaction().replace(R.id.content, new Settings_Fragment()).commit();
        getFragmentManager().beginTransaction().replace(android.R.id.content, new Settings_Fragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        //return super.onOptionsItemSelected(item);
        return true;

    }
}
