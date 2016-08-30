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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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

		if (start == null || goal == null)
			throw new NullPointerException("Cannot find route from or to null node");
		MapNode startNode = correspPointNode.get(start);
		MapNode endNode = correspPointNode.get(goal);
		if (startNode == null) {
			System.err.println("Start node " + start + " does not exist");
			return null;
		}
		if (endNode == null) {
			System.err.println("End node " + goal + " does not exist");
			return null;
		}
		
		// setup to begin BFS
		HashMap<MapNode,MapNode> parentMap = new HashMap<MapNode,MapNode>();
		PriorityQueue<MapNode> toExplore = new PriorityQueue<MapNode>(1, new DistComparator() );//������� ����� DistComparator � �� ���� ����������� ��������� Comparable
		//� ������ DistComparator ��������� ������� ���������. 1 �������� ������ ������� (������������� ������������� �� ���������� �������)
		//PriorityQueue<MapNode> toExplore = new PriorityQueue<MapNode>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		startNode.setPriority(0.0);
		toExplore.add(startNode);
		MapNode next = null;
		MapNode neighborNode = null;
		int visitedCount = 0;
		
		while (!toExplore.isEmpty()) {
			next = toExplore.poll();
			visitedCount++;
			// hook for visualization
			nodeSearched.accept(next.getCurrLocation() );
			System.out.println("Dijkstra: location: " + next.getCurrLocation() + " priority: " + next.getPriority());
			
			if (!visited.contains(next.getCurrLocation()) ) {
				visited.add(next.getCurrLocation());
				
				if (next.getCurrLocation().equals(endNode.getCurrLocation()) ){
					endNode = next;//��� ��� �������� ��� ������ �� ���� �������� ������, ������� ������������ � ������ �������� (��� ��� ������������� ���� ���������� 
					//����������� �������
					break;
				}
				
				MapEdge bestNeighbor = null;
				for (MapEdge neighbor : next.getEdges()) {
					neighborNode = correspPointNode.get(neighbor.getEnd());
					if (!visited.contains(neighborNode.getCurrLocation() ) )  { //������, ��� ����������� ��������� ���-�� ��������������� �����
						/*if (bestNeighbor == null || neighbor.getDistance() < bestNeighbor.getDistance() ) {
							if (bestNeighbor != null) {
								//System.out.println("curr neighbor dist " + neighbor.getDistance() + " bestNieghbor dist " + bestNeighbor.getDistance() );
							}
							bestNeighbor = neighbor;
						}*/
						bestNeighbor = neighbor;
						neighborNode = correspPointNode.get(bestNeighbor.getEnd()).clone();//��������� ����, ��� ��� ����� ���� ������� �������, �� � ������ �����������. ��� �����!!!!
						neighborNode.setPriority(0);//�������� ��������� ����� ��� �������������.
						
						neighborNode.setPriority(next.getPriority() + bestNeighbor.getDistance() + getSuperPriority(bestNeighbor) );
						//neighborNode.setPriority(next.getPriority() + bestNeighbor.getDistance()  );
						System.out.println("Destination " + neighbor.getDistance() );
						System.out.println("SuperPriority : " + getSuperPriority(bestNeighbor) );
						System.out.println("MaxSpeed : " + neighbor.getMaxSpeed());
						//������������� ���������. �� ���� ��� ��������� ��� ���� 
						if (!toExplore.isEmpty() ) {
							Iterator<MapNode> it = toExplore.iterator();
							while (it.hasNext() ) {
								MapNode tempNode = it.next();
								if (neighborNode.getCurrLocation().equals(tempNode.getCurrLocation())) {
									if (neighborNode.getPriority() < tempNode.getPriority() ) {
										it.remove();
									}
								}
									
							}
						}
						
						parentMap.put(neighborNode, next);
						toExplore.add(neighborNode);
					}
				}
			}
			//next.setPriority(0.0);
			
		}
		
		if (!next.equals(endNode)) {
			System.out.println("No path found from " +start+ " to " + goal);
			return null;
		}
		// Reconstruct the parent path
		List<GeographicPoint> path = reconstructPath(parentMap, startNode, endNode);
		System.out.println("Nodes visited in search: " + visitedCount);
		return path;

		//===============================================================================================================
		/*
		List<GeographicPoint> bfsOut; 					//Create the variable bfsOUt (returned track List)
		HashMap<GeographicPoint, MapNode> visited = new HashMap<GeographicPoint, MapNode>(); 	//Create the HashMap visited
		PriorityQueue<MapNode> q = new PriorityQueue<MapNode>(1, new DistComparator() ); 	//Create the queue
		MapNode curr = correspPointNode.get(start).clone(); 	//Get the object MapNode corresponded start GeorgaphicPoing
		
		
		//GeographicPoint currEnd;		 //Create the variable kind of GeographicPoint 
		MapNode currNode;				 //Create the variable kind of MapNode
		
		//visited.add(curr); 				//add to the visited set current MapNode
		q.add(curr); 					//add to the queue current MapNode
		
		while (!q.isEmpty() ) {
			curr = q.poll(); 	
			nodeSearched.accept(curr.getCurrLocation()); 
			if (!visited.containsKey(curr.getCurrLocation() ) ) {
				visited.put(curr.getCurrLocation(), curr);
				//curr.setCurrPathNull();
				System.out.println("I am not visited and I am in the queue: " + curr.getPriority());
				//---------------------------------------------
				if (curr.getCurrLocation().equals(goal) ) { 	
					curr.addPointToCurrPath(curr.getCurrLocation()); 	 
					bfsOut = new ArrayList<GeographicPoint>(curr.getCurrPath() );
					//Set all the last PathList of MapNode to null
					curr.setCurrPathNull();
					curr.setPriority(0);
					while (!q.isEmpty() ) {
						curr = q.remove();
						curr.setCurrPathNull();
						curr.setPriority(0);//Clear the Current Path list (current track)
						//q.clear();
					}
					for (GeographicPoint temp : bfsOut) {
						System.out.println("bfsOut " + temp);
					}
					
					return bfsOut; 	//return the created list (list of the best Path from start point to goal, best track)
				}
				//--------------------------------------------
				MapEdge currEdgeBest = null;
				for (MapEdge currEdge : curr.getEdgesList() ) {
					currNode = correspPointNode.get(currEdge.getEnd()).clone();
					//System.out.println("I am in the currEdge getting and currNode is " + currNode.getCurrLocation());
					if (!visited.containsKey(currNode.getCurrLocation() ) ) {
						//System.out.println("I am in not visited");
						if (currEdgeBest == null || currEdge.getDistance() > currEdgeBest.getDistance() ) {
							currEdgeBest = currEdge;
							//System.out.println("I am the best!!!");
						}
					}
				}
				if (currEdgeBest.equals(null)) {
					System.out.println("CurrEdge == null");
				}
				System.out.println("get points " + currEdgeBest.getEnd() + " " + currEdgeBest.getDistance());
				currNode = correspPointNode.get(currEdgeBest.getEnd()).clone();
				currNode.setCurrPathNull();
				currNode.setPriority(0);
				if (curr.getCurrPath() != null) { 	
					currNode.addListToCurrPath(curr.getCurrPath() );	//Add parent's current Path list (current track of parent) to the neighbor Path list (neighbor track)
				}
				currNode.addPointToCurrPath(curr.getCurrLocation() );	//Add the GeographicPoint to the neighbor Path list (neighbor track)
				currNode.setPriority(curr.getPriority() + currEdgeBest.getDistance()); // Set the distance of currNode
				System.out.println("new distance " + currNode.getPriority());
				q.add(currNode);															// And add that currNode to the priority queue
			}
			curr.setCurrPathNull();
			curr.setPriority(0);
		}
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		

		return null; 	//If the goal point isn't find return null
		*/
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

	}
	
/*	
	private List<GeographicPoint> reconstructPath(HashMap<GeographicPoint,GeographicPoint> parentMap,
			MapNode start, MapNode goal)
	{
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		GeographicPoint current = goal.getCurrLocation();

		while (!current.equals(start.getCurrLocation())) {
			path.addFirst(current);
			current = parentMap.get(current);
		}

		// add start
		path.addFirst(start.getCurrLocation());
		return path;
	}
*/

	private double getSuperPriority(MapEdge currEdge) {
		int time = 0;
		double accel = 1.0;
		double startAccel = 0;
		double maxSpeed = currEdge.getMaxSpeed();
		double distance = currEdge.getDistance();
		for (double i = 0; i <= (distance/2.0); ) {
			if (startAccel <= maxSpeed) {
				startAccel += accel;
			}
			i += startAccel;
			time++;
		}
		return 2*time;
	}
	

	private List<GeographicPoint> reconstructPath(HashMap<MapNode,MapNode> parentMap,
					MapNode start, MapNode goal)
	{
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		MapNode current = goal;

		while (!current.equals(start)) {
			path.addFirst(current.getCurrLocation());
			current = parentMap.get(current);
		}

		// add start
		path.addFirst(start.getCurrLocation());
		return path;
	}


	private double getCrowFly(GeographicPoint start, GeographicPoint end) {

		double crowFly = Math.pow ( (Math.pow((end.getX() - start.getX() ), 2) + Math.pow((end.getY() - start.getY() ), 2) ), 1/2 );
		return crowFly;
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
		if (start == null || goal == null)
			throw new NullPointerException("Cannot find route from or to null node");
		MapNode startNode = correspPointNode.get(start);
		MapNode endNode = correspPointNode.get(goal);
		if (startNode == null) {
			System.err.println("Start node " + start + " does not exist");
			return null;
		}
		if (endNode == null) {
			System.err.println("End node " + goal + " does not exist");
			return null;
		}
		
		// setup to begin BFS
		HashMap<MapNode,MapNode> parentMap = new HashMap<MapNode,MapNode>();
		PriorityQueue<MapNode> toExplore = new PriorityQueue<MapNode>(1, new DistComparator() );
		//PriorityQueue<MapNode> toExplore = new PriorityQueue<MapNode>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		//HashSet<MapNode> visited = new HashSet<MapNode>();
		startNode.setPriority(0.0);
		toExplore.add(startNode);
		MapNode next = null;
		MapNode neighborNode = null;
		int visitedCount = 0;
		
		while (!toExplore.isEmpty()) {
			
			/*for (MapNode e : toExplore ) {
					System.out.println("point " + e.getCurrLocation() + " priority " + e.getPriority() + " :" );
				}
			System.out.println();*/
			
			next = toExplore.poll();
			visitedCount++;
			System.out.println("A*: location: " + next.getCurrLocation() + " priority: " + next.getPriority());
			
			// hook for visualization
			nodeSearched.accept(next.getCurrLocation() );
			//System.out.println("neighbor next " + next.getCurrLocation() + " priority " + next.getPriority());
						
			if (!visited.contains(next.getCurrLocation()) ) {
				visited.add(next.getCurrLocation());

				if (next.getCurrLocation().equals(endNode.getCurrLocation())) {
					//System.out.println("BINGO " + next.getCurrLocation() + " " + next.getPriority());
					endNode = next;
					break;
				}
				
				MapEdge bestNeighbor = null;
				for (MapEdge neighbor : next.getEdges()) {
					neighborNode = correspPointNode.get(neighbor.getEnd());
					if (!visited.contains(neighborNode.getCurrLocation())) {
						/*if (bestNeighbor == null || neighbor.getDistance() < bestNeighbor.getDistance() ) {
							bestNeighbor = neighbor;
						}*/
						bestNeighbor = neighbor;
						//System.out.println("edge distance " + bestNeighbor.getDistance());
						neighborNode = correspPointNode.get(bestNeighbor.getEnd()).clone();
						neighborNode.setPriority(0);
						neighborNode.setDistance(0);
						
						double CrowFly = neighborNode.getCurrLocation().distance(endNode.getCurrLocation() );
						//double CrowFly = getCrowFly(neighborNode.getCurrLocation(), endNode.getCurrLocation() );
						//System.out.println("Summ priority " + (CrowFly + next.getPriority() ) );next.getPriority() + bestNeighbor.getDistance()
						neighborNode.setDistance(next.getDistance() + bestNeighbor.getDistance());
						
						//neighborNode.setPriority( neighborNode.getDistance() + CrowFly );
						neighborNode.setPriority( neighborNode.getDistance()*100 + getHeuristic(neighborNode, endNode)*100 );
						System.out.println("Destination : " + neighbor.getDistance()*100 );
						System.out.println("Destination heuristic : " + getHeuristic(neighborNode, endNode)*100 );
						System.out.println("Priority : " + neighborNode.getPriority() );
						//System.out.println("neighbor " + neighborNode.getCurrLocation() + " priority " + neighborNode.getPriority());
						if (!toExplore.isEmpty() ) {
							Iterator<MapNode> it = toExplore.iterator();
							while (it.hasNext() ) {
								MapNode tempNode = it.next();
								if (neighborNode.getCurrLocation().equals(tempNode.getCurrLocation())) {
									if (neighborNode.getDistance() < tempNode.getDistance() ) {
										it.remove();
									}
								}
									
							}
						}
						
						parentMap.put(neighborNode, next);
						toExplore.add(neighborNode);
					}
				}
				//System.out.println("======================================");
			}
			next.setPriority(0);
			next.setDistance(0);
			
		}
		
		if (!next.getCurrLocation().equals(endNode.getCurrLocation())) {
			System.out.println("No path found from " +start+ " to " + goal);
			return null;
		}
		// Reconstruct the parent path
		//System.out.println("parentMap " + parentMap);
		List<GeographicPoint> path =
				reconstructPath(parentMap, startNode, endNode);
		System.out.println("Nodes visited in search: " + visitedCount);
		return path;
		
	}
	
	private double getHeuristic(MapNode current, MapNode end) {
		double H = 
				/*Math.pow( (Math.pow( (current.getCurrLocation().getX() - end.getCurrLocation().getX() ), 2) + 
						Math.pow( (current.getCurrLocation().getY() - end.getCurrLocation().getY() ), 2) )
						, 1/2 );*/
		(Math.abs(current.getCurrLocation().getX() - end.getCurrLocation().getX() ) + 
				Math.abs(current.getCurrLocation().getY() - end.getCurrLocation().getY() ) );
		return H;
	}
		
		/*
		List<GeographicPoint> bfsOut; 					//Create the variable bfsOUt (returned track List)
		Set<MapNode> visited = new HashSet<MapNode>(); 	//Create the HashMap visited
		Queue<MapNode> q = new LinkedList<MapNode>(); 	//Create the queue
		MapNode curr = correspPointNode.get(start); 	//Get the object MapNode corresponded start GeorgaphicPoing
		
		GeographicPoint currEnd;		 //Create the variable kind of GeographicPoint 
		MapNode currNode;				 //Create the variable kind of MapNode
		
		visited.add(curr); 				//add to the visited set current MapNode
		q.add(curr); 					//add to the queue current MapNode
		
		
		Implement the BFS algorithm
		dequeue the MapNode object from q
		feature for search visualization
		Check the current GoegraphicPoint is equals the goal point 
		Add to the end of list (Current Path or current track) yourself GeographicPoint 
		Create the returned List (track)
		
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
			
			Get the all Edges of current MapNode (vertex)
			get the end GeographicPoint of currEdge (neighbor of current vertex adjacent by the current edge)
			get the MapNode object (vertex) correspond to the end GeographicPoint of currEdge
			Check the visited Set
			Clear the current Path of the neighbor (clear the track of current neighbor)
			Exception the NullPoint
			
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
		*/
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		

	

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);

		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);
		
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
		
		/*MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("all vertices " + firstMap.getVertices());
		for (MapNode temp : firstMap.vertices) {
			//System.out.println("Edge " +temp.getEdges() );
			for (MapEdge edge : temp.getEdgesList() ) {
				System.out.println("start " + edge.getStart() +" end " + edge.getEnd() + " distance " + edge.getDistance());
			}
		}
		//System.out.println( getNumVertices().toString() );
		//int temp = getNumEdges();
		//System.out.println(getNumEdges());
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		 Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 
		
		
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
		
		
		
		
		 Use this code in Week 3 End of Week Quiz 
		MapGraph theMap = new MapGraph();
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
