package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.repository.ContributionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    ContributionRepo contributionRepo;

    @Override
    public Iterable<Contribution> listAllContributions() {
        return contributionRepo.findAll();
    }

    @Override
    public Contribution getContributionById(int id) {
        return contributionRepo.findOne(id);
    }

    @Override
    public Contribution saveContribution(Contribution contribution) {
        return contributionRepo.save(contribution);
    }

    @Override
    public void deleteContribution(int id) {
        contributionRepo.delete(id);
    }
}
