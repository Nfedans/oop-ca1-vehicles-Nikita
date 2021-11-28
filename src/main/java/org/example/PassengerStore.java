package org.example;
//
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id  = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id,name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // TODO - see functional spec for details of code to add

   /* public boolean addPassenger(String name, String email, String phone, double latitude, double longitude)
    {
        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);
        boolean found = false;
        // loop through here and check that email and password of passengers dont match
        for(Passenger p: passengerList)
        {
            if(p.equals(passenger))
            {
                found = true;
                return found;
            }
        }
        passengerList.add(passenger);
        return found;

    }*/

    public void addPassenger()
    {
        Scanner sc = new Scanner(System.in);

        String name = "";
        String email = "";
        String phone = "";
        Double latitude = -91.0;
        Double longitude = -181.0;

        while (name.equals("")) {
            System.out.println("Enter passenger name: ");
            name = sc.nextLine();
        }

        while (email.equals("")) {
            System.out.println("Enter passenger email: ");
            email = sc.nextLine();

            for(Passenger p: passengerList) {
                if (p.getEmail().equals(email)) {
                    email = "";
                    System.out.println("Duplicate email found");
                }
            }


        }

        while (phone.equals("")) {
            System.out.println("Enter passenger phone: ");
            phone = sc.nextLine();

            for(Passenger p: passengerList) {
                if (p.getPhone().equals(phone)) {
                    phone = "";
                    System.out.println("Duplicate phone found");
                }
            }

        }

        while (latitude < -90 || latitude > 90) {
            System.out.println("Enter passenger latitude: ");
            latitude = sc.nextDouble();
        }

        while (longitude < -180 || longitude > 180) {
            System.out.println("Enter passenger longitude: ");
            longitude = sc.nextDouble();
        }

        int id = 0;
        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);
        for(Passenger p: passengerList) {
            if (p.getId() > id ) {
               id = p.getId();
            }
        }
        id++;

        passengerList.add(new Passenger(id ,name, email, phone, latitude, longitude));
    }


    //
    // Delete, edit, print methods

    public void editPassenger(String name, String email, String phone, double latitude, double longitude)
    {
        Scanner sc = new Scanner(System.in);


        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);
        boolean found = false;
        for (Passenger p: passengerList)
        {
            if(p.equals(passenger))
            {
                //Validation and/or exception handling
                System.out.println("\nPlease enter new name");
                String newName = sc.nextLine();
                if(newName != null)
                {
                    p.setName(newName);
                }
                else
                {
                    p.setName(name);
                }
                System.out.println("\nPlease enter new email");
                String newEmail = sc.nextLine();
                if(newEmail != null)
                {
                    p.setEmail(newEmail);
                }
                else
                {
                    p.setEmail(email);
                }
                System.out.println("\nPlease enter new Phone number");
                String newPhone = sc.nextLine();
                if(newPhone != null)
                {
                    p.setPhone(newPhone);
                }
                else
                {
                    p.setPhone(phone);
                }
                System.out.println("\nPlease enter latitude");
                double latit = 0.0;
                latit = sc.nextDouble();
                System.out.println("\nPlease enter longitude");
                double longit = 0.0;
                longit = sc.nextDouble();
                if(latit != 0.0 && longit != 0.0)
                {
                    p.setLocation(latit, longit);
                }
                else
                {
                    p.setLocation(latit, longit);
                }
                break;
            }
        }
    }

    public void deletePassenger(String name, String email, String phone, double latitude, double longitude )
    {
        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);
        for (Passenger p : this.passengerList) {
            if(p.equals(passenger))
            {
                passengerList.remove(p);
                break;
            }
        }
    }

    public void deletePassengerByNameAndEmail(String name, String email)
    {
        String phone = "dummy";
        double latitude = 4;
        double longitude = 4;
        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);
        for (Passenger p : this.passengerList) {
            if(p.getName().equals(passenger.getName()) && p.getEmail().equals(passenger.getEmail()))
            {
                passengerList.remove(p);
                break;
            }
        }
    }

    public Passenger findPassengerByName(String name)
    {
        String email = "dummy";
        String phone = "dummy";
        double latitude = 4;
        double longitude = 4;
        Passenger passenger = new Passenger(name, email, phone, latitude, longitude);
        for(Passenger p: passengerList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

} // end class
