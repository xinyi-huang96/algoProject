package subway;

import java.util.ArrayList;
import java.util.List;

public class Station {
	public String name; 
	public String line;
	public double lat;
	public double lon;	
	public Boolean isTransferStation;
    public List<String> transferLines = new ArrayList<>();
    public List<Station> adjacentStation = new ArrayList<>();
    public List<Station> adjStationBFS = new ArrayList<>();
    public int feature = 0;	//1-only next; 2-only previous
    public int mapId;
    public int stationId;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public Boolean getIsTransferStation() {
		return isTransferStation;
	}
	public void setIsTransferStation(Boolean isTransferStation) {
		this.isTransferStation = isTransferStation;
	}
	public List<String> getTransferLines() {
		return transferLines;
	}
	public void setTransferLines(List<String> lines) {
		this.transferLines = lines;
	}
	public List<Station> getAdjacentStation() {
		return adjacentStation;
	}
	public void setAdjacentStation(List<Station> adjacentStation) {
		this.adjacentStation = adjacentStation;
	}   
	
	public Station() {
		// TODO Auto-generated constructor stub
	}
	
	public Station(String name) {
		this.name = name;
	}
	public int getFeature() {
		return feature;
	}
	public void setFeature(int feature) {
		this.feature = feature;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public List<Station> getAdjStationBFS() {
		return adjStationBFS;
	}
	public void setAdjStationBFS(List<Station> adjStationBFS) {
		this.adjStationBFS = adjStationBFS;
	}

}
