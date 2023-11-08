package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

public interface CanReceiveAnEventType {
    void onEventTypeRetrieved(EventType eventType);
    void onDatabaseFailure();
}
