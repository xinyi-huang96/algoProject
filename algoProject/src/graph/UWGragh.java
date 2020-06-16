package graph;

import java.util.ArrayList;
import java.util.List;

import subway.Station;
import subway.SubwayInfo;

public class UWGragh {
	private int n;
	private int m;
	private List<List<Station>> adj;

	/**
	 * Initializes an empty graph
	 */
	public UWGragh(SubwayInfo info) {
		this.n= info.stationNumbers;
		this.m = 0;
		adj = new ArrayList<List<Station>>();
		int i = 0;
		for(Station st : info.stations) {
			int num = i;
			if(st.isTransferStation) {	//if the station is transfer station, add the new adjacent Station
				for(int j = 0; j < i; j++) {
					if(!adj.get(j).isEmpty() && adj.get(j).get(0).mapId == st.mapId) {
						num = j;
						break;
					}
				}
			}
			st.setStationId(num);
			if(num == i) {
				List<Station> list = new ArrayList<Station>();
				adj.add(list);
				list.add(st);
				i++;	
			}
			for(Station adjSt : st.adjStationBFS) {
				addEdge(num, adjSt);
			}
		}
		n = i;
	}
	
	public int order() {
		return n;
	}
	
	public int size() {
		return m;
	}
	
	public void addEdge(int num, Station st) {
		adj.get(num).add(st);
		
	}
	
	public List<Station> allStation() {
		List<Station> all = new ArrayList<Station>();
		for(int i = 0; i < adj.size(); i++) {
			Station st = adj.get(i).get(0);
			all.add(st);
		}
		return all;
	}
	
	public List<Station> outNeighbors(int v) {
		List<Station> nodes = new ArrayList<Station>();
		for(Station st : adj.get(v)) {
			nodes.add(st);
		}
		return nodes;
	}
	
	public void print() {
        for (int i = 0; i < adj.size(); i++) {
        	List<Station> list = adj.get(i);
            for (int j = 1; j < list.size(); j++) {
                System.out.println(list.get(0).name + " , " + list.get(j).name);
            }
            System.out.println();
        }
    }
}
