/*
 Alfonso Pruneda, MW 4:30-5:50 PM
 Lab 3 - Array of Objects
 This is The Runner Class to initialze the Package class in order to provide the information
for the list of packages provided.
*/



import java.util.*;
import java.io.*;

public class Runner{
	public static void main(String[] args) throws FileNotFoundException{

		ArrayList<ArrayList<Double>> list = fileReader();
		Package[] packages = new Package[list.size()];
		Double[] p = new Double[3];
		for(int i = 0; i < list.size(); i++){
			ArrayList<Double> row = list.get(i);
			p = row.toArray(new Double[row.size()]); //Used to convert from List Object to Double Object
			double[] changer = new double[p.length];
			for(int j = 0; j < p.length;j++){
				changer[j] = p[j].doubleValue(); // converting Object to primitive value
			}
			packages[i] = new Package(changer);
		}
		largestPackage(packages); // initializing all the methods
		System.out.println();
		numberOfCubicAndNonCubic(packages);
		System.out.println();
		indicesAndDimensions(packages);
		System.out.println();
		volumeOfCubicPackages(packages);
		System.out.println();
	}
	public static ArrayList<ArrayList<Double>> fileReader() throws FileNotFoundException{ // Used to import file and convert that to usable variables
		File inFile = new File("input.txt"); //importing the file
		Scanner input = new Scanner(inFile); //reading the file
		int counter = 0;
		ArrayList<ArrayList<Double>> list = new ArrayList<ArrayList<Double>>();
		while(input.hasNextLine()){
			list.add(new ArrayList<Double>());
			String temp = input.nextLine();
			String[] temp2 = temp.split(" ");
			if(temp2.length != 3){
				System.out.printf("On line %d there is either more or less than three measurements\n", counter+1);
				System.exit(0);
			}
			double[] convert = new double[temp2.length];
			for(int i = 0; i < convert.length; i++){
				convert[i] = Double.parseDouble(temp2[i]); //convert String to Double
				list.get(counter).add(convert[i]);
			}
			counter++;
		}
		return list;
	}
	public static void largestPackage(Package[] p){ //returns the package with the highest volume
		int index = 0;
		for(int i = 1; i < p.length; i++){
			if(p[index].getVolume() < p[i].getVolume()){
				index = i;
			}
		}
		double l = p[index].getLength();
		double w = p[index].getWidth();
		double h = p[index].getHeight();
		System.out.println("THE LARGEST PACKAGE -");
		System.out.printf(" index:  %d\n length: %,.2f\n width:  %,.2f\n height: %,.2f\n Volume: %,.2f\n", index, l, w, h, p[index].getVolume());
	}
	public static void numberOfCubicAndNonCubic(Package[] p){ // returns how many packages are cubes and how many are not
		int cubic = 0;
		int noncubic = 0;
		for(int i = 0; i < p.length; i++){
			if(p[i].isCube() == true){
				cubic++;
			}
			else
				noncubic++;
		}
		System.out.println("FINDING HOW MANY CUBIC PACKAGES THERE ARE - ");
		System.out.printf(" Number of Cubes: %d\n Number of Non-Cubes: %d\n", cubic, noncubic);
	}
	public static void indicesAndDimensions(Package[] p){ // returns the The index and dimensions of all the cubic packages
		System.out.println("INDEX AND DIMENSIONS OF PACKAGES THAT ARE CUBES -");
		for(int i = 0; i < p.length; i++){
			if(p[i].isCube() == true){
				double l = p[i].getLength();
				double w = p[i].getWidth();
				double h = p[i].getHeight();
				System.out.printf(" Package %d:\n  index:  %d\n  length: %,.2f\n  width:  %,.2f\n  height: %,.2f\n", i+1, i, l, w, h);
			}
		}
	}
	public static void volumeOfCubicPackages(Package[] p){ // returns the average volume of all cubic packages
		System.out.println("AVERAGE VOLUME BETWEEN CUBIC PACKAGES -");
		int cubic = 0;
		double average = 0;
		for(int i = 0; i < p.length; i++){
			if(p[i].isCube() == true){
				average += p[i].getVolume();
				cubic++;
			}
		}
		average = average / cubic;
		System.out.printf(" The overall Volume average between all cubic packages: %,.2f\n",average);
	}
}