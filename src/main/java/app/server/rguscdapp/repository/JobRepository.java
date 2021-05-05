package app.server.rguscdapp.repository;


import app.server.rguscdapp.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
