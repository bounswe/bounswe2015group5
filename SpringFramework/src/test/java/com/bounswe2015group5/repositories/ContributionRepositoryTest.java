package com.bounswe2015group5.repositories;

import com.bounswe2015group5.configuration.RepositoryConfiguration;
import com.bounswe2015group5.domain.Contribution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ContributionRepositoryTest {

    private ContributionRepository contributionRepository;

    @Autowired
    public void setContributionRepository(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    @Test
    public void testSaveContribution() {
        //setup contribution
        Contribution contribution = new Contribution();
        contribution.setTitle("Sample Title");
        contribution.setContent("Sample Content");

        //save contribution, verify has ID value after save
        assertNull(contribution.getId()); //null before save
        contributionRepository.save(contribution);
        assertNotNull(contribution.getId()); //not null after save

        //fetch from DB
        Contribution fetchedContribution = contributionRepository.findOne(contribution.getId());

        //should not be null
        assertNotNull(fetchedContribution);

        //should equal
        assertEquals(contribution.getId(), fetchedContribution.getId());
        assertEquals(contribution.getContent(), fetchedContribution.getContent());

        //update title and save
        fetchedContribution.setTitle("New Title");
        contributionRepository.save(fetchedContribution);

        //get from DB, should be updated
        Contribution fetchedUpdatedContribution = contributionRepository.findOne(fetchedContribution.getId());
        assertEquals(fetchedContribution.getTitle(), fetchedUpdatedContribution.getTitle());

        //verify count of contributions in DB
        long contributionCount = contributionRepository.count();
        assertEquals(contributionCount, 1);

        //get all contributions, list should only have one
        Iterable<Contribution> contributions = contributionRepository.findAll();

        int count = 0;

        for (Contribution t : contributions) {
            count++;
        }

        assertEquals(count, 1);
    }
}
