package app.server.rguscdapp.sorting;

import app.server.rguscdapp.entity.Districting;

import java.util.Comparator;

public class SortByObjectiveScore implements Comparator<Districting> {
    public int compare(Districting a, Districting b)
    {
        if(a.getObjectiveScore()==b.getObjectiveScore())
            return 0;
        else if(a.getObjectiveScore()>b.getObjectiveScore())
            return 1;
        else
            return -1;
    }
}

