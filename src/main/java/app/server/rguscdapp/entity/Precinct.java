package app.server.rguscdapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Precinct {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int precinctId;

    private int statePrecinctId;
    private String stateName;

    private int totalPopulation;
    private int blackPopulation;
    private int hispanicPopulation;
    private int asianPopulation;
    private int nativePopulation;
    private int otherPopulation;

    @Lob
    @Column(name = "file", columnDefinition = "LONGBLOB")
    private String geoData;

    @Transient
    private ArrayList coordinate;

    private int CVAP;

    private int VAP;
    private int blackVAP;
    private int hispanicVAP;
    private int asianVAP;
    private int nativeVAP;
    private int otherVAP;


    private String incumbent;
    private double area;
    private String county;
    private int democraticPopulation;



    private String neighbor;

    @ManyToMany(mappedBy = "precincts")
    private List<District> districts;


}
