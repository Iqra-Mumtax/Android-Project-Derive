package com.example.hamza.derive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {

    private static final String table_name="users";

    EditText name_et;
    EditText age_et;
    EditText city_et;
    EditText country_et;
    EditText contact_et;

    Button saveBtn;
    Button cancelBtn;

    User userObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userObj=getIntent().getParcelableExtra("user");

        name_et = findViewById(R.id.usernameprofileID);
        age_et = findViewById(R.id.ageID);
        city_et = findViewById(R.id.cityID);
        country_et = findViewById(R.id.countryID);
        contact_et = findViewById(R.id.contactID);

        name_et.setText(userObj.getName());
        age_et.setText(userObj.getAge());
        city_et.setText(userObj.getCity());
        country_et.setText(userObj.getCountry());
        contact_et.setText(userObj.getContact());


        saveBtn = findViewById(R.id.cancelID);
        cancelBtn = findViewById(R.id.saveID);


    }

    public void onSaveClick(View v){    //updating data in user profile and firebase

        String name = name_et.getText().toString();
        String age = age_et.getText().toString();
        String city = city_et.getText().toString();
        String country = country_et.getText().toString();
        String contact = contact_et.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        userObj.setName(name);
        userObj.setAge(age);
        userObj.setCity(city);
        userObj.setCountry(country);
        userObj.setContact(contact);

        ref.child(table_name).child(userObj.getId()).setValue(userObj);

        Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();


        finish();
    }

    public void onCancelClick(View v){
        finish();
    }
}
