package trainSystem;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RoutesApplicationTest extends RoutesApplication{
	
	
	
	/*TestSample uses the sample data sent from the email to create a directed graph for a one way train system.  The test 
	 * makes sure that the data is produced is valid as the results have been calculated before hand.
	 */
	@Test
	public void testSample(){
		String [] args = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
		createTrainRoutes(args);
		
		
		assertTrue(getDistance("A-B-C").matches("9"));
		assertTrue(getDistance("A-D").matches("5"));
		assertTrue(getDistance("A-D-C").matches("13"));
		assertTrue(getDistance("A-E-B-C-D").matches("22"));
		assertTrue(getDistance("A-E-D").matches("NO SUCH ROUTE"));
		assertTrue(possiblePaths("C", "C", 3, maxStops).matches("2"));
		assertTrue(possiblePaths("A", "C", 4, exactStops).matches("3"));
		assertTrue(minDistance("A", "C").matches("9"));
		assertTrue(minDistance("B", "B").matches("9"));
		assertTrue(possiblePaths("C", "C", 30, maxDist).matches("7"));
		
		
	}
	
	/*TestInvalidInput is to ensure that the program handles invalid input.  Such examples would include a string with only one town referenced ('A7')
	 * or if the paths weight is not there ('AB').  Granted however, the program assumes that each town is given only one letter for identification (A-Z).
	 */
	
	@Test
	public void testInvalidInput(){
		//First test is for when we get a repeat in town names like AA7 as TownA should differ from TownB
		String [] args = {"AA7", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};
		RoutesApplication trainApp = new RoutesApplication();
		try{
			trainApp.createTrainRoutes(args);
			fail("Expected IllegalArugmentException");
		}
		catch(IllegalArgumentException e){
		}
		
		//The next test goes for input that exceeds the designated naming system for each town (one character).
		String [] args2 = {"ADDSASDASD7", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};

		try{
			trainApp.createTrainRoutes(args2);
			fail("Expected IllegalArugmentException");
		}
		catch(IllegalArgumentException e){
		}
		
		//The next is used to ensure that the program is expecting a numerical value for the path's weight
		String [] args3 = {"ADD", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"};

		try{
			trainApp.createTrainRoutes(args3);
			fail("Expected IllegalArugmentException");
		}
		catch(IllegalArgumentException e){
		}
		
		//This next should not throw an exception.  This test is to show that the program is not restricted to single digit values
		//for the path length.
		String [] args4 = {"AD11", "BC444", "CD8888", "DC88888", "DE6", "AD5", "CE2", "EB3", "AE7"};

		try{
			trainApp.createTrainRoutes(args4);
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
			fail("Did not expect an IllegalArugmentExcpetion");

		}
		
		


	}

	

}
