package app.server.rguscdapp.controller;





import app.server.rguscdapp.entity.Districting;
import app.server.rguscdapp.service.DistrictingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path= "districting") //http://localhost:8080/districting
public class DistrictingController {

    @Autowired
    private DistrictingService districtingService;

    @PostMapping("/addDistricting")
    public Districting addDistricting(@RequestBody Districting districting){
        return districtingService.saveDistricting(districting);
    }

    @PostMapping("/addDistrictings")
    public List<Districting> addDistrictings(@RequestBody List<Districting> districtings){
        return districtingService.saveDistrictings(districtings);
    }
    @GetMapping("/districtings")
    public List<Districting> findAllDistrictings(){
        return districtingService.getDistrictings();
    }

    @GetMapping("/districtingById/{id}")
    public Districting findDistrictingById(@PathVariable int id){
        return districtingService.getDistrictingById(id);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteDistricting(@PathVariable int id){
        return districtingService.deleteDistricting(id);
    }




}