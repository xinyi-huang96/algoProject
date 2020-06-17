package main;

import subway.*;
import distance.*;
import graph.BFS;
import graph.DijkstraSP;
import graph.UWGraph;
import graph.WDgraph;

public class Main {

	public static void main(String[] args) {

		SubwayInfo sl = new SubwayInfo();
		sl.subwayMap();

		/* read data */
		for (SubwayLine sli : sl.lines) {
			System.out.println(sli.name + " ");
			for(Station st : sli.stations) {
				System.out.print(st.mapId + " " + st.getName() + " " + st.getLat() + " " + st.getLon() + " ");
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


		/*Unweighted graph*/
		//UWGraph ug = new UWGraph(sl);
		//ug.print();
		/* BFS */
		//System.out.println(BFS.bfs(ug, "Western"));
		//BFS.printSP(ug, "Western");
		
		/* Weighted graph */
		//WDgraph g = new WDgraph(sl);
		//g.print();
		/* Dijkstra algorithm */
		//System.out.println(DijkstraSP.DijkstraSP(g, "Western"));
		//DijkstraSP.printSP(g, "Western");
		
	}
}
