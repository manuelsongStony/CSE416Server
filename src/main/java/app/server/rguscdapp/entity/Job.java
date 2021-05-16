package app.server.rguscdapp.entity;


import app.server.rguscdapp.*;


import java.util.*;


import app.server.rguscdapp.enums.CompactnessType;
import app.server.rguscdapp.enums.Minority;
import app.server.rguscdapp.enums.PopulationType;
import app.server.rguscdapp.enums.Summary;
import app.server.rguscdapp.sorting.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<Districting> districtings;

    @Transient
    private List<Districting> ctDistrictings;


    @JsonIgnore
    //@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    //private String stateName;


    @Transient
    private Map<Summary, Collection<Districting>> summaryDistrictings;

    private String mgggCodeParameters;


    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<Incumbent> incumbents;

    private int numberOfDistricts;
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
    private Districting enactedDistricting;

    @Transient
    private ArrayList<double[]> boxAndWhiskerArray;


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

    public void creatBoxAndWhiskerArray(Minority minority){

        for (int i = 0; i < ctDistrictings.size(); i++) {
            ctDistrictings.get(i).createBoxAndWhiskerData(minority);
        }


        for(int i=0; i<numberOfDistricts;i++) {
            double[] boxAndWhiskerStat =new double[6];

            List<Double> temp= new ArrayList<>();

            for (int j = 0; j < ctDistrictings.size(); j++) {
                temp.add(ctDistrictings.get(j).getBoxAndWhiskerData().get(i));
            }
            boxAndWhiskerStat[0]=percentile(temp,0);
            boxAndWhiskerStat[1]=percentile(temp,25);
            boxAndWhiskerStat[2]=percentile(temp,50);
            boxAndWhiskerStat[3]=percentile(temp,75);
            boxAndWhiskerStat[4]=percentile(temp,100);
            boxAndWhiskerStat[5]=average(temp);

        }
    }
    public  double percentile(List<Double> data, double percentile) {
        Collections.sort(data);
        int index = (int) Math.ceil(percentile / 100.0 * data.size());
        return data.get(index-1);
    }
    public  double average(List<Double> data) {
        double sum=0;
        for(int i=0; i<data.size();i++){
            sum+=data.get(i);
        }
        return sum/data.size();
    }


    public void pickAverageDistricting(){
        calculateBoxAndWhiskerScore();
        int id=0;
        for (int i = 0; i < ctDistrictings.size(); i++) {
            if (ctDistrictings.get(i).getBoxAndWhiskerScore()<ctDistrictings.get(id).getBoxAndWhiskerScore()){
                id=i;
            }

        }
        averageDistricting=ctDistrictings.get(id);
    }

    public void calculateBoxAndWhiskerScore(){
        for (int i = 0; i < ctDistrictings.size(); i++) {
            double sum=0;
            for(int j = 0; j < numberOfDistricts; j++){

               sum+= Math.abs(ctDistrictings.get(i).getBoxAndWhiskerData().get(j)-boxAndWhiskerArray.get(j)[5]);
            }

            ctDistrictings.get(i).setBoxAndWhiskerScore(sum);
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
