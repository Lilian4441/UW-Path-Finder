import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackendDeveloperTests {

    /**
     * test's the backend read data method
     */
    @Test
    public void testReadData() {

        // instantiates a new dijkstra graph
        DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(new PlaceholderMap<>());

        // instantiates a new backend object
        Backend backend1 = new Backend(dijkstraGraph);

        // asserts that an IOException is thrown if given an invalid file
        Assertions.assertThrows(FileNotFoundException.class, () -> backend1.readData("songs.csv"));

        // checks that the read data file works if given a valid file
        try {
            // reads in data from valid file
            backend1.readData("./campus.dot");
        } catch (IOException e) {
            // asserts that if catch statement is reached, readData has failed to properly read the data from the file
            Assertions.fail("A valid file path should NOT have thrown an IOException.");
        }

    }

    /**
     * tests backend's shortestPath method
     *
     * @throws IllegalArgumentException if shortestPath method returns an empty path
     */
    @Test
    public void testShortestPath() throws IllegalArgumentException, IOException {

        // instantiates a new dijkstra graph
        DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(new PlaceholderMap<>());

        // instantiates a new backend object
        Backend backend2 = new Backend(dijkstraGraph);

        // reads in data
        try {
            // reads in data from valid file
            backend2.readData("./campus.dot");
        } catch (IOException e) {
            // asserts that if catch statement is reached, readData has failed to properly read the data from the file
            Assertions.fail("A valid file path should NOT have thrown an IOException.");
        }

        // creates expected fields for expected shortest path object
        List<String> expectedBuildings = new ArrayList<>();
        List<Double> expectedSegWalkTimes = new ArrayList<>();
        double expectedTotalWalkTime = 289.7;

        // adds expected buildings to expected buildings list
        expectedBuildings.add("Memorial Union");
        expectedBuildings.add("Radio Hall");
        expectedBuildings.add("Education Building");

        // adds expected walking times to expected walking times list
        expectedSegWalkTimes.add(176.7);
        expectedSegWalkTimes.add(113.0);

        // creates expected shortest path object
        ShortestPath expectedShortestPath = new ShortestPath(expectedBuildings, expectedSegWalkTimes, expectedTotalWalkTime);

        // creates actual shortest path object
        ShortestPath actualShortestPath = backend2.shortestPath("Memorial Union", "Education Building");

        // checks that the actual and expected equal each other
        Assertions.assertEquals(expectedShortestPath.path(), actualShortestPath.path());
    }

    /**
     * tests backend's toString method
     */
    @Test
    public void testToString() {

        // instantiates a new dijkstra graph
        DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(new PlaceholderMap<>());

        // instantiates a new backend object
        Backend backend3 = new Backend(dijkstraGraph);

        // reads data in from file
        try {
            // reads in data from valid file
            backend3.readData("./campus.dot");
        } catch (IOException e) {
            // asserts that if catch statement is reached, readData has failed to properly read the data from the file
            Assertions.fail("A valid file path should NOT have thrown an IOException.");
        }

        // string saves value returned by toString method
        String result = backend3.toString();

        // string saves expected value
        String expected = "Number of Buildings: 160\nNumber of Edges: 400\nTotal Walking Time in Seconds: 110675.49999999997";

        // checks if result string equals expected string
        Assertions.assertEquals(expected, result);
    }

    /**
     * tests shortestPath's getter method for the shortest path
     */
    @Test
    public void testShortestPathGetters() {

        // instantiates a list of buildings on the path
        List<String> buildings = new LinkedList<>();

        // adds buildings to arraylist
        buildings.add("Memorial Union");
        buildings.add("Computer Sciences and Statistics");
        buildings.add("Engineering Hall");
        buildings.add("Engineering Centers Building");

        // instantiates list of walking times for each path on the shortest path
        List<Double> walkingTimes = new LinkedList<>();

        // adds times to arraylist
        walkingTimes.add(300.0);
        walkingTimes.add(200.0);
        walkingTimes.add(100.0);
        walkingTimes.add(50.0);

        // variable for total cost
        double totalCost = 650;

        // instantiates a new shortest path object
        ShortestPath path1 = new ShortestPath(buildings, walkingTimes, totalCost);

        // arraylist saves values returned by path method
        List<String> pathResult = path1.path();

        // asserts that result is correct
        Assertions.assertEquals(buildings, pathResult);
    }

    /**
     * tests shortestPath's getter method for the walking times
     */
    @Test
    public void testGetterWalkingTimes() {

        // instantiates a list of buildings on the path
        List<String> buildings = new LinkedList<>();

        // adds buildings to arraylist
        buildings.add("Memorial Union");
        buildings.add("Computer Sciences and Statistics");
        buildings.add("Engineering Hall");
        buildings.add("Engineering Centers Building");

        // instantiates list of walking times for each path on the shortest path
        List<Double> walkingTimes = new LinkedList<>();

        // adds times to arraylist
        walkingTimes.add(300.0);
        walkingTimes.add(200.0);
        walkingTimes.add(100.0);
        walkingTimes.add(50.0);

        // variable for total cost
        double totalCost = 650;

        // instantiates a new shortest path object
        ShortestPath path2 = new ShortestPath(buildings, walkingTimes, totalCost);

        // arraylist saves values returned by walkingTime method
        List<Double> walkingTimeResult = path2.walkingTime();

        // asserts that result is correct
        Assertions.assertEquals(walkingTimes, walkingTimeResult);
    }

    /**
     * tests shortestPath's getter method for the total cost
     */
    @Test
    public void testTotalCost() {

        // instantiates a list of buildings on the path
        List<String> buildings = new ArrayList<>();

        // adds buildings to arraylist
        buildings.add("Memorial Union");
        buildings.add("Computer Sciences and Statistics");
        buildings.add("Engineering Hall");
        buildings.add("Engineering Centers Building");

        // instantiates list of walking times for each path on the shortest path
        List<Double> walkingTimes = new ArrayList<>();

        // adds times to arraylist
        walkingTimes.add(300.0);
        walkingTimes.add(200.0);
        walkingTimes.add(100.0);
        walkingTimes.add(50.0);

        // variable for total cost
        int totalCost = 650;

        // instantiates a new shortest path object
        ShortestPath path3 = new ShortestPath(buildings, walkingTimes, totalCost);

        // arraylist saves values returned by path method
        double totalCostResult = path3.totalCost();

        // asserts that result is not empty
        Assertions.assertEquals(totalCost, totalCostResult);
    }

    /**
     * Test the frontend's loadDataFile method
     */
    @Test
    public void integrationTestLoadDataFile() {
        // instantiates a new dijkstra graph
        DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(new PlaceholderMap<>());

        // instantiates a new backend object
        Backend backend = new Backend(dijkstraGraph);

        // instantiates a new frontend object
        Frontend frontend = new Frontend(backend);

        // fails if exception is thrown (should not be thrown)
        try {
            frontend.loadDataFile("./campus.dot");
        } catch (FileNotFoundException e) {
            Assertions.fail("should not have thrown an exception");
        }
    }

    /**
     * Test the frontend's displayClosestConnection method
     */
    @Test
    public void integrationTestDisplayClosestConnection() {
        // instantiates a new dijkstra graph
        DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(new PlaceholderMap<>());

        // instantiates a new backend object
        Backend backend = new Backend(dijkstraGraph);

        // instantiates a new frontend object
        Frontend frontend = new Frontend(backend);

	// reads in data
        try {
            // reads in data from valid file
            backend.readData("./campus.dot");
        } catch (IOException e) {
            // asserts that if catch statement is reached, readData has failed to properly read the data from the file
            Assertions.fail("A valid file path should NOT have thrown an IOException.");
        }

        // saves result of calling frontend's displayClosestConnection method
        ShortestPath result = (ShortestPath) frontend.displayClosestConnection("Memorial Union", "Education Building");

        // asserts that result is not null
        Assertions.assertNotNull(result);
    }

}
