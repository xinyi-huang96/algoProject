package distance;

import subway.Station;

public class Calculate {
	
	private static final  double EARTH_RADIUS = 6378137;
	
	private static double rad(double d){
	    return d * Math.PI / 180.0;
	}
	
	public static Double getDistance(Station s1, Station s2) {
		Double distance = 0.0;
		double lat1 = s1.getLat();
		double lat2 = s2.getLat();
		double lon1 = s1.getLon();
		double lon2 = s2.getLon();
		double radLat1 = rad(lat1);
	    double radLat2 = rad(lat2);
	    double a = radLat1 - radLat2;
	    double b = rad(lon1) - rad(lon2);
	    double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))); 
	    distance = s * EARTH_RADIUS; 
		
		return distance;
	}
	
}
