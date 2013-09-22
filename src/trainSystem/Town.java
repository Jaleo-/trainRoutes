package trainSystem;
import java.util.HashMap;
import java.util.Set;
/*The town class is used to represent a town and all the other towns it can reach directly by train.  Has a HashMap
 * to hold each town it can reach and the weight of that path.*/

public class Town {

	private HashMap<String, Integer> routes = new HashMap<String, Integer>();
	private String name  = "";
	private boolean visited = false;
	
	public Town (String name){
		this.name = name;
	}
	
	public void addRoute(String otherCity, Integer distance){
		routes.put(otherCity, distance);
	}
	
	public int getDistance(String destination){
		if(routes.get(destination)==null)
			return -1;
		return routes.get(destination);		
	}
	public boolean status(){
		return visited;
	}
	public void visited(){
		visited = !visited;
	}
	
	public Set<String> possibleRoutes(){
		return routes.keySet();
	}
}
