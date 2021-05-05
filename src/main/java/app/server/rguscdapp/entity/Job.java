package app.server.rguscdapp.entity;


import app.server.rguscdapp.*;


import java.util.*;


import app.server.rguscdapp.enums.CompactnessType;
import app.server.rguscdapp.enums.Minority;
import app.server.rguscdapp.enums.PopulationType;
import app.server.rguscdapp.enums.Summary;
import app.server.rguscdapp.sorting.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data //build getter and setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int jobId; // for database


    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<Districting> districtings;

    @Transient
    private Collection<Districting> ctDistrictings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Transient
    private Map<Summary, Collection<Districting>> summaryDistrictings;

    private String mgggCodeParameters;


    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<Incumbent> incumbents;

    private int minMajorityMinorityDistricts;
    private double mincompactness;
    private CompactnessType compactnessType;
    private double popConstraint;
    private PopulationType popType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weight_id")
    private Weight weight;
    private Minority minority;
    @Transient
    private Districting averageDistricting;
    @Transient
    private List<boxAndWhisker> boxAndWhiskerData;


    public Collection<Districting> applyConstraints(){

        ctDistrictings=new ArrayList<>();

        for (Districting dis : districtings) {
            double compactness=0;
            switch(compactnessType) {
                case GRAPHCOMPACTNESS:
                    compactness=dis.getCompactnessGraph();
                    break;
                case POPFATNESS:
                    compactness=dis.getCompactnessFatness();
                    break;
                case POLSBYPOPPER:
                    compactness=dis.getCompactnessPolsby();
                    break;
            }

            if(dis.getNumMajMinDistricts()>=minMajorityMinorityDistricts){
                if(dis.getPopEqualityDifference()>=popConstraint){
                    if(compactness>=mincompactness){
                        if(!dis.getConflictingIncumbents()){
                            ctDistrictings.add(dis);
                        }
                    }
                }
            }


        }

        return ctDistrictings;

    }

    public void calcObjectiveScores(){

        double objectiveScore=0;
        for (Districting ctdis : ctDistrictings) {
            double compactness=0;
            switch(compactnessType) {
                case GRAPHCOMPACTNESS:
                    compactness=ctdis.getCompactnessGraph();
                    break;
                case POPFATNESS:
                    compactness=ctdis.getCompactnessFatness();
                    break;
                case POLSBYPOPPER:
                    compactness=ctdis.getCompactnessPolsby();
                    break;
            }

            objectiveScore+=ctdis.getPopEqualityDifference()*weight.getPopulationEquality();
            objectiveScore+=ctdis.getSplitCountyScore()*weight.getSplitCounty();
            objectiveScore+=ctdis.getDeviationFromAverage()*weight.getDeviationFromAverageDistricting();
            objectiveScore+=ctdis.getDeviationFromEnactedArea()*weight.getDeviationFromEnactedPlan();
            objectiveScore+=ctdis.getDeviationFromEnactedPop()*weight.getDeviationFromEnactedPlan();
            objectiveScore+=compactness*weight.getCompactness();
            objectiveScore+=ctdis.getEfficiencyGap()*weight.getPoliticalFairness();

            ctdis.setObjectiveScore(objectiveScore);
        }
        /// summary part
        setSummaryDistricting();

    }
    public void setSummaryDistricting(){
        summaryDistrictings.put(Summary.TOPTEN, new ArrayList<>());
        summaryDistrictings.put(Summary.HIGHSCORINGCLOSETOENACTEDAREA, new ArrayList<>());
        summaryDistrictings.put(Summary.HIGHSCORINGCLOSETOENACTEDPOP, new ArrayList<>());
        summaryDistrictings.put(Summary.DESIREDMAJORMINORITY, new ArrayList<>());
        summaryDistrictings.put(Summary.TOPFIVEDIFFERENTAREA, new ArrayList<>());

        ArrayList<Districting>summaryList= new ArrayList(ctDistrictings);
        Collections.sort(summaryList, new SortByObjectiveScore());
        for(int i=0; i<10; i++){
            summaryDistrictings.get(Summary.TOPTEN).add(summaryList.get(i));
        }

        Collections.sort(summaryList, new HighScoringCloseToEnactedArea());
        for(int i=0; i<5; i++){
            summaryDistrictings.get(Summary.HIGHSCORINGCLOSETOENACTEDAREA).add(summaryList.get(i));
        }
        Collections.sort((ArrayList)summaryDistrictings.get(Summary.HIGHSCORINGCLOSETOENACTEDAREA),new SortByObjectiveScore());

        Collections.sort(summaryList, new HighScoringCloseToEnactedPop());
        for(int i=0; i<5; i++){
            summaryDistrictings.get(Summary.HIGHSCORINGCLOSETOENACTEDPOP).add(summaryList.get(i));
        }
        Collections.sort((ArrayList)summaryDistrictings.get(Summary.HIGHSCORINGCLOSETOENACTEDPOP),new SortByObjectiveScore());


        Collections.sort(summaryList, new DesiredMajorMinority());
        for(int i=0; i<5; i++){
            summaryDistrictings.get(Summary.DESIREDMAJORMINORITY).add(summaryList.get(i));
        }
        Collections.sort((ArrayList)summaryDistrictings.get(Summary.DESIREDMAJORMINORITY),new SortByObjectiveScore());


        Collections.sort(summaryList, new TopFiveDifferentArea());
        for(int i=0; i<5; i++){
            summaryDistrictings.get(Summary.TOPFIVEDIFFERENTAREA).add(summaryList.get(i));
        }


    }

    /*
    + : Job
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
    + : void
    */


}
