package org.example;

import java.time.LocalDateTime;

public class BookingYearFilter implements IFilter{

    private String year;

    public BookingYearFilter(String year) {
        this.year = year;
    }

    @Override
    public boolean matches(Object other) {
        Booking b = (Booking) other;
        LocalDateTime date = b.getBookingDateTime();
        int yearInt = date.getYear();
        String yearComp = Integer.toString(yearInt);
        return yearComp.equalsIgnoreCase(year);
    }

}
