package graph;

import distance.Calculate;
import subway.Station;

public class DirectedEdge {
	private final Station s;
	private final Station t;
	private final double weight;
	
	public DirectedEdge(Station s, Station t) {
		this.s = s;
		this.t = t;
		weight = Calculate.getDistance(s, t);
	}

	public Station from() {
		return s;
	}

	public Station to() {
		return t;
	}

	public double Weight() {
		return weight;
	}
}
