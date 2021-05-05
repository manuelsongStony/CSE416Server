package app.server.rguscdapp.entity;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data //build getter and setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class District {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int districtId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "districting_id", nullable = false)
    private Districting districting;

    private int number;
    //private Collection<Precinct> precincts;
    @Transient
    private String[] incumbents;
    //private double[][] geoData; we can get this from Districting database
    private double area;

    private int sumPopulation;
    private int sumPopulationBlack;
    private int sumPopulationHispanic;
    private int sumPopulationAsian;
    private int sumPopulationNative;
    private int sumCVAP;
    private int sumVAP;
    private int sumPopulationDem;
    private int sumPopulationRep;
    private boolean majMinDistrict;


/*
+ getPercentBlackPopulation(): double
+ getPercentHispanicPopulation(): double
+ getPercentAsianPopulation(): double
+ getPercentNativePopulation(): double
 */

}
