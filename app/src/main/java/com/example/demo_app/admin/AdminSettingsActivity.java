package com.example.demo_app.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.demo_app.R;
import com.example.demo_app.SettingsFragment;
import com.example.demo_app.UserSettings;
import com.example.demo_app.admin.AdminHomeActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class AdminSettingsActivity extends AppCompatActivity {

    private View parentView;
    private SwitchMaterial themeSwitch;
    private TextView themeTv;

    private UserSettings settings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        getSupportActionBar().setTitle("Settings");

        if (findViewById(R.id.settings_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getFragmentManager().beginTransaction().add(R.id.settings_container, new SettingsFragment()).commit();
        }

        settings = (UserSettings) getApplication();

        iniWidgets();
        loadSharedPreferences();
        initSwitchListener();
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserSettings.PREFERENCES,MODE_PRIVATE);
        String theme = sharedPreferences.getString(UserSettings.CUSTOM_THEME,UserSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);
        updateView();
    }
    private void initSwitchListener() {
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    settings.setCustomTheme(UserSettings.DARK_THEME);
                else
                    settings.setCustomTheme(UserSettings.LIGHT_THEME);

                SharedPreferences.Editor editor = getSharedPreferences(UserSettings.PREFERENCES,MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOM_THEME,settings.getCustomTheme());
                editor.apply();
                updateView();
            }
        });
    }

    private void updateView() {
        final int black = ContextCompat.getColor(this,R.color.black);
        final int white = ContextCompat.getColor(this,R.color.white);

        if (settings.getCustomTheme().equals(UserSettings.DARK_THEME)){
            themeTv.setTextColor(black);
            themeTv.setText("Light");
            parentView.setBackgroundColor(white);
            themeSwitch.setChecked(true);
        }
        else {
            themeTv.setTextColor(white);
            themeTv.setText("Dark");
            parentView.setBackgroundColor(black);
            themeSwitch.setChecked(false);
        }
    }


    private void iniWidgets() {
        themeTv = findViewById(R.id.themeTv);
        themeSwitch = findViewById(R.id.themeSwitch);
        parentView = findViewById(R.id.parentView);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, AdminHomeActivity.class);
        startActivity(i);
        finish();
    }
}