package app.server.rguscdapp.controller;

import app.server.rguscdapp.Constraint;
import app.server.rguscdapp.entity.State;
import app.server.rguscdapp.service.MainService;
import app.server.rguscdapp.Weight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping(path= "")
//@RequestMapping(path= "api/v1/rguscdapp") //http://localhost:8080/api/v1/rguscdapp
public class MainController {

    @Autowired
    private MainService mainService;




    /*
    @GetMapping(path= "state")//http://localhost:8080/api/v1/rguscdapp/state
    public String loadState(@RequestParam(required = false)String statename){

        return mainService.loadState(statename);
    }
    */
    @GetMapping("/state/{id}")//http://localhost:8080/state/1
    public State findStateById(@PathVariable int id){

        return mainService.getStateById(id);
    }
    @PostMapping("/addState")
    public State addState(@RequestBody State state){
        return mainService.saveState(state);
    }

    public String loadJob(@RequestParam(required = false)int number){

        return mainService.loadJob(number);
    }

    @PostMapping(path= "setConstraint/{jobId}")
    public int setConstraints(@PathVariable("jobId") Long jobId,@RequestBody Constraint constraint){

        return mainService.setConstraints(jobId,constraint);

    }

    @PostMapping(path= "setWeights/{jobId}")
    public String setWeights(@PathVariable("jobId") Long jobId,@RequestBody Weight weight){

        return mainService.setWeights(jobId,weight);

    }

    @GetMapping(path= "incumbents")//http://localhost:8080/api/v1/rguscdapp/incumbents
    public String loadIncumbents(){


        return mainService.loadIncumbents();
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
