package app.server.rguscdapp.repository;


import app.server.rguscdapp.entity.State;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
    //State findByName(String name);
}
