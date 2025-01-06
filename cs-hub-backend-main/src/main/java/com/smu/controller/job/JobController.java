package com.smu.controller.job;


import com.smu.service.JobService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.smu.model.Job;

import javax.validation.Valid;



@RequiredArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test successful!");
    }

    //note: the requestbody needs an id, though the one you give will be ignored
    @PostMapping("/add")
    public ResponseEntity<Long> addJob(@Valid @RequestBody Job job) {
        Long jobId = jobService.addJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobId);
    }

    @GetMapping("/listjobs")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean isDeleted = jobService.deleteJobById(id);
        return isDeleted ? ResponseEntity.ok("Job deleted.") 
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return (job != null) ? ResponseEntity.ok(job) 
                             : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
