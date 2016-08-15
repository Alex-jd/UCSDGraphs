package roadgraph;

import geography.GeographicPoint;

public class MapEdge {
	
	GeographicPoint start;
	GeographicPoint end;
	String streetName;
	String roadType;
	double distance;
		
	public MapEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) {
		this.start = from;
		this.end = to;
		this.streetName = roadName;
		this.roadType = roadType;
		this.distance = length;
		
	}
	
	public GeographicPoint getStart() {
		return start;
	}
	
	public GeographicPoint getEnd() {
		return end;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public String getRoadType() {
		return roadType;
	}

	public double getDistance() {
		return distance;
	}
	
	public String toString()
    {
    	return "start: " + getStart() + ", end: " + getEnd() + "\n";
    }
}
