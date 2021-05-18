package app.server.rguscdapp.sorting;

        import app.server.rguscdapp.entity.District;

        import java.util.Comparator;

public class PopNumber implements Comparator<District> {
    public int compare(District a, District b)
    {
        if(a.getVAP()==b.getVAP())
            return 0;
        else if(a.getVAP()>b.getVAP())
            return 1;
        else
            return -1;
    }
}