package app.server.rguscdapp.sorting;

import app.server.rguscdapp.entity.District;

import java.util.Comparator;

public class BoxAndWhiskerAsian implements Comparator<District> {
    public int compare(District a, District b)
    {
        if((a.getSumPopulationAsian()/a.getSumPopulation())==(a.getSumPopulationAsian()/b.getSumPopulation()))
            return 0;
        else if((a.getSumPopulationAsian()/a.getSumPopulation())>(a.getSumPopulationAsian()/b.getSumPopulation()))
            return 1;
        else
            return -1;
    }
}