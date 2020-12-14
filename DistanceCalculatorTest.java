/**
 * Tests DistanceCalculator program
 * Based on Exercise 5. on page 194 of Building Java Programs, 4th edition
 * @author Suzanne Balik
 */
public class DistanceCalculatorTest {
    /**
     * Tests DistanceCalculator program by calculating distance from Nashville, TN, Los Angeles, CA
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        double distance = DistanceCalculator.calculatePlanarDistance(36.12, -86.67, 33.94, -118.4);
        System.out.println("\nExpected: 1801.612857337826  \nActual:   " + distance);
        distance = DistanceCalculator.calculateSphericalDistance(36.12, -86.67, 33.94, -118.4);
        System.out.println("\nExpected: 1793.6640322077499 \nActual:   " + distance);
    }
}
