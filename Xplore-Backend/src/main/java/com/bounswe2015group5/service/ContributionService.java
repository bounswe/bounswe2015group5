package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Contribution;

public interface ContributionService {

    Iterable<Contribution> listAllContributions();
    Contribution getContributionById(int id);
    Contribution saveContribution(Contribution contribution);
    void deleteContribution(int id);

}
