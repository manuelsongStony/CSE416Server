package app.server.rguscdapp.service;

import app.server.rguscdapp.entity.Districting;
import app.server.rguscdapp.repository.DistrictingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictingService {
    @Autowired
    private DistrictingRepository districtingRepository;

    public Districting saveDistricting(Districting districting){

        return districtingRepository.save(districting);
    }
    public List<Districting> saveDistrictings(List<Districting> districtings){
        return districtingRepository.saveAll(districtings);
    }
    public List<Districting> getDistrictings(){

        return districtingRepository.findAll();
    }
    public Districting getDistrictingById(int id){

        return districtingRepository.findById(id).orElse(null);
    }
    public String deleteDistricting(int id){
        districtingRepository.deleteById(id);
        return "districting remove!!"+id;
    }

}
