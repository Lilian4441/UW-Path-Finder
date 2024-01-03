
// --== CS400 Fall 2023 File Header Information ==--
// Name: <Rain Hu>
// Email: <jhu364@wisc.edu>
// Group: <G18>
// TA: <Grant Waldow>
// Lecturer: <Florian Heimerl>
// Notes to Grader: <optional extra notes>
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This interface is the Backend Role for project2: UW Path Finder. It have
 * methods that read DOT file with name provided by frontend, find the shortest
 * path between two location and return string that gives information about the
 * dataset. It takes an instance of the GraphADT.
 * 
 * @author rainhu
 *
 */
public interface BackendInterface {
	/**
	 * Class constructor that takes in an instance of GraphADT and initialize the
	 * graphADT variable with the instance.
	 */
	// private GraphADT graphADT;
	// public BackendInterface(GraphADT graphADT ) {
	// B
	// this.graphADT = graphADT;
	// }

	/**
	 * This method read data from a DOT file when provided with the name of this
	 * A
	 * file. This method will throw an FileNotFoundException if there is any trouble
	 * loading the file based on the name provided. The method should store the data
	 * into the GraphADT structure.
	 * 
	 * @param fileName: the name of file this method is loading
	 * @return hash map containing parsed nodes and edges
	 * @throws FileNotFoundException: throw exception when the file name cannot be
	 *                                found
	 */
	public void readData(String fileName) throws IOException;

	/**
	 * This method get the shortest path from a start to a destination building in
	 * the dataset. The method takes in the start and end location as string.
	 * 
	 * @param start:       name of the starting building
	 * @param destination: name of the destination building
	 * @throws NoSuchElementException: this method throws NoSuchElementException
	 *                                   if the start or end location provided
	 *                                   cannot be found in the dataset
	 */
	public ShortestPath shortestPath(String start, String destination) throws NoSuchElementException;


	/**
	 * This method return a String representation of the dataset. The string will
	 * include a list of information: the number of buildings (nodes), the number of
	 * edges, and total walking time (sum of weights) for all edges in the graph.
	 * 
	 */

	public String toString();
}
