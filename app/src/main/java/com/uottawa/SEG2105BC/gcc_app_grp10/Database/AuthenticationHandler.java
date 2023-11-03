package com.uottawa.SEG2105BC.gcc_app_grp10.Database;
import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.MainActivity;
import com.uottawa.SEG2105BC.gcc_app_grp10.Registration;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

public class AuthenticationHandler {
    private FirebaseAuth mAuth;

    public AuthenticationHandler() {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * performs the first time authorisation and automatically calls the database handler to make a new user
     * @param main the class currently controlling the main thread, this method must be called from the registration screen
     * @param user the user to register
     * @param email
     * @param password
     * @param activity the current activity being displayed
     * @param context no clue honestly, might be the main thread
     */
    public void signUp(Registration main, User user, String email, String password, final Activity activity, final Context context) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        System.out.println("Authentication succesful");
                        FirebaseUser fUser = mAuth.getCurrentUser();
                        onSignUpAuthorised(fUser, user);
                        main.onRegistrationComplete(user);
                    } else {
                        main.onRegistrationAuthenticationFailure();
                        System.out.println("Authentication failed");
                    }
                });
    }
    private void onSignUpAuthorised(FirebaseUser fUser, User user){
        DatabaseHandler databaseHandler=new DatabaseHandler();
        databaseHandler.addNewUserData(fUser.getUid(),user.getRole().toString(),user);
    }

    /**
     * Signs in an existing user, and automatically called the database handler to load in the users data
     * @param main the class currently controlling the main thread, this method must be called from the login screen
     * @param email
     * @param password
     * @param role
     * @param activity the current activity being displayed
     * @param context no clue honestly, might be the main thread
     */
    public void signIn(MainActivity main, String email, String password, String role, final Activity activity, final Context context) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        System.out.println("authorisation succesful");
                        FirebaseUser fUser = mAuth.getCurrentUser();
                        onSignInAuthorised(main, fUser, role);
                        // You can do something with the user object if needed
                    } else {
                        main.onLoginAuthorisationFailure();
                        System.out.println("uh oh, authorisation failed");
                    }
                });
    }

    private void onSignInAuthorised(MainActivity main,FirebaseUser fUser, String role){
        DatabaseHandler databaseHandler=new DatabaseHandler();
        //loads the users data from the database into a user instance
        databaseHandler.loadUserData(main,fUser.getUid(), role);
    }




    // Method to sign out
    public void signOut() {
        mAuth.signOut();
    }
}
