package roadgraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import geography.GeographicPoint;



//Class MapNode is the vertex contained the info about
//own GeographicPiont - currentLocation
//list of adjacency Edges - neighbors
//current Track - currPath
//public class MapNode implements Cloneable, Comparable<MapNode> {
public class MapNode implements Cloneable {
	
	private GeographicPoint currentLocation;
	private List <MapEdge> neighbors;
	private List<GeographicPoint> currPath;
	private double distance;
	private double priority;
	
	public MapNode() {
		
	}
	//Create the object with particular location
	public MapNode(GeographicPoint location) {
		this.currentLocation = location;
		neighbors = new ArrayList<MapEdge>();
		currPath = new ArrayList<GeographicPoint>();
		priority = 0;
		distance  = 0;
	}
	
	 @Override
	  public MapNode clone() {
	    try {
	      return (MapNode)super.clone();
	    }
	    catch( CloneNotSupportedException ex ) {
	      throw new InternalError();
	    }
	  }    
	
	//get the own GeographicPoint
	public GeographicPoint getCurrLocation() {
		return currentLocation;
	}
	
	public int compareTo(MapNode node2) {
		if (this.getPriority() < node2.getPriority() ) return -1;
		if (this.getPriority() > node2.getPriority() ) return 1;
		return 0;
	  }
	
	//add the Edge to the neighbor list
	//@param from - the start GeographicPoint of this edge
	//@param to - the end GeographicPoint of this edge 
	//@param roadName - road/street name String
	//@param roadType - type of road String
	//@param length - distance double
	public boolean addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) {
		
		MapEdge currMapEdge = new MapEdge(from, to, roadName, roadType, length);
		
		if (neighbors.contains(currMapEdge) ) {
			return false;
		}
		else {
			neighbors.add(currMapEdge);
			return true;
		}
		
	}
	
	//get the current Track (current Path)
	public List<GeographicPoint> getCurrPath() {
		return currPath;
	}
	
	//clear the current Track (current Path)
	public void setCurrPathNull() {
		currPath.clear();;
		//currPath = null;
	}
	
	//add the GeographicPoint to the current Track/Path
	public void addPointToCurrPath(GeographicPoint point) {
		currPath.add(point);
	}
	
	//add the list of GeographicPoints to the current Track/Path
	public void addListToCurrPath(List<GeographicPoint> parentPath) {
		currPath.addAll(parentPath);
	}
	
	//get the list of neighbors
	public List<MapEdge> getEdgesList() {
		return neighbors;
	}
	
	//get the set of neighbors
	public Set<MapEdge> getEdges() {
		Set<MapEdge> setEdges = new HashSet<MapEdge>();
		for (MapEdge gPoint : neighbors) {
			setEdges.add(gPoint);
		}
		return setEdges;
	}
	
	public void setPriority(double prior) {
		this.priority = prior;
	}
	
	public double getPriority() {
		return this.priority;
	}
	
	public void setDistance(double dist) {
		this.distance = dist;
	}
	
	public double getDistance() {
		return this.distance;
	}
}
