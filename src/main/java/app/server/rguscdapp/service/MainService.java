package app.server.rguscdapp.service;

import app.server.rguscdapp.entity.Weight;
import app.server.rguscdapp.entity.State;

import app.server.rguscdapp.repository.StateRepository;

import app.server.rguscdapp.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainService {
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private WeightRepository weightRepository;

    public State getStateById(int id){

        return stateRepository.findById(id).orElse(null);
    }

    public State saveState(State state){

        return stateRepository.save(state);
    }

    public Weight setWeights(Weight weight){
        return weightRepository.save(weight);
    }

   

    public String loadIncumbents(){

        return "[{\"incumbent\": \"Rick Crawford\", \"district\": 1}, " +
                "{\"incumbent\": \"French Hill\", \"district\": 2}, {\"incumbent\": \"Steve Womack\", \"district\": 3}, " +
                "{\"incumbent\": \"Bruce Westerman\", \"district\": 4}]";
    }



}
