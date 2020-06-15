package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import subway.Station;

public class WDgraph {
	
	private int n;
	private int m;
	private List<Station> adj[];

	/**
	 * Initializes an empty graph
	 */
	public WDgraph(int nbVertices) {
		this.n= nbVertices;
		this.m = 0;
		adj = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Station>();		
		}
	}
	
	public int order() {
		return n;
	}
	
	public int size() {
		return m;
	}
		
	public List<Station>[] getAdj() {
		return adj;
	}

	public void addEdge(int s, int t, double weight) {
		//adj[s].add(new Station(s, t, weight));
	}
	
	public List<Integer> vertices() {
		List<Integer> nodes = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			nodes.add(adj[i].get(0).from());
		}
		return nodes;
	}
	
	public List<Integer> inNeighbors(int v) {
		List<Integer> nodes = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			for(DirectedEdge e : adj[i]) {
				if(e.to() == v) {
					nodes.add(e.from());
					break;
				}
			}
		}
		return nodes;
	}
	
	public List<Integer> outNeighbors(int v) {
		List<Integer> nodes = new ArrayList<Integer>();
		for(DirectedEdge e : adj[v]) {
			nodes.add(e.to());
		}
		return nodes;
	}
	
	public void print() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (DirectedEdge e : adj[i]) {
                System.out.print("(" + e.from() + ", " + e.to() + ", " + e.Weight() + "), ");
            }
            System.out.println();
        }
    }
}
