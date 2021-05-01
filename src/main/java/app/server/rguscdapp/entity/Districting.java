package app.server.rguscdapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data //build getter and setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Districting {
    @Id
    @GeneratedValue
    private int DistrictingId;

    @OneToMany(mappedBy = "districting", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<District> districts;

    private String geoData;//path of geojson file
    private double objectiveScore;
    private int numMajMinDistricts;
    private double compactnessFatness;
    private double compactnessGraph;
    private double compactnessPolsby;
    private double maxPopDiff;
    private double splitCountyScore;
    private double efficiencyGap;

    @Transient
    private double[] boxAndWhiskerArray;

    private double deviationFromAverage;

    @Transient
    private String[][] conflictingIncumbents;
    //private String[][] conflictingIncumbents;
    //private SplitCounty[] splitCounties;

    /*
    + createBoxAndWhiskerArray(minority: Minority): double[]
    + isIncumbentProtected(incumbent: String): boolean
    + deviationFromAverage(avg: AverageDistricting): double
    + deviationFromEnacted(enacted: Districting): double
    + calcObjectiveScore(weights: double[]): double
    + toGillConstruct(): void
    + getSummary(): JSON
    + toJSON(): JSON
    +setboxAndWhiskerArray();void
    +setCharacteristic():void
    +setDeviationFromAverage():void
    +setMeasures():void
     */
}
