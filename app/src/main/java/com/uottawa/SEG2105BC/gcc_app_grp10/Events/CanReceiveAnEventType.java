package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

public interface CanReceiveAnEventType {
    void onEventTypeRetrieved(String retreivingFunctionName, EventType eventType);

    void onEventTypeDatabaseFailure(String retreivingFunctionName);
}
