package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

//
class Booking
{
    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDateTime bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time

    //TODO - see specification


    public Booking(int bookingId, int passengerId, int vehicleId, LocalDateTime bookingDateTime, LocationGPS startLocation, LocationGPS endLocation, double cost)
    {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = bookingDateTime;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.cost = cost;

    }

    public int getPassengerId() {
        return passengerId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public LocationGPS getStartLocation() {
        return startLocation;
    }

    public LocationGPS getEndLocation() {
        return endLocation;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", vehicleId=" + vehicleId +
                ", bookingDateTime=" + bookingDateTime +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", cost=" + cost +
                '}';
    }

}
