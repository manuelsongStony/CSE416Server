package app.server.rguscdapp.configuration;

import app.server.rguscdapp.entity.Job;
import app.server.rguscdapp.entity.Precinct;
import app.server.rguscdapp.repository.JobRepository;
import app.server.rguscdapp.repository.PrecinctRepository;
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
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Configuration
public class JsonConfig {
    @Autowired
    private PrecinctRepository precinctRepository;

    @Bean
    CommandLineRunner commandLineRunner(JobRepository jobRepository){
        return args -> {
            /*
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Job> typeReference = new TypeReference<Job>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Georgia-5000.json");


            //test_texas2_100.json
            //json/Georgia-5000.json
            //json/Georgia-Geometry.json

            //job.setEnactedDistricting(/json/enactedDistricting)
            //job.setState(stateRepository.findById(id).orElse(null))
            //job.setDistricting


            Job job = mapper.readValue(inputStream,typeReference);

            //userService.save(users);
            jobRepository.save(job);
            System.out.println("Job Saved!");

             */
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


            
//            System.out.println((precinctRepository.findById(1)).get().getStateName());
//            System.out.println((precinctRepository.findById(1)).get().getGeoData());
//            Precinct precinct =new Precinct();
//            precinctRepository.save(job);


        };
    }
}
