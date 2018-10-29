package com.example.hamza.derive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfile extends AppCompatActivity {

    Button saveBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveBtn = findViewById(R.id.cancelID);
        cancelBtn = findViewById(R.id.saveID);
    }

    public void onSaveClick(View v){    //updating data in user profile and firebase



        finish();
    }

    public void onCancelClick(View v){
        finish();
    }
}
