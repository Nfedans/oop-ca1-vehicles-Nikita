package org.example;

// Class that wraps the latitude and longitude of a location
// into one object for convenience.
//
public class LocationGPS
{
    private double latitude;
    private double longitude;

    public LocationGPS(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude()
    {
        return latitude;
    }
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
    public double getLongitude()
    {
        return longitude;
    }
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }


    public static double distanceGPS(LocationGPS startLoc, LocationGPS endLoc)
    {

        double latitude1 = startLoc.getLatitude();
        double latitude2 = endLoc.getLatitude();
        double longitude1 = startLoc.getLongitude();
        double longitude2 = endLoc.getLongitude();


        longitude1 = Math.toRadians(longitude1);
        longitude2 = Math.toRadians(longitude2);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        // Haversine formula
        double distLat = latitude2 - latitude1;
        double distLon = longitude2 - longitude1;
        double a = Math.pow(Math.sin(distLat / 2), 2)
                + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.pow(Math.sin(distLon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in km
        double radiusEarth = 3956;

        // calculate and format result to 3 dec places
        double result = (c * radiusEarth * 1000);
        result = Math.round(result);
        result = result / 1000;

        return result;
    }


    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "{latitude=" + latitude + ", longitude=" + longitude + "}";
    }



}
