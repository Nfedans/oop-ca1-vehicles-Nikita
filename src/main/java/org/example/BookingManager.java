package org.example;

import java.time.DateTimeException;
import java.time.LocalDateTime;
/*import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

//
public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;
    //private Passenger passenger;


    // Constructor
    public BookingManager( PassengerStore ps, VehicleManager vm) {
        this.bookingList = new ArrayList<>();
        passengerStore = ps;
        vehicleManager = vm;
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

    /*public void DisplayBookingsByPassengerId(String passengerID)
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
    }*/



    public void DisplayBookingsByPassengerId(String passengerID, ArrayList<Booking> list)
    {
            for(Booking b: bookingList )
            {
                int a = b.getPassengerId();
                String c = Integer.toString(a);

                if(c.equalsIgnoreCase(passengerID))
                {
                    list.add(b);
                }
            }

        }




    public void showBookingsByPassengerName(String passengerName, ArrayList<Booking> list){
        Passenger x = passengerStore.findPassengerByName(passengerName);
        int id = x.getId();

        if(x!=null)
        {
            for(Booking b: bookingList )
            {
                if(b.getPassengerId() == id)
                {
                    list.add(b);
                }
            }

        }
    }


    public void DisplayBookingsByName(String name)
    {
        System.out.println("These are the current bookings\n");
        Passenger x = passengerStore.findPassengerByName(name);

        if (x == null)
        {
            System.out.println("No passenger matching the name \"" + name + "\"");
        }
        else {
            System.out.println("Found passenger: \n" + x);


            for(Booking b: bookingList) {

               int z = b.getPassengerId();
               String c = Integer.toString(z);

            if(x != null) {
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



    public void editBooking(String bookingId)
    {
        Booking bk = findBookingById(bookingId);
        DisplayBookingsByBookingId(bookingId);
        modMenu(bk);

    }

    public void modMenu(Booking booking) {
        final String MENU_ITEMS = "\n*** EDIT MENU ***\n"
                + "1. Vehicle ID\n"
                + "2. Date and Time of booking\n"
                + "3. Starting Co-ordinates \n"
                + "4. Destination Co-ordinates \n"
                + "5. Exit \n"
                + "Enter Option [1,5]";

        final int EDIT_VID = 1;
        final int EDIT_DATE = 2;
        final int EDIT_START = 3;
        final int EDIT_DEST = 4;
        final int EXIT = 5;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case EDIT_VID:
                        System.out.println("This is the current Vehicle Id : " + booking.getVehicleId());
                        System.out.println("Please set the new Vehicle ID: ");
                        String vID = keyboard.nextLine();
                        Vehicle v = vehicleManager.findSingleVehicleById(vID);
                        if(v != null) {
                            booking.setVehicleId(vID);
                            System.out.println("Vehicle ID edit was successful!");
                        }
                        else
                        {
                            System.out.println("Vehicle ID edit was unsuccessful!");
                        }
                        break;
                    case EDIT_DATE:
                        System.out.println("This is the current Date & Time: " + booking.getBookingDateTime());
                        System.out.println("Press 1 to edit / Press any other button to exit");
                        String eDateTime = keyboard.nextLine();
                        if(eDateTime.equalsIgnoreCase("1"))
                        {
                            System.out.println("press 1 to edit Year");
                            System.out.println("press 2 to edit Month");
                            System.out.println("press 3 to edit Day");
                            String pickEdit = keyboard.nextLine();
                            if(pickEdit.equalsIgnoreCase("1"))
                            {
                                int yr = 0;
                                System.out.println("Please enter year in the format **** e.g. 2018");

                                try {
                                    yr = keyboard.nextInt();
                                }
                                catch(InputMismatchException e)
                                {
                                    System.out.println("Try again");
                                }
                                catch(DateTimeException e)
                                {
                                    System.out.println("Try again");
                                }



                                    if(yr >= 2016 && yr <= 2026){
                                        dealWithYear(booking, yr);
                                    }
                                    else
                                    {
                                        System.out.println("Please try again, 2016 - 2026 is allowed ");
                                    }


                            }
                            else if(pickEdit.equalsIgnoreCase("2"))
                            {
                                int mth = 0;
                                while (mth < 1 || mth > 12) {
                                    //mth < 1 && mth > 12
                                    System.out.println("Please enter Month in the format 1-12 e.g. 2 = February");
                                    try {
                                        mth = keyboard.nextInt();
                                    }
                                    catch(InputMismatchException e)
                                    {
                                        System.out.println("Try again");
                                        keyboard.next();
                                    }
                                    catch(DateTimeException e)
                                    {
                                        System.out.println("Try again");
                                        keyboard.next();
                                    }

                                }
                                dealWithMonth(booking, mth);
                            }
                            else if(pickEdit.equalsIgnoreCase("3"))
                            {

                                System.out.println("Please enter day of month");
                                int day = 0;
                                try {
                                    day = keyboard.nextInt();
                                }
                                catch (DateTimeException e)
                                {
                                    System.out.println("Please make sure you have your date right");
                                    keyboard.next();
                                }
                                catch(InputMismatchException e)
                                {
                                    System.out.println("Try again");
                                    keyboard.next();
                                }
                                    if(day >= 1 && day <= 31){
                                        dealWithDay(booking, day);
                                    }
                                    else
                                    {
                                        System.out.println("Please try again, 1 - 31 is allowed");
                                    }
                            }
                            else
                            {
                                System.out.println("Please read instructions on screen and try again");
                            }
                        }
                        break;
                    case EDIT_START:
                        //System.out.println("This is the current Start Location: " + booking.getLocation());
                        //System.out.println("Please set the new Vehicle ID: ");
                        //String vID = keyboard.nextLine();
                        //Vehicle v = vehicleManager.findSingleVehicleById(vID);
                        //if(v != null) {
                        //    booking.setVehicleId(vID);
                        //    System.out.println("Vehicle ID edit was successful!");
                        //}
                        //else
                        //{
                        //   System.out.println("Vehicle ID edit was unsuccessful!");
                        //}
                        break;
                    case EDIT_DEST:

                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid option - please enter number in range");
        }
    } while (option != EXIT);

}


    public void dealWithYear(Booking booking, int year)
    {
        LocalDateTime bookingTimeData = booking.getBookingDateTime();
        int month = bookingTimeData.getMonthValue();
        int day = bookingTimeData.getDayOfMonth();
        int hour = bookingTimeData.getHour();
        int minute = bookingTimeData.getMinute();
        int second = bookingTimeData.getSecond();
        int nano = bookingTimeData.getNano();

        LocalDateTime updated = LocalDateTime.of(year, month, day, hour, minute, second, nano);

        booking.setBookingDateTime(updated);

        System.out.println("Year successfully updated!");
    }


    public void dealWithMonth(Booking booking, int month)
    {
        LocalDateTime bookingTimeData = booking.getBookingDateTime();
        int year = bookingTimeData.getYear();
        int day = bookingTimeData.getDayOfMonth();
        int hour = bookingTimeData.getHour();
        int minute = bookingTimeData.getMinute();
        int second = bookingTimeData.getSecond();
        int nano = bookingTimeData.getNano();

        LocalDateTime updated = LocalDateTime.of(year, month, day, hour, minute, second, nano);

        booking.setBookingDateTime(updated);

        System.out.println("Month successfully updated!");
    }

    public void dealWithDay(Booking booking, int day)
    {
        LocalDateTime bookingTimeData = booking.getBookingDateTime();
        int year = bookingTimeData.getYear();
        int month = bookingTimeData.getMonthValue();
        int hour = bookingTimeData.getHour();
        int minute = bookingTimeData.getMinute();
        int second = bookingTimeData.getSecond();
        int nano = bookingTimeData.getNano();

        LocalDateTime updated = LocalDateTime.of(year, month, day, hour, minute, second, nano);

        booking.setBookingDateTime(updated);

        System.out.println("day successfully updated!");
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



// Make this one work with string inputs
    public Booking findBookingById(String bookingId)
    {
        for(Booking b: bookingList) {

            int z = b.getBookingId();
            String c = Integer.toString(z);

            if (c.equalsIgnoreCase(bookingId)) {
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
