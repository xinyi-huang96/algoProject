package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import subway.Station;

public class BFS {
	private static  boolean[] marked;
    private static  int[] previous;
    private static int[] distance;
    private static int sourceNode;
    
    public static List<Integer> bfs(UWGragh G, String rootName) {
    	int root = 0;
    	for(Station st : G.allStation()) {
    		if(rootName.equals(st.name)) {
    			root = st.stationId;
    			break;
    		}
    	}
        sourceNode = root;
        marked = new boolean[G.order()];
        previous = new int[G.order()];
        distance = new int[G.order()];
        for (int i = 0; i < G.order(); i++){
            previous[i] = -1;
            distance[i] = Integer.MAX_VALUE;
        }
        List<Integer> visitOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> distanceQueue = new LinkedList<>();
        queue.add(root);
        marked[root] = true;
        distanceQueue.add(0);
        distance[root] = 0;
        while(!queue.isEmpty())  {
            int index = queue.remove();
            int nodeDistance = distanceQueue.remove();
            visitOrder.add(index);
            List<Station> ouN = G.outNeighbors(index);
            for(Station s : ouN) {
            	int v = s.stationId;
            	if(!marked[v]) {
            		queue.add(v);
            		marked[v] = true;
            		previous[v] = index;
            		distanceQueue.add(nodeDistance + 1);
                    distance[v] = nodeDistance + 1;
            	}
            }
        }
        return visitOrder;
    }
    
    public static int cc(UWGragh G, String rootName) {
        int count = 0;
        marked = new boolean[G.order()];
        for (int i = 0; i < G.order(); i++) {
            marked[i] = false;
        }
        bfs(G, rootName);
        count++;
        for (int j = 0; j < G.order(); j++) {
            if (!marked[j]) {
                bfs(G, G.allStation().get(j).name);
                count++;
            }
        }
        return count;
    }
    
    public static boolean isConnected(UWGragh G, String rootName){
        int count = cc(G, rootName);
        if(count != 0)
        	return true;
        else
        	return false;
    }

    public static boolean hasPathTo(int v){
        return marked[v];
    }

    public static int disTo(int v){
        return distance[v];
    }
    
    public  static int pre(int v){
        return previous[v];
    }

    public static void printSP(UWGragh G, String rootName){
        ArrayList<Integer> shortestPath = (ArrayList<Integer>) bfs(G, rootName);
        for(int i : shortestPath) {
        	for(Station st : G.allStation()) {
        		if(i == st.stationId) {
        			System.out.println(i + " : " + st.name);
        			break;
        		}
        	}
        }
    }

}
