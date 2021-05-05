package app.server.rguscdapp.entity;


import app.server.rguscdapp.*;


import java.util.Collection;
import java.util.List;
import java.util.Map;


import app.server.rguscdapp.enums.CompactnessType;
import app.server.rguscdapp.enums.Minority;
import app.server.rguscdapp.enums.PopulationType;
import app.server.rguscdapp.enums.Summary;
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
    private AverageDistricting averageDistricting;
    @Transient
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
