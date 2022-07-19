package ru.startandroid.develop.apptest0;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Text_Content_Activity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView text_content;
    private ImageView img_Content;
    private Typeface typeF1;
    private SharedPreferences def_pref;
    private int category;
    private int position;
    private String[] array_title_fish = {"fish 1", "fish 2", "fish 3", "fish 4", "fish 5"};
    private int[] array_fish = {R.string.fish_1, R.string.fish_2, R.string.fish_3, R.string.fish_4, R.string.fish_5};
    private int[] array_naj = {R.string.naj_1, R.string.naj_2};
    private int[] array_image_fish = {R.drawable.ic_menu_gallery, R.drawable.ic_menu_manage, R.drawable.ic_menu_manage, R.drawable.ic_menu_manage, R.drawable.ic_menu_manage};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);
        init();
        reciveIntent();

    }


    private void init() {
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        text_content = findViewById(R.id.text_main_content);
        img_Content = findViewById(R.id.image_content);
        typeF1 = Typeface.MONOSPACE;
        text_content.setTypeface(typeF1);
        actionBar = getSupportActionBar();
        String text = def_pref.getString("main_text_size", "Medium");
        switch (text) {
            case "Big":
                text_content.setTextSize(24);
                break;
            case "Medium":
                text_content.setTextSize(18);
                break;
            case "Small":
                text_content.setTextSize(12);
                break;
        }

    }

    private void reciveIntent() {

        Intent i = getIntent();
        if (i != null) {
            category = i.getIntExtra("category", 0);
            position = i.getIntExtra("position", 0);

        }
        switch (category) {
            case 0:
                text_content.setText(array_fish[position]);
                img_Content.setImageResource(array_image_fish[position]);
                actionBar.setTitle(array_title_fish[position]);
                //Intent intentData = new Intent(Text_Content_Activity.this, AsyncRequest.class);
                //startActivityForResult(intentData, 1);

                break;
            case 1:
                text_content.setText(array_naj[position]);
                break;
            case 2:
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String returnString = data.getStringExtra("result");

            }
        }
    }
}