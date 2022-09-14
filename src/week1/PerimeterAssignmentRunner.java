package week1;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int num = 0;
        for (Point p:s.getPoints()){
            num += 1;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s) / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Check if the current distance bigger than the largestSide, if so update the largestSide
            if(currDist > largestSide){
                largestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        int largestX = 0;
        for(Point p: s.getPoints()){
            if(p.getX() > largestX){
                System.out.println(p.getX());
                largestX = p.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPer = 0.0;
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPer){
                largestPer = getPerimeter(s);
            }
        }
        return largestPer;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double largestPer = 0.0;
        DirectoryResource dir = new DirectoryResource();
        for (File f : dir.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > largestPer){
                largestPer = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("num of points = " + numPoints);
        double avgLen = getAverageLength(s);
        System.out.println("average length = " + avgLen);
        double largestX = getLargestX(s);
        double largestSide = getLargestSide(s);
        System.out.println("largest X = " + largestX);
        System.out.println("largest side = " + largestSide);
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public void testPerimeter (String filename) {
        FileResource fr = new FileResource(filename);
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("num of points = " + numPoints);
        double avgLen = getAverageLength(s);
        System.out.println("average length = " + avgLen);
        double largestX = getLargestX(s);
        double largestSide = getLargestSide(s);
        System.out.println("largest X = " + largestX);
        System.out.println("largest side = " + largestSide);
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        String[] list = {"datatest1.txt", "datatest2.txt", "datatest4.txt",  "datatest5.txt",  "datatest6.txt"};
        for (String fs:list) {
            System.out.println(fs);
            pr.testPerimeter("week1/"+fs);
        }
    }
}
