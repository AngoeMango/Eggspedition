package com.uottawa.SEG2105BC.gcc_app_grp10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.UserFactory;

import java.security.InvalidParameterException;

public class Registration extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText username ;
    EditText email;
    EditText bio;
    EditText password;
    RadioButton roleParticipant;
    RadioButton roleClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.nameEditText);
        email = findViewById(R.id.emailEditText);
        bio = findViewById(R.id.bioEditText);
        password = findViewById(R.id.passwordEditText);
        roleParticipant = findViewById(R.id.roleParticipant);
        roleClub = findViewById(R.id.roleClub);
    }

    public void OnRegisterButton(View view) {
        //if username is left empty, that's a nono
        if (username.getText().length() == 0){
            Toast.makeText(Registration.this, "Invalid username!", Toast.LENGTH_SHORT).show();
            return;
        }
        //if the email is empty, or if it doesn't have an @ or a . in it, that's a nono
        if (email.getText().toString().trim().length() == 0
                || email.getText().toString().trim().indexOf('@') == -1
                || email.getText().toString().trim().indexOf(".") == -1){
            Toast.makeText(Registration.this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.getText().toString().length() < 6){
            Toast.makeText(Registration.this, "Password must be at least 6 characters long!", Toast.LENGTH_SHORT).show();
            return;
        }

        /*
        * database stuff
        */
        fAuth = FirebaseAuth.getInstance();
        FirebaseDatabase fDB = FirebaseDatabase.getInstance();

        UserFactory uF = new UserFactory();//makes new user
        User user;//to hold new users
        String path = "users/";//path in database where to save user info

        /*
        * checks the radio buttons to choose the role
        * then it alters path accordingly
        * and makes the user with the info to be saved a little later
        */
        if (roleParticipant.isChecked()){
            path = path + "participant/";
            user = uF.makeUser("participant",
                    username.getText().toString().trim(),
                    password.getText().toString().trim(),
                    email.getText().toString().trim(),
                    bio.getText().toString().trim());
        } else if (roleClub.isChecked()) {
            path = path + "club/";
            user = uF.makeUser("club",
                    username.getText().toString().trim(),
                    password.getText().toString().trim(),
                    email.getText().toString().trim(),
                    bio.getText().toString().trim());
        } else {
            Toast.makeText(Registration.this, "Invalid Role!", Toast.LENGTH_SHORT).show();
            return;
        }


        /*
        * this is the thing that makes the user account in the Authentication thing in firebase
        * it's pretty nuts and i don't totally get how it works
        */
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
                            intent.putExtra("username", username.getText().toString());
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


    }

}