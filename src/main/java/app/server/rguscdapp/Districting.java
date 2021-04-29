package app.server.rguscdapp;

import java.util.Collection;

public class Districting {
    private Collection<District> districts;
    private double[][] geoData;
    private double objectiveScore;
    private int numMajMinDistricts;
    private double compactnessFatness;
    private double compactnessGraph;
    private double compactnessPolsby;
    private double maxPopDiff;
    private String[][] conflictingIncumbents;
    private SplitCounty[] splitCounties;
    private double splitCountyScore;
    private double efficiencyGap;
    private int districtingId;
    private double[] boxAndWhiskerArray;
    private double deviationFromAverage;

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
