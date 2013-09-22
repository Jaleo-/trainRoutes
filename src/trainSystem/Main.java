package trainSystem;


public class Main {
	public final static int exactStops = 0;
	public final static int maxStops   = 1;
	public final static int maxDist    = 2;
	
	public static void main (String args[]){
		RoutesApplication routesApp = new RoutesApplication();
		routesApp.createTrainRoutes(args);
		System.out.println(routesApp.getDistance("A-B-C"));
		System.out.println(routesApp.getDistance("A-D"));
		System.out.println(routesApp.getDistance("A-D-C"));
		System.out.println(routesApp.getDistance("A-E-B-C-D"));
		System.out.println(routesApp.getDistance("A-E-D"));
		System.out.println(routesApp.possiblePaths("C", "C", 3, maxStops));
		System.out.println(routesApp.possiblePaths("A", "C", 4, exactStops));
		System.out.println(routesApp.minDistance("A", "C"));
		System.out.println(routesApp.possibleRoutes("B", "E"));
		System.out.println(routesApp.minDistance("B", "B"));
		System.out.println(routesApp.possiblePaths("C", "C", 30, maxDist));
	}
}
