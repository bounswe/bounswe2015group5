package com.bounswe2015group5.controllers;

import com.bounswe2015group5.domain.Contribution;
import com.bounswe2015group5.services.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContributionController {

    private ContributionService contributionService;

    @Autowired
    public void setContributionService(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @RequestMapping(value = "/contributions", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("contributions", contributionService.listAllContributions());
        return "contributions";
    }

    @RequestMapping("contribution/{id}")
    public String showContribution(@PathVariable Integer id, Model model) {
        model.addAttribute("contribution", contributionService.getContributionById(id));
        return "contributionshow";
    }

    @RequestMapping("contribution/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("contribution", contributionService.getContributionById(id));
        return "contributionform";
    }

    @RequestMapping("contribution/new")
    public String newContribution(Model model) {
        model.addAttribute("contribution", new Contribution());
        return "contributionform";
    }

    @RequestMapping(value = "contribution", method = RequestMethod.POST)
    public String saveContribution(Contribution contribution) {
        contributionService.saveContribution(contribution);
        return "redirect:/contribution/" + contribution.getId();
    }

    @RequestMapping("contribution/delete/{id}")
    public String delete(@PathVariable Integer id) {
        contributionService.deleteContribution(id);
        return "redirect:/contributions";
    }
}
