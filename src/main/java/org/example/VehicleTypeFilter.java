package org.example;

import java.time.LocalDateTime;

public class VehicleTypeFilter implements IFilter{

    private String type;

    public VehicleTypeFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean matches(Object other) {
        Vehicle v = (Vehicle) other;

        String gottenType = v.getType();

        return gottenType.equalsIgnoreCase(type);
    }

}