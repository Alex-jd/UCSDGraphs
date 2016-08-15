package roadgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import geography.GeographicPoint;

public class TreeMapNode {
	
	private GeographicPoint currentLocation;
	private HashMap <GeographicPoint, TreeMapNode> neighbors;
	private List <GeographicPoint> track;
	
	public TreeMapNode(GeographicPoint location, List <GeographicPoint> track) {
		neighbors = new HashMap <GeographicPoint, TreeMapNode>();
		this.track = new ArrayList <GeographicPoint>(track);
		this.currentLocation = location;
		this.track.add(currentLocation);
		
	}
	
	public List<GeographicPoint> getTrack() {
		return track;
	}
	
	public void addTrackNeighbor(GeographicPoint end) {
		
	}
	
	
}
