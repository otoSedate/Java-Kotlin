package ru.startandroid.develop.apptest0;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

import ru.startandroid.develop.apptest0.settings.Setting_Activity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse {
//public class MainActivity extends AppCompatActivity implements AsyncResponse {
    private DrawerLayout drawer;
    private ListView list;
    private TextView eventTest;
    private String[] array;
    private ArrayAdapter<String> adapter;
    private Toolbar toolbar;
    private int category_index;
    private Object Intent;
    int LAUNCH_SECOND_ACTIVITY = 1;
    public static JSONArray jsonArray1;

    private AsyncRequest asyncRequest = new AsyncRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asyncRequest.delegate = this;
        //asyncRequest.execute();
        //
        list = findViewById(R.id.list_view);
        array = getResources().getStringArray(R.array.fish_array);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array)));
        list.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       // Intent intent1 = new Intent(MainActivity.this, AsyncRequest.class);
       // startActivityForResult(intent1, LAUNCH_SECOND_ACTIVITY);
//
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Text_Content_Activity.class);
                intent.putExtra("category", category_index);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

/*
        eventTest.findViewById(R.id.eventTest);
        eventTest.setText("SQL");
        eventTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSQL = new Intent(MainActivity.this, AsyncRequest.class);
                startActivityForResult(intentSQL, LAUNCH_SECOND_ACTIVITY);
            }
        });*/
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                eventTest.setText(result);
            }
        }

    }*/
//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.fish);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, Setting_Activity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.id_fish) {
            //Toast.makeText(this, "Home pressed", Toast.LENGTH_SHORT).show();
            /*toolbar.setTitle(R.string.fish);
            array = getResources().getStringArray(R.array.fish_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_index = 0;*/
            fillArray(R.string.fish, R.array.fish_array, 0);
        }
        else if (id == R.id.id_naj) {
            /*toolbar.setTitle(R.string.naj);
            array = getResources().getStringArray(R.array.naj_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_index = 1;*/
            fillArray(R.string.naj, R.array.naj_array, 1);
        }
        else if (id == R.id.id_snasti) {
            /*toolbar.setTitle(R.string.snasti);
            array = getResources().getStringArray(R.array.snasti_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_index = 2;*/
            fillArray(R.string.snasti, R.array.snasti_array, 2);
        }
        else if (id == R.id.id_prikorm) {
            /*toolbar.setTitle(R.string.prikorm);
            array = getResources().getStringArray(R.array.prikorm_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_index = 3;*/
            fillArray(R.string.prikorm, R.array.prikorm_array, 3);
        }
        else if (id == R.id.id_history) {
            /*toolbar.setTitle(R.string.history);
            array = getResources().getStringArray(R.array.history_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_index = 4;*/
            fillArray(R.string.history, R.array.history_array, 4);
        }
        else if (id == R.id.id_advice) {
            /*toolbar.setTitle(R.string.advice);
            array = getResources().getStringArray(R.array.advice_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            category_index = 5;*/
            fillArray(R.string.advice, R.array.advice_array, 5);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void fillArray(int title, int arrayList, int index) {
        toolbar.setTitle(title);
        array = getResources().getStringArray(arrayList);
        adapter.clear();
        adapter.addAll(array);
        adapter.notifyDataSetChanged();
        category_index = index;
    }


//
    @Override
    public void processFinish(JSONArray output) {
        //jsonArray1;

    }
}
