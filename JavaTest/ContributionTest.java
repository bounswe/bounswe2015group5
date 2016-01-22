import static org.junit.Assert.*;

import org.junit.Test;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;

public class ContributionTest {

	User creator = new User();
	User creator2 = new User();
	String title = "title";
	String title2 = "title2";
	String content = "content";
	String content2 = "content2";
	String referenceList = "referenceList";
	String referenceList2  = "referenceList2";
	
	Contribution contribution = new Contribution(title,content,referenceList,creator);
	Contribution contribution2 = new Contribution(title,content,referenceList,creator);

	@Test
	public void test() {
		
		assertEquals(creator,contribution.getCreator());
		assertNotEquals(creator2,contribution.getCreator());
		assertEquals(title, contribution.getTitle());
		assertNotEquals(title2, contribution.getTitle());
		assertEquals(referenceList, contribution.getReferenseList());
		assertNotEquals(referenceList2, contribution.getReferenseList());
		
		//Equals method does not work properly when two different contribution objects compared with same values.
		assertFalse(contribution.equals(contribution2));
	}

}
