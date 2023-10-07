package com.uottawa.SEG2105BC.gcc_app_grp10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }
}