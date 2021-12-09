package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

//
public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;
    private Passenger passenger;


    // Constructor
    public BookingManager() {
        this.bookingList = new ArrayList<>();
    }

    //TODO implement functionality as per specification

    // add a booking, find booking
    /*public boolean addBooking(int bookingId, int passengerId, int vehicleId, double LatitStart, double LatitEnd,
                              double LongitStart, double LongitEnd, double cost, int year, int month,
                              int day, int hour, int minute, int second)
    {
        LocationGPS startLocation = new LocationGPS(LatitStart, LongitStart);
        LocationGPS endLocation = new LocationGPS(LatitEnd, LongitEnd);
        LocalDateTime bookingDateTime = LocalDateTime.of(year, month, day, hour, minute, second);


        Booking booking = new Booking(bookingId, passengerId, vehicleId, bookingDateTime, startLocation, endLocation, cost);

        boolean found = false;
        // loop through here and check that email and password of passengers dont match
        for(Booking b: bookingList)
        {
            if(booking.equals(b))
            {
                found = true;
                return found;
            }
        }
        bookingList.add(booking);
        return found;
    }*/


/*
    public boolean addBooking(int bookingId, Vehicle checkVeh, Passenger checkPass, LocationGPS endLocation)
    {

        int passengerId = checkPass.getId();
        int vehicleId = checkVeh.getId();
        LocalDateTime now = LocalDateTime.now();


        LocationGPS startLocation = checkPass.getLocation();


        double distance = LocationGPS.distanceGPS(startLocation, endLocation);
        //System.out.println("distance : " + distance);
        String reg = checkVeh.getRegistration();
        //System.out.println("reg : " + reg);


        double cost2 = calculateCostsCorrect(checkVeh, distance);
        //System.out.println("cost : " + cost2);
        cost2 = avoidLong(cost2);

        LocationGPS depotLat = checkVeh.getDepotGPSLocation();
        double distance1 = LocationGPS.distanceGPS(depotLat, startLocation);


        //System.out.println("DepotLatitude" + depotLat);
        //System.out.println("startLocation" + startLocation);

        //System.out.println("distance : " + distance1);
        String reg1 = checkVeh.getRegistration();
        //System.out.println("reg : " + reg1);

        double cost1 = calculateCostsCorrect(checkVeh, distance1);
        System.out.println("cost : " + cost1);
        cost1 = avoidLong(cost1);

        double distance3 = LocationGPS.distanceGPS(endLocation, depotLat);
        double cost3 = calculateCostsCorrect(checkVeh, distance3);
        //System.out.println("cost : " + cost3);

        cost3 = avoidLong(cost3);

        System.out.println("distance 1 : " + distance1);
        System.out.println("distance 2 : " + distance);
        System.out.println("distance 3 : " + distance3);

        System.out.println("Cost 1 : " + cost1);
        System.out.println("Cost 2 : " + cost2);
        System.out.println("Cost 3 : " + cost3);


        Double totCost = cost1 + cost2 + cost3;
        totCost = avoidLong(totCost);
        System.out.println("total cost " + totCost);


        //totCost = (totCost * 100);
        //totCost = Math.round(totCost);

        Booking booking = new Booking(bookingId, passengerId, vehicleId, now, startLocation, endLocation, totCost);


        boolean found = false;
        // loop through here and check that email and password of passengers dont match
        for(Booking b: bookingList)
        {
            if(booking.equals(b))
            {
                found = true;
                return found;
            }
        }
        bookingList.add(booking);
        return found;
    }*/

    public void DeleteBookingById(String id)
    {
        boolean errorMsg = false;
        for(Booking b: bookingList) {

            int z = b.getBookingId();
            String c = Integer.toString(z);


            if (c.equalsIgnoreCase(id)) {
                bookingList.remove(b);
                errorMsg = true;
                System.out.println("Successfully removed booking by ID " + b.getBookingId());
                break;
            }
        }

        if(!errorMsg)
        {
            System.out.println("Delete unsuccessful, either booking doesnt exist, or wrong id entered :(");
        }
    }

    public void DisplayBookings()
    {
        System.out.println("These are the current bookings\n");
        for(Booking b: bookingList) {

            System.out.println("--------------------------------------------------------------------------------------------------------------");
            System.out.println("\t\tBooking ID : \t\t\t\t\t"  + b.getBookingId());
            System.out.println("\t\tPassenger ID : \t\t\t\t\t"  + b.getPassengerId());
            System.out.println("\t\tVehicle ID : \t\t\t\t\t"  + b.getVehicleId());
            System.out.println("\t\tBooking Date & Time : \t\t\t"  + b.getBookingDateTime());
            System.out.println("\t\tStarting Co-ordinates : \t\t"  + b.getStartLocation());
            System.out.println("\t\tDestination Co-ordinates : \t\t"  + b.getEndLocation());
            System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€"  + b.getCost());
            System.out.println("--------------------------------------------------------------------------------------------------------------");

        }
    }

    public void DisplayBookingsByPassengerId(String passengerID)
    {
        System.out.println("These are the current bookings\n");
        for(Booking b: bookingList) {

            int z = b.getPassengerId();
            String c = Integer.toString(z);

            if(c.equalsIgnoreCase(passengerID)) {
                System.out.println("--------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\tBooking ID : \t\t\t\t\t" + b.getBookingId());
                System.out.println("\t\tPassenger ID : \t\t\t\t\t" + b.getPassengerId());
                System.out.println("\t\tVehicle ID : \t\t\t\t\t" + b.getVehicleId());
                System.out.println("\t\tBooking Date & Time : \t\t\t" + b.getBookingDateTime());
                System.out.println("\t\tStarting Co-ordinates : \t\t" + b.getStartLocation());
                System.out.println("\t\tDestination Co-ordinates : \t\t" + b.getEndLocation());
                System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€" + b.getCost());
                System.out.println("--------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void DisplayBookingsByName(String name)
    {
        System.out.println("These are the current bookings\n");
        for(Booking b: bookingList) {

         //   int z = b.getPassengerId();
         //   String c = Integer.toString(z);

            passenger = passengerStore.findPassengerByName(name);

            if(passenger != null) {
                System.out.println("--------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\tBooking ID : \t\t\t\t\t" + b.getBookingId());
                System.out.println("\t\tPassenger ID : \t\t\t\t\t" + b.getPassengerId());
                System.out.println("\t\tVehicle ID : \t\t\t\t\t" + b.getVehicleId());
                System.out.println("\t\tBooking Date & Time : \t\t\t" + b.getBookingDateTime());
                System.out.println("\t\tStarting Co-ordinates : \t\t" + b.getStartLocation());
                System.out.println("\t\tDestination Co-ordinates : \t\t" + b.getEndLocation());
                System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€" + b.getCost());
                System.out.println("--------------------------------------------------------------------------------------------------------------");
            }
        }
    }


    public void DisplayBookingsByBookingId(String bookingId)
    {
        System.out.println("These are the current bookings\n");
        for(Booking b: bookingList) {

            int z = b.getBookingId();
            String c = Integer.toString(z);

            if(c.equalsIgnoreCase(bookingId)) {
                System.out.println("--------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\tBooking ID : \t\t\t\t\t" + b.getBookingId());
                System.out.println("\t\tPassenger ID : \t\t\t\t\t" + b.getPassengerId());
                System.out.println("\t\tVehicle ID : \t\t\t\t\t" + b.getVehicleId());
                System.out.println("\t\tBooking Date & Time : \t\t\t" + b.getBookingDateTime());
                System.out.println("\t\tStarting Co-ordinates : \t\t" + b.getStartLocation());
                System.out.println("\t\tDestination Co-ordinates : \t\t" + b.getEndLocation());
                System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€" + b.getCost());
                System.out.println("--------------------------------------------------------------------------------------------------------------");
            }
        }
    }


    public boolean addBooking(int bookingId, Vehicle veh, Passenger passenger, LocationGPS endLocation)
    {

        int passengerId = passenger.getId();
        int vehicleId = veh.getId();
        LocalDateTime now = LocalDateTime.now();
        LocationGPS startLocation = passenger.getLocation();
        LocationGPS depotLocation = veh.getDepotGPSLocation();

        double distance1 = LocationGPS.distanceGPS(depotLocation, startLocation);
        double distance2 = LocationGPS.distanceGPS(startLocation, endLocation);
        double distance3 = LocationGPS.distanceGPS(endLocation, depotLocation);

        double cost1 = calculateCosts(veh, distance1);
        cost1 = RoundTo2DecPoints(cost1);

        double cost2 = calculateCosts(veh, distance2);
        cost2 = RoundTo2DecPoints(cost2);

        double cost3 = calculateCosts(veh, distance3);
        cost3 = RoundTo2DecPoints(cost3);

        double totalCost = cost1 + cost2 + cost3;
        totalCost = RoundTo2DecPoints(totalCost);

        Booking booking = new Booking(bookingId, passengerId, vehicleId, now, startLocation, endLocation, totalCost);

        boolean found = false;
        // loop through here and check that email and password of passengers dont match
        for(Booking b: bookingList)
        {
            if(booking.equals(b))
            {
                found = true;
                return found;
            }
        }
        bookingList.add(booking);
        return found;
    }




    public double calculateCosts(Vehicle vehicle, double distance)
    {
        double milePrice = vehicle.getCostPerMile();
        double total = milePrice * distance;
        return total;
    }


    public double RoundTo2DecPoints(double cost)
    {
        cost = Math.round(cost * 100) / 100.0;
        return cost;
    }




    public Booking findBookingById(int bookingId)
    {
        for(Booking b: bookingList) {
            if (b.getBookingId() == bookingId) {
                return b;
            }
        }
        return null;
    }


    public void displayAllBookings()
    {
        System.out.println("These are the current bookings\n");
        for(Booking b: bookingList) {
            System.out.println(b.toString());
        }
    }

    public int assignBookingId()
    {
        int id = 0;
        int largest = 0;
        int new_load = 0;
        for (Booking b : bookingList) {
             new_load = b.getBookingId();
            if(new_load > largest)
            {
                largest = new_load;
            }
        }
        id = largest + 1;
        return id;
    }



}
