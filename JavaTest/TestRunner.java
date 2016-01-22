import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
	      Result resultContribution = JUnitCore.runClasses(ContributionTest.class);
	      Result resultComment = JUnitCore.runClasses(CommentTest.class);
	      Result resultTag = JUnitCore.runClasses(TagTest.class);
	      Result resultUser = JUnitCore.runClasses(UserTest.class);
	      Result resultUserRate = JUnitCore.runClasses(UserRateTest.class);

	      for (Failure failure : resultContribution.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(resultContribution.wasSuccessful());

	      for (Failure failure : resultComment.getFailures()) {
		         System.out.println(failure.toString());
		      }
	      System.out.println(resultComment.wasSuccessful());

	      for (Failure failure : resultTag.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(resultTag.wasSuccessful());

	      for (Failure failure : resultUser.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(resultUser.wasSuccessful());

	      for (Failure failure : resultUserRate.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(resultUserRate.wasSuccessful());
	   }
}
