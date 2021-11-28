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

public class App_old_version
{
    public static void main(String[] args)
    {
        System.out.println("\nWelcome to the VEHICLE BOOKINGS MANAGEMENT SYSTEM - CA1 for OOP\n");

        // create PassengerStore and load it with passenger records from text file
        PassengerStore passengerStore = new PassengerStore("passengers.txt");
        System.out.println("List of all passengers:");
        passengerStore.displayAllPassengers();

       /* boolean valPassenger = passengerStore.addPassenger("John Smith", "john.smith@gmail.com", "4569374",6.7, 5.8);
            if(valPassenger)
            {
                System.out.println("duplicate found");
            }
            else
            {
                System.out.println("New passenger added to arraylist");
            }*/

        passengerStore.editPassenger("John Smith", "john.smith@gmail.com", "085 555 5555",8.1, 4.6);
        passengerStore.displayAllPassengers();

        passengerStore.deletePassengerByNameAndEmail("Jackson", "jacksonboy@gmail.com");
        passengerStore.displayAllPassengers();

        VehicleManager vehicleManager = new VehicleManager("vehicles.txt");
        System.out.println("List of all Vehicles:");
        vehicleManager.displayAllVehicles();

        Vehicle vehicle = vehicleManager.findSingleVehicleByReg("151D95g105");
        if(vehicle != null)
        {
            System.out.println("Vehicle was found in arraylist");
        }
        else
        {
            System.out.println("No Vehicle found in arraylist");
        }

        BookingManager bookingManager = new BookingManager();
        boolean valBooking = bookingManager.addBooking(500, 104, 107,  5.8, 9.7, 6.6, 5,9.3, 2020, 11, 23, 14, 55, 23);
        if(valBooking)
        {
            System.out.println("duplicate found of Booking");
        }
        else
        {
            System.out.println("New booking added");
        }


        Booking bookingValidator = bookingManager.findBookingById(500);
        if (bookingValidator != null)
        {
            System.out.println("Booking found ");
        }
        else
        {
            System.out.println("No booking found ");
        }



        System.out.println("Program exiting... Goodbye");
    }
}
