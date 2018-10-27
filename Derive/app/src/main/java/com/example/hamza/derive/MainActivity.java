package com.example.hamza.derive;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText passwordText;
    Button signup;
    TextView login;
    ProgressBar progressbar;

    boolean signupMode;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();  //getting firebase instance

        username = (EditText) findViewById(R.id.usernameID);
        passwordText = (EditText) findViewById(R.id.passswordID);
        signup = (Button) findViewById(R.id.signupBtn);
        login = (TextView) findViewById(R.id.loginBtn);
        progressbar = (ProgressBar) findViewById(R.id.progressbarID);

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

            String email = username.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

         //   if (password.length() < 6) {
           //     Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
             //   return;
            //}

            progressbar.setVisibility(View.VISIBLE);
            //create user
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(MainActivity.this, UserProfile.class );
                                startActivity(intent);
                                //finish();
                            }
                        }
                    });
        } else {    //log in functionality of button

            String email = username.getText().toString();
            final String password = passwordText.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            progressbar.setVisibility(View.VISIBLE);

            //authenticate user
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            progressbar.setVisibility(View.GONE);
                            /*if (!task.isSuccessful()) {
                                // there was an error
                                if (password.length() < 6) {
                                    passwordText.setError(getString(R.string.minimum_password));
                                } else {
                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                }
                            } else {*/
                                Intent intent = new Intent(MainActivity.this, UserProfile.class );
                                startActivity(intent);
                              //  finish();
                            //}
                        }
                    });
        }
    }
}
