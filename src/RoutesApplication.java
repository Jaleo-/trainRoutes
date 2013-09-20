
public class RoutesApplication {
	public static void main(String args[])
	{	
		TrainRoutes trainRoutes = new TrainRoutes();
		for(String path : args){
			if(path.length()>2)
				trainRoutes.addRoute(path.substring(0,1), path.substring(1,2), Integer.parseInt(path.substring(2,3)));
		}
		//trainRoutes.evaluateRoute("A", "C");
		System.out.println(trainRoutes.maxStopsPaths("C", "C", 3));
		System.out.println(trainRoutes.generatePossiblePaths("C", "C"));
		trainRoutes.reset();
		System.out.println(trainRoutes.minDist("A", "C"));
		trainRoutes.reset();
		System.out.println(trainRoutes.minDist("B", "B"));
	}
}
