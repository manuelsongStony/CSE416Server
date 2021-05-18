package app.server.rguscdapp.configuration;

import app.server.rguscdapp.entity.*;
import app.server.rguscdapp.enums.Summary;
import app.server.rguscdapp.repository.*;
import app.server.rguscdapp.service.DistrictingService;
import app.server.rguscdapp.service.JobService;
import app.server.rguscdapp.sorting.PopNumber;
import app.server.rguscdapp.sorting.SortByObjectiveScore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.util.*;


@Configuration
public class JsonConfig {
    @Autowired
    private PrecinctRepository precinctRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private DistrictingRepository districtingRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private DistrictingService districtingService;
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {

            //addGeorgiaPrecint();
            addJob("Georgia","/json/Georgia-5000.json");
            addJob("Texas","/json/test_texas2_100.json");



            //System.out.println(((District)(districtRepository.findById(1).orElse(null))).getPrecinctString());
            //System.out.println(((District)(districtRepository.findById(1).orElse(null))).getPrecinctString());
            //System.out.println(((jobRepository.findById(1).orElse(null))).getDistrictings());
        };
    }


    public void addJob(String nameOfState,String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //add state
        State state =new State();
        state.setStateName(nameOfState);
        stateRepository.save(state);

        InputStream inputStream = TypeReference.class.getResourceAsStream(fileName);
        Map<String, Object> jsonJobMap = mapper.readValue(inputStream, Map.class);


        //add job
        Job job =new Job();
        //job.setStateName(state.getStateName());
        job.setState(state);
        //Collection<Districting> dtingsCollection=new ArrayList<>();

        jobService.saveJob(job);
        //jobRepository.save(job);//save job



        List<Object> districtingList = (List<Object>) jsonJobMap.get("plans");

        for(Object o : districtingList) {
            //add Districting
            Districting districting =new Districting();
            districting.setJob(job);
            //List<District> dtCollection=new ArrayList<>();
            districtingRepository.save(districting);//save districting

            int districtingVAP=0;
            int districtingWVAP=0;
            int districtingBVAP=0;
            int districtingHVAP=0;
            int districtingAMINVAP=0;
            int districtingASIANVAP=0;
            int districtingNHPIVAP=0;

            if(o instanceof Map) {


                Map<String,Object> districtingMap = (Map<String,Object>) o;
                List<Object> districtList = (List<Object>) districtingMap.get("districts");

                for(Object districtElement : districtList) {
                    //add District
                    District district =new District();
                    district.setDistricting(districting);

                    if (districtElement instanceof Map) {
                        Map<String,Object> districtMap = (Map<String,Object>) districtElement;
                        if(nameOfState.equals("Texas")){
                            district.setNumber(Integer.parseInt((String)districtMap.get("districtNumber")));
                        }else{
                            district.setNumber((Integer)districtMap.get("districtNumber"));
                        }
                        int vap=((Integer)districtMap.get("VAP")== null)? 0:(Integer)districtMap.get("VAP");
                        districtingVAP+=vap;
                        district.setVAP(vap);

                        int hvap=((Integer)districtMap.get("HVAP")== null)? 0:(Integer)districtMap.get("HVAP");
                        districtingHVAP+=hvap;
                        district.setHVAP(hvap);


                        int wvap=((Integer)districtMap.get("WVAP")== null)? 0:(Integer)districtMap.get("WVAP");
                        districtingWVAP+=wvap;
                        district.setWVAP(wvap);


                        int bvap=((Integer)districtMap.get("BVAP")== null)? 0:(Integer)districtMap.get("BVAP");
                        districtingBVAP+=bvap;
                        district.setBVAP(bvap);

                        district.setAMINVAP(((Integer)districtMap.get("AMINVAP")== null)? 0:(Integer)districtMap.get("AMINVAP") );
                        int aminvap=((Integer)districtMap.get("AMINVAP")== null)? 0:(Integer)districtMap.get("AMINVAP");
                        districtingAMINVAP+=aminvap;
                        district.setAMINVAP(aminvap);


                        int asianvap=((Integer)districtMap.get("ASIANVAP")== null)? 0:(Integer)districtMap.get("ASIANVAP");
                        districtingASIANVAP+=asianvap;
                        district.setASIANVAP(asianvap);


                        int nhpivap=((Integer)districtMap.get("NHPIVAP")== null)? 0:(Integer)districtMap.get("NHPIVAP");
                        districtingNHPIVAP+=nhpivap;
                        district.setNHPIVAP(nhpivap);



                        if(vap==0){
                            vap=hvap+wvap+bvap+aminvap+asianvap+nhpivap;
                            district.setVAP(vap);
                        }


                        if(nameOfState.equals("Texas")){
                            Object pricintList = districtMap.get("precincts");
                            district.setPrecinctString(pricintList.toString());
                        }else{
                            Object pricintList = districtMap.get("precincts");
                            district.setPrecinctString(pricintList.toString());
                        }

                        districtRepository.save(district);
                    }

                    //dtCollection.add(district);
                }

                //districting.setDistricts(dtCollection);



            }

            districting.setVAP(districtingVAP);
            if(districtingVAP==0){
                districting.setVAP(districtingWVAP+districtingBVAP+districtingHVAP+districtingAMINVAP+districtingNHPIVAP);
            }
            districtingRepository.save(districting);
            /*
            districting.setWVAP(districtingWVAP);
            districting.setBVAP(districtingBVAP);
            districting.setHVAP(districtingHVAP);
            districting.setAMINVAP(districtingAMINVAP);
            districting.setASIANVAP(districtingASIANVAP);
            districting.setNHPIVAP(districtingNHPIVAP);


            districtingRepository.save(districting);
            */

            //dtingsCollection.add(districting);

            /*
            System.out.println(districting.getDistricts());
            ArrayList<District> dts=(ArrayList<District>)(districting.getDistricts());
            Collections.sort(dts,new PopNumber());
            double equality=dts.get(dts.size()-1).getVAP()-dts.get(0).getVAP();
            equality=equality/(districting.getVAP()/dts.size());
            districting.setPopEqualityDifference(equality);
            districtingRepository.save(districting);
            */

        }
        //job.setDistrictings(dtingsCollection);
        //jobRepository.save(job);//save job

    }

    public void addGeorgiaPrecint() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Georgia-Geometry.json");
        Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
        //System.out.println(jsonMap.get("type"));

        List<Object> list = (List<Object>) jsonMap.get("features");



        int i=0;
        for(Object o : list) {
            i++;

            if(o instanceof Map) {
                Map<String,Object> map = (Map<String,Object>) o;
                //System.out.println(map.get("properties"));
                Map<String, Object> propertiesMap = (Map<String, Object>) map.get("properties");

                Precinct precinct =new Precinct();
                precinct.setStateName("Georgia");
                precinct.setStatePrecinctId((Integer)propertiesMap.get("ID"));

                precinct.setTotalPopulation((Integer)propertiesMap.get("TOTPOP"));
                precinct.setBlackPopulation((Integer)propertiesMap.get("BLACK"));
                precinct.setHispanicVAP((Integer)propertiesMap.get("HISP"));
                precinct.setAsianPopulation((Integer)propertiesMap.get("ASIAN"));
                precinct.setNativePopulation((Integer)propertiesMap.get("NHPI"));
                precinct.setOtherPopulation((Integer)propertiesMap.get("OTHER")+(Integer)propertiesMap.get("AMIN"));

                precinct.setVAP((Integer)propertiesMap.get("VAP"));
                precinct.setBlackVAP((Integer)propertiesMap.get("BVAP"));
                precinct.setHispanicVAP((Integer)propertiesMap.get("HVAP"));
                precinct.setAsianVAP((Integer)propertiesMap.get("ASIANVAP"));
                precinct.setNativeVAP((Integer)propertiesMap.get("NHPIVAP"));
                precinct.setOtherVAP((Integer)propertiesMap.get("OTHERVAP")+(Integer)propertiesMap.get("AMINVAP"));

                precinct.setNeighbor((String) propertiesMap.get("NEIGHBORS"));

                //Map<String, Object> geoMap = (Map<String, Object>) map.get("geometry");
                Object geoMap = map.get("geometry");
                //System.out.println("code is "+(ArrayList)(geoMap.get("coordinates")).);
                precinct.setGeoData(geoMap.toString());
                //String json = new Gson().toJson(foo );
                //precinct.setGeoData((String) geoMap.get("coordinates"));
                //precinct.setCoordinate((ArrayList) geoMap.get("coordinates"));

                //System.out.println(geoMap.toString());
                precinctRepository.save(precinct);
            }

            System.out.println("int is "+i);
        }
    }

    public void addTexasPrecint(){

    }


}
