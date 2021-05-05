package app.server.rguscdapp.sorting;

import app.server.rguscdapp.entity.Districting;

import java.util.Comparator;

public class HighScoringCloseToEnactedPop implements Comparator<Districting> {
    public int compare(Districting a, Districting b)
    {
        if(a.getDeviationFromEnactedPop()==b.getDeviationFromEnactedPop())
            return 0;
        else if(a.getDeviationFromEnactedPop()<b.getDeviationFromEnactedPop())
            return 1;
        else
            return -1;
    }
}