package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.AuthenticationHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanRegister;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Admin;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Club;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Participant;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

public class ParticipantCanRegister extends AppCompatActivity implements CanRegister {
    com.google.android.material.textfield.TextInputLayout username ;
    com.google.android.material.textfield.TextInputLayout email;
    com.google.android.material.textfield.TextInputLayout bio;
    com.google.android.material.textfield.TextInputLayout password;
    com.google.android.material.textfield.TextInputLayout firstName;
    AuthenticationHandler authenticationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_registration);

        username = findViewById(R.id.usernameEditText);
        email = findViewById(R.id.emailEditText);
        bio = findViewById(R.id.bioEditText);
        password = findViewById(R.id.passwordEditText);
        firstName = findViewById(R.id.firstNameEditText);
        authenticationHandler=new AuthenticationHandler();
    }

    public void OnRegisterButton(View view) {
        if(!validateInputs()){return;}
        User user;//to hold new users

        user = new Participant(username.getEditText().getText().toString().trim(), password.getEditText().getText().toString().trim(), email.getEditText().getText().toString().trim(), bio.getEditText().getText().toString().trim());
        user.setUsername(firstName.getEditText().getText().toString().trim());
        //makes the user with the info to be saved a little later
        authenticationHandler.signUp(this, user, email.getEditText().getText().toString().trim(), password.getEditText().getText().toString().trim(), this, this);
    }

    /**
     * called by the AuthenticationHandler once the new user is registered, note the database creates teh new user first
     * but there isn't a way to catch errors from it yet
     * @param user the user that was just registered
     */
    public void onRegistrationComplete(User user){
        Toast.makeText(ParticipantCanRegister.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        if (user.getClass().equals(Admin.class)) {
            Intent intent = new Intent(getApplicationContext(), AdminWelcome.class);
            startActivity(intent);
        }
        else if (user.getClass().equals(Club.class)) {

            Intent intent = new Intent(getApplicationContext(), ClubWelcome.class);
            intent.putExtra("firstName", user.getUsername());
            intent.putExtra("role", user.getRole());
            intent.putExtra("clubName", user.getUsername());
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(getApplicationContext(), ParticipantWelcome.class);
            // Adds information to the intent for the welcome page to access
            intent.putExtra("role", user.getRole());
            intent.putExtra("participantEmail", user.getEmail());
            intent.putExtra("participantPassword", user.getPassword());
            intent.putExtra("participantUsername", user.getUsername());

            System.out.println("going to next activity");
            startActivity(intent);
        }

    }

    /**
     * called by the AuthenticationHandler if the authentication fails
     */
    public void onRegistrationAuthenticationFailure(){
        Snackbar.make(findViewById(android.R.id.content), "registration failed" , Toast.LENGTH_LONG).show();
    }

    /**
     * called by the DatabaseHandler if the creation fails
     */
    public void onDatabaseFailure(){
        Snackbar.make(findViewById(android.R.id.content), "registration failed" , Toast.LENGTH_LONG).show();
    }

    private boolean validateInputs(){
        //if username is left empty, that's a nono
        if (username.getEditText().getText().length() == 0) {
            Toast.makeText(ParticipantCanRegister.this, "You need a username!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (firstName.getEditText().getText().length() == 0) {
            Toast.makeText(ParticipantCanRegister.this, "You need a first name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.getEditText().getText().length() == 0) {
            Toast.makeText(ParticipantCanRegister.this, "You need an email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //if the first name is empty, or if it doesn't match standard email rules (RFC 5322)
        if (!firstName.getEditText().getText().toString().matches(".*\\p{L}.*")) {
            Toast.makeText(ParticipantCanRegister.this, "You need a valid first name with letters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.getEditText().getText().toString().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            Toast.makeText(ParticipantCanRegister.this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getEditText().getText().toString().length() < 6) {
            Toast.makeText(ParticipantCanRegister.this, "Password must be at least 6 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



}