package trainSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class TrainRoutes {
	private HashMap<String, Town> availableTowns = new HashMap<String, Town>();
	
	public void addRoute (String townA, String townB, Integer distance){
		Town tempTown = availableTowns.get(townA);
		if(tempTown ==null){
			tempTown = new Town(townA);
			availableTowns.put(townA, tempTown);
		}
		
		tempTown.addRoute(townB, distance);
	}
	
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
	
	public int totalTripsMax(String townA, String townB, int maxStops){
	    if(maxStops<1){
            return 0;   
        }
       
        int  acceptableRoutes = 0;
        Town tempTown         = availableTowns.get(townA);
       
        if(tempTown==null)
            return 0;
       
        if(tempTown.getDistance(townB) > 0){
           acceptableRoutes++;
        }
        Set<String> availablePaths = tempTown.possibleRoutes();
        for(String path : availablePaths){
        	acceptableRoutes += totalTripsMax(path, townB, maxStops-1);
        }
        return acceptableRoutes;
       
    }
   
    public int specific_path(String [] towns){
       
        if(towns.length<2){
            System.err.println("Must enter more than one town/node for a path.");
            return -1;
        }
       
        int distance = 0;
        int index    = 0;
        Town tempTown;
        String townA, townB;
       
        while(index>towns.length-1){
           
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
   
    public int specific_path(String path){
        String[] towns = path.split("-");

        
        if(towns.length<2){
            System.err.println("Must enter more than one town/node for a path.");
            return -1;
        }
        
       
        int distance = 0;
        int index    = 0;
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
    				if(!tempTown.status()){
    					tempTown.visited();
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
	
	
	public void reset() {
		for(Town town : availableTowns.values()){
			if(town.status())
				town.visited();
		}
	}

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
