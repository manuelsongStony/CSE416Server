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
public class Incumbent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int incumbentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private String incumbentName;
    private boolean protection;
    private int districtNumber;


}
