import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Backend implements BackendInterface {

    private GraphADT<String, Double> graph;
    private double totalCost;

    /**
     * Class constructor that takes in an instance of GraphADT and initialize the
     * graphADT variable with the instance.
     */
    public Backend(GraphADT<String, Double> graph) {
        this.graph = graph;
    }

    /**
     * This method read data from a DOT file when provided with the name of this
     * file. This method will throw an FileNotFoundException if there is any trouble
     * loading the file based on the name provided. The method should store the data
     * into the GraphADT structure.
     *
     * @param fileName: the name of file this method is loading
     * @throws FileNotFoundException: throw exception when the file name cannot be
     *                                found
     */
    @Override
    public void readData(String fileName) throws IOException {

        // checks if file path is null
        if(fileName == null){
            throw new NullPointerException("File cannot be null");
        }

        Scanner fileReader;
        File campusDOT = new File(fileName);

        if (campusDOT.exists()){
            // opens DOT file for parsing
            fileReader = new Scanner(new FileReader(campusDOT));
        } else {
            throw new FileNotFoundException("File not found");
        }


        // reads each line in the dot file
        while (fileReader.hasNextLine()) {

            // instantiates a string to hold scanned lines
            String line = fileReader.nextLine();

            if (!line.trim().startsWith("\"")) continue; // skip any lines that do not start with a quotation mark

            String[] parts = line.split("\" -- \""); // array holds the parts of the line after a split
            if (parts.length < 2) continue; // skips any invalid lines

            String sourceName = parts[0].replaceAll("\"", "").trim(); // string holds the source node name

            String[] destinationParts = parts[1].split("\" \\[seconds="); // array holds the parts of the line after split between dest. node and travel time
            if (destinationParts.length < 2) continue; //skips any invalid lines

            String destinationName = destinationParts[0].trim(); // string holds dest. node name
            double timeInSeconds = Double.parseDouble(destinationParts[1].replace("];", "").trim()); // double holds travel time
            totalCost += timeInSeconds;

            // checks that graph doesn't already contain starting building
            if (!graph.containsNode(sourceName)) {
                graph.insertNode(sourceName); // inserts starting building into graph
            }

            //  checks that graph doesn't already contain destination building
            if (!graph.containsNode(destinationName)) {
                graph.insertNode(destinationName); // inserts destination building into graph
            }

            // insert edges
            graph.insertEdge(sourceName, destinationName, timeInSeconds);
        }

        // closes the reader
        fileReader.close();
    }

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
    @Override
    public ShortestPath shortestPath(String start, String destination) throws NoSuchElementException {
        try {
            // creates a list that holds all buildings on the shortest path
            List<String> buildingsOnPath = this.graph.shortestPathData(start, destination);

            // creates a list that holds all walking times (for each segment) on the shortest path
            List<Double> segmentWalkTime = new LinkedList<>();

            // a loop that adds each segment's walking time to the list
            for (int i = 0; i < buildingsOnPath.size() - 1; i++) {
                segmentWalkTime.add(this.graph.getEdge(buildingsOnPath.get(i), buildingsOnPath.get(i + 1)).doubleValue());
            }

            // creates a variable that holds the total walking time for the shortest path
            double totalWalkTimeCost = this.graph.shortestPathCost(start, destination);

            // returns the shortest path object
            return new ShortestPath(buildingsOnPath, segmentWalkTime, totalWalkTimeCost);

        } catch (NoSuchElementException e){
            throw new NoSuchElementException("Start or end location cannot be found in the dataset or path between " +
                    "start and end cannot be found");
        }
    }

    /**
     * This method return a String representation of the dataset. The string will
     * include a list of information: the number of buildings (nodes), the number of
     * edges, and total walking time (sum of weights) for all edges in the graph.
     *
     * @return a String that contains in
     */
    @Override
    public String toString(){

        return "Number of Buildings: " + graph.getNodeCount() + "\n"
                + "Number of Edges: " + (graph.getEdgeCount()/2) + "\n"
                + "Total Walking Time in Seconds: " + totalCost;
    }

    public static void main (String[] args) {
        DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(new PlaceholderMap<>());
    	Backend backend = new Backend(dijkstraGraph);
        Frontend frontend = new Frontend(backend);

        try{
            frontend.startCommandLoop();
        } catch (IOException e){
            System.out.println("FileNotFoundException thrown");
        }
    }
}
