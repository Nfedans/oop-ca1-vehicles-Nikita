package org.example;
//
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList) {
            System.out.println(v.toString());
        }
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public ArrayList<Vehicle> findVehicleByType(String input){
        ArrayList<Vehicle> filteredVehicles = this.getVehicleList();
        Iterator<Vehicle> iterator = filteredVehicles.iterator();

        if(input.equals("Car"))
        {
        while(iterator.hasNext()) {
            Vehicle v = iterator.next();

            if(!(v instanceof Car))
            {
                iterator.remove();
            }
        }
        return filteredVehicles;
        }
        else
        {
            while(iterator.hasNext()) {
                Vehicle v = iterator.next();

                if(!(v instanceof Van))
                {
                    iterator.remove();
                }
            }
            return filteredVehicles;
        }
    }



    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                int loadSpace = sc.nextInt();

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {
                    // construct a Van object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    //TODO add more functionality as per spec.

    public Vehicle findSingleVehicleByReg(String reg)
    {
        for(Vehicle v: vehicleList) {
            if (v.getRegistration().equalsIgnoreCase(reg)) {
                return v;
            }
        }
                return null;
    }

   /* public void addVehiclesToList(List<Vehicle> vehiclesList, String type) {
        for (Vehicle v : this.vehicleList) {
            vehiclesList.add( new Van(v.getId(), v.getType(), v.getMake(), v.getModel(),
                    v.getMilesPerKm(), v.getRegistration(), v.getCostPerMile(), v.getLastServicedDate(),
                    ));
        }
    }*/

    // Add to menu, write methods, make comparators
/*
    public void Vehicle findVehicleByRegistration()
    {

    }

    public void Vehicle findVehicleByType()
    {

    }

    // this one should return an arraylist of all vehicles
    public void Vehicle findAllVehicles()
    {

    }
*/

}
