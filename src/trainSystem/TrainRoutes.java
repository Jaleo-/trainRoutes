package trainSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/*Holds all the towns a user can start in a hashMap where the key is the town's name and the value is the Town object.
 * 
 */

public class TrainRoutes {
	private HashMap<String, Town> availableTowns = new HashMap<String, Town>();
	
	/*Adds a new route for townA.  If townA does not already exists, it makes a new Town instance.*/
	public void addRoute (String townA, String townB, Integer distance){
		Town tempTown = availableTowns.get(townA);
		if(tempTown ==null){
			tempTown = new Town(townA);
			availableTowns.put(townA, tempTown);
		}
		
		tempTown.addRoute(townB, distance);
	}
	
	/*Computes the number of possible trips a user can take where the number of stops exactly equals
	 * the user's specifications.
	 */
	public int totalTripsExact(String townA, String townB, int exactStops){
		 int  acceptableRoutes = 0;
		 if(exactStops < 1){
	            return 0;   
	     }
	       
        Town tempTown         = availableTowns.get(townA);
       
        if(tempTown==null)
            return 0;
       
        if((tempTown.getDistance(townB)>0)&&(exactStops==1)){
           acceptableRoutes++;
        }
        Set<String> availablePaths = tempTown.possibleRoutes();
        for(String path : availablePaths){
        	acceptableRoutes += totalTripsExact(path, townB, exactStops-1);
        }
		
		return acceptableRoutes;
	}
	
	
	/*Computes the number of possible trips a user can take where the number of stops must be less than or equal to the maxStops. */
	public int totalTripsMax(String townA, String townB, int maxStops){
	    if(maxStops<1)
            return 0;   
        
       
        int  acceptableRoutes = 0;
        Town tempTown         = availableTowns.get(townA);
       
        if(tempTown==null)
            return 0;
       
        if(tempTown.getDistance(townB) > 0)
           acceptableRoutes++;
        
        Set<String> availablePaths = tempTown.possibleRoutes();
        for(String path : availablePaths){
        	acceptableRoutes += totalTripsMax(path, townB, maxStops-1);
        }
        
        return acceptableRoutes;
       
    }
   
/*Computes the distance from a specified path, ex. A-B-C.  If no such path exists, returns 0.*/
    public int specific_path(String path){
        String[] towns = path.split("-");
        int distance   = 0;
        int index      = 0;
        Town tempTown;
        String townA, townB;
        
        
        while(index<towns.length-1){
            townA    = towns[index];
            townB    = towns[index+1];

            tempTown = availableTowns.get(townA);
            if(tempTown==null)
                return 0;
            else if(tempTown.getDistance(townB)<0)
                return 0;
           
            distance += tempTown.getDistance(townB);
            index++;
           
        }
        
        return distance;
    }

    
    /*Function is used to find all possible paths from townA and townB.  Function uses recursion to get all routes.*/
    public ArrayList<String> generatePossiblePaths(String townA, String townB){
    	ArrayList<String> possiblePaths = new ArrayList<String>();
    	Town tempTown = availableTowns.get(townA);
    	if(tempTown!=null){
    		if( (tempTown.getDistance(townB)) > 0){
    			possiblePaths.add(townA+"-"+townB);
    		}
    		Set<String> availableStops = tempTown.possibleRoutes();
    		
    		for(String nextStop : availableStops){
    			tempTown = availableTowns.get(nextStop);
    			if(tempTown!=null){
    				if(tempTown.status()==false){
    					tempTown.visited();                  //Prevents StackOverflow
    					availableTowns.put(nextStop, tempTown);
    					ArrayList<String> otherPaths = generatePossiblePaths(nextStop, townB);
    					tempTown.visited();
    					availableTowns.put(nextStop, tempTown);
    					for(String path: otherPaths){
    						possiblePaths.add(townA+"-"+path);
    					}
    				}
    			}
    		}
    	}
    	return possiblePaths;
    }
    
    /*Computes the minimum distance from townA to townB using generatePossiblePaths and specific_path*/
	public int minDist (String townA, String townB){
		int min = -1;
		int distance;
		ArrayList<String> possibleRoutes = generatePossiblePaths(townA, townB);
		if(possibleRoutes.isEmpty())
			return -1;
		
		for(String path : possibleRoutes){
			distance = specific_path(path);
			if((min == -1) || (distance < min))
				min = distance;
		}
		return min;
	}
	
	

/*Computes the total number of trips a user can take from townA to townB that is less than a specified distance*/
	public int totalTripsDist(String townA, String townB, int limit, int totalDist) {
		if(totalDist >= limit)
			return 0;
		int acceptableRoutes = 0;
		Town tempTown         = availableTowns.get(townA);
	       
        if(tempTown==null)
            return 0;
        
        int tempDistance = tempTown.getDistance(townB); 
        if( (tempDistance > 0) && ((totalDist+tempDistance) < limit) ){
           acceptableRoutes++;
        }
        Set<String> availablePaths = tempTown.possibleRoutes();
        for(String nextStop : availablePaths){
        	acceptableRoutes += totalTripsDist(nextStop, townB, limit, totalDist+tempTown.getDistance(nextStop));
        }
		
		return acceptableRoutes;
		
			
	}
	


}
