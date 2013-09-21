package trainSystem;


public class RoutesApplication {
	public static TrainRoutes trainRoutes;
	public final static int exactStops = 0;
	public final static int maxStops   = 1;
	public final static int maxDist    = 2;
	public static void main(String args[])
	{	
		trainRoutes = new TrainRoutes();
		for(String path : args){
			if(path.length()<3){
				System.err.println("Error missing input");
				System.exit(1);
			}
			
			if(!path.matches("[A-Z][A-Z][0-9]")){
				System.err.println("Error must have a number for the weighted path instead of "+path);
				System.exit(1);
			}
			
			trainRoutes.addRoute(path.substring(0,1), path.substring(1,2), Integer.parseInt(path.substring(2,3)));
		}
		

		System.out.println(getDistance("A-B-C-"));
		System.out.println(getDistance("A-D"));
		System.out.println(getDistance("A-D-C"));
		System.out.println(getDistance("A-E-B-C-D"));
		System.out.println(getDistance("A-E-D"));
		System.out.println(possiblePaths("C", "C", 3, maxStops));
		System.out.println(possiblePaths("A", "C", 4, exactStops));
		System.out.println(minDistance("A", "C"));
		System.out.println(minDistance("B", "B"));
		System.out.println(possiblePaths("C", "C", 30, maxDist));
		

	}
	
	private static String minDistance(String townA, String townB){
		int distance = trainRoutes.minDist(townA, townB); 
		if(distance <1)
			return "NO SUCH ROUTE";
		return ""+distance;
	
	}
	
	private static String possiblePaths(String townA, String townB, int value, int type){
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

	private static String getDistance(String path){
		if(!path.contains("-")){
			System.err.println("Error request requires a - for input.");
			System.exit(1);
		}
		int distance = trainRoutes.specific_path(path); 
		if(distance <1)
			return "NO SUCH ROUTE";
		return ""+distance;
	}
}
