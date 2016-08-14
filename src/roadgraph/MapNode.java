package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

public class MapNode {
	
	private GeographicPoint currentLocation;
	private List <MapEdge> neighbors;
	
	public MapNode() {
		
	}
	
	public MapNode(GeographicPoint location) {
		this.currentLocation = location;
		List <MapEdge> neighbors = new ArrayList<MapEdge>();
	}
	
	
	public GeographicPoint getCurrLocation() {
		return currentLocation;
	}
}
