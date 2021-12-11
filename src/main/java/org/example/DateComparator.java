package org.example;

import java.time.LocalDateTime;
import java.util.Comparator;

public class DateComparator implements Comparator<Booking>{

    public int compare(Booking b1, Booking b2)
    {
        return b1.getBookingDateTime().compareTo(b2.getBookingDateTime());

    }
}