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
	private double maxSpeed;
	private enum roadTypeName {
		MOTORWAY("motorway"), MOTORWAY_LINK("motorway_link"), PRIMARY ("primary"), PRIMARY_LINK("primary_link"), 
		SECONDARY("secondary"), SECONDARY_LINK("secondary_link"),TERTIARY("tertiary"),TERTIARY_LINK("tertiary_link"),
		UNCLASSIFIED("unclassified"),RESIDENTIAL("residential"); 
		private String typeOfRoad; 
        private roadTypeName(String road) { 
            this.typeOfRoad = road; 
        } 
      
        @Override 
        public String toString(){ 
            return typeOfRoad; 
        } 
	};
		
	//Create the object particular start and end Point, and another info
	public MapEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) {
		this.start = from;
		this.end = to;
		this.streetName = roadName;
		this.roadType = roadType;
		this.distance = length;
		this.maxSpeed = currMaxSpeed();
		
	}
	
	//The methods for getting the variables of this object
	private double currMaxSpeed() {
		double currSpeed = 0;
		if (roadType != null) {
			if (roadTypeName.MOTORWAY.toString().equals(roadType) || roadTypeName.MOTORWAY_LINK.toString().equals(roadType) ) {
				currSpeed = 110;
			}
			else if (roadTypeName.PRIMARY.toString().equals(roadType) || roadTypeName.PRIMARY_LINK.toString().equals(roadType) ) {
				currSpeed = 100;
			}
			else if (roadTypeName.SECONDARY.toString().equals(roadType) || roadTypeName.SECONDARY_LINK.toString().equals(roadType) ) {
				currSpeed = 90;
			}
			else if (roadTypeName.TERTIARY.toString().equals(roadType) || roadTypeName.TERTIARY_LINK.toString().equals(roadType) ) {
				currSpeed = 80;
			}
			else if (roadTypeName.UNCLASSIFIED.toString().equals(roadType) ) {
				currSpeed = 70;
			}
			else if (roadTypeName.RESIDENTIAL.toString().equals(roadType)  ) {
				currSpeed = 50;
			}
		}
		else {
			currSpeed = 50;
		}
		return currSpeed;
	}
	
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(double speed) {
		this.maxSpeed = speed;
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
