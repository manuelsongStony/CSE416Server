package app.server.rguscdapp.sorting;

        import app.server.rguscdapp.entity.Districting;

        import java.util.Comparator;

public class DesiredMajorMinority implements Comparator<Districting> {
    public int compare(Districting a, Districting b)
    {
        if(a.getDeviationFromAverage()==b.getDeviationFromAverage())
            return 0;
        else if(a.getDeviationFromAverage()>b.getDeviationFromAverage())
            return 1;
        else
            return -1;
    }
}

