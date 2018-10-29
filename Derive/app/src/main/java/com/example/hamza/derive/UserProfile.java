package com.example.hamza.derive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    private static final String table_name="users";

    Button editBtn;

    User userObj=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editBtn = (Button) findViewById(R.id.profileeditBtnID);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child(table_name);

        //String filterName="Muntahaa Mumtaz";

        //databaseReference.orderByChild("name")
        databaseReference
        //        .startAt(filterName)
        //         .endAt(filterName + "\uf8ff")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                            userObj = singleSnapshot.getValue(User.class);
                        }

                        if (userObj==null) {
                            Toast.makeText(UserProfile.this, R.string.noResultsFound, Toast.LENGTH_SHORT).show();
                        } else {
                            TextView name_et = findViewById(R.id.usernameprofileID);
                            TextView age_et = findViewById(R.id.ageID);
                            TextView city_et = findViewById(R.id.cityID);
                            TextView country_et = findViewById(R.id.countryID);
                            TextView contact_et = findViewById(R.id.contactID);

                            name_et.setText(userObj.getName());
                            age_et.setText(userObj.getAge());
                            city_et.setText(userObj.getCity());
                            country_et.setText(userObj.getCountry());
                            contact_et.setText(userObj.getContact());
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        //Log.w("Database Error", getString(R.string.search_loadCancel), databaseError.toException());
                        // ...
                    }
                });
    }

    public void onEditClick(View v){
        Intent intent = new Intent(UserProfile.this, EditProfile.class );
        intent.putExtra("user",userObj);
        startActivityForResult(intent,1);
    }
}
