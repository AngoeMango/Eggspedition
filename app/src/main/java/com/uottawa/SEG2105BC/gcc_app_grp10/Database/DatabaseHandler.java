package com.uottawa.SEG2105BC.gcc_app_grp10.Database;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.SEG2105BC.gcc_app_grp10.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.*;


public class DatabaseHandler {
    DatabaseReference ref;


    public DatabaseHandler(){
        ref = FirebaseDatabase.getInstance().getReference();
    }
    // Method to write data to the database
    public void writeData(String userId, String role, String data) {
        ref.child("users/"+role+"/"+userId).setValue(data);
    }
    public void addNewUserData(String userId, String role, User user){
        ref.child("users/theAdminsLittleBlackBook/"+user.getUsername()).setValue(userId);
        ref.child("users/"+role+"/"+userId).setValue(user);
    }

    //method for updating data in the database
    public void updateData(String userId, String role, String newData) {
        ref.child("users/"+role+"/"+userId).setValue(newData);
    }

    //method for deleting data from the database, just takes the admin to make sure no other user can use it
    public void deleteUserData(Admin admin, String userId, String role) {
        ref.child("users/"+role+"/"+userId).removeValue();
    }

    public void getUserData(String userId){

    }


    /**
     * Loads the data of an existing user, and passes it back to the main thread via the onUserDataRetrieved() method
     * @param main the class currently controlling the main thread, this method must be called while the login screen is active
     *             we might want to overload it later to work from any screen
     * @param userId
     * @param role
     */
    public void loadUserData(CanReceiveAUser main, String userId, String role){
        System.out.println("trying to read");
        //sends a request ot the server for data
        DatabaseReference userRef= readUserData(userId, role);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //taskSuccessful=true;
                    System.out.println("database works");
                    // Retrieve data from the DataSnapshot
                    UserFactory userFactory=new UserFactory();
                    User user=userFactory.makeUser(dataSnapshot, role);
                    main.onUserDataRetrieved(user);
                }
                else{
                    main.onDatabaseFailure();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("uh oh");
            }
        });
    }

    // Method to read data from the database
    private DatabaseReference readUserData(String userId, String role) {
        return ref.child("users/"+role+"/"+userId);
    }



}
