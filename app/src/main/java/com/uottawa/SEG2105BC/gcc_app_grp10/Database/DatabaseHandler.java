package com.uottawa.SEG2105BC.gcc_app_grp10.Database;

import static java.util.Objects.requireNonNull;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.CanReceiveAnEventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.EventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
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





    /*
    Event Methods
     */

    public void addEventType(String eventTypeName, EventType eventType){
        ref.child("eventTypes/"+eventTypeName).setValue(eventType);
    }

    public void addEvent(String eventName, Event event){
        ref.child("events/"+eventName).setValue(event);
    }

    public void deleteEventType(String eventTypeName){
        ref.child("eventTypes/"+eventTypeName).removeValue();
    }

    public void deleteEvent(String eventName){
        ref.child("events/"+eventName).removeValue();
    }

    /**
     * used to load an EventType from the database
     * @param main the class currently controlling the main thread
     * @param eventTypeName the name of the EventType your looking for
     */
    public void loadEventType(CanReceiveAnEventType main, String eventTypeName){
        DatabaseReference userRef= ref.child("events/"+eventTypeName);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    System.out.println("database works");
                    // Retrieve data from the DataSnapshot
                    EventType eventType=new EventType(dataSnapshot);
                    main.onEventTypeRetrieved(eventType);
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

    /**
     * used to load an Event from the database
     * @param main the class currently controlling the main thread
     * @param eventName the name of the event your looking for
     */
    public void loadEvent(CanReceiveAnEvent main, String eventName){
        DatabaseReference userRef= ref.child("events/"+eventName);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    System.out.println("database works");
                    // Retrieve data from the DataSnapshot
                    Event event=new Event(dataSnapshot);
                    main.onEventRetrieved(event);
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





    /*
    User Methods
     */

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
        DatabaseReference userRef= ref.child("users/"+role+"/"+userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
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

  }




