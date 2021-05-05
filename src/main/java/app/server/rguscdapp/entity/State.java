package app.server.rguscdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class State {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int stateId;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Collection<Job> jobs;

    private String stateName;
    private int totalPopulation;
    private int totalBlackPopulation;
    private int totalHispanicPopulation;
    private int totalAsianPopulation;
    private int totalNativePopulation;
    private int totalDemocraticPopulation;
    private int totalCVAP;
    private int totalVAP;
    private int enactedDistrictingId;


}
