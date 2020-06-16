package main;

import subway.*;
import distance.*;
import graph.DijkstraSP;
import graph.UWGragh;
import graph.WDgraph;

public class Main {

	public static void main(String[] args) {
	       
		//ReadFile newRead = new ReadFile();
		//newRead.readStops("gtfs/stops.txt");
		//newRead.readRoutes("gtfs/routes.txt");
		SubwayInfo sl = new SubwayInfo();
		sl.subwayMap();
/*
		for (SubwayLine sli : sl.lines) {
			System.out.println(sli.name + " ");
			for(Station st : sli.stations) {
				System.out.print(st.getName() + " " + st.getLat() + " " + st.getLon() + " " + st.getFeature() + " ");
				System.out.print("Transfer line: ");
				for(String s : st.transferLines) {
					System.out.print(" " + s + " ");
				}
				System.out.print("Connect stations: ");
				for(Station stw: st.adjacentStation) {
            		System.out.print(stw.getName() + " ");
            	}
			}
			System.out.println();
		}
		for (SubwayLine sli : SubwayInfo.lines) {
			for(int i = 1; i < sli.stations.size(); i++) {
				System.out.print(Calculate.getDistance(sli.stations.get(i-1), sli.stations.get(i)));
			}
		}
	
*/
		/*Unweighted graph*/
		UWGragh ug = new UWGragh(sl);
		ug.print();
		
		/* Weighted graph */
		//WDgraph g = new WDgraph(sl);
		//g.print();
		/* Dijkstra algorithm */
		//System.out.println(DijkstraSP.DijkstraSP(g, "Western"));
		//DijkstraSP.printSP(g, "Western");
		
	}
}
