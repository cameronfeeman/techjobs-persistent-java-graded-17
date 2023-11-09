package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    //add field employerRepository
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Job> jobs = jobRepository.findAll();
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobs);

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");

    //Get data from employerRepository
    Iterable<Employer> employers = employerRepository.findAll();

    //Add the data
    model.addAttribute("employers", employers);
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId) {

        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }
        //get employer based off ID from employerRepository
        Optional<Employer> employerOptional = employerRepository.findById((employerId));

        if (employerOptional.isPresent()) {
            Employer selectedEmployer = employerOptional.get();

            //set the selected employer for the new job
            newJob.setEmployer(selectedEmployer);
        } else {
            return "redirect:";
        }
        jobRepository.save(newJob);

        return "redirect:/jobs";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
