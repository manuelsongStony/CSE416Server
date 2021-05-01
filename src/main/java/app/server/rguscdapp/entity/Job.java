package app.server.rguscdapp.entity;


import app.server.rguscdapp.*;
import app.server.rguscdapp.entity.Districting;
import app.server.rguscdapp.entity.State;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Job {


    private Long id; // for database


    private Collection<Districting> districtings;
    private Map<Summary, Collection<Districting>> summaryDistrictings;
    private State state;
    private String MGGGCodeParameters;
    private String[] protectedIncumbents;
    private int minMajorityMinorityDistricts;
    private double mincompactness;
    private CompactnessType compactnessType;
    private double popConstraint;
    private PopulationType popType;
    private double[] weights;
    private Minority minority;
    private AverageDistricting averageDistricting;
    private List<boxAndWhisker> boxAndWhiskerData;


    /*
    + applyConstraints(): Job
    + calcObjectiveScores(): void
    + getTop10(): Job
    + getHighScoreEnacted(): Job
    + getHighScoreMajMin(): Job
    + getAreaPairDeviations(): Job
    + getBoxAndWhiskerData(minority: Minority): double[][]
    + pickAverageDistricting(): Districting
    + getSummary(): JSON
    +setBoxAndWhiskerData():void
    + getObjFunctionDetail(districtingId: int): double[]
    + setSummaryDistricting(): void
    */


}
