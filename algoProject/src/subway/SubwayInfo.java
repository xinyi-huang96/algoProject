package subway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.ElementScanner6;

import com.csvreader.CsvReader;

public class SubwayInfo {
	
	public static List<SubwayLine> lines = new ArrayList<>();
	public static List<Station> stations = new ArrayList<>();
	public static List<String[]> stationsInfo = new ArrayList<>();
	public static int stationNumbers;
	
	
	public void subwayMap() {
		 setAdjBFS();
	}
	
	public void setAdjBFS() {
		setAdjacentStation();
		for(SubwayLine sl : lines){
            for(int i = 0; i < sl.stations.size(); i++) {
            	String subwayMap = "stationMap.txt";
            	try {
            		BufferedReader bufferedReader = new BufferedReader(new FileReader(subwayMap));
        			String line;
        			while ((line = bufferedReader.readLine()) != null) {
        				String[] splitSubway = line.split(": ", 2);
        				if(sl.stations.get(i).getLine().equals(splitSubway[0])) {
        					String[] splitStation = splitSubway[1].split(", ");
        					for(int j = 0; j < splitStation.length; j++) {
        						if(j == 0) {
        							if(splitStation[j].contains(sl.stations.get(i).getName())) {
        								for(int k = 0; k < sl.stations.size(); k++) {
        									if(splitStation[j+1].contains(sl.stations.get(k).getName())) {
        										sl.stations.get(i).adjStationBFS.add(sl.stations.get(k));
        									}
        								}
        							}
        						} else if(j == (splitStation.length - 1)) {
    								if(splitStation[j].contains(sl.stations.get(i).getName())) {
    									for(int k = 0; k < sl.stations.size(); k++) {
        									if(splitStation[j-1].contains(sl.stations.get(k).getName())) {
        										sl.stations.get(i).adjStationBFS.add(sl.stations.get(k));
        									}
        								}
    								}
        						} else if(j > 0 && j < (splitStation.length - 1)) {
        							if(splitStation[j].contains(sl.stations.get(i).getName()) && !splitStation[j].contains(";") && !splitStation[j].contains("?")) {
    									for(int k = 0; k < sl.stations.size(); k++) {
        									if(splitStation[j-1].contains(sl.stations.get(k).getName())) {
        										sl.stations.get(i).adjStationBFS.add(sl.stations.get(k));
        									}
        									if(splitStation[j+1].contains(sl.stations.get(k).getName()) && i < k) {
        										sl.stations.get(i).adjStationBFS.add(sl.stations.get(k));
        									}
        								}
    								} else if(splitStation[j].contains(sl.stations.get(i).getName()) && splitStation[j].contains("!")) {
    									for(int k = 0; k < sl.stations.size(); k++) {
        									if(splitStation[j+1].contains(sl.stations.get(k).getName())) {
        										sl.stations.get(i).adjStationBFS.add(sl.stations.get(k));
        									}
        								}	
    								} else if(splitStation[j].contains(sl.stations.get(i).getName()) && splitStation[j].contains(";")) {
    									for(int k = 0; k < sl.stations.size(); k++) {
        									if(splitStation[j-1].contains(sl.stations.get(k).getName())) {
        										sl.stations.get(i).adjStationBFS.add(sl.stations.get(k));
        									}
        								}	
    								} else if(splitStation[j].contains(sl.stations.get(i).getName()) && splitStation[j].contains(";") && j == (splitStation.length - 2)) {
    									for(int k = 0; k < sl.stations.size(); k++) {
        									if(splitStation[j+1].contains(sl.stations.get(k).getName())) {
        										sl.stations.get(i).adjStationBFS.add(sl.stations.get(k));
        									}
        								}	
    									
    								}
        						}
        					}
        				}
    				}	
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
		}
	}
	
	public void setAdjacentStation() {
		setLocation();
		for(SubwayLine sl : lines){
            for(int i = 0; i < sl.stations.size(); i++) {
            	if(sl.stations.get(i).getFeature() == 1 || sl.stations.get(i).getFeature() == 3) {
            		String subwayMap = "stationMap.txt";
            		try {
            			BufferedReader bufferedReader = new BufferedReader(new FileReader(subwayMap));
            			String line;
            			while ((line = bufferedReader.readLine()) != null) {
            				String[] splitSubway = line.split(": ", 2);
            				if(sl.stations.get(i).getLine().equals(splitSubway[0])) {
            					String[] splitStation = splitSubway[1].split(", ");
            					for(int j = 0; j < splitStation.length; j++) {
            						if(sl.stations.get(i).getFeature() == 1) {
            							if(!splitStation[j].contains("?") && splitStation[j].contains(sl.stations.get(i).getName())) {
            								for(int k = 0; k < sl.stations.size(); k++) {
            									if(splitStation[j+1].contains(sl.stations.get(k).getName())) {
            										sl.stations.get(i).adjacentStation.add(sl.stations.get(k));
            									}
            								}
            							}
        							}else if(sl.stations.get(i).getFeature() == 3){
        								if(splitStation[j].contains("!") && splitStation[j].contains(sl.stations.get(i).getName())) {
        									for(int k = 0; k < sl.stations.size(); k++) {
            									if(splitStation[j-1].contains(sl.stations.get(k).getName())) {
            										sl.stations.get(i).adjacentStation.add(sl.stations.get(k));
            									}
            								}
        								}
        							}
            					}
            				}
        				}
            		} catch (Exception e) {
            			e.printStackTrace();
            		}
            	}
            	if(sl.stations.get(i).getFeature() == 0 || sl.stations.get(i).getFeature() == 3) {
            		sl.stations.get(i).adjacentStation.add(sl.stations.get(i-1));
            		sl.stations.get(i).adjacentStation.add(sl.stations.get(i+1));
            	}
            	if(sl.stations.get(i).getFeature() == 2) {	
            		String subwayMap = "stationMap.txt";
            		try {
            			BufferedReader bufferedReader = new BufferedReader(new FileReader(subwayMap));
            			String line;
            			while ((line = bufferedReader.readLine()) != null) {
            				String[] splitSubway = line.split(": ", 2);
            				if(sl.stations.get(i).getLine().equals(splitSubway[0])) {
            					String[] splitStation = splitSubway[1].split(", ");
            					for(int j = 0; j < splitStation.length; j++) {
            						if(j == (splitStation.length - 1) && splitStation[j].contains(sl.stations.get(i).getName())) {
            							sl.stations.get(i).adjacentStation.add(sl.stations.get(i-1));
            						} else if(!splitStation[j].contains("?") && splitStation[j].contains(sl.stations.get(i).getName())){
        								if(splitStation[j].contains(sl.stations.get(i).getName())) {
        									for(int k = 0; k < sl.stations.size(); k++) {
            									if(splitStation[j-1].contains(sl.stations.get(k).getName())) {
            										sl.stations.get(i).adjacentStation.add(sl.stations.get(k));
            									}
            								}
        								}
        							}
            					}
            				}
        				}
            		} catch (Exception e) {
            			e.printStackTrace();
            		}
            	}
            }
        }
	}
	
	public void setLocation() {
		readStationMap();
		readLocFile();
		for(SubwayLine sl : lines){
            for(int i = 0; i < sl.stations.size(); i++) {
            	for (String[] si : stationsInfo) {
                	if (sl.stations.get(i).name.equals(si[0]) && si[1].contains(sl.stations.get(i).line)) {
                		sl.stations.get(i).setLat(Double.parseDouble(si[2]));
                		sl.stations.get(i).setLon(Double.parseDouble(si[3]));
                		sl.stations.get(i).setMapId(Integer.parseInt(si[4]));

                	}
                }
            }
        }
	}
	
	public void readStationMap() {
		String subwayMap = "stationMap.txt";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(subwayMap));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] splitSubway = line.split(": ", 2);
				SubwayLine subwayLine = new SubwayLine();
				subwayLine.name = splitSubway[0];
				String[] splitStation = splitSubway[1].split(", ");				
				for (String stations: splitStation) {
					Boolean isTransferStation = stations.contains("#");
					Station station = new Station();
					stationNumbers++;
					if (!stations.contains("?") && !stations.contains("!")) {
						if (isTransferStation) {
							if (stations.contains("*")) {
								String[] splitStationInfo = stations.split("\\*", 2);
								stations = splitStationInfo[0];
								station.setFeature(1);
							} else if (stations.contains(";")) {
								String[] splitStationInfo = stations.split(";", 2);
								stations = splitStationInfo[0];
								station.setFeature(2);
							} else if (stations.contains("+")) {
								String[] splitStationInfo = stations.split("\\+", 2);
								stations = splitStationInfo[0];
								station.setFeature(3);
							}
							station.line = splitSubway[0];
							station.isTransferStation = true;
							String[] splitTransfer = stations.split("#");
							station.name = splitTransfer[0];
							for (int i = 1; i < splitTransfer.length; i++) {
								if (splitTransfer[i].equals("Red"))
									station.transferLines.add("Red Line");
								else if (splitTransfer[i].equals("Pur"))
									station.transferLines.add("Purple Line");
								else if (splitTransfer[i].equals("Yel"))
									station.transferLines.add("Yellow Line");
								else if (splitTransfer[i].equals("Blu"))
									station.transferLines.add("Blue Line");
								else if (splitTransfer[i].equals("Pin"))
									station.transferLines.add("Pink Line");
								else if (splitTransfer[i].equals("Gre"))
									station.transferLines.add("Green Line");
								else if (splitTransfer[i].equals("Ora"))
									station.transferLines.add("Orange Line");
								else if (splitTransfer[i].equals("Bro"))
									station.transferLines.add("Brown Line");	
							}
						}
						else {
							if (stations.contains("*")) {
								String[] splitName = stations.split("\\*", 2);
								station.name = splitName[0];
								station.setFeature(1);
							} else if (stations.contains(";")) {
								String[] splitName = stations.split(";", 2);
								station.name = splitName[0];
								station.setFeature(2);
							} else if (stations.contains("+")) {
								String[] splitName = stations.split("\\+", 2);
								station.name = splitName[0];
								station.setFeature(3);
							} else {
								station.name = stations;
							}
							station.line = splitSubway[0];
							station.isTransferStation = false;
						}
						station.setStationId(stationNumbers);
						subwayLine.stations.add(station);
						SubwayInfo.stations.add(station);
					}
				}
				lines.add(subwayLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readLocFile() {
		try {
			String filePath = "CTA_-_System_Information_-_List_of__L__Stops_-_Map.csv";
            ArrayList<String[]> csvList = new ArrayList<String[]>(); 
            CsvReader reader = new CsvReader(filePath,',',Charset.forName("GBK"));
            while(reader.readRecord()){
                csvList.add(reader.getValues());
            }
            reader.close();
            for (int row = 1; row < csvList.size(); row++) {
                String[]  stationInfo = new String[5];
                stationInfo[0] = csvList.get(row)[2];
                stationInfo[1] = "";
                if(csvList.get(row)[5].equals("TRUE"))
                	stationInfo[1] += "Red Line ";
                if(csvList.get(row)[6].equals("TRUE"))
                	stationInfo[1] += "Blue Line ";
                if(csvList.get(row)[7].equals("TRUE"))
                	stationInfo[1] += "Green Line ";
                if(csvList.get(row)[8].equals("TRUE"))
                	stationInfo[1] += "Brown Line ";
                if(csvList.get(row)[9].equals("TRUE") || csvList.get(row)[10].equals("TRUE"))
                	stationInfo[1] += "Purple Line ";
                if(csvList.get(row)[11].equals("TRUE"))
                	stationInfo[1] += "Yellow Line ";
                if(csvList.get(row)[12].equals("TRUE"))
                	stationInfo[1] += "Pink Line ";
                if(csvList.get(row)[13].equals("TRUE"))
                	stationInfo[1] += "Orange Line ";
                String[] location = deleteChar(deleteChar(csvList.get(row)[14], '('), ')').split(", ");
                stationInfo[2] = location[0];
                stationInfo[3] = location[1];
                stationInfo[4] = csvList.get(row)[3];
                if (!stationInfo[1].equals(""))
                	stationsInfo.add(stationInfo);
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	//detele "(" and ")" in the string
	public String deleteChar(String source, char elemData) {
        String tmpString = "";
        tmpString += elemData;
        StringBuffer stringBuffer = new StringBuffer(source);
        int iFlag = -1;
        do {
            iFlag = stringBuffer.indexOf(tmpString);
            if (iFlag != -1) {
                stringBuffer.deleteCharAt(iFlag);
            }
        } while (iFlag != -1);
        return stringBuffer.toString();
    }
	
}
