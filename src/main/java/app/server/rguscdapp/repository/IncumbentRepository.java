package app.server.rguscdapp.repository;


        import app.server.rguscdapp.entity.Incumbent;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface IncumbentRepository extends JpaRepository<Incumbent, Integer> {

}
