package app.server.rguscdapp.repository;



import app.server.rguscdapp.entity.Precinct;
import app.server.rguscdapp.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StateRepository extends JpaRepository<State, Integer> {
    @Query("SELECT state FROM State state WHERE state.stateName=?1")
    State findStudentByStatePrecinctId(String stateName);
}
