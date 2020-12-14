/**
 * Program calculates both the planar and spherical distances from Raleigh, NC, to other points
 * in North America given their latitude and longitude
 *
 * @author Jonathan Nguyen 
 *
 */
public class DistanceCalculator{
    /**
    * {@value EARTH_RADIUS} miles 
    */
    public static final double EARTH_RADIUS = 3959;
    /**
    * {@value RALEIGH_LATITUDE} degrees
    */
    public static final double RALEIGH_LATITUDE = 35.78; 
    /**
    * {@value RALEIGH_LONGITUDE} degrees
    */
    public static final double RALEIGH_LONGITUDE = -78.64;
    /**
    * {@value MIN_LATITUDE} degrees
    */
    public static final int MIN_LATITUDE = 30;
    /**
    * {@value MAX_LATITUDE} degrees
    */
    public static final int MAX_LATITUDE = 50; 
    /**
    * {@value MIN_LONGITUDE} degrees 
    */
    public static final int MIN_LONGITUDE = -120; 
    /**
    * {@value MAX_LONGITUDE} degrees 
    */
    public static final int MAX_LONGITUDE = -70; 
    /**
    * {@value LATITUDE_INCREMENT} degrees 
    */
    public static final int LATITUDE_INCREMENT = 10; 
    /**
    * {@value LONGITUDE_INCREMENT} degrees 
    */
    public static final int LONGITUDE_INCREMENT = 5;
    
    /**
     * Outputs planar and spherical distances in miles
     * 
     * @param args command line arguments (not used) 
     */
    public static void main(String[] args){
        System.out.printf("%9sDistance (miles) from Raleigh (lat %5.2f long %5.2f)\n","",
            RALEIGH_LATITUDE,RALEIGH_LONGITUDE);
        System.out.print(" ");
        for(int latitude = MIN_LATITUDE; latitude <= MAX_LATITUDE; 
            latitude += LATITUDE_INCREMENT) {
            System.out.printf("%14slat %2d","",latitude);
        }
        String e = ("\nlong");
        String f = ("Planar Spherical");
        System.out.printf("%s %21s %19s %19s\n",e,f,f,f);
        System.out.print("----      ------ ---------");
        System.out.println("    ------ ---------    ------ ---------");

        //outer for loop creates how many rows and prints out longitudes 
        for(int longitude = MIN_LONGITUDE; longitude <= MAX_LONGITUDE; 
            longitude += LONGITUDE_INCREMENT) {
            System.out.printf("%4d %2s",longitude," ");
            /*inner for loop prints out the planar and spherical values 
            with the correct spacing so it 
            lines up correctly */
            for(int latitude = MIN_LATITUDE; latitude <= MAX_LATITUDE; 
                latitude += LATITUDE_INCREMENT) {
                double planar = calculatePlanarDistance(RALEIGH_LATITUDE,RALEIGH_LONGITUDE,
                    latitude,longitude);
                double spherical = calculateSphericalDistance(RALEIGH_LATITUDE,RALEIGH_LONGITUDE,
                    latitude,longitude);
                System.out.printf("%9.0f",planar);
                System.out.printf("%10.0f ",spherical);
            }
            System.out.println();
        }
    }

    /**
     * Calculates the distance between two points on the earth's surface assuming
     * the spherical earth has been projected to a plane
     * 
     * @param latitude1 latitude of first point in degrees
     * @param longitude1 longitude of first point in degrees
     * @param latitude2 latitude of second point in degrees
     * @param longitude2 longitude of second point in degrees
     * @return planar distance in miles between the two points
     */
    public static double calculatePlanarDistance(double latitude1, double longitude1, 
                                                 double latitude2, double longitude2) {
        //converting degrees to radians 
        double lat1Radian = Math.toRadians(latitude1);
        double long1Radian = Math.toRadians(longitude1);
        double lat2Radian = Math.toRadians(latitude2);
        double long2Radian = Math.toRadians(longitude2);

        //difference between the two latitudes
        double latDifference = (lat1Radian - lat2Radian);
        //difference between the two longitudes
        double longDifference = (long1Radian - long2Radian);
        //average of the two latitudes 
        double latAverage = ((lat1Radian + lat2Radian) / 2);
        //formula for planar distance
        double planarDistance = EARTH_RADIUS * (Math.sqrt(Math.pow(latDifference,2) +
            Math.pow((Math.cos(latAverage) * longDifference),2)));
        return planarDistance;
    }

    /**
     * Calculates the spherical distance between two points on the earth's surface 
     * 
     * @param latitude1 latitude of first point in degrees
     * @param longitude1 longitude of first point in degrees
     * @param latitude2 latitude of second point in degrees
     * @param longitude2 longitude of second point in degrees
     * @return spherical distance in miles between the two points
     */ 
    public static double calculateSphericalDistance(double latitude1, double longitude1, 
                                                    double latitude2, double longitude2) {
        //converting between degrees to radians
        double lat1Radian = Math.toRadians(latitude1);
        double long1Radian = Math.toRadians(longitude1);
        double lat2Radian = Math.toRadians(latitude2);
        double long2Radian = Math.toRadians(longitude2);

        //difference between the two longitudes
        double longDifference = (long1Radian - long2Radian);

        //formula for spherical distance
        double sphericalDistance = EARTH_RADIUS * (Math.acos((Math.sin(lat1Radian) * 
            Math.sin(lat2Radian) + (Math.cos(lat1Radian) * Math.cos(lat2Radian) * 
                Math.cos(longDifference)))));
        return sphericalDistance;
    }
    
}