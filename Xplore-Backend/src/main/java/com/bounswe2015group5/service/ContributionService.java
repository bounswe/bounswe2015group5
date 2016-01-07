package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;

public interface ContributionService {

    /**
     *
     * @return list of all contributions in database
     */
    Iterable<Contribution> listAllContributionsOfUser(User user);

    /**
     *
     * @param user user object
     * @return list of all contributions of a user
     */
    Iterable<Contribution> listAllContributions();

    /**
     *
     * @param id contribution id
     * @return Contibution with given id
     */
    Contribution getContributionById(int id);

    /**
     *
     * @param contribution to be saved
     * @return contribution object
     */
    Contribution saveContribution(Contribution contribution);

    /**
     * Deletes contribution with given id
     * @param id contribution id of contribution to be deleted
     */
    void deleteContribution(int id);
}
