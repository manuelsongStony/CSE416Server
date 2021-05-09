package app.server.rguscdapp.controller;

import app.server.rguscdapp.entity.State;
import app.server.rguscdapp.service.MainService;
import app.server.rguscdapp.entity.Weight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(path= "")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/state/{id}")//http://localhost:8080/state/1
    public State findStateById(@PathVariable int id){

        return mainService.getStateById(id);
    }
    @PostMapping("/state/addState")
    public State addState(@RequestBody State state){

        return mainService.saveState(state);
    }

    @PostMapping(path= "setWeights")
    public Weight setWeights(@RequestBody Weight weight){

        return mainService.setWeights(weight);

    }


    @GetMapping(path= "incumbents")//http://localhost:8080/incumbents
    public String loadIncumbents(){

        return mainService.loadIncumbents();
    }


}
