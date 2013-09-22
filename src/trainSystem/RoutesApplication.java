package trainSystem;

import java.util.ArrayList;
/*Class is used to create trainRoutes with the user's input.  It also checks the arguments given to ensure that proper
 * values are being sent to trainRoutes.  It also serves to read and interpret trainRoutes' return statements.  
 */

public class RoutesApplication {
	public TrainRoutes trainRoutes;
	public final static int exactStops = 0;
	public final static int maxStops   = 1;
	public final static int maxDist    = 2;

	/*Creates an instance of trainRoutes and adds the paths designated by the user's arguments*/
	public void createTrainRoutes(String args []){
		trainRoutes = new TrainRoutes();
		for(String path : args){
			if(path.length()<3){
				System.err.println("Error missing input");
				throw new IllegalArgumentException();
			}
			
			if(!path.matches("[A-Z][A-Z][0-9]+"))
				throw new IllegalArgumentException("Error input order must be townA townB distance.  Example AE8.");
			
			
			if(path.substring(0,1).matches(path.substring(1,2)))
				throw new IllegalArgumentException("Error townA must be different from townB.  Example. AA8 is not accepted.");			
			
			
			trainRoutes.addRoute(path.substring(0,1), path.substring(1,2), Integer.parseInt(path.substring(2)));
		}
	}

	/*Generates possible routes that a user could take to go from townA to townB */
	public String possibleRoutes(String townA, String townB){
		ArrayList<String> possiblePaths = trainRoutes.generatePossiblePaths(townA, townB);
		if(possiblePaths.isEmpty())
			return "NO SUCH ROUTE";
		return possiblePaths.toString();
	}
	
	/*Reads in trainRoutes minDist function and if the value is less than 1, then no such route exists*/
	public String minDistance(String townA, String townB){
		int distance = trainRoutes.minDist(townA, townB); 
		if(distance <1)
			return "NO SUCH ROUTE";
		return ""+distance;
	
	}
	
	
	/*Based on the user's type preference, it will return the number of trips or unique paths that they can take.*/
	public String possiblePaths(String townA, String townB, int value, int type){
		int numberOfTrips = 0;
		
		if (type == maxStops)
			numberOfTrips = trainRoutes.totalTripsMax(townA, townB, value);
		
		else if (type == exactStops)
			numberOfTrips = trainRoutes.totalTripsExact(townA, townB, value);
		
		else if (type == maxDist)
			numberOfTrips = trainRoutes.totalTripsDist(townA, townB, value, 0);
		
		if (numberOfTrips < 1)
			return "NO SUCH ROUTE";
		
		return ""+numberOfTrips;
		
	}
	
	/*Returns the distance from a specified path. Makes sure that the path's format is valid ex. A-E is good AE is not*/
	public String getDistance(String path){
		if(!path.contains("-")){
			throw new IllegalArgumentException("Error request requires a - for input.");
			}
		int distance = trainRoutes.specific_path(path); 
		if(distance <1)
			return "NO SUCH ROUTE";
		return ""+distance;
	}
}
