package org.example;

public class NumSeatFilter implements IFilter{

    private String numSeats;

    public NumSeatFilter(String numSeats) {
        this.numSeats = numSeats;
    }

   /* @Override
    public boolean matches(Object other) {
        Car c = (Car) other;

        double gottenSeats = c.getNumSeats();
        String nS = Double.toString(gottenSeats);

        return nS.equalsIgnoreCase(numSeats);
    }*/


    @Override
    public boolean matches(Object other)
    {
        Vehicle v = (Vehicle) other;

        if(v instanceof Car)
        {
            double gottenSeats = ((Car) v).getNumSeats();
            String nS = Double.toString(gottenSeats);
            return nS.equalsIgnoreCase(numSeats);
        }
        else
        {
            double gs = ((Van) v).getLoadSpace();
            String nS = Double.toString(gs);
            return nS.equalsIgnoreCase(numSeats);
        }


    }

}