package com.smu.service;


import com.smu.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smu.model.Job;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        List<Job> allJobs = jobRepository.findAll();
        return allJobs;
    }

    public boolean deleteJobById(Long id) {
        if (jobExists(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
        // need to account for job not being found here

    }

    public Job getJobById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        // checks if job exists
        if (job.isPresent()) {
            return job.get();
        }
        else {
            // need to do something else here probably idk
            return null;
        }

    }

    public Long addJob(Job job) {
        /*
        jobRepository.save(job);
        System.out.println(job.getEmail());
        return job.getId();
        */

        jobRepository.save(job);
        return job.getId();


    }

    public boolean jobExists(Long id) {
        return jobRepository.existsById(id);
    }

}
