package com.bounswe2015group5.controllers;

import com.bounswe2015group5.domain.Contribution;
import com.bounswe2015group5.domain.Tag;
import com.bounswe2015group5.services.ContributionService;
import com.bounswe2015group5.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TagController {

    private TagService tagService;
    private ContributionService contributionService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setContributionService(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("tags", tagService.listAllTags());
        return "tags";
    }

    @RequestMapping("tag/{id}")
    public String showTag(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "tagshow";
    }

    @RequestMapping("tag/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "tagform";
    }

    @RequestMapping("tag/new")
    public String newTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "tagform";
    }

    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public String saveTag(Tag tag) {
        tagService.saveTag(tag);
        return "redirect:/tag/" + tag.getId();
    }

    @RequestMapping("tag/delete/{id}")
    public String delete(@PathVariable Integer id) {
        tagService.deleteTag(id);
        return "redirect:/tags";
    }

    @RequestMapping("tag/{tagId}/addContribution/{contributionId}")
    public String addContribution(@PathVariable Integer tagId, @PathVariable Integer contributionId, Model model) {
        Contribution c = contributionService.getContributionById(contributionId);
        Tag t = tagService.getTagById(tagId);
        t.getContributions().add(c);
        tagService.saveTag(t);
        model.addAttribute("tag", t);
        return "tagshow";
    }
}
