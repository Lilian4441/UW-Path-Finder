import java.util.ArrayList;
import java.util.List;

public class ShortestPath implements ShortestPathInterface{

    	private List<String> path;
    	private List<Double> walkingTime;
    	private double totalCost;

    /**
     * Constructor of class shortestPath that takes in segmented path along the
     * shortestPath in a ArrayList, list of walking time in ArrayList and the
     * totalCost of the path as integer. The constructor also initialize the private
     * variable in this class.
     */
    public ShortestPath(List<String> path, List<Double> walkingTime, double totalCost){
			this.path = path;
			this.walkingTime = walkingTime;
			this.totalCost = totalCost;
}

    /**
     * This is the getter method for the segemented path along the shortestPath.
     * This method returns a list of buildings along the shortest path between two
     * locations.
     *
     * @return a ArrayList of string that represents a list of buildings.
     */
    @Override
    public List<String> path() {return this.path;}

    /**
     * This method returns a list of walking time for path segments which is the
     * time it takes to walk from one building to the next.
     *
     * @return a ArrayList of Integer that represents the list of walking time for
     *         path segments
     */
    @Override
    public List<Double> walkingTime() {return this.walkingTime;}

    /**
     * This method return the total path cost as the estimated time it takes to walk
     * from the start to the destination building.
     *
     * @return a integer that represents the total path cost.
     */
    @Override
    public double totalCost() {return this.totalCost;}
}
