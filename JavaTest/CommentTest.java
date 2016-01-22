import static org.junit.Assert.*;

import org.junit.Test;

import com.bounswe2015group5.model.Comment;
import com.bounswe2015group5.model.Contribution;
import com.bounswe2015group5.model.User;

public class CommentTest {

	String content = "Content";
	String content2 = "Content2";
	User user = new User();
	User user2 = new User();
	Contribution contribution  = new Contribution();
	Contribution contribution2  = new Contribution();
	Comment comment = new Comment(content,user,contribution);
	
	@Test
	public void test() {
		
		assertEquals(comment.getContent(),content);
		assertNotEquals(comment.getContent(),content2);
		assertEquals(user,comment.getUser());
		assertNotEquals(user2,comment.getUser());
		assertEquals(contribution,comment.getContribution());
		assertNotEquals(contribution2,comment.getContribution());
	}

}
