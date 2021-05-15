package app.server.rguscdapp.entity;

        import com.fasterxml.jackson.annotation.JsonBackReference;
        import com.fasterxml.jackson.annotation.JsonManagedReference;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import javax.persistence.*;
        import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PrecinctCoordinate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int precinctCordinateId;


    double latitude;
    double longitude;


}
