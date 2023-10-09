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
            throw new InvalidParameterException("Invalid username!");
        }
        //if the email is empty, or if it doesn't have an @ or a . in it, that's a nono
        if (email.getText().toString().trim().length() == 0
                || email.getText().toString().trim().indexOf('@') == -1
                || email.getText().toString().trim().indexOf(".") == -1){
            throw new InvalidParameterException("Invalid email address!");
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
        } else {throw new InvalidParameterException("Invalid role!");}


        String finalPath = path;//they got mad at me if i didn't make a "final" version of this variable

        /*
        * this is the thing that makes the user account in the Authentication thing in firebase
        * it's pretty nuts and i don't totally get how it works
        */
        fAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.println(Log.INFO, "INFO", "DOES IT GO IN HERE CHECKPOINT");
                            FirebaseUser fUser = fAuth.getCurrentUser();//current user
                            String IDstring = fUser.getUid();//current user id

                            //uses current user id as primary key to save account as under
                            DatabaseReference ref = fDB.getReference(finalPath + IDstring);
                            ref.setValue(user);//this saves all the info really easy-like

                            //this is just so we know it all worked
                            Log.d("INFO", "createUserWithEmail:success");
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
                            Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        Log.d("INFO", "THIS IF AFTER THE IF CHECKPOINT");
                    }
                });


    }

}