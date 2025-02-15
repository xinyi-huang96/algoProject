package graph;

import java.util.ArrayList;
import java.util.List;

import subway.Station;
import subway.SubwayInfo;

public class WDgraph {
	
	private int n;
	private int m;
	private List<DirectedEdge> adj[];

	/**
	 * Initializes an empty graph
	 */
	public WDgraph(SubwayInfo info) {
		this.n= info.stationNumbers;
		this.m = 0;
		adj = new ArrayList[n];
		int i = 0;
		for(Station st : info.stations) {
			adj[i] = new ArrayList<DirectedEdge>();	
			int num = i;
			if(st.isTransferStation) {	//if the station is transfer station, add the new adjacent Station
				for(int j = 0; j < i; j++) {
					if(!adj[j].isEmpty() && adj[j].get(0).from().mapId == st.mapId) {
						num = j;
						break;
					}
				}
			}
			st.setStationId(num);
			for(Station adjSt : st.adjacentStation) {
				addEdge(num, st, adjSt);
			}
			if(num == i)
				i++;
		}
		n = i;
	}
	
	public int order() {
		return n;
	}
	
	public int size() {
		return m;
	}
		
	public List<DirectedEdge>[] getAdj() {
		return adj;
	}

	public List<Station> getStation() {
		List<Station> allStation = new ArrayList<Station>();
		for(int i = 0; i < n; i++) {
			Station st = adj[i].get(0).from();
			allStation.add(st);
		}
		return allStation;
	}
	
	public void addEdge(int num, Station s, Station t) {
		adj[num].add(new DirectedEdge(s, t));
	}
	
	public void print() {
        for (int i = 0; i < n; i++) {
            System.out.print(adj[i].get(0).from().stationId + " : ");
            for (DirectedEdge e : adj[i]) {
                System.out.print("(" + e.from().name + ", " + e.to().name + ", " + e.to().stationId + ", " + e.Weight() + "), ");
            }
            System.out.println();
        }
    }
}
