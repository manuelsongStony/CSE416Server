package app.server.rguscdapp.sorting;

        import app.server.rguscdapp.entity.District;

        import java.util.Comparator;

public class BoxAndWhiskerHispanic implements Comparator<District> {
    public int compare(District a, District b)
    {
        if((a.getSumPopulationHispanic()/a.getSumPopulation())==(a.getSumPopulationHispanic()/b.getSumPopulation()))
            return 0;
        else if((a.getSumPopulationHispanic()/a.getSumPopulation())>(a.getSumPopulationHispanic()/b.getSumPopulation()))
            return 1;
        else
            return -1;
    }
}
