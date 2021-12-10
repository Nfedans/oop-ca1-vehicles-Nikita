package org.example;

/** NIKITA
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 *
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 *
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 *
 */

import java.io.IOException;
import java.util.*;

public class App {
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings
    Booking booking;
    Vehicle vehicle;
    ArrayList<Vehicle> vehicles;

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }




    public void start() {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");

        // create VehicleManager, and load all vehicles from text file3

        vehicleManager = new VehicleManager("vehicles.txt");

        bookingManager = new BookingManager(passengerStore, vehicleManager);

        vehicles = vehicleManager.getVehicleList();






        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }


        //   vehicleManager.displayAllVehicles();


        //   String registration = "172LH234106";
        //   Vehicle vehicle = vehicleManager.findVehicleByReg(registration);
        //   if (vehicle == null)
        //       System.out.println("No vehicle with registration " + registration + " was found.");
        //   else
        //       System.out.println("Found Vehicle: " + vehicle.toString());

        // Create BookingManager and load all bookings from file
        // bookingManager = new BookingManager("bookings.txt");

        //pMgr.saveToFile();

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
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int EXIT = 4;

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
                        if (p == null)
                            System.out.println("No passenger matching the name \"" + name + "\"");
                        else
                            System.out.println("Found passenger: \n" + p.toString());
                        break;
                    case ADD_PASSENGER:
                        System.out.println("Add a passenger");
                        passengerStore.addPassenger();
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
                + "4. Find Vehicles by type \n"
                + "5. Exit \n"
                + "Enter Option [1,5]";

        final int SHOW_ALL = 1;
        final int FIND_BY_REG = 2;
        final int SORT_BY_REG = 3;
        final int FIND_BY_VEHI_TYPE = 4;
        final int EXIT = 5;

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
                        //vehicleManager.displayAllVehicles();
                        display(vehicleManager.getVehicleList());
                        break;
                    case FIND_BY_REG:
                        System.out.println("Find Vehicle by registration");
                        System.out.println("Enter registration number: ");
                        String reg = keyboard.nextLine();
                        Vehicle v = vehicleManager.findSingleVehicleByReg(reg);
                        if (v == null)
                            System.out.println("No vehicle matching the registration \"" + reg + "\"");
                        else
                            System.out.println("Found vehicle: \n" + v.toString());
                        break;
                    case SORT_BY_REG:
                        System.out.println("All vehicles sorted by registration:");
                        VehicleRegistrationComparator vRComparator = new VehicleRegistrationComparator();
                        Collections.sort( vehicles, vRComparator );
                        display(vehicles);
                        break;



                    case FIND_BY_VEHI_TYPE:
                        String vehiType= "";
                        String input = "";
                        System.out.println("Find Vehicle by Type");

                        while(!(vehiType.equals("v")) && !(vehiType.equals("c"))) {
                            System.out.println("Enter v for VAN  /  Enter c for CAR: ");
                            vehiType = keyboard.nextLine();
                            vehiType = vehiType.toLowerCase();
                        }
                        if(vehiType.equals("v")){
                            input = "Van";
                        }
                        else{
                            input = "Car";
                        }
                        if(vehicleManager.findVehicleByType(input) == null)
                        {
                            System.out.println("Sorry, we found nothing");
                        }
                        else {
                            display(vehicleManager.findVehicleByType(input));
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
                + "9. Exit \n"
                + "Enter Option [1,9]";

        final int SHOW_ALL = 1;
        final int ADD_A_BOOKING = 2;
        final int FIND_BOOKING = 3;
        final int SHOW_BY_PID = 4;
        final int SHOW_BY_BID = 5;
        final int SHOW_BY_NAME = 6;
        final int DEL_BY_ID = 7;
        final int EDIT_BOOKING = 8;
        final int EXIT = 9;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:

                        //System.out.println("Display ALL Bookings");
                        //bookingManager.displayAllBookings();
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

                        } /*else {
                            System.out.println("you're already in our system, excellent!");
                            vehId = vehicle.getId(checkVeh);
                            // write method to return vehicle id here
                        }*/

                        int bookingId = bookingManager.assignBookingId();

                        double endLat = 0.0;
                        double endLong = 0.0;


                        while(endLat == 0.0)
                        {
                            System.out.println("Please enter the end latitude.");
                            endLat = keyboard.nextDouble();
                        }
                        while(endLong == 0.0)
                        {
                            System.out.println("Please enter the end longitude.");
                            endLong = keyboard.nextDouble();
                        }


                        LocationGPS endLocation = new LocationGPS(endLat, endLong);

                       boolean bk = bookingManager.addBooking(bookingId, checkVeh, checkPass, endLocation);

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
                        if (b == null)
                            System.out.println("No booking matching the ID");
                        else
                            System.out.println("Found booking: \n" + b.toString());
                        break;
                    case SHOW_BY_PID:
                        //System.out.println("Please enter Passenger ID to view all related bookings");
                        //String passengerID = keyboard.nextLine();
                        //bookingManager.DisplayBookingsByPassengerId(passengerID);

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
                        System.out.println("List of bookings by passenger name");
                        System.out.println("=============================");
                        System.out.print("Enter passenger name: ");
                        String passengerName = keyboard.nextLine();
                        if(passengerStore.findPassengerByName(passengerName) == null)
                        {
                            System.out.println("Passenger doesnt exist");
                        }
                        else {
                            bookingManager.showBookingsByPassengerName(passengerName, listByName);
                            System.out.println();
                            System.out.println("List of bookings by passenger " + passengerName + " sorted by date");
                            System.out.println("====================================");
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
                        bookingManager.editBooking(bid);
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