package org.launchcode.techjobs.persistent.controllers;
import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//to push up
@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    @RequestMapping("/") //empty string
    public String index(Model model) {
        //fetch all data from skill repository
        Iterable<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);
        //show all data when on employers
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }
    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }

        //save the data using the Repository
        skillRepository.save(newSkill);

        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {
        //look up skill by unique Id
        Optional<Skill> optionalSkill = skillRepository.findById(skillId);
        //if present grab it
        if (optionalSkill.isPresent()) {
            Skill skill = optionalSkill.get();
            model.addAttribute("skill", skill);
            //display it
            return "skills/view";
            //if not found go back to skill list
        } else {
            return "redirect:../";
        }

    }
}
