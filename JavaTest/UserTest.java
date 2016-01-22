import static org.junit.Assert.*;

import org.junit.Test;

import com.bounswe2015group5.model.User;

public class UserTest {

	String email = "email";
	String email2 = "email2";
	String username = "username";
	String username2 = "username2";
	String password=  "password";
	String password2  = "password2";
	
	User user = new User(username,password,email);
	
	@Test
	public void test() {
		
		assertEquals(username,user.getUsername());
		assertNotEquals(username2,user.getUsername());
		assertEquals(password,user.getPassword());
		assertNotEquals(password2,user.getEmail());
		assertEquals(email,user.getEmail());
		assertNotEquals(email2,user.getEmail());

	}

}
