import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {


    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping
    public String index(Model model) {
        //fetch all data from employer repository
        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers", employers);
        //show all data when on employers
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }

        //save the data using the Repository
        employerRepository.save(newEmployer);

        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
        //look up employer by unique Id
        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        //if present grab it
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            model.addAttribute("employer", employer);
            //display it
            return "employers/view";
            //if not found go back to employer list
        } else {
            return "redirect:../";
        }

    }
}
