package org.example;

public class Car extends Vehicle{

    private int numSeats;

    public Car(String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numSeats)
    {

        super(type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numSeats = numSeats;
    }


    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int numSeats)
    {

        super(id,type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.numSeats = numSeats;
    }

    public double getNumSeats() {
        return numSeats;
    }
    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Number of seats =" + numSeats +
                "} " + super.toString();
    }
}
