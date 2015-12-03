package com.bounswe2015group5.services;

import com.bounswe2015group5.domain.Contribution;

public interface ContributionService {
    Iterable<Contribution> listAllContributions();

    Contribution getContributionById(Integer id);

    Contribution saveContribution(Contribution contribution);

    void deleteContribution(Integer id);
}
