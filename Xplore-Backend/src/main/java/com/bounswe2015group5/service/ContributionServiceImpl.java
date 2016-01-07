package com.bounswe2015group5.service;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;
import com.bounswe2015group5.repository.ContributionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    ContributionRepo contributionRepo;

    /**
     *
     * @return list of all contributions in database
     */
    @Override
    public Iterable<Contribution> listAllContributions() {
        return contributionRepo.findAll();
    }

    /**
     *
     * @param user user object
     * @return list of all contributions of a user
     */
    @Override
    public List<Contribution> listAllContributionsOfUser(User user) {
        return contributionRepo.findByCreator_Username(user.getUsername());
    }

    /**
     *
     * @param id contribution id
     * @return Contibution with given id
     */
    @Override
    public Contribution getContributionById(int id) {
        return contributionRepo.findOne(id);
    }

    /**
     *
     * @param contribution to be saved
     * @return contribution object
     */
    @Override
    public Contribution saveContribution(Contribution contribution) {
        return contributionRepo.save(contribution);
    }

    /**
     * Deletes contribution with given id
     * @param id contribution id of contribution to be deleted
     */
    @Override
    public void deleteContribution(int id) {
        contributionRepo.delete(id);
    }
}
