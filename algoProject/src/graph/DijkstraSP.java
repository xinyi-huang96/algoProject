package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import subway.Station;

public class DijkstraSP {
    private static  boolean[] marked;
    private static  int[] previous;
    private static double[] distance;
    private static int sourceNode;

    public static List<Integer> DijkstraSP(WDgraph G, String name){
    	int s = 0;
    	for(Station st : G.getStation()) {
    		if(name.equals(st.name)) {
    			s = st.stationId;
    			break;
    		}
    	}
        sourceNode = s;
        marked = new boolean[G.order()];
        previous = new int[G.order()];
        distance = new double[G.order()];
        //store unvisited nodes
        HashSet<Integer> openedNodes = new HashSet<>();
        for (int i = 0; i < G.order(); i++) {
            previous[i] = -1;
            distance[i] = Double.MAX_VALUE;
            openedNodes.add(i);
        }
        ArrayList<Integer> visitOrder = new ArrayList<>();
        distance[s] = 0;
        marked[s] = true;
        while (!openedNodes.isEmpty()) {
            double smallestDistance = Double.MAX_VALUE;
            int smallestNode = -1;
            for (Integer thisNode : openedNodes) {
                if (distance[thisNode] < smallestDistance) {
                    smallestDistance = distance[thisNode];
                    smallestNode = thisNode;
                }
            }
            openedNodes.remove(smallestNode);
            visitOrder.add(smallestNode);
            // If remained nodes are not available, it is not a connected graph,terminate the progress.
            if (smallestNode == -1) {
                break;
            }
            // Check all neighbours and update distances           
            for (DirectedEdge edge : G.getAdj()[smallestNode]) {
            	int childNode = edge.to().stationId;
                double alt = distance[smallestNode] + edge.Weight();
                if (alt < distance[childNode]) {
                    marked[childNode] = true;
                    previous[childNode] = smallestNode;
                    distance[childNode] = alt;
                }
            }
        }
        return visitOrder;
    }
    
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    public double distTo(int v) {
        return distance[v];
    }
    
    public static void printSP(WDgraph G, String name) {
        ArrayList<Integer> shortestPath = (ArrayList<Integer>) DijkstraSP(G, name);
        for(int i : shortestPath) {
        	for(Station st : G.getStation()) {
        		if(i == st.stationId) {
        			System.out.println(i + " : " + st.name);
        			break;
        		}
        	}
        }
    }
}
