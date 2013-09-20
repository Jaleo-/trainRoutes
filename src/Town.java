import java.util.HashMap;
import java.util.Set;


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
