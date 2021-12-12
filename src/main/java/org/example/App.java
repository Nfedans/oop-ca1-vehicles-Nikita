package org.example;

/** NIKITA FEDANS SD2A*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class App {
    PassengerStore passengerStore;
    VehicleManager vehicleManager;
    BookingManager bookingManager;
    Booking booking;
    Vehicle vehicle;
    ArrayList<Vehicle> vehicles;
    EmailManager emailManager;
    Passenger passenger;

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        passengerStore = new PassengerStore("pass.txt");
        vehicleManager = new VehicleManager("v.txt");
        bookingManager = new BookingManager("b.txt",passengerStore, vehicleManager, passenger, booking);
        vehicles = vehicleManager.getVehicleList();
        emailManager = new EmailManager(passengerStore, bookingManager);
        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;
                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVehicleMenu();
                        break;
                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        displayBookingMenu();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        passengerStore.writePassengerDataToFile("pass.txt");
                        vehicleManager.writeVehicleDataToFile("v.txt");
                        bookingManager.writeBookingDataToFile("b.txt");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add a Passenger\n"
                + "4. Delete a Passenger by Name and email\n"
                + "5. Edit a Passenger\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int DEL_PASSENGER = 4;
        final int EDIT_PASSENGER = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if (p == null) {
                            System.out.println("No passenger matching the name \"" + name + "\"");
                        }
                        else {
                            System.out.println("Found passenger: \n");

                            System.out.println("----------------------------------------------------------------------------------------------------------------------");
                            System.out.println("\t\tPassenger ID : \t\t\t\t\t\t"  + p.getId());
                            System.out.println("\t\tPassenger Name : \t\t\t\t\t"  + p.getName());
                            System.out.println("\t\tPassenger Email : \t\t\t\t\t"  + p.getEmail());
                            System.out.println("\t\tPassenger Phone : \t\t\t\t\t"  + p.getPhone());
                            System.out.println("\t\tLocation : \t\t\t\t\t\t\t"  + p.getLocation());
                            System.out.println("----------------------------------------------------------------------------------------------------------------------");
                        }
                        break;
                    case ADD_PASSENGER:
                        System.out.println("Add a passenger");
                        passengerStore.addPassenger();
                        break;
                    case DEL_PASSENGER:
                        System.out.println("Enter Name of Passenger you wish to delete");
                        String delName = keyboard.nextLine();
                        System.out.println("Enter email of Passenger you wish to delete");
                        String delEmail = keyboard.nextLine();

                        boolean validate = passengerStore.deletePassengerByNameAndEmail(delName, delEmail);

                        if(validate)
                        {
                            System.out.println("Deleted " + delName + " from records successfully!");
                        }
                        else
                        {
                            System.out.println("either the record didn't exist or name / email was wrong. Try again.");
                        }
                        break;
                    case EDIT_PASSENGER:
                        System.out.println("Please enter ID of Passenger you'd like to edit");
                        String pID = keyboard.nextLine();
                        passengerStore.editPassenger(pID);
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


    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** VEHICLE MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by registration\n"
                + "3. Sort and view vehicles by registration \n"
                + "4. Filter Vehicles by type \n"
                + "5. Filter Cars by number of seats \n"
                + "6. Exit \n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int SORT_BY_REG = 3;
        final int FILTER_BY_VEHI_TYPE = 4;
        final int FILTER_BY_NUM_SEATS = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Vehicles");

                        ArrayList<Vehicle> vList = vehicleManager.getVehicleList();

                        for(Vehicle veh: vList)
                        {
                        System.out.println("----------------------------------------------------------------------------------------------------");
                        System.out.println("\t\tVehicle ID : \t\t\t\t" + veh.getId());
                        System.out.println("\t\tVehicle Type : \t\t\t\t" + veh.getType());
                        System.out.println("\t\tVehicle Make : \t\t\t\t" + veh.getMake());
                        System.out.println("\t\tVehicle Model : \t\t\t" + veh.getModel());
                        System.out.println("\t\tVehicle registration : \t\t" + veh.getRegistration());
                        System.out.println("\t\tVehicle cost per mile : \t€" + veh.getCostPerMile());
                        System.out.println("\t\tLast service date : \t\t" + veh.getLastServicedDate());
                        System.out.println("\t\tVehicle Mileage : \t\t\t" + veh.getMileage());
                        System.out.println("\t\tVehicle Location : \t\t\t" + veh.getDepotGPSLocation());

                        if(veh instanceof Car)
                        {

                             System.out.println("\t\tNumber of seats : \t\t\t" + ((Car)veh).getNumSeats());
                             System.out.println("----------------------------------------------------------------------------------------------------");
                        }
                        else
                        {
                            System.out.println("\t\tLoad Space : \t\t\t\t" + ((Van)veh).getLoadSpace());
                            System.out.println("----------------------------------------------------------------------------------------------------");
                        }
                        }
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicle by registration");
                        System.out.println("Enter registration number: ");
                        String reg = keyboard.nextLine();
                        Vehicle v = vehicleManager.findSingleVehicleByReg(reg);
                        if (v == null) {
                            System.out.println("No vehicle matching the registration \"" + reg + "\"");
                        }
                        else {
                            System.out.println("Found vehicle: \n");

                            System.out.println("----------------------------------------------------------------------------------------------------");
                            System.out.println("\t\tVehicle ID : \t\t\t\t" + v.getId());
                            System.out.println("\t\tVehicle Type : \t\t\t\t" + v.getType());
                            System.out.println("\t\tVehicle Make : \t\t\t\t" + v.getMake());
                            System.out.println("\t\tVehicle Model : \t\t\t" + v.getModel());
                            System.out.println("\t\tVehicle registration : \t\t" + v.getRegistration());
                            System.out.println("\t\tVehicle cost per mile : \t€" + v.getCostPerMile());
                            System.out.println("\t\tLast service date : \t\t" + v.getLastServicedDate());
                            System.out.println("\t\tVehicle Mileage : \t\t\t" + v.getMileage());
                            System.out.println("\t\tVehicle Location : \t\t\t" + v.getDepotGPSLocation());

                            if(v instanceof Car)
                            {

                                 System.out.println("\t\tNumber of seats : \t\t\t" + ((Car)v).getNumSeats());
                                 System.out.println("----------------------------------------------------------------------------------------------------");
                            }
                            else
                            {
                                System.out.println("\t\tLoad Space : \t\t\t\t" + ((Van)v).getLoadSpace());
                                System.out.println("----------------------------------------------------------------------------------------------------");
                            }
                        }
                        break;
                    case SORT_BY_REG:
                        System.out.println("All vehicles sorted by registration:");
                        VehicleRegistrationComparator vRComparator = new VehicleRegistrationComparator();
                        Collections.sort( vehicles, vRComparator );

                        for(Vehicle veh: vehicles)                                                                                                                        
                        {
                        System.out.println("----------------------------------------------------------------------------------------------------");
                        System.out.println("\t\tVehicle ID : \t\t\t\t" + veh.getId());
                        System.out.println("\t\tVehicle Type : \t\t\t\t" + veh.getType());
                        System.out.println("\t\tVehicle Make : \t\t\t\t" + veh.getMake());
                        System.out.println("\t\tVehicle Model : \t\t\t" + veh.getModel());
                        System.out.println("\t\tVehicle registration : \t\t" + veh.getRegistration());
                        System.out.println("\t\tVehicle cost per mile : \t€" + veh.getCostPerMile());
                        System.out.println("\t\tLast service date : \t\t" + veh.getLastServicedDate());
                        System.out.println("\t\tVehicle Mileage : \t\t\t" + veh.getMileage());
                        System.out.println("\t\tVehicle Location : \t\t\t" + veh.getDepotGPSLocation());

                        if(veh instanceof Car)
                        {

                             System.out.println("\t\tNumber of seats : \t\t\t" + ((Car)veh).getNumSeats());
                             System.out.println("----------------------------------------------------------------------------------------------------");
                        }
                        else
                        {
                            System.out.println("\t\tLoad Space : \t\t\t\t" + ((Van)veh).getLoadSpace());
                            System.out.println("----------------------------------------------------------------------------------------------------");
                        }
                        }
                        break;
                    case FILTER_BY_VEHI_TYPE:
                           System.out.println("Input the type of vehicle you'd like to filter by: (Car / 4x4 / Truck / Van)");
                           String typeFilter = keyboard.nextLine();
                           List<Vehicle> typeList = vehicleManager.filterBy(new VehicleTypeFilter(typeFilter));

                           if(typeList != null) {
                               for (Vehicle veh : typeList) {
                                   System.out.println("----------------------------------------------------------------------------------------------------");
                                   System.out.println("\t\tVehicle ID : \t\t\t\t" + veh.getId());
                                   System.out.println("\t\tVehicle Type : \t\t\t\t" + veh.getType());
                                   System.out.println("\t\tVehicle Make : \t\t\t\t" + veh.getMake());
                                   System.out.println("\t\tVehicle Model : \t\t\t" + veh.getModel());
                                   System.out.println("\t\tVehicle registration : \t\t" + veh.getRegistration());
                                   System.out.println("\t\tVehicle cost per mile : \t€" + veh.getCostPerMile());
                                   System.out.println("\t\tLast service date : \t\t" + veh.getLastServicedDate());
                                   System.out.println("\t\tVehicle Mileage : \t\t\t" + veh.getMileage());
                                   System.out.println("\t\tVehicle Location : \t\t\t" + veh.getDepotGPSLocation());

                                   if(typeFilter.equalsIgnoreCase("car") || typeFilter.equalsIgnoreCase("4x4") )
                                   {
                                        System.out.println("\t\tNumber of seats : \t\t\t" + ((Car)veh).getNumSeats());
                                        System.out.println("----------------------------------------------------------------------------------------------------");
                                   }
                                   else
                                   {
                                       System.out.println("\t\tLoad Space : \t\t\t\t" + ((Van)veh).getLoadSpace());
                                       System.out.println("----------------------------------------------------------------------------------------------------");
                                   }
                               }
                           }
                        break;
                    case FILTER_BY_NUM_SEATS:

                    System.out.println("Input the number of seats you'd like to filter cars by:");
                    String numberSeats = keyboard.nextLine();
                    numberSeats = numberSeats + ".0";
                    List<Vehicle> carList = vehicleManager.filterBy(new NumSeatFilter(numberSeats));

                    if(carList != null) {
                        for (Vehicle veh : carList) {
                            System.out.println("----------------------------------------------------------------------------------------------------");
                            System.out.println("\t\tVehicle ID : \t\t\t\t" + veh.getId());
                            System.out.println("\t\tVehicle Type : \t\t\t\t" + veh.getType());
                            System.out.println("\t\tVehicle Make : \t\t\t\t" + veh.getMake());
                            System.out.println("\t\tVehicle Model : \t\t\t" + veh.getModel());
                            System.out.println("\t\tVehicle registration : \t\t" + veh.getRegistration());
                            System.out.println("\t\tVehicle cost per mile : \t€" + veh.getCostPerMile());
                            System.out.println("\t\tLast service date : \t\t" + veh.getLastServicedDate());
                            System.out.println("\t\tVehicle Mileage : \t\t\t" + veh.getMileage());
                            System.out.println("\t\tVehicle Location : \t\t\t" + veh.getDepotGPSLocation());
                            System.out.println("\t\tNumber of seats : \t\t\t" + ((Car)veh).getNumSeats());
                            System.out.println("----------------------------------------------------------------------------------------------------");
                        }
                    }
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
    private void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Add a Booking\n"
                + "3. Find Booking \n"
                + "4. Show bookings by Passenger ID \n"
                + "5. Show bookings by Booking ID \n"
                + "6. Show bookings by Passenger Name \n"
                + "7. Delete Booking by ID \n"
                + "8. Edit Booking by ID \n"
                + "9. Show all current bookings \n"
                + "10. Filter bookings by year \n"
                + "11. Get average length of booking journeys \n"
                + "12. View mailing list \n"
                + "13. Exit \n"
                + "Enter Option [1,13]";

        final int SHOW_ALL = 1;
        final int ADD_A_BOOKING = 2;
        final int FIND_BOOKING = 3;
        final int SHOW_BY_PID = 4;
        final int SHOW_BY_BID = 5;
        final int SHOW_BY_NAME = 6;
        final int DEL_BY_ID = 7;
        final int EDIT_BOOKING = 8;
        final int SHOW_CURRENT = 9;
        final int SHOW_FILTERED_BY_YEAR = 10;
        final int GET_AVG = 11;
        final int VIEW_MAIL_LIST = 12;
        final int EXIT = 13;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        bookingManager.DisplayBookings();
                        break;
                    case ADD_A_BOOKING:

                        System.out.println("Add a booking");
                        System.out.println("Please enter your name");
                        String nameForBooking = keyboard.nextLine();

                        Passenger checkPass = passengerStore.findPassengerByName(nameForBooking);
                        if(checkPass == null){
                            System.out.println("We did not find you in our records, please create a profile!");
                            passengerStore.addPassenger();
                            checkPass = passengerStore.findPassengerByName(nameForBooking);
                        }
                        else{
                            System.out.println("you're already in our system, excellent!");
                        }
                            Vehicle checkVeh = null;
                            String vehId = "";

                        if (checkVeh == null) {
                            while(checkVeh == null) {
                                System.out.println("Please enter the registration of the vehicle you'd like to rent.");
                                String regForBooking = keyboard.nextLine();
                                checkVeh = vehicleManager.findSingleVehicleByReg(regForBooking);
                            }

                        }

                        int bookingId = bookingManager.assignBookingId();

                        double endLat = -100.0;
                        double endLong = -200.0;

                        while(endLat == -100.0)
                        {
                            System.out.println("Please enter the end latitude.");
                            try {
                                endLat = keyboard.nextDouble();
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Please enter a value between -90 and 90");
                                keyboard.next();
                            }
                        }
                        while(endLong == -200.0)
                        {
                            System.out.println("Please enter the end longitude.");
                            try {
                                endLong = keyboard.nextDouble();
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Please enter a value between -180 and 180");
                                keyboard.next();
                            }
                        }

                        LocationGPS endLocation = new LocationGPS(endLat, endLong);

                       boolean bk = bookingManager.addBooking(bookingId, checkVeh, checkPass, endLocation);

                       String idForEmail = Integer.toString(bookingId);
                       emailManager.addEmail(idForEmail, checkPass);

                       if (bk == false)
                       {
                           System.out.println("\n Your Booking Has Been Added Successfully");
                       }
                       else
                       {
                           System.out.println("\n We have found a duplicate booking!");
                       }
                        break;
                    case FIND_BOOKING:
                        System.out.println("Find a booking, please enter ID number");
                        String BookId = keyboard.nextLine();
                        keyboard.nextLine();
                        Booking b = bookingManager.findBookingById(BookId);
                        if (b == null) {
                            System.out.println("No booking matching the ID");
                        }
                        else {
                            System.out.println("Found booking: \n");
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
                        break;
                    case SHOW_BY_PID:

                        ArrayList<Booking> listByPassID = new ArrayList<>();
                        System.out.println("List of bookings by passenger ID");
                        System.out.println("=============================");
                        System.out.print("Enter passenger id: ");
                        String passengerID = keyboard.nextLine();
                        if(passengerStore.findPassengerById(passengerID) == null)
                        {
                            System.out.println("Passenger doesnt exist");
                        }
                        else {
                            bookingManager.DisplayBookingsByPassengerId(passengerID, listByPassID);
                            System.out.println();
                            System.out.println("List of bookings by passenger " + passengerID + " sorted by date");
                            System.out.println("====================================");
                            for (Booking bkng : listByPassID) {
                                System.out.println("--------------------------------------------------------------------------------------------------------------");
                                System.out.println("\t\tBooking ID : \t\t\t\t\t" + bkng.getBookingId());
                                System.out.println("\t\tPassenger ID : \t\t\t\t\t" + bkng.getPassengerId());
                                System.out.println("\t\tVehicle ID : \t\t\t\t\t" + bkng.getVehicleId());
                                System.out.println("\t\tBooking Date & Time : \t\t\t" + bkng.getBookingDateTime());
                                System.out.println("\t\tStarting Co-ordinates : \t\t" + bkng.getStartLocation());
                                System.out.println("\t\tDestination Co-ordinates : \t\t" + bkng.getEndLocation());
                                System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€" + bkng.getCost());
                                System.out.println("--------------------------------------------------------------------------------------------------------------");
                            }
                        }
                        break;
                    case SHOW_BY_BID:
                        System.out.println("Please enter Booking ID to view all related bookings");
                        String bookingID = keyboard.nextLine();
                        bookingManager.DisplayBookingsByBookingId(bookingID);
                        break;
                    case SHOW_BY_NAME:
                        ArrayList<Booking> listByName = new ArrayList<>();
                        DateComparator dc = new DateComparator();
                        System.out.println("List of bookings by passenger name");
                        System.out.println("============================================");
                        System.out.print("Enter passenger name: ");
                        String passengerName = keyboard.nextLine();
                        if(passengerStore.findPassengerByName(passengerName) == null)
                        {
                            System.out.println("Passenger doesnt exist");
                        }
                        else {
                            bookingManager.showBookingsByPassengerName(passengerName, listByName);
                            Collections.sort(listByName, dc);
                            System.out.println();
                            System.out.println("List of bookings by passenger " + passengerName + " sorted by date");
                            System.out.println("============================================");
                            for (Booking bkng : listByName) {
                                System.out.println("--------------------------------------------------------------------------------------------------------------");
                                System.out.println("\t\tBooking ID : \t\t\t\t\t" + bkng.getBookingId());
                                System.out.println("\t\tPassenger ID : \t\t\t\t\t" + bkng.getPassengerId());
                                System.out.println("\t\tVehicle ID : \t\t\t\t\t" + bkng.getVehicleId());
                                System.out.println("\t\tBooking Date & Time : \t\t\t" + bkng.getBookingDateTime());
                                System.out.println("\t\tStarting Co-ordinates : \t\t" + bkng.getStartLocation());
                                System.out.println("\t\tDestination Co-ordinates : \t\t" + bkng.getEndLocation());
                                System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€" + bkng.getCost());
                                System.out.println("--------------------------------------------------------------------------------------------------------------");
                            }
                        }
                        break;
                    case DEL_BY_ID:
                        System.out.println("Please enter ID of booking you'd like to delete");
                        String id = keyboard.nextLine();
                        bookingManager.DeleteBookingById(id);
                        break;
                    case EDIT_BOOKING:
                        System.out.println("Please enter ID of booking you'd like to edit");
                        String bid = keyboard.nextLine();

                        Booking validateBooking = bookingManager.findBookingById(bid);
                        
                        if(validateBooking != null) {
                            bookingManager.editBooking(bid);
                        }
                        else
                        {
                            System.out.println("No such booking  by ID: " + bid + " exists, try again.");
                        }
                        break;
                    case SHOW_CURRENT:
                         ArrayList<Booking> listCurrent = new ArrayList<>();
                         System.out.println("List of current bookings");
                         bookingManager.getCurrentBookings(listCurrent);

                         DateComparator dateComp = new DateComparator();
                         Collections.sort(listCurrent, dateComp);

                         for (Booking bkng : listCurrent) {
                             System.out.println("--------------------------------------------------------------------------------------------------------------");
                             System.out.println("\t\tBooking ID : \t\t\t\t\t" + bkng.getBookingId());
                             System.out.println("\t\tPassenger ID : \t\t\t\t\t" + bkng.getPassengerId());
                             System.out.println("\t\tVehicle ID : \t\t\t\t\t" + bkng.getVehicleId());
                             System.out.println("\t\tBooking Date & Time : \t\t\t" + bkng.getBookingDateTime());
                             System.out.println("\t\tStarting Co-ordinates : \t\t" + bkng.getStartLocation());
                             System.out.println("\t\tDestination Co-ordinates : \t\t" + bkng.getEndLocation());
                             System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€" + bkng.getCost());
                             System.out.println("--------------------------------------------------------------------------------------------------------------");
                         }
                         break;
                    case SHOW_FILTERED_BY_YEAR:

                              System.out.println("Type in year you'd like to filter by: ");
                              String yrFilter = keyboard.nextLine();
                              List<Booking> bkList = bookingManager.filterBy(new BookingYearFilter(yrFilter));

                              if(bkList != null) {
                                  
                              DateComparator yc = new DateComparator();

                              Collections.sort(bkList, yc);

                                  for (Booking bkng : bkList) {
                                      System.out.println("--------------------------------------------------------------------------------------------------------------");
                                      System.out.println("\t\tBooking ID : \t\t\t\t\t" + bkng.getBookingId());
                                      System.out.println("\t\tPassenger ID : \t\t\t\t\t" + bkng.getPassengerId());
                                      System.out.println("\t\tVehicle ID : \t\t\t\t\t" + bkng.getVehicleId());
                                      System.out.println("\t\tBooking Date & Time : \t\t\t" + bkng.getBookingDateTime());
                                      System.out.println("\t\tStarting Co-ordinates : \t\t" + bkng.getStartLocation());
                                      System.out.println("\t\tDestination Co-ordinates : \t\t" + bkng.getEndLocation());
                                      System.out.println("\n\t\tTotal Cost : \t\t\t\t\t€" + bkng.getCost());
                                      System.out.println("--------------------------------------------------------------------------------------------------------------");
                                  }
                              }
                        break;
                    case GET_AVG:
                         double avg = bookingManager.getAvg();
                         double avgRounded = bookingManager.RoundTo2DecPoints(avg);
                        System.out.println("\n\nThis is the average length of booking journeys : " + avgRounded);
                        break;
                    case VIEW_MAIL_LIST:
                        System.out.println("Here's the current mailing list: (only new bookings generate emails)");
                        ArrayList<Email> mailingList = emailManager.getMailingList();
                        if(mailingList != null) {
                            for (Email ml : mailingList) {
                                String res = ml.toString();
                                System.out.println(res);
                            }
                        }
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

    public void display( ArrayList<Vehicle> vehicles )
    {
        for (Vehicle v: vehicles) { System.out.println(v); }
    }

}