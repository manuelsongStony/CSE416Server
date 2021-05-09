package app.server.rguscdapp.controller;


import app.server.rguscdapp.entity.Job;
import app.server.rguscdapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path= "job") //http://localhost:8080/job
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/addJob") //http://localhost:8080/job/addJob
    public Job addJob(@RequestBody Job job){
        return jobService.saveJob(job);
    }

    @PostMapping("/addJobs")
    public List<Job> addJobs(@RequestBody List<Job> jobs){
        return jobService.saveJobs(jobs);
    }
    @GetMapping("/jobs")
    public List<Job> findAllJobs(){
        return jobService.getJobs();
    }

    @GetMapping("/jobById/{id}")
    public Job findJobById(@PathVariable int id){
        return jobService.getJobById(id);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteJob(@PathVariable int id){
        return jobService.deleteJob(id);
    }

    @PutMapping("/applyConstraints")// /update
    public Job updateJob(@RequestBody Job job){
        return jobService.updateJob(job);
    }
}