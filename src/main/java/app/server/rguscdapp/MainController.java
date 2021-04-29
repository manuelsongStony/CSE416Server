package app.server.rguscdapp;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

//@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin
@RestController
@RequestMapping(path= "") //http://localhost:8080/api/v1/rguscdapp
//@RequestMapping(path= "api/v1/rguscdapp")
public class MainController {
    /*
    @GetMapping(path= "state")//http://localhost:8080/api/v1/rguscdapp/state
    public String loadState(){
        //return studentService.getStudents();
        return "state object as json";

    }

    @GetMapping(path= "job")//http://localhost:8080/api/v1/rguscdapp/job
    public String loadJob(){
        //return studentService.getStudents();
        return "job object as json";

    }
    */


    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path= "state")//http://localhost:8080/api/v1/rguscdapp/state
    public String loadState(@RequestParam(required = false)String statename){

        if(statename.equals("Arkansas"))
            return "[{\"id\": 1, \"state\": \"Arkansas\", \"districtings\": 10000, \"params\": \"-g ./src/data/arkansas.json -c 20 -r 10000\"}, {\"id\": 2, \"state\": \"Arkansas\", \"districtings\": 200, \"params\": \"-g ./src/data/arkansas.json -c 20\"}, {\"id\": 3, \"state\": \"Arkansas\", \"districtings\": 2000, \"params\": \"-g ./src/data/arkansas.json -r 2000\"}, {\"id\": 4, \"state\": \"Arkansas\", \"districtings\": 10000, \"params\": \"-g ./src/data/arkansas.json -c 50 -r 10000\"}, {\"id\": 5, \"state\": \"Arkansas\", \"districtings\": 5000, \"params\": \"-g ./src/data/arkansas.json -c 40 -r 5000\"}] ";
        return "state name is "+statename;
    }


    @GetMapping(path= "job")//http://localhost:8080/api/v1/rguscdapp/job
        public String loadJob(@RequestParam(required = false)int number){

        return "job number is "+number;

    }

    @PostMapping(path= "setConstraint/{jobId}")
    public int setConstraints(@PathVariable("jobId") Long jobId,@RequestBody Constraint constraint){
        return 1;

    }

    @PostMapping(path= "setWeights/{jobId}")
    public String setWeights(@PathVariable("jobId") Long jobId,@RequestBody Weight weight){
        return "Json";

    }

    @GetMapping(path= "incumbents")//http://localhost:8080/api/v1/rguscdapp/incumbents
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
