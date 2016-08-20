package roadgraph;

import java.util.Comparator;

public class DistComparator implements Comparator<MapNode> {
	
	public int compare(MapNode node1, MapNode node2) {
		if (node1.getPriority() < node2.getPriority() ) return -1;
		if (node1.getPriority() > node2.getPriority() ) return 1;
		return 0;
	  }

}
