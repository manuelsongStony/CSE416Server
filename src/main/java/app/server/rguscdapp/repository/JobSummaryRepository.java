package app.server.rguscdapp.repository;

        import app.server.rguscdapp.entity.JobSummary;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSummaryRepository extends JpaRepository<JobSummary, Integer> {

}
