package app.server.rguscdapp.sorting;

        import app.server.rguscdapp.entity.Districting;

        import java.util.Comparator;

public class HighScoringCloseToEnactedArea implements Comparator<Districting> {
    public int compare(Districting a, Districting b)
    {
        if(a.getDeviationFromEnactedArea()==b.getDeviationFromEnactedArea())
            return 0;
        else if(a.getDeviationFromEnactedArea()<b.getDeviationFromEnactedArea())
            return 1;
        else
            return -1;
    }
}