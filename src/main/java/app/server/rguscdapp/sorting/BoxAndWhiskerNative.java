package app.server.rguscdapp.sorting;

        import app.server.rguscdapp.entity.District;

        import java.util.Comparator;

public class BoxAndWhiskerNative implements Comparator<District> {
    public int compare(District a, District b)
    {
        if((a.getSumPopulationNative()/a.getSumPopulation())==(a.getSumPopulationNative()/b.getSumPopulation()))
            return 0;
        else if((a.getSumPopulationNative()/a.getSumPopulation())>(a.getSumPopulationNative()/b.getSumPopulation()))
            return 1;
        else
            return -1;
    }
}
