package org.example;
//
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;

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
                int var = sc.nextInt();

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {
                    // construct a Van object and add it to the passenger list
                    int loadSpace = var;

                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
                else if(type.equalsIgnoreCase("Car") ||
                        type.equalsIgnoreCase("4X4")){

                    int numSeats = var;

                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            numSeats));

                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }


    public void writeVehicleDataToFile(String fileName) throws FileNotFoundException {

        // create a print writer class
        PrintWriter pw = new PrintWriter(fileName);


        for (Vehicle v : this.vehicleList) {
            pw.print(v.getId() + ",");
            pw.print(v.getType() + ",");
            pw.print(v.getMake() + ",");
            pw.print(v.getModel() + ",");
            pw.print(v.getMilesPerKm() + ",");
            pw.print(v.getRegistration() + ",");
            pw.print(v.getCostPerMile() + ",");

            LocalDate lastServ = v.getLastServicedDate();
            pw.print(lastServ.getYear() + ",");
            pw.print(lastServ.getMonthValue() + ",");
            pw.print(lastServ.getDayOfMonth() + ",");
            pw.print(v.getMileage() + ",");

            LocationGPS depotLoc = v.getDepotGPSLocation();

            pw.print(depotLoc.getLatitude() + ",");
            pw.print(depotLoc.getLongitude() + ",");

            if(v instanceof Car)
            {
                pw.print(Math.round(((Car)v).getNumSeats()) + "\n");
            }
            else
            {
                pw.print(Math.round(((Van)v).getLoadSpace()) + "\n");
            }
        }

        pw.close();
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

    public Vehicle findSingleVehicleById(String vID)
    {
        for(Vehicle v: vehicleList) {

            int a = v.getId();
            String c = Integer.toString(a);

            if (c.equalsIgnoreCase(vID)) {
                return v;
            }
        }
        return null;
    }



    public List<Vehicle> filterBy(IFilter filter)            // I stands for Interface
    {
        List<Vehicle> filteredList = new ArrayList<>();
        for (Vehicle v : this.vehicleList) {
            if (filter.matches(v))    // use matches() method of the filter to match products
                filteredList.add(v);
        }

        return filteredList;
    }


}
