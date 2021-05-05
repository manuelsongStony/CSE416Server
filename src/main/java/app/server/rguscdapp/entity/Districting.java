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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int districtingId;

    @OneToMany(mappedBy = "districting", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<District> districts;

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
    private double[] boxAndWhiskerArray;

    private double deviationFromAverage;
    private double deviationFromEnactedArea;
    private double deviationFromEnactedPop;
    @Transient
    private boolean conflictingIncumbents;

    public boolean getConflictingIncumbents() {
        return conflictingIncumbents;
    }
}
