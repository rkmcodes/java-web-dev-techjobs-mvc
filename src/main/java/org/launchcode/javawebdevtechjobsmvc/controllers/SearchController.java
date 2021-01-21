package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    ArrayList jobs = new ArrayList();

    @PostMapping(value = "results")
    @ResponseBody
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){

        model.addAttribute("jobs", "Jobs");
        model.addAttribute("columns", columnChoices);

        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            jobs = JobData.findAll();
        }
        if (searchType.equals("all")) {
            jobs = JobData.findByColumnAndValue("all", searchTerm);
            model.addAttribute("title", "All Jobs");
        }
        if (searchType.equals("positionType")) {
            jobs = JobData.findByColumnAndValue("positionType", searchTerm);
            model.addAttribute("title", "Jobs by Position Type");
        }
        if (searchType.equals("employer")) {
            jobs = JobData.findByColumnAndValue("employer", searchTerm);
            model.addAttribute("title", "Jobs by Employer");
        }
        if (searchType.equals("skill")) {
            jobs = JobData.findByColumnAndValue("skill", searchTerm);
            model.addAttribute("title", "Jobs by Skill");
        }
        if (searchType.equals("location")) {
            jobs = JobData.findByColumnAndValue("location", searchTerm);
            model.addAttribute("title", "Jobs by Location");
        }

        return "Results: " + jobs;
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.





}
