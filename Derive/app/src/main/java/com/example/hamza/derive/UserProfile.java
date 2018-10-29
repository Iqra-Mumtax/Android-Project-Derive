package com.example.hamza.derive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    private DatabaseReference mDatabase;
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editBtn = (Button) findViewById(R.id.profileeditBtnID);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void onEditClick(View v){
        Intent intent = new Intent(UserProfile.this, EditProfile.class );
        startActivityForResult(intent,1);
    }

    //add new user
    private void writeNewUser(String username, String city, String country, String age, String contact) {
        User user = new User(username, city, country, age, contact);

        mDatabase.child("users").child(username).setValue(user);
    }
}
