package app.server.rguscdapp.service;

import app.server.rguscdapp.Constraint;
import app.server.rguscdapp.Weight;
import app.server.rguscdapp.entity.State;

import app.server.rguscdapp.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Service
public class MainService {
    @Autowired
    private StateRepository stateRepository;


    /*
    public String loadState(String statename){
        if(statename.equals("Arkansas"))
            return "[{\"id\": 1, \"state\": \"Arkansas\", \"districtings\": 10000, \"params\": \"-g ./src/data/arkansas.json -c 20 -r 10000\"}, {\"id\": 2, \"state\": \"Arkansas\", \"districtings\": 200, \"params\": \"-g ./src/data/arkansas.json -c 20\"}, {\"id\": 3, \"state\": \"Arkansas\", \"districtings\": 2000, \"params\": \"-g ./src/data/arkansas.json -r 2000\"}, {\"id\": 4, \"state\": \"Arkansas\", \"districtings\": 10000, \"params\": \"-g ./src/data/arkansas.json -c 50 -r 10000\"}, {\"id\": 5, \"state\": \"Arkansas\", \"districtings\": 5000, \"params\": \"-g ./src/data/arkansas.json -c 40 -r 5000\"}] ";
        return "state name is "+statename;
    }
    */

    public State getStateById(int id){
        return stateRepository.findById(id).orElse(null);
    }

    public State saveState(State state){
        return stateRepository.save(state);
    }


    public String loadJob(int number){
        return "job number is "+number;

    }


    public int setConstraints(Long jobId, Constraint constraint){
        return 1;

    }


    public String setWeights( Long jobId, Weight weight){
        return "Json";

    }

    public String loadIncumbents(){

        return "[{\"incumbent\": \"Rick Crawford\", \"district\": 1}, {\"incumbent\": \"French Hill\", \"district\": 2}, {\"incumbent\": \"Steve Womack\", \"district\": 3}, {\"incumbent\": \"Bruce Westerman\", \"district\": 4}]";

    }


      /*
    + loadState(stateName: String): JSON
    + loadJob(jobId: int): int
    + setConstraints(incumbents: String[], majMinDistricts: int, compactness: double, compactType: enum, eqPop: double, popType: enum, minority: enum): int
    + setWeights(<See Seq. Diagram 11>): JSON
    + setCurrent(districtingId: int): JSON
    + getBoxWhiskerData(): double[][]
    + getObjFunctionDetail(districtingId: int): double[]
    +calDeviationFromAve()
    +calculateMeasure(uj:job) job
    + getDistrictDetail(district): double[]
     */
}
