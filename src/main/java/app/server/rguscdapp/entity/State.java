package app.server.rguscdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class State {
    @Id
    @GeneratedValue
    private int id;
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
