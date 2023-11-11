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

    public void addEventType(EventType eventType){
        ref.child("eventTypes/"+eventType.getName()).setValue(eventType);
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
     * @param retreivingFunctionName the name of the type of operation being performed on the event type (add, edit, delete)
     */
    public void loadEventType(CanReceiveAnEventType main, String eventTypeName, String retreivingFunctionName){
        DatabaseReference userRef;

        try {
            userRef = ref.child("eventTypes/" + eventTypeName);
        }
        catch (Exception e) {
            main.onEventTypeDatabaseFailure("invalidName");
            return;
        }
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    System.out.println("database works");
                    // Retrieve data from the DataSnapshot
                    EventType eventType=new EventType(dataSnapshot);
                    main.onEventTypeRetrieved(retreivingFunctionName, eventType);
                }
                else{
                    main.onEventTypeDatabaseFailure(retreivingFunctionName);
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
                    main.onEventDatabaseFailure();
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
    public void deleteUserData(CanReceiveAUser main, String userName, String role) {
        loadUserDataFromBook(main, userName,role);
    }

    private void finishDeletion(CanReceiveAUser main, String userId, String role, String userName){

        //sends a request to the server for data
        DatabaseReference userRef= ref.child("users/"+role+"/"+userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ref.child("users/"+role+"/"+userId).removeValue();
                    ref.child("users/theAdminsLittleBlackBook/"+userName).removeValue();
                    main.onUserDeleteAccountSuccess();
                }
                else{
                    main.onUserDeleteAccountFailed();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("uh oh");
                main.onUserDeleteAccountFailed();
            }
        });
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
                    main.onUserDatabaseFailure();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("uh oh");
            }
        });
    }


    /**
     * helper method for deleting a user using their username, find their user id, then calls finishDeleting with that id
     * @param userName the username of the user you're looking for
     * @param role the role of the user you want to delete
     */
    public void loadUserDataFromBook(CanReceiveAUser main, String userName, String role){
        System.out.println("trying to read");
        DatabaseReference userRef;
        //sends a request to the server for data
        try {
             userRef = ref.child("users/theAdminsLittleBlackBook/" + userName);
        }
        catch (Exception e) {
            main.onUserDeleteAccountFailed();
            return;
        }

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        System.out.println("database works");
                        // Retrieve data from the DataSnapshot
                        String userId = dataSnapshot.getValue(String.class);
                        finishDeletion(main, userId, role, userName);
                    }
                    catch (Exception e) {
                        main.onUserDeleteAccountFailed();
                    }
                }
                else{
                    main.onUserDeleteAccountFailed();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("uh oh");
                main.onUserDeleteAccountFailed();
            }
        });
    }

  }




