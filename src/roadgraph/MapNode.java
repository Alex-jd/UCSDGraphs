package roadgraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import geography.GeographicPoint;

public class MapNode {
	
	private GeographicPoint currentLocation;
	private List <MapEdge> neighbors;
	
	public MapNode() {
		
	}
	
	public MapNode(GeographicPoint location) {
		this.currentLocation = location;
		neighbors = new ArrayList<MapEdge>();
	}
	
	
	public GeographicPoint getCurrLocation() {
		return currentLocation;
	}
	
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
	
	public List<MapEdge> getEdgesList() {
		return neighbors;
	}
	
	public Set<MapEdge> getEdges() {
		Set<MapEdge> setEdges = new HashSet<MapEdge>();
		for (MapEdge gPoint : neighbors) {
			setEdges.add(gPoint);
		}
		return setEdges;
	}
}
