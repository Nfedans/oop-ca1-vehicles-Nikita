package org.example;


import java.time.LocalDateTime;
import java.util.*;

public class EmailManager {

    private final ArrayList<Email> mailingList;
    private BookingManager bookingManager;
    private PassengerStore passengerStore;

    public EmailManager(PassengerStore ps, BookingManager bm) {
        this.mailingList = new ArrayList<>();
        passengerStore = ps;
        bookingManager = bm;
    }

    public ArrayList<Email> getMailingList() {
        return mailingList;
    }

    public void addEmail(String idForEmail, Passenger psngr)
    {
        Booking b = bookingManager.findBookingById(idForEmail);

        String to = psngr.getEmail();
        String from = "Tesla@CustomerSupport.com";
        String subject = "Your Booking ( id : " + b.getBookingId() + ")";
        LocalDateTime timeSent = LocalDateTime.now();
        String text = "Hi " + psngr.getName() + ",\n\n" +
                "This is a confirmation email from Tesla with your booking details,\n\n" +
                "you have booked vehicle number " + b.getVehicleId() + " for " + b.getBookingDateTime()
                + " at a total cost of €" + b.getCost() + ".\n\n" +
                "We wish you a safe and fun journey,\n" +
                "Tesla customer support";

        Email newEmail = new Email(to, from, subject, timeSent, text);
        mailingList.add(newEmail);
    }


}
