package com.ideal.apps.ici.api;

public class Constants {
    //public static final String URL = "http://localhost:3000";
    public static final String URL = "http://10.33.105.146:3000";

    public static class Headers {
        public static final String USER_TOKEN = "x-ici-user-token";
    }

    public static class Parameters {
        public static final String PLACE_RADIUS = "radius";
        public static final String PLACE_LATITUDE = "lat";
        public static final String PLACE_LONGITUDE = "lng";
        public static final String PLACE_CATEGORY = "category";
    }

    public static class Path {
        public static final String PLACE_ID = "placeId";
    }
}
