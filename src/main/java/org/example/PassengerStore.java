package org.example;
//
import java.io.*;
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
            //System.out.println(p.toString());

            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("\t\tPassenger ID : \t\t\t\t\t\t"  + p.getId());
            System.out.println("\t\tPassenger Name : \t\t\t\t\t"  + p.getName());
            System.out.println("\t\tPassenger Email : \t\t\t\t\t"  + p.getEmail());
            System.out.println("\t\tPassenger Phone : \t\t\t\t\t"  + p.getPhone());
            System.out.println("\t\tLocation : \t\t\t\t\t\t\t"  + p.getLocation());
            System.out.println("----------------------------------------------------------------------------------------------------------------------");


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

    public void writePassengerDataToFile(String fileName) throws FileNotFoundException {

        // create a print writer class
        PrintWriter pw = new PrintWriter(fileName);


        for (Passenger p : this.passengerList) {
            pw.print(p.getId() + ",");
            pw.print(p.getName() + ",");
            pw.print(p.getEmail() + ",");
            pw.print(p.getPhone() + ",");

            LocationGPS passLoc = p.getLocation();

            pw.print(passLoc.getLatitude() + ",");
            pw.print(passLoc.getLongitude() + "\n");
        }

        pw.close();
    }


    public void addPassenger()
    {
        Scanner sc = new Scanner(System.in);

        String name = "";
        String email = "";
        String phone = "";
        Double startLatitude = -91.0;
        Double startLongitude = -181.0;
        Double endLatitude = -91.0;
        Double endLongitude = -181.0;

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

        while (startLatitude < -90 || startLatitude > 90) {
            System.out.println("Enter passenger start latitude (range: -90 to 90): ");

            try {
                startLatitude = sc.nextDouble();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a floating point value");
                sc.next();
            }
        }

        while (startLongitude < -180 || startLongitude > 180) {
            System.out.println("Enter passenger longitude  (range: -180 to 180): ");

            try {
                startLongitude = sc.nextDouble();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a floating point value");
                sc.next();
            }
        }

        int id = 0;
        Passenger passenger = new Passenger(name, email, phone, startLatitude,  startLongitude);
        for(Passenger p: passengerList) {
            if (p.getId() > id ) {
               id = p.getId();
            }
        }
        id++;

        passengerList.add(new Passenger(id ,name, email, phone, startLatitude, startLongitude));
    }



    public void editPassenger(String PassengerID)
    {
        Passenger targetPass = findPassengerById(PassengerID);
        System.out.println(targetPass);
        Scanner keyboard = new Scanner(System.in);
        if (targetPass != null)
        {
            boolean exit = false;
            String newName ="";
            String newEmail ="";
            String newPhone ="";
            double startLatitude = -100;
            double startLongitude = -200;




            while(exit == false)
            {
                final String MENU = "\n*** PASSENGER EDITOR ***\n"
                        + "1. Edit name\n"
                        + "2. Edit email\n"
                        + "3. Edit phone \n"
                        + "4. Edit Location \n"
                        + "5. Exit \n"
                        + "Enter Option [1,5]";
                        System.out.println(MENU);
                String usersInput = keyboard.nextLine();

                if(usersInput.equalsIgnoreCase("1"))
                {
                    System.out.println("please enter new name ");
                    newName = keyboard.nextLine();
                    targetPass.setName(newName);
                }
                else if(usersInput.equalsIgnoreCase("2"))
                {
                    System.out.println("please enter new email ");
                    newEmail = keyboard.nextLine();
                    targetPass.setEmail(newEmail);

                }
                else if(usersInput.equalsIgnoreCase("3"))
                {
                    System.out.println("please enter new phone ");
                    newPhone = keyboard.nextLine();
                    targetPass.setPhone(newPhone);

                }
                else if(usersInput.equalsIgnoreCase("4"))
                {

                    while (startLatitude < -90 || startLatitude > 90) {
                        System.out.println("Enter passenger start latitude (range: -90 to 90): ");

                        try {
                            startLatitude = keyboard.nextDouble();
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Please enter a floating point value");
                            keyboard.next();
                        }
                    }

                    while (startLongitude < -180 || startLongitude > 180) {
                        System.out.println("Enter passenger longitude  (range: -180 to 180): ");

                        try {
                            startLongitude = keyboard.nextDouble();
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Please enter a floating point value");
                            keyboard.next();
                        }
                    }
                    targetPass.setLocation(startLatitude,startLongitude);
                }
                else
                {
                    exit = true;
                }
            }
        }
        else
        {
            System.out.println("No customer exists for the ID you specified");
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

    public boolean deletePassengerByNameAndEmail(String name, String email)
    {
        boolean validate = false;
        for (Passenger p : this.passengerList) {
            if(p.getName().equals(name) && p.getEmail().equals(email))
            {
                passengerList.remove(p);
                validate = true;
                break;
            }
        }
        return validate;
    }

    public Passenger findPassengerByName(String name)
    {

        for(Passenger p: passengerList) {
            if (p.getName().equalsIgnoreCase(name)) {

                /*System.out.println("----------------------------------------------------------------------------------------------------------------------");
                System.out.println("\t\tPassenger ID : \t\t\t\t\t\t"  + p.getId());
                System.out.println("\t\tPassenger Name : \t\t\t\t\t"  + p.getName());
                System.out.println("\t\tPassenger Email : \t\t\t\t\t"  + p.getEmail());
                System.out.println("\t\tPassenger Phone : \t\t\t\t\t"  + p.getPhone());
                System.out.println("\t\tLocation : \t\t\t\t\t\t\t"  + p.getLocation());
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                */
                return p;

            }
        }
        return null;
    }


    public Passenger findPassengerById(String id)
    {

        for(Passenger p: passengerList) {

            String c = Integer.toString(p.getId());

            if (c.equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

}
