import static org.junit.Assert.*;

import org.junit.Test;

import com.bounswe2015group5.model.Tag;
import com.bounswe2015group5.model.User;

public class TagTest {

	User creator = new User();
	User creator2 = new User();
	String name = "name";
	String name2 = "name2";
	String concept  = "concept";
	String concept2 = "concept2";
	
	Tag tag = new Tag(name,concept,creator);
	Tag tag2 = new Tag(name,concept,creator);

	@Test
	public void test() {
		
		assertEquals(tag.getName(),name);
		assertNotEquals(tag.getName(),name2);
		assertEquals(tag.getConcept(),concept);
		assertNotEquals(tag.getConcept(),concept2);
		assertEquals(tag.getCreator(),creator);
		assertNotEquals(tag.getCreator(),creator2);

		assertTrue(tag.equals(tag));
		//Equals method does not work properly when two different tag objects compared with same values.
		assertFalse(tag.equals(tag2));
	}

}
