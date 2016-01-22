import static org.junit.Assert.*;

import org.junit.Test;

import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;
import com.bounswe2015group5.model.UserRate;

public class UserRateTest {

	Contribution contribution  = new Contribution();
	Contribution contribution2 = new Contribution();
	User user = new User();
	User user2 = new User();
	Integer value1 = 0;
	Integer value2 = 1;
	
	UserRate userRate  = new UserRate(contribution,user,value1);
	
	@Test
	public void test() {

		assertEquals(contribution,userRate.getContribution());
		assertNotEquals(contribution2, userRate.getContribution());
		assertEquals(user,userRate.getUser());
		assertNotEquals(user2,userRate.getUser());
		assertEquals(value1,userRate.getValue());
		assertNotEquals(value2,userRate.getValue());

	}

}
