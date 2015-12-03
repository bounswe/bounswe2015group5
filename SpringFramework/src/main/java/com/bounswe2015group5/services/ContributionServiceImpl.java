package com.bounswe2015group5.services;

import com.bounswe2015group5.domain.Contribution;
import com.bounswe2015group5.repositories.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributionServiceImpl implements ContributionService {
    private ContributionRepository contributionRepository;

    @Autowired
    public void setContributionRepository(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }


    @Override
    public Iterable<Contribution> listAllContributions() {
        return contributionRepository.findAll();
    }

    @Override
    public Contribution getContributionById(Integer id) {
        return contributionRepository.findOne(id);
    }

    @Override
    public Contribution saveContribution(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    @Override
    public void deleteContribution(Integer id) {
        contributionRepository.delete(id);
    }
}
