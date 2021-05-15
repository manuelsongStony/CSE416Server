package app.server.rguscdapp.entity;

import app.server.rguscdapp.enums.CompactnessType;
import app.server.rguscdapp.enums.Minority;
import app.server.rguscdapp.enums.PopulationType;
import app.server.rguscdapp.enums.Summary;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data //build getter and setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobSummary {

    @Id
    private int jobSummaryId; // for database

    private String mgggCodeParameters;

    private int numberOfDistricts;
    private int minMajorityMinorityDistricts;
    private double mincompactness;
    private CompactnessType compactnessType;
    private double popConstraint;
    private PopulationType popType;

    private Minority minority;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;


    public JobSummary(Job job){
        this.jobSummaryId=job.getJobId();
        this.mgggCodeParameters=job.getMgggCodeParameters();
        this.numberOfDistricts=job.getNumberOfDistricts();
        this.minMajorityMinorityDistricts=job.getMinMajorityMinorityDistricts();
        this.mincompactness=job.getMincompactness();
        this.compactnessType=job.getCompactnessType();
        this.popConstraint=job.getPopConstraint();
        this.popType=job.getPopType();
        this.minority=job.getMinority();
        this.state=job.getState();
    }
}
