package com.uottawa.SEG2105BC.gcc_app_grp10;

import org.junit.Test;

import static org.junit.Assert.*;

import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.EventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.PropertyType;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.SpecifiedProperty;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Club;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Participant;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addingEventToParticipant(){
        Participant participant = new Participant("participant1", "12345",
                "participant1@gmail.com", "bio");
        EventType eventType = new EventType("eventtype1");
        PropertyType properties = new PropertyType("property1", "String");
        SpecifiedProperty specifiedProperty = new SpecifiedProperty("value1", properties);
        ArrayList<SpecifiedProperty> specifiedProperties = new ArrayList<>();
        specifiedProperties.add(specifiedProperty);
        Event event = new Event("event1", eventType, specifiedProperties, "club1");

        participant.addEvent(event);

        assertEquals("club1", participant.getEvents().get(participant.getEvents().size() - 1).getClubName());
    }

    @Test
    public void addingEventToParticipant2(){
        Participant participant = new Participant("participant1", "12345",
                "participant1@gmail.com", "bio");
        EventType eventType = new EventType("eventtype1");
        PropertyType properties = new PropertyType("property1", "String");
        SpecifiedProperty specifiedProperty = new SpecifiedProperty("value1", properties);
        ArrayList<SpecifiedProperty> specifiedProperties = new ArrayList<>();
        specifiedProperties.add(specifiedProperty);
        Event event = new Event("event1", eventType, specifiedProperties, "club1");

        participant.addEvent(event);

        assertEquals("eventtype1", participant.getEvents().get(participant.getEvents().size() - 1).getEventTypeName());
    }

    @Test
    public void addingEventToParticipant3(){
        Participant participant = new Participant("participant1", "12345",
                "participant1@gmail.com", "bio");
        EventType eventType = new EventType("eventtype1");
        PropertyType properties = new PropertyType("property1", "String");
        SpecifiedProperty specifiedProperty = new SpecifiedProperty("value1", properties);
        ArrayList<SpecifiedProperty> specifiedProperties = new ArrayList<>();
        specifiedProperties.add(specifiedProperty);
        Event event = new Event("event1", eventType, specifiedProperties, "club1");

        participant.addEvent(event);

        assertEquals(specifiedProperties, participant.getEvents().get(participant.getEvents().size() - 1).getSpecifiedProperties());
    }

    @Test
    public void addingEventToParticipant4(){
        Participant participant = new Participant("participant1", "12345",
                "participant1@gmail.com", "bio");
        EventType eventType = new EventType("eventtype1");
        PropertyType properties = new PropertyType("property1", "String");
        SpecifiedProperty specifiedProperty = new SpecifiedProperty("value1", properties);
        ArrayList<SpecifiedProperty> specifiedProperties = new ArrayList<>();
        specifiedProperties.add(specifiedProperty);
        Event event = new Event("event1", eventType, specifiedProperties, "club1");
        Event event2 = new Event("event2", eventType, specifiedProperties, "club2");

        participant.addEvent(event);
        participant.addEvent(event2);
        participant.removeEvent(event);

        assertEquals("club2", participant.getEvents().get(0).getClubName());
    }

    @Test
    public void addingEventToParticipant5(){
        Participant participant = new Participant("participant1", "12345",
                "participant1@gmail.com", "bio");
        EventType eventType = new EventType("eventtype1");
        PropertyType properties = new PropertyType("property1", "String");
        SpecifiedProperty specifiedProperty = new SpecifiedProperty("value1", properties);
        ArrayList<SpecifiedProperty> specifiedProperties = new ArrayList<>();
        specifiedProperties.add(specifiedProperty);
        Event event = new Event("event1", eventType, specifiedProperties, "club1");
        Event event2 = new Event("event2", eventType, specifiedProperties, "club2");

        participant.addEvent(event);
        participant.addEvent(event2);
        participant.removeEvent(event);

        assertEquals("club2", participant.getEvents().get(0).getClubName());
    }


    @Test
    public void addRatingToClub1(){
        Club club = new Club("club1", "12345", "club1@gmail.com", "bio",
                "club1", "club1", "111111111");

        club.addRating("2094", "good club", 5);

        assertEquals("good club", club.getRatings().get(club.getRatings().size() - 1).getRatingDescription());
    }

    @Test
    public void addRatingToClub2(){
        Club club = new Club("club1", "12345", "club1@gmail.com", "bio",
                "club1", "club1", "111111111");

        club.addRating("2094", "good club", 5);

        assertEquals("2094", club.getRatings().get(club.getRatings().size() - 1).getRatingID());
    }

    @Test
    public void addRatingToClub3(){
        Club club = new Club("club1", "12345", "club1@gmail.com", "bio",
                "club1", "club1", "111111111");

        club.addRating("2094", "good club", 5);

        assertEquals(5, club.getRatings().get(club.getRatings().size() - 1).getRating());
    }

    @Test
    public void addRatingToClub4(){
        Club club = new Club("club1", "12345", "club1@gmail.com", "bio",
                "club1", "club1", "111111111");

        club.addRating("2094", "good club", 5);
        club.addRating("2095", "bad club", 2);

        assertEquals("bad club", club.getRatings().get(1).getRatingDescription());
    }

    @Test
    public void addRatingToClub5(){
        Club club = new Club("club1", "12345", "club1@gmail.com", "bio",
                "club1", "club1", "111111111");

        club.addRating("2094", "good club", 5);
        club.addRating("2095", "bad club", 2);

        assertEquals("2095",  club.getRatings().get(1).getRatingID());
    }


}