package com.uottawa.SEG2105BC.gcc_app_grp10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.UserFactory;

import java.security.InvalidParameterException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText email;
    EditText password;
    RadioButton roleParticipant;
    RadioButton roleClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        roleParticipant = findViewById(R.id.roleParticipant);
        roleClub = findViewById(R.id.roleClub);
    }

    public void toRegisterPageButton(View view){
        //when go to register gets pressed, bring you to register page
        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity (intent);

    }


    public void OnLoginButton(View view) {

        /*
         * database stuff
         */
        fAuth = FirebaseAuth.getInstance();
        FirebaseDatabase fDB = FirebaseDatabase.getInstance();

        UserFactory uF = new UserFactory();//makes new user

        String path = "users/";//path in database where to save user info

        if (roleParticipant.isChecked()){
            path = path + "participant/";
        } else if (roleClub.isChecked()) {
            path = path + "club/";
        } else {throw new InvalidParameterException("Invalid role!");}

        /*
         * this is the thing that makes the user account in the Authentication thing in firebase
         * it's pretty nuts and i don't totally get how it works
         */
        String finalPath = path;

        fAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser fUser = fAuth.getCurrentUser(); // Get the current user
                            String IDstring = fUser.getUid(); // Get the current user's ID

                            // Reference to the user's data in Firebase Realtime Database
                            DatabaseReference userRef = fDB.getReference(finalPath + IDstring);

                            // Attach a listener for a single read
                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {

                                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                                        // Retrieve data from the DataSnapshot
                                        String username = dataSnapshot.child("username").getValue(String.class);

                                        //switches window to welcome window
                                        Intent intent = new Intent(getApplicationContext(), Welcome.class);
                                        // Adds information to the intent for the welcome page to access
                                        intent.putExtra("username", username);
                                        if (roleParticipant.isChecked()) {
                                            intent.putExtra("role", roleParticipant.getText().toString());
                                        } else if (roleClub.isChecked()) {
                                            intent.putExtra("role", roleClub.getText().toString());
                                        }
                                        else {
                                            intent.putExtra("role", "unknown");
                                        }
                                        startActivity (intent);
                                    }
                                    else {
                                        Snackbar.make(findViewById(android.R.id.content), "No user exists with given role!", Snackbar.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Snackbar.make(findViewById(android.R.id.content), "" + Objects.requireNonNull(task.getException()).getMessage(), Snackbar.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user
                            Snackbar.make(findViewById(android.R.id.content), "" + Objects.requireNonNull(task.getException()).getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });

    }

}