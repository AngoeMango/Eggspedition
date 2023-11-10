package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.AuthenticationHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.UserFactory;

public class Registration extends AppCompatActivity {

    EditText username ;
    EditText email;
    EditText bio;
    EditText password;
    EditText firstName;
    RadioButton roleParticipant;
    RadioButton roleClub;
    AuthenticationHandler authenticationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.usernameEditText);
        email = findViewById(R.id.emailEditText);
        bio = findViewById(R.id.bioEditText);
        password = findViewById(R.id.passwordEditText);
        firstName = findViewById(R.id.firstNameEditText);
        roleParticipant = findViewById(R.id.roleParticipant);
        roleClub = findViewById(R.id.roleClub);
        authenticationHandler=new AuthenticationHandler();
    }

    public void OnRegisterButton(View view) {
        if(!validateInputs()){return;}
        UserFactory uF = new UserFactory();//makes new user
        User user;//to hold new users
        String role=selectRole();

        user = uF.makeUser(role, username.getText().toString().trim(), password.getText().toString().trim(), email.getText().toString().trim(), bio.getText().toString().trim(), firstName.getText().toString().trim());
        //makes the user with the info to be saved a little later
        authenticationHandler.signUp(this, user, email.getText().toString().trim(), password.getText().toString().trim(), this, this);
    }

    /**
     * called by the AuthenticationHandler once the new user is registered, note the database creates teh new user first
     * but there isn't a way to catch errors from it yet
     * @param user the user that was just registered
     */
    public void onRegistrationComplete(User user){
        Toast.makeText(Registration.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        //switches window to welcome window
        Intent intent = new Intent(getApplicationContext(), Welcome.class);
        // Adds information to the intent for the welcome page to access
        intent.putExtra("firstName", firstName.getText().toString());
        intent.putExtra("role", user.getRole().toString());

        startActivity (intent);
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
        if (username.getText().length() == 0) {
            Toast.makeText(Registration.this, "You need a username!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (firstName.getText().length() == 0) {
            Toast.makeText(Registration.this, "You need a first name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.getText().length() == 0) {
            Toast.makeText(Registration.this, "You need an email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //if the first name is empty, or if it doesn't match standard email rules (RFC 5322)
        if (!firstName.getText().toString().matches(".*\\p{L}.*")) {
            Toast.makeText(Registration.this, "You need a valid first name with letters!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.getText().toString().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            Toast.makeText(Registration.this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() < 6) {
            Toast.makeText(Registration.this, "Password must be at least 6 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!roleParticipant.isChecked()&&!roleClub.isChecked()){
            Toast.makeText(Registration.this, "Select a Role!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String selectRole(){
        if (roleParticipant.isChecked()) {
            return "participant";
        } else if (roleClub.isChecked()) {
            return "club";
        }
        return null;
    }




        /*
        * this is the thing that makes the user account in the Authentication thing in firebase
        * it's pretty nuts and i don't totally get how it works
        */
        /*
        String finalPath = path;

        fAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser fUser = fAuth.getCurrentUser();//current user
                            String IDstring = fUser.getUid();//current user id

                            //uses current user id as primary key to save account as under
                            DatabaseReference ref = fDB.getReference(finalPath + IDstring);
                            ref.setValue(user);//this saves all the info really easy-like

                            Toast.makeText(Registration.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                            //switches window to welcome window
                            Intent intent = new Intent(getApplicationContext(), Welcome.class);
                            // Adds information to the intent for the welcome page to access
                            intent.putExtra("firstName", firstName.getText().toString());
                            if (roleParticipant.isChecked()) {
                                intent.putExtra("role", roleParticipant.getText().toString());
                            } else if (roleClub.isChecked()) {
                                intent.putExtra("role", roleClub.getText().toString());
                            }
                            else {
                                intent.putExtra("role", "unknown");
                            }
                            startActivity (intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Snackbar.make(findViewById(android.R.id.content), "" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                */



}