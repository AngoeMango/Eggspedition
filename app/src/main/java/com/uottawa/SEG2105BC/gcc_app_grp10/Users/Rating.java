package com.uottawa.SEG2105BC.gcc_app_grp10.Users;

public class Rating {
    String ratingID;
    String ratingDescription;
    int rating;

    public Rating(String ratingID, String ratingDescription, int rating){
        this.ratingID = ratingID;
        this.ratingDescription = ratingDescription;
        this.rating = rating;
    }

    public String getRatingID(){
        return ratingID;
    }

    public String getRatingDescription(){
        return ratingDescription;
    }

    public int getRating(){
        return rating;
    }
}
