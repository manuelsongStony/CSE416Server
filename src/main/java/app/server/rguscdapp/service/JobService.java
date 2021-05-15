package app.server.rguscdapp.service;


import app.server.rguscdapp.entity.Job;
import app.server.rguscdapp.entity.JobSummary;
import app.server.rguscdapp.repository.JobRepository;

import app.server.rguscdapp.repository.JobSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobSummaryRepository jobSummaryRepository;

    public Job saveJob(Job job){
        Job jobNew=jobRepository.save(job);
        jobSummaryRepository.save(new JobSummary(jobNew));

        return jobNew;
    }
    public List<Job> saveJobs(List<Job> jobs){

        return jobRepository.saveAll(jobs);
    }
    public List<Job> getJobs(){

        return jobRepository.findAll();
    }
    public Job getJobById(int id){

        return jobRepository.findById(id).orElse(null);
    }

    public String deleteJob(int id){
        jobRepository.deleteById(id);
        return "job remove!!"+id;
    }

    public Job updateJob(Job job){
        Job existingJob=jobRepository.findById(job.getJobId()).orElse(null);
        existingJob.setMinority(job.getMinority());
        existingJob.setMinMajorityMinorityDistricts(job.getMinMajorityMinorityDistricts());

        existingJob.setCompactnessType(job.getCompactnessType());
        existingJob.setMincompactness(job.getMincompactness());

        existingJob.setPopType(job.getPopType());
        existingJob.setPopConstraint(job.getPopConstraint());

        existingJob.setIncumbents(job.getIncumbents());

        existingJob.applyConstraints();

        return jobRepository.save(existingJob);
    }


}
