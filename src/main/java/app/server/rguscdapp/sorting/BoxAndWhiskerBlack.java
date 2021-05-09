package app.server.rguscdapp.sorting;

        import app.server.rguscdapp.entity.District;

        import java.util.Comparator;

public class BoxAndWhiskerBlack implements Comparator<District> {
    public int compare(District a, District b)
    {
        if((a.getSumPopulationBlack()/a.getSumPopulation())==(a.getSumPopulationBlack()/b.getSumPopulation()))
            return 0;
        else if((a.getSumPopulationBlack()/a.getSumPopulation())>(a.getSumPopulationBlack()/b.getSumPopulation()))
            return 1;
        else
            return -1;
    }
}
