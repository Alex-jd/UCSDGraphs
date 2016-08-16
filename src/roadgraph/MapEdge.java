package roadgraph;

import geography.GeographicPoint;

//class of Edge contains the
// start GeographicPoint
// end GeographicPoint
// street/road Name
// roadType
// distance 
public class MapEdge {
	
	private GeographicPoint start;
	private GeographicPoint end;
	private String streetName;
	private String roadType;
	private double distance;
		
	//Create the object particular start and end Point, and another info
	public MapEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) {
		this.start = from;
		this.end = to;
		this.streetName = roadName;
		this.roadType = roadType;
		this.distance = length;
		
	}
	
	//The methods for getting the variables of this object
	
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
