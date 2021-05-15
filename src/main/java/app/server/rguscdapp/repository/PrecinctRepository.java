
package app.server.rguscdapp.repository;


        import app.server.rguscdapp.entity.Precinct;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import java.util.Optional;

public interface PrecinctRepository extends JpaRepository<Precinct, Integer> {
        @Query("SELECT prc FROM Precinct prc WHERE prc.statePrecinctId=?1")
        Precinct findStudentByStatePrecinctId(int statePrecinctId);


}
