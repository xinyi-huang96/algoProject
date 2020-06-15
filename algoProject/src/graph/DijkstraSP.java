package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class DijkstraSP {
    private static  boolean[] marked;
    private static  int[] previous;
    private static double[] distance;
    private static int sourceNode;

    private static boolean verifyNonNegative(WDgraph G){
        for (List<DirectedEdge> edges: G.getAdj()){
            for (DirectedEdge e: edges){
                if (e.Weight() <= 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static List<Integer> DijkstraSP(WDgraph G, int s){
        if (!verifyNonNegative(G))
            return null;
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
        //visitOrder.add(s);
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
                int childNode = edge.to();
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
    
    public static void printSP(int v) {
        ArrayList<Integer> shortestPath = new ArrayList<Integer>();
        int thisNode = v;
        while (thisNode > -1) {
            shortestPath.add(thisNode);
            thisNode = previous[thisNode];
            if (thisNode == sourceNode) {
                shortestPath.add(sourceNode);
                break;
            }
        }
        Collections.reverse(shortestPath);
        System.out.println(shortestPath);
    }
}
