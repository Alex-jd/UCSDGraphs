Class: MapGraph

Modifications made to MapGraph (what and why):
1. Implement the constructor of MapGraph
	create the ArrayList of vertices and HashMap corresponded GeographicalPoint with MapNode (vertices)

2. Implement getNumVertices method
3. Implement getVertices method
4. Implement getNumEdges method
5. Implement addVertex method
  Adding the vertices to the both data structure HashMap and ArrayList
  
6. Implement addEdge method
  Get argument and call method of MapNode class
  
7. Implement bfs method
  Create three required data structure: bfs - ArrayList, visited - HashSet,q - LinkedList(aka queue).
  Three inner variabels: curr - current MapNode object, currEnd - current GeographicalPoint with end info about addEdge,
  currNode - neighbor MapNode object of current (curr) object.
  Use the while loop for create the track (bfs ArrayList)
  
  
Class name:MapNode

Purpose and description of class:
This class provides to store information about vertex and currnet track for bfs algorithm
This is the vertex object which composed
  currentLocation - corresponded GeographicalPoint
  neighbors - list of MapEdge objects
  currPath - list of current track
  
getCurrLocation method - get the corresponded GeographicalPoint
addEdge method - adding the Edge to the neighbors list
getEdgesList - get the neighbors list
getEdges -  get the HashSet of MapEdge objects
getCurrPath - get the list of current track
setCurrPathNull - clear the lis of current track
addPointToCurrPath - add the GeographicalPoint object to the list of track
addListToCurrPath - add the list of track of the parent MapNode to the list of track



Class name: MapEdge

Purpose and description of class:
This class provides to store information about Edge
-start point 
-end point
-street/road name
-roadtype
-distance

The methods:
getStart - get the start point
getEnd - get the end point
getStreetName - get the street/road name
getRoadType - get the roadtype
getDistance - get the distance
toString - out the info for debug





In this programming assignment used the adjacency list matrix representation.
Each vertex is represented as an MapNode object.
Each edge is represented as an MapEdge object.
The MapNode odject has got a list of edges (MapEdge objects) and list of GeographicPoints (current track) to store the path.

BFS algotithm was implemented in a standard way. But to save the current track in each direction used list of GeorgaphicPoints
that is updated as the search path. As soon as the exit GeographicPoint of the field of view of its list of cleared.



It contains a MapGraph.java, MapNode.java, MapEdge.java and README.txt.
The creation of new classes and the implementation of the algorithm BFS.
