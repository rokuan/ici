package com.ideal.apps.ici.model;


import java.util.ArrayList;
import java.util.List;

public class Place {
    public enum Category {
        FOOD,
        CLOTHES,
        MARKET
    }

    public enum Status {
        NEW,
        OPEN,
        CLOSED,
        CLOSING,
        UNDER_CONSTRUCTION
    }

    static public class Location {
        public double lat;
        public double lng;

        public double getLatitude(){
            return lat;
        }

        public double getLongitude(){
            return lng;
        }
    }

    private String id;
    private String name;
    private Location location;
    private Status status;
    private Category category;
    private boolean open = false;
    private List<Event> events = new ArrayList<>();
}
