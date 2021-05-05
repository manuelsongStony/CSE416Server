package app.server.rguscdapp.service;

import app.server.rguscdapp.entity.District;
import app.server.rguscdapp.repository.DistrictRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    public District saveDistrict(District district){

        return districtRepository.save(district);
    }
    public List<District> saveDistricts(List<District> districts){

        return districtRepository.saveAll(districts);
    }
    public List<District> getDistricts(){

        return districtRepository.findAll();
    }
    public District getDistrictById(int id){
        return districtRepository.findById(id).orElse(null);
    }
    public String deleteDistrict(int id){
        districtRepository.deleteById(id);
        return "district remove!!"+id;
    }


}
