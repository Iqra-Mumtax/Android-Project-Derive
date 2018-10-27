package com.example.hamza.derive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button signup;
    TextView login;

    boolean signupMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.usernameID);
        password = (EditText) findViewById(R.id.passswordID);
        signup = (Button) findViewById(R.id.signupBtn);
        login = (TextView) findViewById(R.id.loginBtn);

        signupMode = true;

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {    //log in view act as button (or vice-versa)
        if (view.getId() == R.id.loginBtn){
            if (signupMode == true){
                signupMode = false;
                login.setText("Sign Up");
                signup.setText("Log In");
            } else {
                signupMode = true;
                login.setText("Log In");
                signup.setText("Sign Up");
            }
        }
    }

    public void onSignupOrLoginClick(View view){    //on sign up button or log in button click
        if (signupMode == true){    //sign up functionality of button

        } else {    //log in functionality of button

        }
    }
}
