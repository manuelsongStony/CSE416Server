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
public class Weight {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int weightId;


    private double populationEquality;
    private double splitCounty;
    private double deviationFromAverageDistricting;
    private double deviationFromEnactedPlan;
    private String compactnessType;
    private double compactness;
    private double politicalFairness;

    @OneToOne(mappedBy = "weight")
    private Job job;

}
