package app.server.rguscdapp.entity;


import app.server.rguscdapp.enums.Minority;
import app.server.rguscdapp.sorting.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data //build getter and setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Districting {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int districtingId;

    @JsonManagedReference
    @OneToMany(mappedBy = "districting", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<District> districts;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private String geoData;//path of geojson file
    private double objectiveScore;
    private int numMajMinDistricts;
    private double popEqualityDifference;
    private double compactnessFatness;
    private double compactnessGraph;
    private double compactnessPolsby;
    private double maxPopDiff;
    private double splitCountyScore;
    private double efficiencyGap;


    @Transient
    private ArrayList<Double> boxAndWhiskerData;
    @Transient
    private double boxAndWhiskerScore;

    private double deviationFromAverage;
    private double deviationFromEnactedArea;
    private double deviationFromEnactedPop;
    @Transient
    private boolean conflictingIncumbents;

    public boolean getConflictingIncumbents() {
        return conflictingIncumbents;
    }

    public void createBoxAndWhiskerData(Minority minority) {


        switch(minority) {
            case BLACK:
                for(int i=0; i<districts.size();i++){
                    boxAndWhiskerData.add((double)(districts.get(i).getSumPopulationBlack())/
                            districts.get(i).getSumPopulation());
                }
                break;
            case HISPANIC:
                for(int i=0; i<districts.size();i++){
                    boxAndWhiskerData.add((double)(districts.get(i).getSumPopulationHispanic())/
                            districts.get(i).getSumPopulation());
                }
                break;
            case ASIAN:
                for(int i=0; i<districts.size();i++){
                    boxAndWhiskerData.add((double)(districts.get(i).getSumPopulationAsian())/
                            districts.get(i).getSumPopulation());
                }
                break;
            case NATIVE:
                for(int i=0; i<districts.size();i++){
                    boxAndWhiskerData.add((double)(districts.get(i).getSumPopulationNative())/
                            districts.get(i).getSumPopulation());
                }
                break;


        }
        Collections.sort(boxAndWhiskerData);


    }

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
