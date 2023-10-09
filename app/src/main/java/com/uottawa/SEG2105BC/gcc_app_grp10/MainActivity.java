package com.uottawa.SEG2105BC.gcc_app_grp10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.UserFactory;

public class MainActivity extends AppCompatActivity {
    EditText username ;
    EditText email;
    EditText bio;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.nameEditText);
        email = findViewById(R.id.emailEditText);
        bio = findViewById(R.id.bioEditText);
        password = findViewById(R.id.passwordEditText);
        register = findViewById(R.id.registerButton);
    }

    public void OnRegisterButton(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Welcome.class);
        startActivity (intent);
        /*
        I'm not 100% sure what the thing above means
        but if you use the following line of code you and pass the right data you can make a user
        it's a static method, so don't worry about initialising UserFactory
        not 100% sure how to access the role, if you pass either "participant" or "club" it'll work
        */
        User newUser=UserFactory.makeUser("role",username.getText().toString(),password.getText().toString(),email.getText().toString());
    }
}