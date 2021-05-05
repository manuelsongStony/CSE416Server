package app.server.rguscdapp.controller;





import app.server.rguscdapp.entity.District;
import app.server.rguscdapp.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path= "district") //http://localhost:8080/district
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping("/addDistrict")
    public District addDistrict(@RequestBody District district){
        return districtService.saveDistrict(district);
    }

    @PostMapping("/addDistricts")
    public List<District> addDistricts(@RequestBody List<District> districts){
        return districtService.saveDistricts(districts);
    }
    @GetMapping("/districts")
    public List<District> findAllDistricts(){
        return districtService.getDistricts();
    }

    @GetMapping("/districtById/{id}")
    public District findDistrictById(@PathVariable int id){
        return districtService.getDistrictById(id);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteDistrict(@PathVariable int id){
        return districtService.deleteDistrict(id);
    }



}