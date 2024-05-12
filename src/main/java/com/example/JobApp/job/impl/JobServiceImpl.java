package com.example.JobApp.job.impl;

import com.example.JobApp.job.Job;
import com.example.JobApp.job.JobRepository;
import com.example.JobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updatedJob(Long id, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {

            Job job = jobOptional.get();

            if (job.getId().equals(id)){
                job.setTitle(updateJob.getTitle());
                job.setDescription(updateJob.getDescription());
                job.setMinSalary(updateJob.getMinSalary());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setLocation(updateJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return  true;
        } catch (Exception e){
            return false;
        }

    }

}
