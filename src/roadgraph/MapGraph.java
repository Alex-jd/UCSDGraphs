/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 2
	List <MapNode> vertices; //The list of vertices (class MapNode)
	HashMap <GeographicPoint, MapNode> correspPointNode; //corresponding HashMap GeographicPoint to MapNode
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 2
		this.vertices = new ArrayList<MapNode>(); //Create the new ArrayList and HashMap
		this.correspPointNode = new HashMap<GeographicPoint, MapNode>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 2
		return vertices.size(); //return the size of Vertices List 
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 2
		//Create the set are containing the all vertices in the graph
		Set<GeographicPoint> setPoints = new HashSet<GeographicPoint>(); //create the new HashSet
		for (MapNode gPoint : vertices) {
			setPoints.add(gPoint.getCurrLocation()); //by the loop get the MapNode object and get the CurrLocation. And add CurrLocation to the HashSet
		}
		return setPoints;
		//return this.vertices.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 2
		int numEdges = 0;
		for (MapNode gPoint : vertices) {
			numEdges += gPoint.getEdges().size(); //by the loop get the size of Edge HashSet of the each vertex. And summ those all.
		}
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 2
		MapNode vertex = new MapNode(location); //Create the new MapNode object with particular location
		if (!correspPointNode.containsKey(location) && location != null) { //Check if this vertex is at graph and location isn't null
			vertices.add(vertex); //Add the new created object MapNode to the ArrayList
			correspPointNode.put(location, vertex); //Add the new created object MapNode to the HashMap
			//nodes.put(location, new Node(location));
			return true;
		}
				
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 2
		MapNode tempNode = correspPointNode.get(from); //Get the required object MapNode
		tempNode.addEdge(from, to, roadName, roadType, length); //Call the method addEdge and pass to him the arguments
		
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 2
		List<GeographicPoint> bfsOut; 					//Create the variable bfsOUt (returned track List)
		Set<MapNode> visited = new HashSet<MapNode>(); 	//Create the HashMap visited
		Queue<MapNode> q = new LinkedList<MapNode>(); 	//Create the queue
		MapNode curr = correspPointNode.get(start); 	//Get the object MapNode corresponded start GeorgaphicPoing
		
		GeographicPoint currEnd;		 //Create the variable kind of GeographicPoint 
		MapNode currNode;				 //Create the variable kind of MapNode
		
		visited.add(curr); 				//add to the visited set current MapNode
		q.add(curr); 					//add to the queue current MapNode
		
		/*
		Implement the BFS algorithm
		dequeue the MapNode object from q
		feature for search visualization
		Check the current GoegraphicPoint is equals the goal point 
		Add to the end of list (Current Path or current track) yourself GeographicPoint 
		Create the returned List (track)
		*/
		while (!q.isEmpty() ) {
			curr = q.remove(); 	
			nodeSearched.accept(curr.getCurrLocation()); 
			if (curr.getCurrLocation().equals(goal) ) { 	
				curr.addPointToCurrPath(curr.getCurrLocation()); 	 
				bfsOut = new ArrayList<GeographicPoint>(curr.getCurrPath() );
				//Set all the last PathList of MapNode to null
				while (!q.isEmpty() ) {
					curr = q.remove();
					curr.setCurrPathNull(); 	//Clear the Current Path list (current track)
				}
				return bfsOut; 	//return the created list (list of the best Path from start point to goal, best track)
			}
			/*
			Get the all Edges of current MapNode (vertex)
			get the end GeographicPoint of currEdge (neighbor of current vertex adjacent by the current edge)
			get the MapNode object (vertex) correspond to the end GeographicPoint of currEdge
			Check the visited Set
			Clear the current Path of the neighbor (clear the track of current neighbor)
			Exception the NullPoint
			*/
			for (MapEdge currEdge : curr.getEdgesList() ) { 
				currEnd = currEdge.getEnd();//
				currNode = correspPointNode.get(currEnd);
				if (!visited.contains(currNode) ) { 
					currNode.setCurrPathNull(); 
					if (curr.getCurrPath() != null) { 	
						currNode.addListToCurrPath(curr.getCurrPath() );	//Add parent's current Path list (current track of parent) to the neighbor Path list (neighbor track)
					}
					currNode.addPointToCurrPath(curr.getCurrLocation() );	//Add the GeographicPoint to the neighbor Path list (neighbor track)
					visited.add(currNode); 			//Add the neighbor to the visited set
					q.add(currNode); 				//enqueue the neighbor 
				}
			}
			curr.setCurrPathNull(); 	//Clear Path list (current track) of current vertex (of dequeue vertex)
		}
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null; 	//If the goal point isn't find return null
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		List<GeographicPoint> bfsOut; 					//Create the variable bfsOUt (returned track List)
		Set<MapNode> visited = new HashSet<MapNode>(); 	//Create the HashMap visited
		Queue<MapNode> q = new LinkedList<MapNode>(); 	//Create the queue
		MapNode curr = correspPointNode.get(start); 	//Get the object MapNode corresponded start GeorgaphicPoing
		
		GeographicPoint currEnd;		 //Create the variable kind of GeographicPoint 
		MapNode currNode;				 //Create the variable kind of MapNode
		
		visited.add(curr); 				//add to the visited set current MapNode
		q.add(curr); 					//add to the queue current MapNode
		
		/*
		Implement the BFS algorithm
		dequeue the MapNode object from q
		feature for search visualization
		Check the current GoegraphicPoint is equals the goal point 
		Add to the end of list (Current Path or current track) yourself GeographicPoint 
		Create the returned List (track)
		*/
		while (!q.isEmpty() ) {
			curr = q.remove(); 	
			nodeSearched.accept(curr.getCurrLocation()); 
			if (curr.getCurrLocation().equals(goal) ) { 	
				curr.addPointToCurrPath(curr.getCurrLocation()); 	 
				bfsOut = new ArrayList<GeographicPoint>(curr.getCurrPath() );
				//Set all the last PathList of MapNode to null
				while (!q.isEmpty() ) {
					curr = q.remove();
					curr.setCurrPathNull(); 	//Clear the Current Path list (current track)
				}
				return bfsOut; 	//return the created list (list of the best Path from start point to goal, best track)
			}
			/*
			Get the all Edges of current MapNode (vertex)
			get the end GeographicPoint of currEdge (neighbor of current vertex adjacent by the current edge)
			get the MapNode object (vertex) correspond to the end GeographicPoint of currEdge
			Check the visited Set
			Clear the current Path of the neighbor (clear the track of current neighbor)
			Exception the NullPoint
			*/
			for (MapEdge currEdge : curr.getEdgesList() ) { 
				currEnd = currEdge.getEnd();//
				currNode = correspPointNode.get(currEnd);
				if (!visited.contains(currNode) ) { 
					currNode.setCurrPathNull(); 
					if (curr.getCurrPath() != null) { 	
						currNode.addListToCurrPath(curr.getCurrPath() );	//Add parent's current Path list (current track of parent) to the neighbor Path list (neighbor track)
					}
					currNode.addPointToCurrPath(curr.getCurrLocation() );	//Add the GeographicPoint to the neighbor Path list (neighbor track)
					visited.add(currNode); //Add the neighbor to the visited set. Add the distance!!!
					currEdge.getDistance(); // Get the edge distance 
					//http://www.java2s.com/Tutorial/Java/0140__Collections/UseaComparatortocreateaPriorityQueueformessages.htm
					q.add(currNode); 				//enqueue the neighbor 
				}
			}
			curr.setCurrPathNull(); 	//Clear Path list (current track) of current vertex (of dequeue vertex)
		}
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null; 	//If the goal point isn't find return null

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		List<GeographicPoint> bfsOut; 					//Create the variable bfsOUt (returned track List)
		Set<MapNode> visited = new HashSet<MapNode>(); 	//Create the HashMap visited
		Queue<MapNode> q = new LinkedList<MapNode>(); 	//Create the queue
		MapNode curr = correspPointNode.get(start); 	//Get the object MapNode corresponded start GeorgaphicPoing
		
		GeographicPoint currEnd;		 //Create the variable kind of GeographicPoint 
		MapNode currNode;				 //Create the variable kind of MapNode
		
		visited.add(curr); 				//add to the visited set current MapNode
		q.add(curr); 					//add to the queue current MapNode
		
		/*
		Implement the BFS algorithm
		dequeue the MapNode object from q
		feature for search visualization
		Check the current GoegraphicPoint is equals the goal point 
		Add to the end of list (Current Path or current track) yourself GeographicPoint 
		Create the returned List (track)
		*/
		while (!q.isEmpty() ) {
			curr = q.remove(); 	
			nodeSearched.accept(curr.getCurrLocation()); 
			if (curr.getCurrLocation().equals(goal) ) { 	
				curr.addPointToCurrPath(curr.getCurrLocation()); 	 
				bfsOut = new ArrayList<GeographicPoint>(curr.getCurrPath() );
				//Set all the last PathList of MapNode to null
				while (!q.isEmpty() ) {
					curr = q.remove();
					curr.setCurrPathNull(); 	//Clear the Current Path list (current track)
				}
				return bfsOut; 	//return the created list (list of the best Path from start point to goal, best track)
			}
			/*
			Get the all Edges of current MapNode (vertex)
			get the end GeographicPoint of currEdge (neighbor of current vertex adjacent by the current edge)
			get the MapNode object (vertex) correspond to the end GeographicPoint of currEdge
			Check the visited Set
			Clear the current Path of the neighbor (clear the track of current neighbor)
			Exception the NullPoint
			*/
			for (MapEdge currEdge : curr.getEdgesList() ) { 
				currEnd = currEdge.getEnd();//
				currNode = correspPointNode.get(currEnd);
				if (!visited.contains(currNode) ) { 
					currNode.setCurrPathNull(); 
					if (curr.getCurrPath() != null) { 	
						currNode.addListToCurrPath(curr.getCurrPath() );	//Add parent's current Path list (current track of parent) to the neighbor Path list (neighbor track)
					}
					currNode.addPointToCurrPath(curr.getCurrLocation() );	//Add the GeographicPoint to the neighbor Path list (neighbor track)
					visited.add(currNode); 			//Add the neighbor to the visited set
					q.add(currNode); 				//enqueue the neighbor 
				}
			}
			curr.setCurrPathNull(); 	//Clear Path list (current track) of current vertex (of dequeue vertex)
		}
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null; 	//If the goal point isn't find return null
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		

	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println(firstMap.getVertices());
		for (MapNode temp : firstMap.vertices) {
			System.out.println(temp.getEdges() );
		}
		//System.out.println( getNumVertices().toString() );
		//int temp = getNumEdges();
		//System.out.println(getNumEdges());
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
