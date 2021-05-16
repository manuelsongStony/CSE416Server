package app.server.rguscdapp.entity;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
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

    private int VAP;
    private int WVAP;
    private int BVAP;
    private int HVAP;
    private int AMINVAP;
    private int ASIANVAP;
    private int NHPIVAP;

    private int sumPopulationDem;
    private int sumPopulationRep;
    private boolean majMinDistrict;

    //mappedBy = "district",
    @ManyToMany
    @JoinTable(
            name = "course_like",
            joinColumns = @JoinColumn(name = "district_id"),
            inverseJoinColumns = @JoinColumn(name = "precinct_id"))
    private List<Precinct> precincts;

    @Lob
    @Column(name = "file", columnDefinition = "LONGBLOB")
    private String precinctString;
/*
+ getPercentBlackPopulation(): double
+ getPercentHispanicPopulation(): double
+ getPercentAsianPopulation(): double
+ getPercentNativePopulation(): double
 */

}
